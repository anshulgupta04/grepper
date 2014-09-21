package com.grepper.http.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


public class WebClient{
	
	private static WebClient client = null;
	private WebClient(){
	}
	
	public static WebClient getInstance(){
		if(client == null){
			client =  new WebClient();
		}
		return client;
		
	}

	public static HttpClient getClient(){
		
		return new HttpClient();
		
	}
	public static void doPost(HashMap<String, String> postParams,String uri){
		
		try {
			client.getClient().executeMethod(setPostParams(postParams,uri));
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void doGet(String uri){
		
		GetMethod method = new GetMethod(uri);
		try {
			client.getClient().executeMethod(method);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static PostMethod setPostParams(HashMap<String, String> postParams,String uri){
		
		PostMethod method = new PostMethod(uri);
		for(Map.Entry<String, String> entry : postParams.entrySet()){
			
			method.setParameter(entry.getKey(), entry.getValue());
		}
		return method;
	}
	
	
/*	
	public static void main(String args[]) {

    HttpClient client = new HttpClient();
    client.getParams().setParameter("http.useragent", "Test Client");

    BufferedReader br = null;

    PostMethod method = new PostMethod("http://search.yahoo.com/search");
    method.addParameter("p", "\"java2s\"");

    try{
      int returnCode = client.executeMethod(method);

      if(returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
        System.err.println("The Post method is not implemented by this URI");
        // still consume the response body
        method.getResponseBodyAsString();
      } else {
        br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
        String readLine;
        while(((readLine = br.readLine()) != null)) {
          System.err.println(readLine);
      }
      }
    } catch (Exception e) {
      System.err.println(e);
    } finally {
      method.releaseConnection();
      if(br != null) try { br.close(); } catch (Exception fe) {}
    }

  }	
*/
	
}
