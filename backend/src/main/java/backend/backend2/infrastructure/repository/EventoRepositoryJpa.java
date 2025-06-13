package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.EventoConverter;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.EventoRepository;
import backend.backend2.infrastructure.entity.EventoJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EventoRepositoryJpa implements EventoRepository {

    @PersistenceContext
    private EntityManager em;

    private final EventoConverter eventoConverter;

    public EventoRepositoryJpa(EventoConverter eventoConverter) {
        this.eventoConverter = eventoConverter;
    }

    @Override
    public Optional<Evento> buscarPorId(Long id) {
        EventoJpa eventoJpa = em.find(EventoJpa.class, id);
        if(eventoJpa == null){
            return Optional.empty();
        }
        return Optional.of(eventoConverter.jpaParaDominio(eventoJpa));
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
    @Transactional
    public Evento salvar(Evento evento) {
        UsuarioJpa usuarioJpa = em.getReference(UsuarioJpa.class, evento.getEmpresaResponsavel().getId());

        EventoJpa eventoJpa = eventoConverter.dominioParaJpa(evento);
        eventoJpa.setEmpresaResponsavel(usuarioJpa);

        if (eventoJpa.getId() == null) {
            em.persist(eventoJpa);
        } else {
            eventoJpa = em.merge(eventoJpa);
        }

        return eventoConverter.jpaParaDominio(eventoJpa);
    }

    @Override
    @Transactional
    public void deletar(Evento evento) {
        EventoJpa eventoJpa = eventoConverter.dominioParaJpa(evento);
        eventoJpa = em.merge(eventoJpa);
        em.remove(eventoJpa);
    }
}


