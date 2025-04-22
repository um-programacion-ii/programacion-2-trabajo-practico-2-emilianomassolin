package util;

import modelo.recurso.RecursoDigital;

import java.util.Comparator;

public class ComparadoresRecursos {

     public static Comparator<RecursoDigital> porTituloAsc() {
        return Comparator.comparing(RecursoDigital::getTitulo, String.CASE_INSENSITIVE_ORDER);
    }
    public static Comparator<RecursoDigital> porCategoriaAsc() {
        return Comparator.comparing(RecursoDigital::getCategoria);
    }


    public static Comparator<RecursoDigital> porEstadoAsc() {
        return Comparator.comparing(RecursoDigital::getEstado, String.CASE_INSENSITIVE_ORDER);
    }
}