package notificaciones;

  class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void notificar(String mensaje) {
        System.out.println("📧 Notificación por Email: " + mensaje);
    }
}
