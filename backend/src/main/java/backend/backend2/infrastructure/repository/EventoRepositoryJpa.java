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

    private final EventoJpaSpringRepository eventoSpringRepository;
    private final EventoConverter eventoConverter;
    private final UsuarioConverter usuarioConverter;

    public EventoRepositoryJpa(EventoJpaSpringRepository eventoSpringRepository, EventoConverter eventoConverter, UsuarioConverter usuarioConverter) {
        this.eventoSpringRepository = eventoSpringRepository;
        this.eventoConverter = eventoConverter;
        this.usuarioConverter = usuarioConverter;
    }

    @Override
    public Evento findById(Long id){
        return eventoConverter.jpaParaDominio(eventoSpringRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com o id "+ id)));
    }

    @Override
    public Evento findByEmpresaResponsavel_Id(Long empresaId){
        return eventoConverter.jpaParaDominio(eventoSpringRepository
                .findByEmpresaResponsavel_Id(empresaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com o id "+ empresaId)));
    }

    @Override
    public List<Evento> findAllByEmpresaResponsavel_Id(Long empresaId){
        return eventoSpringRepository.findAllByEmpresaResponsavel_Id(empresaId).stream().map(eventoConverter::jpaParaDominio).toList();
    }

    @Override
    public boolean existsById(Long id){
        return eventoSpringRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        eventoSpringRepository.deleteById(id);
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
    public void adicionaUsuarioNoEvento(Long eventoId, Long usuarioId) {
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
}


