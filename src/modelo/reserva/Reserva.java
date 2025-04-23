package modelo.reserva;

import modelo.usuario.Usuario;
import modelo.recurso.RecursoDigital;

import java.time.LocalDateTime;

public class Reserva  {
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDateTime fechaReserva;


    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();

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






    @Override
    public String toString() {
        return "Reserva{" +
                "usuario=" + usuario.getNombre() +
                ", recurso=" + recurso.getTitulo() +
                ", fecha=" + fechaReserva +

                '}';
    }
}
