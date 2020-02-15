## Java HTTP 编程 (HttpClient)

**HttpClient**  
**• JDK HTTP Client （JDK自带，从9开始)**  
**• Apache HttpComponents的HttpClient (Apache出品)**  



**JDK HttpClient**  
**• JDK 9 新增，JDK10更新，JDK11正式发布**  
**• java.net.http包**  
**• 取代URLConnection**  
**• 支持HTTP/1.1和HTTP/2**  
**• 实现大部分HTTP方法**  
**• 主要类**  
**– HttpClient**  
**– HttpRequest**  
**– HttpResponse**  



注意这个jdk使用的是jdk11

```java
├─src
│  ├─main
│  │  ├─java
│  │  │      HttpComponentsGetTest.java
│  │  │      HttpComponentsPostTest.java
│  │  │      JDKHttpClientGetTest.java
│  │  │      JDKHttpClientPostTest.java
│  │  │      
│  │  └─resources
│  └─test
│      ├─java
│      └─resources
```

```java
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class JDKHttpClientGetTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		doGet();
	}
	
	public static void doGet() {
		try{
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
			HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```

```java
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JDKHttpClientPostTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		doPost();
	}
	
	public static void doPost() {
		try {
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://tools.usps.com/go/ZipLookupAction.action"))
					//.header("Content-Type","application/x-www-form-urlencoded")
					.header("User-Agent", "HTTPie/0.9.2")
					.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8")
					//.method("POST", HttpRequest.BodyPublishers.ofString("tAddress=1 Market Street&tCity=San Francisco&sState=CA"))
					//.version(Version.HTTP_1_1)
					.POST(HttpRequest.BodyPublishers.ofString("tAddress=" 
					    + URLEncoder.encode("1 Market Street", "UTF-8") 
					    + "&tCity=" + URLEncoder.encode("San Francisco", "UTF-8") + "&sState=CA"))
					//.POST(HttpRequest.BodyPublishers.ofString("tAddress=" + URLEncoder.encode("1 Market Street", "UTF-8") + "&tCity=" + URLEncoder.encode("San Francisco", "UTF-8") + "&sState=CA"))
					.build();
			HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.headers());
			System.out.println(response.body().toString());

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
```



**HttpComponents**  
**• hc.apache.org, Apache出品**  
**• 从HttpClient进化而来**  
**• 是一个集成的Java HTTP工具包**  
**– 实现所有HTTP方法：get/post/put/delete**  
**– 支持自动转向**  
**– 支持https协议**  
**– 支持代理服务器等**  

```java


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpComponentsGetTest {

    public final static void main(String[] args) throws Exception {
    	
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        httpGet.setConfig(requestConfig);
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");//获得返回的结果                
                System.out.println(srtResult);
            }else
            {
                //异常处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```

```java


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpComponentsPostTest {

    public final static void main(String[] args) throws Exception {
    	
    	//获取可关闭的 httpCilent
        //CloseableHttpClient httpClient = HttpClients.createDefault();
    	CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    	//配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(10000).setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000).setRedirectsEnabled(false).build();
         
        HttpPost httpPost = new HttpPost("https://tools.usps.com/go/ZipLookupAction.action");
        //设置超时时间
        httpPost.setConfig(requestConfig);
        
        //装配post请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>(); 
        list.add(new BasicNameValuePair("tAddress", URLEncoder.encode("1 Market Street", "UTF-8")));  //请求参数
        list.add(new BasicNameValuePair("tCity", URLEncoder.encode("San Francisco", "UTF-8"))); //请求参数
        list.add(new BasicNameValuePair("sState", "CA")); //请求参数
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8"); 
            //设置post求情参数
            httpPost.setEntity(entity);
            httpPost.setHeader("User-Agent", "HTTPie/0.9.2");
            //httpPost.setHeader("Content-Type","application/form-data");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String strResult = "";
            if(httpResponse != null){ 
                System.out.println(httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                }
                else {
                    strResult = "Error Response: " + httpResponse.getStatusLine().toString();
                } 
            }else{
                 
            }
            System.out.println(strResult);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close(); //释放资源
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```



进一步延伸
• 爬虫
• 自动刷票机器人
• 各类Web监控
• Web回归测试
• ......



**总结**  
**• 了解JDK 11自带的Java HTTP Client 包**  
**• 了解Apache Http Components 包**  
**• 了解Java网络编程的进一步应用**  