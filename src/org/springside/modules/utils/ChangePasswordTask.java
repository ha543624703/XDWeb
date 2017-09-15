package org.springside.modules.utils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tool.SHAUserPassword;

import db.LDAPDBOperation;


@Component
public class ChangePasswordTask {

	//@Scheduled(cron="0 0/30 9-17 * * ? ")
    
	@Scheduled(cron="0 0 12 * * ?")
    public void taskCycle(){
    	System.out.println("=============..aaa=======");
    	LDAPDBOperation ldap = LDAPDBOperation.getInstance("ou=people");
    	String filter = "";
    	filter = "(&";
    	filter = filter + "(mail=*268*)";
    	filter = filter + ")";
    	List retList = new ArrayList();
    	retList = ldap.search("", filter);
    	System.out.println("=============.."+retList.size()+"..=======");
    	for (int i = 0; i < retList.size(); i++) {
    		String[][] node = (String[][])retList.get(i);
            String[][] modifynode = new String[2][2];
            String[][] modifynode1 = new String[2][2];
            modifynode[0][0] = node[0][0];
            modifynode[1][0] = "userPassword";
            modifynode1[0][0] = node[0][0];
            modifynode1[1][0] = "mail";
    		for (int j = 0; j < node.length; j++) {
    	          String nodeValue = node[j][0];
    	          if (node[j][0].equals("carLicense")) {
    	                String idcard = node[j][1];
    	                if ((idcard.length() >= 15) && (idcard.length() <= 18)) {
    	                  String attributeValue = password(idcard
    	                    .substring(idcard.length() - 6, 
    	                    idcard.length()));
    	                  modifynode[1][1] = attributeValue;
    	                  modifynode1[1][1] = "269";
    	                  ldap.modifyExistAttributeValue(modifynode, "");
    	                  ldap.modifyExistAttributeValue(modifynode1, "");
    	                  break;
    	                }
    	          }
    		}
    	}
    }
    
    public String password(String pwd) {
        return SHAUserPassword.SHA(pwd);
    }
    
    
    public static String GetResponseDataByID(String url, String postData) {
       String data = null;
       try {
    	   URL dataUrl = new URL(url);
    	   HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
    	   con.setRequestMethod("POST");
    	   con.setRequestProperty("Proxy-Connection", "Keep-Alive");
    	   con.setDoOutput(true);
    	   con.setDoInput(true);
    	   OutputStream os = con.getOutputStream();
    	   DataOutputStream dos = new DataOutputStream(os);
    	   dos.write(postData.getBytes());
    	   dos.flush();
    	   dos.close();
    	   InputStream is = con.getInputStream();
    	   DataInputStream dis = new DataInputStream(is);
    	   byte d[] = new byte[dis.available()];
    	   dis.read(d);
    	   data = new String(d);
    	   con.disconnect();
       } 
       catch (Exception ex) {
    	   ex.printStackTrace();        
       }        
       return data;    
   }
}
