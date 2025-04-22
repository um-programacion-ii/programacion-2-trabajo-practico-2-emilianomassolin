package modelo.recurso;

public class Audiolibro implements RecursoDigital, Prestable {
    private final String titulo;
    private final String narrador;
    private boolean prestado = false;
    @Override
    public String toString() {
        return "🎧 Audiolibro: " + getTitulo() + " | Narrador: " + getAutor();
    }


    public Audiolibro(String titulo, String narrador) {
        this.titulo = titulo;
        this.narrador = narrador;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescripcion() {
        return "🎧 Audiolibro - Título: " + titulo + ", Narrado por: " + narrador;
    }

    @Override
    public String getAutor() {
        return "";
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
    public String getEstado() {
        return prestado ? "PRESTADO" : "DISPONIBLE";
    }

    @Override
    public CategoriaRecurso getCategoria() {
        return CategoriaRecurso.AUDIOLIBRO;
    }



}

