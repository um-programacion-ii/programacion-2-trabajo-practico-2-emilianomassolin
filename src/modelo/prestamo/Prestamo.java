package modelo.prestamo;

import modelo.usuario.Usuario;
import modelo.recurso.RecursoDigital;

import java.time.LocalDate;

public class Prestamo {
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now();
    }

    @Override
    public String toString() {
        return "📄 Préstamo de: " + recurso.getTitulo() +
                " a " + usuario.getNombre() +
                " | Fecha: " + fechaPrestamo +
                (fechaDevolucion != null ? " | Devuelto: " + fechaDevolucion : " | En curso");
    }
}
