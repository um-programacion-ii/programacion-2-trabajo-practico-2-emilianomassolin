package gestor;

import modelo.recurso.CategoriaRecurso;
import modelo.recurso.RecursoDigital;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorRecursos {
     private final List<RecursoDigital> recursos = new ArrayList<>();

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        for (RecursoDigital recurso : recursos) {
            System.out.println("Título: " + recurso.getTitulo());
            System.out.println("Descripción: " + recurso.getDescripcion());
            System.out.println("Estado: " + recurso.getEstado());
            System.out.println("----------------------------------");
        }
    }
    public RecursoDigital buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }
    public List<RecursoDigital> filtrarPorCategoria(CategoriaRecurso categoria) {
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoria)
                .toList();
    }

    public List<RecursoDigital> obtenerRecursosOrdenados(Comparator<RecursoDigital> comparador) {
        return recursos.stream()
                .sorted(comparador)
                .toList(); // Java 16+
    }




}
