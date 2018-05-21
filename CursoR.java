package ar.com.eduit.curso.java.repositories;

import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.utils.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoR implements I_CursoR{
    private Connection conn;
    public CursoR(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void save(Curso curso) {
        if(curso==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
                    "insert into cursos (titulo,profesor,dia,turno) values (?,?,?,?)",1
            );
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia());
            ps.setString(4, curso.getTurno());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) curso.setId(rs.getInt(1));
        } catch (Exception e) {
            Logger.log(e);
        }
    }

    @Override
    public void remove(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Curso>getByFiltro(String filtro){
        List<Curso>lista=new ArrayList();
        String query="select * from cursos where "+filtro;
        try {
            ResultSet rs=conn.createStatement().executeQuery(query);
            while(rs.next()){
                lista.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("profesor"),
                        rs.getString("dia"),
                        rs.getString("turno")
                ));
            }
        } catch (Exception e) {
            Logger.log(e);
        }
        return lista;
    }
    
    @Override
    public Curso getById(int id) {
        List<Curso> lista=getByFiltro("id="+id);
        return (lista.isEmpty())?null:lista.get(0);
    }
    @Override
    public List<Curso> getAll() {
        return getByFiltro("1=1");
    }
    @Override
    public List<Curso> getByTitulo(String titulo) {
        return getByFiltro("titulo='"+titulo+"'");
    }
    @Override
    public List<Curso> getByProfesor(String profesor) {
        return getByProfesor("profesor='"+profesor+"'");
    }
    @Override
    public List<Curso> getByDia(String dia) {
        return getByDia("dia='"+dia+"'");
    }
    @Override
    public List<Curso> getByTurno(String turno) {
        return getByTurno("turno='"+turno+"'");
    }
    @Override
    public List<Curso> getByDiaTurno(String dia, String turno) {
        return getByFiltro(String.format("dia = '%s' and turno = '%s'", dia,turno));
    }
    
}
