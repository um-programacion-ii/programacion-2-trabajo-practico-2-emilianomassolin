package notificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("📱 SMS a " + destinatario + ": " + mensaje);
    }
}
