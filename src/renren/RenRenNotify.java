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
            // ����ҳ���������ص� ץ�����������û�з��͵�������  
            // String autoLogin = "true";  
            // ����һ��POST��������Httclient�ṩ�İ�  
            
          
            HttpPost httpPost = new HttpPost(loginForm);  
            // ��Ҫ���͵����ݷ��  
            List<NameValuePair> params = new ArrayList<NameValuePair>();  
            params.add(new BasicNameValuePair("email", userName));  
            params.add(new BasicNameValuePair("password", password));  
            params.add(new BasicNameValuePair("origURL", origURL));  
            params.add(new BasicNameValuePair("domain", domain));  
      
            // �����ӵ�Post����  
            try {  
                httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
            } catch (UnsupportedEncodingException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
            // �� get ��post ����������һ����������ȥ,������ǵ�½�����ˡ�  
            response = postMethod(httpPost);  
             
            /*  System.out.println(response.getStatusLine());//����302 
              Header[]headers=response.getAllHeaders(); for (int i = 0; i < 
              headers.length; i++) { Header header = headers[i]; 
              System.out.println(header.getName()+": "+header.getValue()); } 
               
            // ��ȡ��ת�ĵ�ַ  
             String redirectUrl = response.getFirstHeader("Location").getValue();  
            // �鿴һ����ת���󣬶�������Щ����.  
             response=getMethod(redirectUrl);//����������  
             System.out.println(response.getStatusLine()); // HTTP/1.1 200 OK  
      
            // ��ȡһ����ҳ����ʲô���� �Ѿ���½��ȥ  
           //  System.out.println(readHtml("http://www.renren.com/home"));  */
        }  
      
        // ��ָ̽��ҳ��Ĵ���  
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
      
        // ��post��������������� �������Ӧ����Ϊpost����Ҫ��װ����������ں����ⲿ��װ�ô���  
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
      
        // ��get��������������� �������Ӧ  
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