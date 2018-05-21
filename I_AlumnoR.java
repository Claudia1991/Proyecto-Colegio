package ar.com.eduit.curso.java.repositories;

import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import java.util.List;

public interface I_AlumnoR {
    void save(Alumno alumno);
    void remove(Alumno alumno);
    void update(Alumno alumno);
    Alumno getById(int id);
    List<Alumno>getAll();
    List<Alumno>getByApellido(String apellido);
    List<Alumno>getLikeApellido(String apellido);
    List<Alumno>getByCurso(Curso curso);
    List<Alumno>getByCurso(int idCurso);
}
