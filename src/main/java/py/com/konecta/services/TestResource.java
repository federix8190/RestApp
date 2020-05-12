package py.com.konecta.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import py.com.konecta.interfaces.RestApiInterface;
import py.com.konecta.interfaces.dto.Usuario;
import py.com.konecta.model.DatosUsuarioDto;
import py.com.konecta.model.Deuda;
import py.com.konecta.model.Servicio;
import py.com.konecta.model.csj.AccesoSistema;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@EJB
	TestServices service;
	
	@Inject
    protected RestApiInterface restService;
	
	@GET
	public HashMap<String, String> test() {
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("status", "0");
		res.put("mensaje", "Hola");
		return res;
	}
	
	@GET
	@Path("/usuarios")
	public Response getUsuarios() {
		
		try {
			
			HashMap<String, Object> r = new HashMap<String, Object>();
			String token = restService.getToken();
			r.put("token", token);
			
			Usuario datos = new Usuario();
			datos.setNombre("Juan");
			datos.setApellido("Perez");
			datos.setCorreo("jperez@test.com");
			datos.setId_ciudad(11L);
			Response res = restService.createUser(datos);
			r.put("status_1", res.getStatus());
			res.close();
			
			return Response.ok(r).build();
	    	
		} catch (Exception e) {
			
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	
	@GET
	@Path("/departamentos")
	public List<AccesoSistema> getDepartamento() {
		
		return service.getDepartamento();
	}
	
	@GET
	@Path("/deudas")
	public ListaPaginada<Deuda> getDeudas() {
		
		List<Deuda> res = new ArrayList<Deuda>();
		res.add(new Deuda(1L, "2020-05-12", "12345678", "300,000"));
		res.add(new Deuda(1L, "2020-06-10", "12345678", "400,000"));
		res.add(new Deuda(1L, "2020-05-19", "12345678", "500,000"));
		res.add(new Deuda(1L, "2020-05-18", "12345678", "600,000"));
		res.add(new Deuda(1L, "2020-05-15", "12345678", "700,000"));
		
		ListaPaginada<Deuda> lista = new ListaPaginada<>(res);
		return lista;
	}
	
	@GET
	@Path("/servicios")
	public List<Servicio> getServicios() {
		
		List<Servicio> res = new ArrayList<Servicio>();
		res.add(new Servicio(1L, "ANDE", 10, "AC"));
		res.add(new Servicio(2L, "ESSAP", 12, "AC"));
		res.add(new Servicio(3L, "COPACO", 15, "AC"));
		res.add(new Servicio(4L, "VOX", 4, "AC"));
		res.add(new Servicio(5L, "TIGO", 5, "AC"));
		res.add(new Servicio(6L, "PERSONAL", 4, "AC"));
		res.add(new Servicio(7L, "CLARO", 3, "IN"));
		return res;
	}
    
    @POST
    @Path("/subir-archivo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirCV(MultipartFormDataInput input) {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        if (inputParts == null || inputParts.size() == 0) {
            return Response.ok().build();
        }
        
        // Recuperar nombre
        String name = null;
        List<InputPart> tmp = uploadForm.get("nombre");
        if (tmp != null && tmp.size() > 0) {
        	InputPart inputPart = tmp.get(0);
        	InputStream is = null;
        	try {
				is = inputPart.getBody(InputStream.class, null);
				byte[] bytes = toByteArray(is);
				name = new String(bytes);
			} catch (IOException e) {
				name = e.getMessage();
			} finally {
				if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException ex) {
	                	return Response.status(500).build();
	                }
	            }
			}
        }
      
        InputStream is = null;

        try {

            InputPart inputPart = inputParts.get(0);
            MultivaluedMap<String, String> header = inputPart.getHeaders();
            String path = System.getProperty("jboss.server.data.dir");
            String fileName = getFileName(header);
            String file = path + "/" + fileName;

            // Guardar archivo en disco
            is = inputPart.getBody(InputStream.class, null);
            byte[] bytes = toByteArray(is);
            writeFile(bytes, file);
            
        } catch (Exception e) {
        	
            System.err.println("Error al guardar archivo : " + e.getMessage());
            return Response.status(500).build();
            
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                	return Response.status(500).build();
                }
            }
        }

        HashMap<String, String> datos = new HashMap<String, String>();
        datos.put("Nombre", name);
        return Response.ok(datos).build();
    }
    
    private void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }
    
    public static byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}

		return os.toByteArray();
	}
    
    protected String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }
    
    @GET
    @Path("ofertas/historial")
    public Response historial() {
    	
    	List<HistorialOferta> ofertas = new ArrayList<HistorialOferta>();
    	ofertas.add(new HistorialOferta("Lunes", 1000L));
    	ofertas.add(new HistorialOferta("Martes", 5000L));
    	ofertas.add(new HistorialOferta("Miercoles", 3000L));
    	ofertas.add(new HistorialOferta("Jueves", 4000L));
    	ofertas.add(new HistorialOferta("Viernes", 2000L));
    	HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("lista", ofertas);
        res.put("exitoso", true);
        return Response.ok().entity(res).build();
	}
    
    @GET
    @Path("test/usuario-logueado")
    public Response getUser() {
    	
    	DatosUsuarioDto datos = new DatosUsuarioDto();
    	return Response.ok().entity(datos).build();
	}
    
    @GET
    @Path("ofertas/resumen-pujas")
    public Response getPujas() {
    	
    	return Response.ok().build();
	}
    
    class HistorialOferta {
    	
    	private String dia;
    	private Long monto;
    	
    	public HistorialOferta(String dia, Long monto) {
    		this.dia = dia;
    		this.monto = monto;
    	}

    	public String getDia() {
    		return dia;
    	}

    	public void setDia(String dia) {
    		this.dia = dia;
    	}

    	public Long getMonto() {
    		return monto;
    	}

    	public void setMonto(Long monto) {
    		this.monto = monto;
    	}

    }
}
