package notificaciones;

class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void notificar(String mensaje) {
        System.out.println("📧 Notificación por SMS: " + mensaje);
    }
}

