package ar.com.eduit.curso.java.connector;

import ar.com.eduit.curso.java.utils.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private static String driver="XxXxXxX";
    private static String vendor="XxXxXxX";
    private static String server="XxXxXxX";
    private static String port="XxXxXxX";
    private static String db="XxXxXxX";
    private static String user="XxXxXxX";
    private static String pass="XxXxXxX";
    
    private static String url="jdbc:"+vendor+"://"+server+":"+port+"/"+db;
    
    private static Connection conn=null;
    
    private Connector(){};
    
    public synchronized static Connection getConnection(){
        if(conn==null){
            try {
                Class.forName(driver);
                conn=DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                Logger.log(e);
            }
        }
        return conn;
    }
}
