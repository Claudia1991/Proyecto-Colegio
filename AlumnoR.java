package ar.com.eduit.curso.java.repositories;

import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.utils.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoR implements I_AlumnoR{

    private Connection conn;

    public AlumnoR(Connection conn) {
        this.conn = conn;
    }
  
    @Override
    public void save(Alumno alumno) {
        if(alumno==null) return;
        //No resulta practico y no es seguro
        /*
        String query="insert into alumnos (nombre,apellido,edad,idCurso) "
                + "values ('"+alumno.getNombre()+"','"+alumno.getApellido()+
                "',"+alumno.getEdad()+","+alumno.getIdCurso()+")";
        query="insert into alumnos (nombre,apellido,edad,idCurso) "
                + "values ('Juan','P',23,1);select * from users;--',23,1)";
        //SQL Inyection
        String apellido="P',23,1);select * from users;-- ";
        */
        
        //PreparedStatement
        try {
            PreparedStatement ps=conn.prepareStatement(
                    "insert into alumnos (nombre,apellido,edad,idCurso) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getIdCurso());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) alumno.setId(rs.getInt(1));
        } catch (Exception e) {
            Logger.log(e);
        }
    }

    @Override
    public void remove(Alumno alumno) {
        if(alumno==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement("delete from alumnos where id=?");
            ps.setInt(1, alumno.getId());
            ps.execute();
        } catch (Exception e) {
            Logger.log(e);
        }
    }

    @Override
    public void update(Alumno alumno) {
        if(alumno==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                "update alumnos set nombre=?, apellido=?, edad=?, idCurso=? where id=?"
            );
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getIdCurso());
            ps.setInt(5, alumno.getId());
            ps.execute();
        } catch (Exception e) {
            Logger.log(e);
        }
    }

    private List<Alumno>getByFiltro(String filtro){
        List<Alumno>lista=new ArrayList();
        String query="select * from alumnos where "+filtro;
        try {
            ResultSet rs=conn.createStatement().executeQuery(query);
            while(rs.next()){
                lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getInt("idCurso")
                ));
            }
        } catch (Exception e) {
            Logger.log(e);
        }
        return lista;
    }
    
    @Override
    public Alumno getById(int id) {
        List<Alumno>lista=getByFiltro("id="+id);
        return (lista.isEmpty())?null:lista.get(0);
    }

    @Override
    public List<Alumno> getAll() {
        return getByFiltro("1=1");
    }

    @Override
    public List<Alumno> getByApellido(String apellido) {
        return getByFiltro("apellido='"+apellido+"'");
    }

    @Override
    public List<Alumno> getLikeApellido(String apellido) {
        return getByFiltro("apellido like'%"+apellido+"%'");
    }

    @Override
    public List<Alumno> getByCurso(Curso curso) {
        return getByCurso(curso.getId());
    }

    @Override
    public List<Alumno> getByCurso(int idCurso) {
        return getByFiltro("idCurso="+idCurso);
    }
    
}
