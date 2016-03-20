package emotion_analysis;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.transform.Templates;

import love.cq.domain.Branch;

import org.apache.bcel.generic.RETURN;
import org.apache.commons.lang3.math.Fraction;
import org.json.JSONArray;
import org.json.JSONException;

import Auto_analyse.newMessage_analyse;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SentimentApiExample
{
	
    public static final String SENTIMENT_URL =
        "http://api.bosonnlp.com/sentiment/analysis";
    
    public static float analyse(String name) throws JSONException, UnirestException,java.io.IOException
    {
    	String body=null;
    	FileReader fileReader=new FileReader("D:\\Project\\"+name+".txt");
    	File f=new File("D:\\Project\\"+name+".txt");
        FileReader fr;
        try {
        	   fr=new FileReader(f);
        	   BufferedReader br=new BufferedReader(fileReader);
        	   String line=br.readLine();
        	   while(line!=null){
        		   body=body+line;
        		   line=br.readLine();
        	   }
        	   br.close();
        	   fr.close();
            } catch (IOException e) {
        	   e.printStackTrace();
        	  }
        
        body=new JSONArray(new String[]{body}).toString();
	
       // body=new JSONArray("["+body+"]").toString();
        HttpResponse<JsonNode> jsonResponse = Unirest.post(SENTIMENT_URL)
            .header("Accept", "application/json")
            .header("X-Token", "QYdRdKZN.4094.0LmppHcluq1S")
            .body(body)
            .asJson();

        String s=jsonResponse.getBody().toString().replace("[","");
        s=s.replace("]","");
        String temp[]=s.split(",");
        float num[]=new float[2];
        for(int i=0;i<2;i++){
        	num[i]=Float.parseFloat(temp[i]);
        }
        return num[0]-num[1];
    }
    
}
