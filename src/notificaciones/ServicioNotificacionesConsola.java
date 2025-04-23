package notificaciones;

public class ServicioNotificacionesConsola implements ServicioNotificaciones {
    @Override
    public void notificar(String mensaje) {
        System.out.println("🔔 Notificación: " + mensaje);
    }
}
