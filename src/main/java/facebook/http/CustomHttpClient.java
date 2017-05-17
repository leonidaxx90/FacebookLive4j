package facebook.http;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import facebook.http.bean.HttpResponseBean;

/**
 * Created by salvo on 09/05/17.
 */
public class CustomHttpClient {

    private static CustomHttpClient instance;
    private final String USER_AGENT = "Mozilla/5.0";
    private String proxyHost = null;
    private String proxyPort = null;
    private String proxyUser = null;
    private String proxyPass = null;
    
    HttpClient client;

    private CustomHttpClient(String proxyHost, String proxyPort, String proxyUser, String proxyPass){
    	this.proxyHost = proxyHost;
    	this.proxyPort = proxyPort;
    	this.proxyUser = proxyUser;
    	this.proxyPass = proxyPass;
    }

    public static CustomHttpClient getInstance(){
        if(instance == null){
            instance = new CustomHttpClient(null, null, null, null);
        }
        return instance;
    }
    
    public static CustomHttpClient getInstanceWithProxy(String proxyHost, String proxyPort){
    	
        if(instance == null){
            instance = new CustomHttpClient(proxyHost, proxyPort, null, null);
        }
        return instance;
    }
    
    public static CustomHttpClient getInstanceWithProxy(String proxyHost, String proxyPort, String userProxy, String userPass){
    	
        if(instance == null){
            instance = new CustomHttpClient(proxyHost, proxyPort, userProxy, userPass);
        }
        return instance;
    }
    
    

    /**
     * Perform an HTTP GET
     * @param url url to call
     * @param queryParams url query string param. Each item is in this form 'name=value'
     * @return
     * @throws IOException
     */
    public HttpResponseBean doGet(String url, List<String> queryParams)throws IOException{

        url = composeUrl(url, queryParams);
        HttpGet requestGet = new HttpGet(url);
        HttpResponseBean responseBean = performAction(url, queryParams, requestGet);

        return responseBean;
    }

    /**
     * Perform an HTTP DELETE
     * @param url
     * @param queryParams
     * @return
     * @throws IOException
     */
    public HttpResponseBean doDelete(String url, List<String> queryParams)throws IOException {

        url = composeUrl(url, queryParams);
        HttpDelete requestDelete = new HttpDelete(url);
        HttpResponseBean responseBean = performAction(url, queryParams, requestDelete);

        return responseBean;
    }

    /**
     * Perform an HTTP POST
     * @param url url to call
     * @param queryParams url query string param. Each item is in this form 'name=value'
     * @param paramsPost  Form text parameter
     * @param filePost Form files (for upload files)
     * @return
     * @throws IOException
     */
    public HttpResponseBean doPost(String url, List<String> queryParams, Map<String, String> paramsPost, Map<String, File> filePost) throws IOException{

    	client = createClient();
        if(queryParams != null) {
            url = composeUrl(url, queryParams);
        }

        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        //add post files
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //MultipartEntity entity = new MultipartEntity();

        if(filePost != null) {
            for (Map.Entry<String, File> entry : filePost.entrySet()) {
                //entity.addPart(entry.getKey(), new FileBody(entry.getValue()));
            	builder.addPart(entry.getKey(), new FileBody(entry.getValue()));
            }
        }

        //add post parameters
        if(paramsPost != null) {
            //List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : paramsPost.entrySet()) {
                //entity.addPart(entry.getKey(), new StringBody(entry.getValue()));
            	builder.addTextBody(entry.getKey(), entry.getValue());
            }
        }

        post.setEntity(builder.build());

        HttpResponse response = client.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        HttpResponseBean responseBean = new HttpResponseBean(result.toString(), responseCode);

        return responseBean;
    }

    /**
     * Return query string encoded
     * @param url url to call
     * @param queryParams url query string param. Each item is in this form 'name=value'
     * @return
     * @throws UnsupportedEncodingException
     */
    private String composeUrl(String url, List<String> queryParams) throws UnsupportedEncodingException{

        List<String> queryStringsEncoded = new ArrayList<>();

        for (String param : queryParams) {
            try {
                queryStringsEncoded.add(param.split("=")[0] + '=' + URLEncoder.encode(param.split("=")[1], "UTF-8"));
            }catch(Exception e){
                //skip param not in form paramName=paramValue
            }
        }

        String[] queryStringsEncodedArray = new String[queryStringsEncoded.size()];
        String queryString = String.join("&",queryStringsEncoded.toArray(queryStringsEncodedArray));

        if(url != null && !url.equals("")){
            if(url.endsWith("?")){
                url += queryString;
            }else if(url.endsWith("/")){
                url = url.substring(0, url.length() -2) + '?' + queryString;
            }else{
                if(queryString != null && !queryString.equals("")){
                    url += '?' +queryString;
                }
            }
        }else{
            url = queryString;
        }

        return url;
    }

    /**
     * Perform a GET/DELETE request
     * @param url
     * @param queryParams
     * @param request
     * @return
     * @throws IOException
     */
    private HttpResponseBean performAction(String url, List<String> queryParams, HttpRequestBase request) throws IOException {

    	client = createClient();
    	// add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        int responseCode = response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        HttpResponseBean responseBean = new HttpResponseBean(result.toString() ,responseCode);

        return responseBean;
    }
    
    /**
     * 
     * @return Return a client normal or proxed
     */
    private HttpClient createClient(){
    	
    	HttpClientBuilder clientBuilder = HttpClientBuilder.create();
    	
    	if(this.proxyHost != null && this.proxyPort != null){
    		
    		HttpHost proxy = new HttpHost(this.proxyHost, Integer.parseInt(this.proxyPort));
    		
    		if(this.proxyUser != null && this.proxyPass != null){
    			BasicCredentialsProvider credential = null;
        		credential = new BasicCredentialsProvider();
        		credential.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUser, proxyPass));
        		clientBuilder.setProxy(proxy);
        		clientBuilder.setDefaultCredentialsProvider(credential);
        		
          		client = clientBuilder.build();
        	}else{
        		clientBuilder.setProxy(proxy);
        		client = clientBuilder.build();
        	}
    	}else{
    		client = clientBuilder.build();
    	}
    	
    	return client;
    	
    }

}
