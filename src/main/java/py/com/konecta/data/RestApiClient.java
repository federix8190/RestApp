package py.com.konecta.data;

import java.io.InputStream;
import java.io.StringWriter;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class RestApiClient {

	public String getDatos() {
        
        InputStream is = null;
        String hostname = "www.personal.com.py"; 
        int port = 8087;
        
        try {
            
        	/*HttpClient client = HttpClientBuilder.create().build();
            HttpHost host = new HttpHost(hostname, port);
            String urlServicio = "";//"/appseguridad/seguridad/sistema/autenticacion.asmx?WSDL";
            HttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("GET", urlServicio);
            
            HttpResponse res = client.execute(host, request);
            HttpEntity entityResponse = res.getEntity();
            is = entityResponse.getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer);
            String str = writer.toString();
            return str;*/
        	
        	SSLContextBuilder SSLBuilder = SSLContexts.custom();
        	//SSLContext sslContext = SSLBuilder.build();
        	
        	final SSLContext sslContext = new SSLContextBuilder()
        	        .loadTrustMaterial(null, new TrustStrategy() {
						@Override
						public boolean isTrusted(X509Certificate[] x509CertChain, String authType)
								throws CertificateException {
							return true;
						}
					})
        	        .build();
        	
        	//Creating SSLConnectionSocketFactory object
            SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
       
            //Creating HttpClientBuilder
            HttpClientBuilder clientbuilder = HttpClients.custom()
            		.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

            //Setting the SSLConnectionSocketFactory
            clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);

            //Building the CloseableHttpClient
            CloseableHttpClient httpclient = clientbuilder.build();
            
            //Creating the HttpGet request
            HttpGet httpget = new HttpGet("https://www.csj.gov.py/appseguridad/seguridad/sistema/autenticacion.asmx?WSDL");
            //HttpGet httpget = new HttpGet("https://www.personal.com.py/#!/public/home/");
            //HttpGet httpget = new HttpGet("https://www.abc.com.py/");
            
            //Executing the request
            HttpResponse httpresponse = httpclient.execute(httpget);
            
            //Retrieving the HttpEntity and displaying the no.of bytes read
            HttpEntity entity = httpresponse.getEntity();
            if (entity != null) {
            	is = entity.getContent();
            	StringWriter writer = new StringWriter();
                IOUtils.copy(is, writer);
                String str = writer.toString();
                return str;
            } 
            
            return "";
            
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception e) {
            }
        }
    }
}
