package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by nikhil on 19/6/16.
 */
public class HttpRequestHandler {
    private URL url;

    public HttpRequestHandler(String url) throws MalformedURLException{
        this.url = new URL(url);
    }

    private String readData(InputStream inputStream){
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private String createParamsData(Map<String,Object> params) throws Exception{
        StringBuilder data = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (data.length() != 0) data.append('&');
            data.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            data.append('=');
            data.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return data.toString();
    }

    public String doRequest(String type,Map<String,Object> params){

        try {
            String data = createParamsData(params);
            byte[] dataBytes = data.toString().getBytes("UTF-8");


            HttpURLConnection urlConnection;

            if(type.toUpperCase()=="POST") {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setInstanceFollowRedirects(false);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.setRequestProperty("Content-Length", Integer.toString(dataBytes.length));
                urlConnection.setUseCaches(false);
                urlConnection.setRequestMethod(type);
                urlConnection.setDoOutput(true);
                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                out.write(dataBytes);
            }else{
                this.url = new URL(url.toString()+"?"+data);
                urlConnection = (HttpURLConnection) url.openConnection();
            }

            if(urlConnection!=null) {
                BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readData(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}