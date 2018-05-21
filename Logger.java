package ar.com.eduit.curso.java.utils;

import java.net.InetAddress;
import java.time.LocalDateTime;

public class Logger {
   private static String file="log.csv";
   private static FileText fileText=new FileText(file);
   
   public static void log(Exception e){
       LocalDateTime ldt=LocalDateTime.now();
       InetAddress inet=null;
       try{inet=InetAddress.getLocalHost();}catch(Exception ex){}
       String user=System.getProperty("user.name");
       String os=System.getProperty("os.name");
       String registro=ldt+";"+inet+";"+user+";"+os+";"+e;
       fileText.addLine(registro);
       System.out.println("Ocurrio un error!");
   }
   
}
