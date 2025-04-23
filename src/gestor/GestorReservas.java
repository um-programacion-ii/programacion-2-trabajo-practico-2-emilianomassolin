package gestor;

import modelo.reserva.Reserva;
import modelo.recurso.RecursoDigital;
import notificaciones.ServicioNotificaciones;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorReservas {
    private final Map<RecursoDigital, Queue<Reserva>> reservasPorRecurso = new ConcurrentHashMap<>();
    private final ServicioNotificaciones servicioNotificaciones;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public GestorReservas(ServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public synchronized void agregarReserva(Reserva reserva) {
        reservasPorRecurso
                .computeIfAbsent(reserva.getRecurso(), r -> new PriorityQueue<>())
                .offer(reserva);

        System.out.println("📥 Reserva agregada para: " + reserva.getRecurso().getTitulo());

        executor.submit(() ->
                servicioNotificaciones.notificar("🔔 Nueva reserva para: " +
                        reserva.getRecurso().getTitulo() + " por " + reserva.getUsuario().getNombre()));
    }

    public synchronized Queue<Reserva> verReservas(RecursoDigital recurso) {
        return reservasPorRecurso.getOrDefault(recurso, new PriorityQueue<>());
    }

    public void shutdown() {
        executor.shutdown();
    }
}
