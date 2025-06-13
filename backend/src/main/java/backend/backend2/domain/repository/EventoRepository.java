package backend.backend2.domain.repository;

import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface EventoRepository {

    Optional<Evento> buscarPorId(Long id);

    List<Evento> listarTodos();

    List<Evento> listaEventosConformeEmpresa(Long empresaId);

    Evento salvar(Evento evento);

    void deletar(Evento evento);

    List<Usuario> buscarUsuariosComCheckinNoEvento(Long eventoId);

    void adicionarUsuarioNoEvento(Long eventoId, Long usuarioId);

    void desvinculaUsuarioDoEvento(Long eventoId, Long usuarioId);

}