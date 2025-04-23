package gestor;

import modelo.reserva.Reserva;
import modelo.recurso.RecursoDigital;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

 public class GestorReservas {
    private final Map<RecursoDigital, Queue<Reserva>> reservasPorRecurso = new ConcurrentHashMap<>();

    public void agregarReserva(Reserva reserva) {
        reservasPorRecurso
                .computeIfAbsent(reserva.getRecurso(), r -> new PriorityQueue<>())
                .offer(reserva);
    }



    public Queue<Reserva> verReservas(RecursoDigital recurso) {
        return reservasPorRecurso.getOrDefault(recurso, new PriorityQueue<>());
    }


}
