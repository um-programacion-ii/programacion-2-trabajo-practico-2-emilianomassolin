package modelo.recurso;

public class Revista implements RecursoDigital, Prestable, Renovable {
    private final String titulo;
    private final int numeroEdicion;
    private boolean prestado = false;
    private int renovaciones = 0;
    private final int MAX_RENOVACIONES = 1;
    @Override
    public String toString() {
        return "📰 Revista: " + getTitulo() + " | Descripción: " + getDescripcion();
    }


    public Revista(String titulo, int numeroEdicion) {
        this.titulo = titulo;
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescripcion() {
        return "📰 Revista - Título: " + titulo + ", Edición Nº: " + numeroEdicion;
    }

    @Override
    public String getAutor() {
        return "";
    }

    @Override
    public boolean prestar() {
        if (!prestado) {
            prestado = true;
            renovaciones = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean devolver() {
        if (prestado) {
            prestado = false;
            renovaciones = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public boolean renovar() {
        if (prestado && renovaciones < MAX_RENOVACIONES) {
            renovaciones++;
            return true;
        }
        return false;
    }

    @Override
    public String getEstado() {
        return prestado ? "PRESTADO (Renovaciones: " + renovaciones + ")" : "DISPONIBLE";
    }
}
