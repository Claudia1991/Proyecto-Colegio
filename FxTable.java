package ar.com.eduit.curso.java.utils;

import java.lang.reflect.Field;
import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FxTable<E> {
    public void cargar(TableView tbl, List<E> lista){
        if(tbl == null || lista== null) return;
        tbl.getItems().clear();
        tbl.getColumns().clear();
        if(lista.isEmpty()) return;
        E e=lista.get(0);
        Field[] field = e.getClass().getDeclaredFields();
        for(Field f:field){
            String nombreCampo = f.getName();
            TableColumn x = new TableColumn<>(nombreCampo);
            x.setCellValueFactory(new PropertyValueFactory(nombreCampo));
            tbl.getColumns().add(x);
        }
        tbl.getItems().addAll(lista);
    }
}