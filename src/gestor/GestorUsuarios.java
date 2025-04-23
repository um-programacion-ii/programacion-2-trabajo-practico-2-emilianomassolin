package gestor;

import excepciones.UsuarioNoEncontradoException;
import modelo.usuario.Usuario;
import notificaciones.ServicioNotificaciones;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
     private Map<String, Usuario> usuarios = new HashMap<>();
    private ServicioNotificaciones servicioNotificaciones;

    public GestorUsuarios(ServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        servicioNotificaciones.notificar("Usuario registrado: " + usuario.getNombre());
    }

    public Usuario buscarUsuario(String id) throws UsuarioNoEncontradoException {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
        return usuario;
    }


    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }
}
