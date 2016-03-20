package weibo;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WeiboCN {
    /**
     * 代码由WebCollector提供，如果不在WebCollector中使用，需要导入selenium相关jar包
     */

        /**
         * 获取新浪微博的cookie，这个方法针对weibo.cn有效，对weibo.com无效
         * weibo.cn以明文形式传输数据，请使用小号
         * @param username 新浪微博用户名
         * @param password 新浪微博密码
         * @return
         * @throws Exception 
         */
        public static String getSinaCookie(String username, String password) throws Exception{
            StringBuilder sb = new StringBuilder();
            HtmlUnitDriver driver = new HtmlUnitDriver();
            driver.setJavascriptEnabled(true);
            driver.get("http://login.weibo.cn/login/");

            WebElement mobile = driver.findElementByCssSelector("input[name=mobile]");
            mobile.sendKeys(username);
            WebElement pass = driver.findElementByCssSelector("input[name^=password]");
            pass.sendKeys(password);
            WebElement rem = driver.findElementByCssSelector("input[name=remember]");
            rem.click();
            WebElement submit = driver.findElementByCssSelector("input[name=submit]");
            submit.click();

            Set<Cookie> cookieSet = driver.manage().getCookies();
            driver.close();
            for (Cookie cookie : cookieSet) {
                sb.append(cookie.getName()+"="+cookie.getValue()+";");
            }
            String result=sb.toString();
            if(result.contains("gsid_CTandWM")){
                return result;
            }else{
                throw new Exception("weibo login failed");
            }
        }
}