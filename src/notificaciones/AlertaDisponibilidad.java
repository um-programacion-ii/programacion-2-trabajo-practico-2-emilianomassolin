package notificaciones;

import modelo.recurso.RecursoDigital;
import modelo.usuario.Usuario;

public class AlertaDisponibilidad {
    private final Usuario usuario;
    private final RecursoDigital recurso;

    public AlertaDisponibilidad(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
    }

    public Usuario getNombre() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    @Override
    public String toString() {
        return "🔔 Recurso disponible: '" + recurso.getTitulo() + "' para el usuario: " + usuario.getNombre();
    }
}
