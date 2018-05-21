package ar.com.eduit.curso.java.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileText implements I_File{

    private File file;

    public FileText(File file) {
        this.file = file;
    }
    
    public FileText(String file){
        this.file = new File(file);
    }
        
    @Override
    public void print() {
        int car;
        try {
            FileReader in=new FileReader(file);
            while((car=in.read())!=-1) System.out.print((char)car);
            //in.close();
        } catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo "+file.getAbsolutePath());
        } catch(IOException e){
            System.out.println("Problemas al leer el archivo "+file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
    }

    @Override
    public String getText() {
        StringBuilder sb=new StringBuilder();
        int car;
        try {
            FileReader in=new FileReader(file);
            while((car=in.read())!=-1) sb.append((char)car);
            //in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sb.toString();
    }

    @Override
    public void setText(String text) {
        try {
            FileWriter out=new FileWriter(file);
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void append(String text) {
        try {
            FileWriter out=new FileWriter(file,true);
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addLine(String line) {
        //append(line+"\n");
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(file,true));
            out.write(line);
            out.newLine();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<String> getLines() {
        List<String> lista=new ArrayList();
        try {
            //BufferedReader in=new BufferedReader(new FileReader(file));
            //String line;
            //while((line=in.readLine())!=null){
            //    lista.add(line);
            //}
            //in.close();
            new BufferedReader(new FileReader(file)).lines().forEach(lista::add);
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public Set<String> getLinkedHashSetLines() {
        Set set=new LinkedHashSet();
        set.addAll(getLines());
        return set;
    }

    @Override
    public Set<String> getTreeSet() {
        Set set=new TreeSet();
        set.addAll(getLines());
        return set;
    }

    @Override
    public void removeLine(String line) {
        List<String>lista=getLines();
        lista.remove(line);
        setLines(lista);
    }

    @Override
    public void setLines(Collection<String> lines) {
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(file));
            for(String st:lines) out.write(st+"\n");
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addLines(Collection<String> lines) {
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(file,true));
            for(String st:lines) out.write(st+"\n");
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
