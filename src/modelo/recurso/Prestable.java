package modelo.recurso;

public interface Prestable {
    void prestar();
    void devolver();
    boolean estaPrestado();
}
