package modelo.reserva;

import modelo.usuario.Usuario;
import modelo.recurso.RecursoDigital;

import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDateTime fechaReserva;
    private final int prioridad; // menor valor == mayor prioridad

    public Reserva(Usuario usuario, RecursoDigital recurso, int prioridad) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
        this.prioridad = prioridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Reserva otra) {
        int comparacionPrioridad = Integer.compare(this.prioridad, otra.prioridad);
        if (comparacionPrioridad != 0) return comparacionPrioridad;
        return this.fechaReserva.compareTo(otra.fechaReserva); // para igualdad de prioridad
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "usuario=" + usuario.getNombre() +
                ", recurso=" + recurso.getTitulo() +
                ", fecha=" + fechaReserva +
                ", prioridad=" + prioridad +
                '}';
    }
}
