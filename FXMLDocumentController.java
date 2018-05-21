package ar.com.eduit.curso.java.gui;

import ar.com.eduit.curso.java.connector.Connector;
import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.repositories.AlumnoR;
import ar.com.eduit.curso.java.repositories.CursoR;
import ar.com.eduit.curso.java.utils.FxTable;
import ar.com.eduit.curso.java.utils.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXMLDocumentController implements Initializable {
    
    private AlumnoR ar;
    private CursoR cr;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private ComboBox<Curso> cmbCursos;
    @FXML
    private Label lblInfoAlumno;
    @FXML
    private TextField txtBuscarApe;
    @FXML
    private TableView<Alumno> tblAlumnos;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtProfesor;
    @FXML
    private TextField txtDia;
    @FXML
    private TextField txtTurno;
    @FXML
    private TextField txtBuscarTitulo;
    @FXML
    private TableView<Curso> tblCursos;
    @FXML
    private Label lblInformativoCurso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ar = new AlumnoR(Connector.getConnection());
        cr = new CursoR (Connector.getConnection());
        cargar();
    }    
    
    private void cargar(){
        //Cargamos cmbCursos
        cmbCursos.getItems().clear();
        cmbCursos.getItems().addAll(cr.getAll());
        cmbCursos.getSelectionModel().selectFirst();
        
        //Cargar tabla Alumnos
        new FxTable().cargar(tblAlumnos, ar.getAll());
        
        //Cargar tabla Cursos
        new FxTable().cargar(tblCursos, cr.getAll());
        
    }
    private void limpiarAlumno(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        cmbCursos.getSelectionModel().selectFirst();
        txtNombre.requestFocus();
    }
    
    private void limpiarCurso(){
        txtTitulo.setText("");
        txtProfesor.setText("");
        txtDia.setText("");
        txtTurno.setText("");
        txtNombre.requestFocus();
    }
    private boolean validarAlumno(){   
        if(!new Validator(txtNombre).length(2,20))    return false;
        if(!new Validator(txtApellido).length(2,20))    return false;
        if(!new Validator(txtEdad).isInteger(18, 120))    return false;
        return true;
    }
    
    private boolean validarCurso(){
        if(!new Validator(txtTitulo).length(2,20))    return false;
        if(!new Validator(txtProfesor).length(2,20))    return false;
        if(!new Validator(txtDia).length(2,20))    return false;
        if(!new Validator(txtTurno).length(2,20))    return false;
        return true;
    }

    @FXML
    private void agregarAlumnos(ActionEvent event) {
        if(validarAlumno()){
            Alumno alumno = new Alumno(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    cmbCursos.getValue().getId()
            );
            ar.save(alumno);
            lblInfoAlumno.setText("Se dio de alta al alumno:"+alumno.getId()+".");
            limpiarAlumno();
        }else{
            lblInfoAlumno.setText("No se pudo ingresar el alumno");
        }
        cargar();
    }
    
    @FXML
    private void buscarApe(KeyEvent event){
        new FxTable().cargar(tblAlumnos, ar.getLikeApellido(txtBuscarApe.getText()));
    }
    @FXML
    private void Eliminar(ActionEvent event){
        Alumno alumno=tblAlumnos.getSelectionModel().getSelectedItem();
        if(alumno == null)  return;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Se borrara un alumno.");
        alert.setContentText("Esta seguro de borrar el alumno id : "+alumno.getId());
        alert.setTitle("Cuidado");
        if(alert.showAndWait().get() == ButtonType.OK){
            ar.remove(alumno);
        }
        cargar();
    }

    @FXML
    private void agregarCurso(ActionEvent event) {
        if(validarCurso()){
            Curso curso = new Curso(
                    txtTitulo.getText(),
                    txtProfesor.getText(),
                    txtDia.getText(),
                    txtTurno.getText()
            );
            cr.save(curso);
            lblInformativoCurso.setText("Se dio de alta el curso:"+curso.getId()+".");
            limpiarCurso();
        }else{
            lblInformativoCurso.setText("No se pudo ingresar el curso");
        }
        cargar();
    }

    @FXML
    private void eliminarCurso(ActionEvent event) {
        Curso curso = tblCursos.getSelectionModel().getSelectedItem();
        if(curso == null)  return;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Se borrara un curso.");
        alert.setContentText("Esta seguro de borrar el curso id : "+curso.getId());
        alert.setTitle("Cuidado");
        if(alert.showAndWait().get() == ButtonType.OK){
            cr.remove(curso);
        }
        cargar();
    }

    @FXML
    private void buscarCurso(KeyEvent event) {
        new FxTable().cargar(tblCursos, cr.getByTitulo(txtBuscarTitulo.getText()));
    }
}