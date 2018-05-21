package ar.com.eduit.curso.java.utils;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validator {
    private JTextField  swTxt=null;
    private TextField   fxTxt=null;

    public Validator(JTextField swTxt) {
        this.swTxt = swTxt;
    }

    public Validator(TextField fxTxt) {
        this.fxTxt = fxTxt;
    }
    
    private String getText(){
        if(swTxt!=null) return swTxt.getText();
        if(fxTxt!=null) return fxTxt.getText();
        return null;
    }
    
    private void requestFocus(){
        if(swTxt!=null) swTxt.requestFocus();
        if(fxTxt!=null) fxTxt.requestFocus();
    }
    
    private void selectAll(){
        if(swTxt!=null) swTxt.selectAll();
        if(fxTxt!=null) fxTxt.selectAll();
    }
    
    public boolean length(int length){
        if(getText().length()==length) return true;
        selectAll();
        requestFocus();
        JOptionPane.showMessageDialog(null, 
                "La longitud debe ser de "+length+" letras.");
        return false;
    }
    
    public boolean length(int min,int max){
        if(getText().length()>=min && getText().length()<=max) return true;
        selectAll();
        requestFocus();
        JOptionPane.showMessageDialog(null, 
                "La longitud debe ser entre "+min+" y "+max+" letras.");
        return false;
    }
    
    public boolean isInteger(){
        try {
            Integer.parseInt(getText());
            return true;
        } catch (Exception e) {
            selectAll();
            requestFocus();
            JOptionPane.showMessageDialog(null, 
                "Debe ser un número entero.");
            return false;
        }
    }
    public boolean isInteger(int min, int max){
        if(!isInteger()) return false;
        int nro=Integer.parseInt(getText());
        if(nro>=min && nro<=max) return true;
        selectAll();
        requestFocus();
        JOptionPane.showMessageDialog(null, 
                "Debe ser un número entero entre "+min+" y "+max+".");
        return false;
    }
}
