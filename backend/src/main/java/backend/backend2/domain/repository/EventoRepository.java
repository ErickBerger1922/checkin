package backend.backend2.domain.repository;

import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.infrastructure.entity.EventoJpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface EventoRepository {

    Evento findById(Long id);

    Evento findByEmpresaResponsavel_Id(Long empresaId);

    List<Evento> findAllByEmpresaResponsavel_Id(Long empresaId);

    boolean existsById(Long id);

    void deleteById(Long id);

    Evento salvar(Evento evento);

    List<Usuario> buscarUsuariosComCheckinNoEvento(Long eventoId);

    void adicionaUsuarioNoEvento(Long eventoId, Long usuarioId);

    void desvinculaUsuarioDoEvento(Long eventoId, Long usuarioId);

}