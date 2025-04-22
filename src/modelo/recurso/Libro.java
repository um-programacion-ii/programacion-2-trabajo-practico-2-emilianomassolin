package modelo.recurso;

public class Libro implements RecursoDigital, Prestable, Renovable {
    private final String titulo;
    private final String autor;
    private boolean prestado = false;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String getDescripcion() {
        return "📘 Libro - Título: " + titulo + ", Autor: " + autor;
    }

    @Override
    public boolean prestar() {
        if (!prestado) {
            prestado = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean devolver() {
        if (prestado) {
            prestado = false;
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
        if (prestado) {
            // lógica para renovar (simplemente retornamos true por ahora)
            return true;
        }
        return false;
    }

    @Override
    public String getEstado() {
        return prestado ? "PRESTADO" : "DISPONIBLE";
    }
}
