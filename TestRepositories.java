package ar.com.eduit.curso.java.test;

import ar.com.eduit.curso.java.connector.Connector;
import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.repositories.AlumnoR;
import ar.com.eduit.curso.java.repositories.CursoR;
import java.sql.Connection;

public class TestRepositories {
    public static void main(String[] args) {
        Connection conn=Connector.getConnection();
        AlumnoR ar=new AlumnoR(conn);
        
        Alumno alumno=new Alumno("Manuel","Garcia",22,1);
        ar.save(alumno);
        System.out.println(alumno);
        
        System.out.println(ar.getById(5));
        
        ar.remove(ar.getById(6));
        
        alumno=ar.getById(7);
        if(alumno!=null){
            alumno.setNombre("Nicolas");
            alumno.setApellido("Debonus");
            ar.update(alumno);
        }
        
        System.out.println("--------------------------");
        ar.getAll().forEach(System.out::println);
        //ar.getLikeApellido("pe").forEach(System.out::println);
        
        CursoR cr=new CursoR(conn);
        //Curso curso=new Curso("PHP","Gomez","Lunes","Noche");
        //Curso curso=new Curso("Java","Torrez","Martes","Tarde");
        Curso curso=new Curso("Html","Sosa","Jueves","Noche");
        cr.save(curso);
        cr.getAll().forEach(System.out::println);
        
    }
}
