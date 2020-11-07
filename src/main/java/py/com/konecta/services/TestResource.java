package py.com.konecta.services;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import py.com.konecta.ws.csj.autenticacion.CControlAutenticacion;
import py.com.konecta.seguridad.Credenciales;
import py.com.konecta.ws.csj.autenticacion.Autenticacion;
import py.com.konecta.ws.csj.autenticacion.AutenticacionSoap;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Context
	private HttpServletRequest httpRequest;
	
	//@Inject
    //AutenticacionSoap wsAutenticacion;
	
	@GET
	public HashMap<String, String> test() {
		
		String ip = httpRequest.getRemoteAddr();
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("status", "0");
		res.put("IP", ip);
		return res;
	}
	
	@POST
	@Path("/web")
	public String get(HashMap<String, String> datos) throws KeyManagementException, NoSuchAlgorithmException {
		
		String url = datos.get("url");
		return get(url);
	}
	
	@POST
	@Path("/login")
	public String testLogin(Credenciales credenciales) {
		
		return login("", "");
	}
	
	public String get(String link) throws NoSuchAlgorithmException, KeyManagementException {
    	
		// Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
        
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        	 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        
		HttpURLConnection con =  null;
        String body = "";
                
        try {
        	
	        URL url = new URL(link);
	        if (link.contains("https")) 
	        	con = (HttpsURLConnection) url.openConnection();
	        else 
	        	con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "text/xml");
			
        } catch(IOException e) {
        	e.printStackTrace();
        	return "Error : " + e.getMessage();
        }
        
		String xml = "";
        try {
		    Reader reader = new InputStreamReader(con.getInputStream());
	        while (true) {
	            int ch = reader.read();
	            if (ch==-1) {
	                break;
	            }
	            xml = xml + ((char)ch);
	        }
        } catch(IOException e) {
        	e.printStackTrace();
        	return "Error : " + e.getMessage();
        }
        
        return xml;
    }
	
	public String login(String username, String password) {
    	
    	try {
    		
    		System.err.println("autenticar ws usuario 1 : " + username + " " + password);
    		
	    	// Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                    return null;
	                }
	                public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                }
	                public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                }
	            }
	        };
	        
	        // Install the all-trusting trust manager
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        	 
	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };
	        
	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	        HttpsURLConnection con =  null;
	        String body = "";
	        	        
	        try {
	        	
		        URL url = new URL("https://www.personal.com.py/oauth2/v1/auth/token");
		        con = (HttpsURLConnection) url.openConnection();
		        con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				body = "grant_type=implicit&client_id=jjNFjjATyNY%3D&user=80027374-5&response_type=token&password=test";
				
	        } catch(IOException e) {
	        	logError("Error autenticacion por WebService IOException 1: " + e.getMessage());
	        	e.printStackTrace();
	        	return "Error : " + e.getMessage();
	        }
	        
			// Send post request
			con.setDoOutput(true);
		    try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
		        wr.writeBytes(body);
		        wr.flush();
		    } catch(IOException e) {
	        	logError("Error autenticacion por WebService IOException 2: " + e.getMessage());
	        	e.printStackTrace();
	        	return "Error : " + e.getMessage();
	        }
			
		    String xml = "";
	        try {
			    Reader reader = new InputStreamReader(con.getInputStream());
		        while (true) {
		            int ch = reader.read();
		            if (ch==-1) {
		                break;
		            }
		            xml = xml + ((char)ch);
		        }
	        } catch(IOException e) {
	        	logError("Error autenticacion por WebService IOException 3: " + e.getMessage());
	        	e.printStackTrace();
	        	return "Error : " + e.getMessage();
	        }
	        
	        return xml;
	        
    	} catch(NoSuchAlgorithmException e) {
        	logError("Error autenticacion por WebService NoSuchAlgorithmException: " + e.getMessage());
        	return "Error : " + e.getMessage();
        } catch(KeyManagementException e) {
        	logError("Error autenticacion por WebService KeyManagementException: " + e.getMessage());
        	return "Error : " + e.getMessage();
        }
    }

	public void logInfo(String mensaje) {
		logger.info(mensaje);
	}
	
	public void logError(String mensaje) {
		logger.info(mensaje);
	}

}
