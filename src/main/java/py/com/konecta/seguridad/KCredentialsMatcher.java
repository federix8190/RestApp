package py.com.konecta.seguridad;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import py.com.konecta.services.UsuarioService;

public class KCredentialsMatcher extends SimpleCredentialsMatcher {

	UsuarioService userService;
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	@Override
    public boolean doCredentialsMatch(AuthenticationToken tok, AuthenticationInfo info) {
		
        try {
        	
	    	if (tok != null && tok.getPrincipal() != null && tok.getCredentials() != null) {
	        	
	        	String username = tok.getPrincipal().toString();
            	String pass = new String((char[]) tok.getCredentials());
            	return authWS(username, pass);
	        }
	    	
        } catch (Exception e) {
        	log.severe("Error en metodo doCredentialsMatch : " + e.getMessage());
        }
        return false;
    }
	
	public boolean authWS(String username, String password) {
    	
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
	        
	        System.err.println("autenticar ws usuario 2 : " + username + " " + password);
	 
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
	        
	        System.err.println("autenticar ws usuario 3 : " + username + " " + password);
	        
	        try {
	        	
		        URL url = new URL("https://www.csj.gov.py/appseguridad/seguridad/sistema/autenticacion.asmx");
		        System.err.println("autenticar ws usuario 4 : " + username + " " + password);
		        con = (HttpsURLConnection) url.openConnection();
		        con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "text/xml");
				con.setRequestProperty("SOAPAction", "http://www.csj.gov.py/Autenticar");
				System.err.println("autenticar ws usuario 5 : " + username + " " + password);
				StringBuilder sb = new StringBuilder();
				sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:csj=\"http://www.csj.gov.py/\">");
				sb.append("<soapenv:Header/><soapenv:Body><csj:Autenticar><csj:usuario>");
				sb.append(username);
				sb.append("</csj:usuario><csj:clave>");
				sb.append(password);
				sb.append("</csj:clave><csj:aplicacion>35</csj:aplicacion><csj:rol>8</csj:rol>");
				sb.append("</csj:Autenticar></soapenv:Body></soapenv:Envelope>");
				body = sb.toString();
				System.err.println("autenticar ws usuario 6 : " + username + " " + password);
				
	        } catch(IOException e) {
	        	logError("Error autenticacion por WebService IOException 1: " + e.getMessage());
	        	e.printStackTrace();
	        	return false;
	        }
	        
			// Send post request
			con.setDoOutput(true);
			System.err.println("autenticar ws usuario 7 : " + username + " " + password);
		    try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
		        wr.writeBytes(body);
		        wr.flush();
		    } catch(IOException e) {
	        	logError("Error autenticacion por WebService IOException 2: " + e.getMessage());
	        	e.printStackTrace();
	        	return false;
	        }
		    System.err.println("autenticar ws usuario 8 : " + username + " " + password);
			
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
	        	return false;
	        }
	        
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = null;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				logError("Error autenticacion por WebService ParserConfigurationException: " + e.getMessage());
				return false;
			}
			
	        try {
	        	
				Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
				NodeList autenticarResult = doc.getElementsByTagName("AutenticarResult");
				Node nNode = null;
				
				String codigoUsuario = "";
				String estadoUsuario = "";
				String resultadoLogin = "";
				String nombre = "";
				String apellido = "";
				
				for (int i = 0; i < autenticarResult.getLength(); i++) {
		            nNode = autenticarResult.item(i);
		            NodeList nodos = nNode.getChildNodes();
		            log.info("Cantidad de nodos : " + nodos.getLength());
		            System.err.println("\n");
		            for (int j = 0; j < nodos.getLength(); j++) {
		            	Node nodo = nodos.item(j);
		            	String nodeName = nodo.getNodeName();
		            	System.err.println("nodeName WS : " + nodeName);
		            	Node value = nodo.getFirstChild();
		            	if (nodeName.equals("ResultadoLogin")) {
		            		resultadoLogin = value.getNodeValue();
		            	}
		            	if (nodeName.equals("CodigoUsuario")) {
		            		codigoUsuario = value.getNodeValue();
		            	}
		            	if (nodeName.equals("EstadoUsuario")) {
		            		estadoUsuario = value.getNodeValue();;
		            	}
		            	if (nodeName.equals("Nombre")) {
		            		nombre = value.getNodeValue();;
		            	}
		            	if (nodeName.equals("Apellido")) {
		            		apellido = value.getNodeValue();;
		            	}
		            }
		            System.err.println("\n");
				}
				
				if (resultadoLogin.equals("true")) {
					System.err.println("Datos usuario WS : " + codigoUsuario + " " + nombre + " " + apellido);
					return true;
				}
				return false;
			
	        } catch (IOException e) {
				logError("Error autenticacion por WebService IOException 4: " + e.getMessage());
				e.printStackTrace();
				return false;
			
			} catch (SAXException e) {
				logError("Error autenticacion por WebService SAXException: " + e.getMessage());
				e.printStackTrace();
				return false;
			}
	        
    	/*} catch(IOException e) {
        	logError("Error autenticacion por WebService IOException: " + e.getMessage());
        	return false;*/
        } catch(NoSuchAlgorithmException e) {
        	logError("Error autenticacion por WebService NoSuchAlgorithmException: " + e.getMessage());
        	return false;
        } catch(KeyManagementException e) {
        	logError("Error autenticacion por WebService KeyManagementException: " + e.getMessage());
        	return false;
        }
    }
    
    public void logInfo(String mensaje) {
    	log.info(mensaje);
	}
    
    public void logError(String mensaje) {
    	log.severe(mensaje);
	}
	
}
