import consola.Consola;
import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import notificaciones.ServicioNotificaciones;
import notificaciones.ServicioNotificacionesConsola;

public class Main {
    public static void main(String[] args) {
        ServicioNotificaciones servicioNotificaciones = new ServicioNotificacionesConsola();

        GestorUsuarios gestorUsuarios = new GestorUsuarios(servicioNotificaciones);
        GestorRecursos gestorRecursos = new GestorRecursos();

        Consola consola = new Consola(gestorUsuarios, gestorRecursos, servicioNotificaciones);
        consola.iniciar();
    }
}

