package backend.backend2.infrastructure.repository;

import backend.backend2.application.converters.CheckinConverter;
import backend.backend2.application.converters.EventoConverter;
import backend.backend2.application.converters.UsuarioConverter;
import backend.backend2.domain.model.checkin.Checkin;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.usuario.Usuario;
import backend.backend2.domain.repository.CheckinRepository;
import backend.backend2.infrastructure.entity.CheckinJpa;
import backend.backend2.infrastructure.entity.EventoJpa;
import backend.backend2.infrastructure.entity.UsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CheckinRepositoryJpa implements CheckinRepository {

    @PersistenceContext
    private EntityManager em;

    private final CheckinConverter checkinConverter;
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final EventoRepositoryJpa eventoRepositoryJpa;
    private final UsuarioConverter usuarioConverter;
    private final EventoConverter eventoConverter;

    public CheckinRepositoryJpa(CheckinConverter checkinConverter, UsuarioRepositoryJpa usuarioRepositoryJpa, EventoRepositoryJpa eventoRepositoryJpa, UsuarioConverter usuarioConverter, EventoConverter eventoConverter) {
        this.checkinConverter = checkinConverter;
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
        this.eventoRepositoryJpa = eventoRepositoryJpa;
        this.usuarioConverter = usuarioConverter;
        this.eventoConverter = eventoConverter;
    }

    @Override
    public Optional<Checkin> buscarPorId(Long id) {
        CheckinJpa checkinJpa = em.find(CheckinJpa.class, id);
        if(checkinJpa == null){
            return Optional.empty();
        }
        return Optional.of(checkinConverter.jpaParaDominio(checkinJpa));
    }

    @Override
    public List<Checkin> listarTodos() {
        String sql = "SELECT * FROM tb_checkin";

        List<CheckinJpa> resultado = em
                .createNativeQuery(sql, CheckinJpa.class)
                .getResultList();

        return resultado.stream()
                .map(checkinConverter::jpaParaDominio)
                .toList();
    }

    @Override
    @Transactional
    public Checkin salvar(Checkin checkin) {
        UsuarioJpa usuarioJpa = em.getReference(UsuarioJpa.class, checkin.getUsuario().getId());
        EventoJpa eventoJpa = em.getReference(EventoJpa.class, checkin.getEvento().getId());
        CheckinJpa checkinJpa = checkinConverter.dominioParaJpa(checkin);
        checkinJpa.setUsuario(usuarioJpa);
        checkinJpa.setEvento(eventoJpa);

        if(checkinJpa.getId() == null){
            em.persist(checkinJpa);
        } else {
            checkinJpa = em.merge(checkinJpa);
        }

        return checkinConverter.jpaParaDominio(checkinJpa);
    }

    @Override
    @Transactional
    public void deletar(Checkin checkin) {
        CheckinJpa checkinJpa = checkinConverter.dominioParaJpa(checkin);
        checkinJpa = em.merge(checkinJpa);
        em.remove(checkinJpa);
    }
}
