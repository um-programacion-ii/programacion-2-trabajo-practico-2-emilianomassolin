package notificaciones;

import modelo.usuario.Usuario;
import modelo.recurso.RecursoDigital;

import java.time.LocalDate;

public class AlertaVencimineto {
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDate fechaVencimiento;

    public AlertaVencimineto(Usuario usuario, RecursoDigital recurso, LocalDate fechaVencimiento) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return "⚠️ ALERTA DE VENCIMIENTO: El recurso '" + recurso.getTitulo() + "' vence el " + fechaVencimiento +
                " para el usuario " + usuario.getNombre();
    }
}
