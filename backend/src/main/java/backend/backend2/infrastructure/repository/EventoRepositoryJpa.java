package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.EventoConverter;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.repository.EventoRepository;
import backend.backend2.infrastructure.entity.EventoJpa;
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

    public EventoRepositoryJpa(EventoConverter eventoConverter) {
        this.eventoConverter = eventoConverter;
    }

    @Override
    public List<Evento> listarTodos() {
        String sql = "SELECT * FROM tb_evento"; // substitua pelo nome real da tabela se for diferente

        List<EventoJpa> resultado = em
                .createNativeQuery(sql, EventoJpa.class)
                .getResultList();

        return resultado.stream()
                .map(eventoConverter::jpaParaDominio)
                .toList();
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
    @Transactional
    public Evento salvar(Evento evento) {
        EventoJpa eventoJpa = eventoConverter.dominioParaJpa(evento);

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


