package ar.com.eduit.curso.java.test;

import ar.com.eduit.curso.java.connector.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class TestConnector {
    public static void main(String[] args) throws Exception{
        Connection con=Connector.getConnection();
        String query="insert into alumnos (nombre,apellido,edad,idCurso) "
                + "values ('Juan','Perez',23,1)";
        Statement st=con.createStatement();
        //st.execute(query);
        int x=st.executeUpdate(query);
        System.out.println(x);
        //con.close();
        
        x=Connector.getConnection().createStatement().executeUpdate(
                "insert into alumnos (nombre,apellido,edad,idCurso) values "
                        + "('Laura','Salas',33,1)"
        );
        System.out.println(x);
        
        //Consulta de select
        ResultSet rs=Connector.getConnection().createStatement()
                .executeQuery("select * from alumnos");
        
        /*
        while(rs.next()){
            System.out.println( rs.getInt("id")+"\t"+
                                rs.getString("nombre")+"\t"+
                                rs.getString("apellido")+"\t"+
                                rs.getInt("edad")+"\t"+
                                rs.getInt("idCurso")
            );
        }
        rs.close();
        */
        
        ResultSetMetaData rsmd=rs.getMetaData();
        while(rs.next()){
            for(int a=1;a<=rsmd.getColumnCount();a++){
                System.out.print(rs.getObject(a)+"\t");
            }
            System.out.println();
        }
        
        
    }
}
