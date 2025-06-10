package backend.backend2.presentation.controller;

import backend.backend2.infrastructure.entity.CheckinJpa;
import backend.backend2.infrastructure.repository.CheckinRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

    @Autowired
    private final CheckinRepositoryJpa repository;

    public CheckinController(CheckinRepositoryJpa repository) {
        this.repository = repository;
    }

    /*@PostMapping
    public CheckinJpa realizarCheckin(@RequestBody CheckinJpa checkin) {
        return repository.save(checkin);
    }

    @GetMapping
    public List<CheckinJpa> listarCheckins() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void excluirCheckin(@PathVariable Long id) {
        repository.deleteById(id);
    }
    */
}