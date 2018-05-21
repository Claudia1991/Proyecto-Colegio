package ar.com.eduit.curso.java.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface I_File {
    void print();
    String getText();
    void setText(String text);
    void append(String text);
    void addLine(String line);
    List<String>getLines();
    void setLines(Collection<String>lines);
    void addLines(Collection<String>lines);
    Set<String>getLinkedHashSetLines();
    Set<String>getTreeSet();
    void removeLine(String line);
}
