package renren;

    import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.util.ArrayList;  
import java.util.List;  
      

import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.ResponseHandler;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  

import org.apache.http.impl.client.BasicResponseHandler;  
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.protocol.HTTP;  

      
    @SuppressWarnings("deprecation")
	public class RenRenNotify {  
        @SuppressWarnings("unused")
		private static HttpResponse response;  
       
		private static DefaultHttpClient httpClient;  
      
     
		@SuppressWarnings("static-access")
		public RenRenNotify(String userName, String password) {  
        
            this.httpClient = new DefaultHttpClient();  
            String loginForm = "http://3g.renren.com/PLogin.do";  
            String origURL = "http://3g.renren.com/Home.do";  
            String domain = "renren.com";  
            // 在首页表单上是隐藏的 抓包后分析，并没有发送到服务器  
            // String autoLogin = "true";  
            // 构造一个POST请求，利用Httclient提供的包  
            
          
            HttpPost httpPost = new HttpPost(loginForm);  
            // 将要发送的数据封包  
            List<NameValuePair> params = new ArrayList<NameValuePair>();  
            params.add(new BasicNameValuePair("email", userName));  
            params.add(new BasicNameValuePair("password", password));  
            params.add(new BasicNameValuePair("origURL", origURL));  
            params.add(new BasicNameValuePair("domain", domain));  
      
            // 封包添加到Post请求  
            try {  
                httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
            } catch (UnsupportedEncodingException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
            // 将 get 和post 方法包含到一个函数里面去,这里就是登陆过程了。  
            response = postMethod(httpPost);  
             
            /*  System.out.println(response.getStatusLine());//返回302 
              Header[]headers=response.getAllHeaders(); for (int i = 0; i < 
              headers.length; i++) { Header header = headers[i]; 
              System.out.println(header.getName()+": "+header.getValue()); } 
               
            // 读取跳转的地址  
             String redirectUrl = response.getFirstHeader("Location").getValue();  
            // 查看一下跳转过后，都出现哪些内容.  
             response=getMethod(redirectUrl);//函数见后面  
             System.out.println(response.getStatusLine()); // HTTP/1.1 200 OK  
      
            // 读取一下主页都有什么内容 已经登陆进去  
           //  System.out.println(readHtml("http://www.renren.com/home"));  */
        }  
      
        // 嗅探指定页面的代码  
        public String notify(String url) {  
            HttpGet get = new HttpGet(url);  
           
            ResponseHandler<String> responseHandler = new BasicResponseHandler();  
            String txt = null;  
            try {  
                //txt = httpClient.execute(get, responseHandler);  
                txt = httpClient.execute(get, responseHandler); 
               
            } catch (ClientProtocolException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                get.abort();  
            }  
            return txt;  
        }  
      
        // 用post方法向服务器请求 并获得响应，因为post方法要封装参数，因此在函数外部封装好传参  
        public HttpResponse postMethod(HttpPost post) {  
            HttpResponse resp = null;  
            try {  
                resp = httpClient.execute(post);  
            } catch (ClientProtocolException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                post.abort();  
            }  
            return resp;  
        }  
      
        // 用get方法向服务器请求 并获得响应  
        public HttpResponse getMethod(String url) {  
            HttpGet get = new HttpGet(url);  
            HttpResponse resp = null;  
            try {  
                resp = httpClient.execute(get);  
            } catch (ClientProtocolException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                get.abort();  
            }  
            return resp;  
        }

      /*  public static void main(String[] args) throws Exception{  
            RenRenNotify notify = new RenRenNotify("13889446741",  
                    "313569773b"); 
           
            System.out.println(notify.notify("http://www.renren.com/home"));  
        }  */
      
    }  