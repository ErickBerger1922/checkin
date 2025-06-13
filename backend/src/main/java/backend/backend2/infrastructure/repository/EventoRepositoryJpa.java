package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.EventoConverter;
import backend.backend2.application.converters.UsuarioConverter;
import backend.backend2.application.exception.RecursoNaoEncontradoException;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.EventoRepository;
import backend.backend2.infrastructure.entity.EventoJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EventoRepositoryJpa implements EventoRepository {

    @PersistenceContext
    private EntityManager em;

    private final EventoConverter eventoConverter;
    private final UsuarioConverter usuarioConverter;

    public EventoRepositoryJpa(EventoConverter eventoConverter, UsuarioConverter usuarioConverter) {
        this.eventoConverter = eventoConverter;
        this.usuarioConverter = usuarioConverter;
    }

    @Override
    public Optional<Evento> buscarPorId(Long id) {
        EventoJpa eventoJpa = em.find(EventoJpa.class, id);
        if (eventoJpa == null) {
            return Optional.empty();
        }
        return Optional.of(eventoConverter.jpaParaDominio(eventoJpa));
    }

    @Override
    public List<Usuario> buscarUsuariosComCheckinNoEvento(Long eventoId) {
        String sql = """
            SELECT u.*
            FROM tb_usuario u
            JOIN tb_evento_usuario eu ON u.id = eu.usuario_id
            WHERE eu.evento_id = :eventoId
        """;

        List<UsuarioJpa> usuariosJpa = em
                .createNativeQuery(sql, UsuarioJpa.class)
                .setParameter("eventoId", eventoId)
                .getResultList();

        return usuariosJpa.stream().map(usuarioConverter::jpaParaDominio)
                .toList();
    }

    @Override
    public List<Evento> listarTodos() {
        String sql = "SELECT * FROM tb_evento";

        List<EventoJpa> resultado = em
                .createNativeQuery(sql, EventoJpa.class)
                .getResultList();

        return resultado.stream()
                .map(eventoConverter::jpaParaDominio)
                .toList();
    }

    @Override
    public List<Evento> listaEventosConformeEmpresa(Long empresaId) {
        String sql = """
        SELECT * FROM tb_evento e
        WHERE e.empresa_id = :empresaId
        """;

        List<EventoJpa> eventosJpa = em.createNativeQuery(sql, EventoJpa.class)
                .setParameter("empresaId", empresaId)
                .getResultList();

        return eventosJpa.stream().map(eventoConverter::jpaParaDominio).toList();
    }

    @Override
    @Transactional
    public Evento salvar(Evento evento) {
        UsuarioJpa empresaResponsavelJpa = em.find(UsuarioJpa.class, evento.getEmpresaResponsavel().getId());
        if (empresaResponsavelJpa == null) {
            throw new RecursoNaoEncontradoException("Empresa responsável não encontrada para o ID: " + evento.getEmpresaResponsavel().getId());
        }

        EventoJpa eventoJpa = eventoConverter.dominioParaJpa(evento);
        eventoJpa.setEmpresaResponsavel(empresaResponsavelJpa);

        eventoJpa = em.merge(eventoJpa);
        return eventoConverter.jpaParaDominio(eventoJpa);
    }

    @Transactional
    public void adicionarUsuarioNoEvento(Long eventoId, Long usuarioId) {
        EventoJpa eventoJpa = em.find(EventoJpa.class, eventoId);
        if (eventoJpa == null) {
            throw new RecursoNaoEncontradoException("Evento não encontrado com id " + eventoId);
        }

        UsuarioJpa usuarioJpa = em.getReference(UsuarioJpa.class, usuarioId);

        eventoJpa.getUsuarios().add(usuarioJpa);

        em.merge(eventoJpa);
    }

    @Override
    @Transactional
    public void desvinculaUsuarioDoEvento(Long eventoId, Long usuarioId) {
        EventoJpa eventoJpa = em.getReference(EventoJpa.class, eventoId);
        UsuarioJpa usuarioJpa = em.getReference(UsuarioJpa.class, usuarioId);

        eventoJpa.getUsuarios().remove(usuarioJpa);
        em.merge(eventoJpa);
    }

    @Override
    @Transactional
    public void deletar(Evento evento) {
        EventoJpa eventoJpa = eventoConverter.dominioParaJpa(evento);
        eventoJpa = em.merge(eventoJpa);
        em.remove(eventoJpa);
    }
}


