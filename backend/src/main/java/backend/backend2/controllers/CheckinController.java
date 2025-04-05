package backend.backend2.controllers;

import backend.backend2.models.entities.Checkin;
import backend.backend2.models.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CheckinController {

    @Autowired
    private final CheckinRepository repository;

    public CheckinController(CheckinRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Checkin realizarCheckin(@RequestBody Checkin checkin) {
        return repository.save(checkin);
    }

    @GetMapping
    public List<Checkin> listarCheckins() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void excluirCheckin(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
