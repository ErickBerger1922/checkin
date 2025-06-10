package backend.backend2.application.service;

import backend.backend2.application.exception.ResourceNotFoundException;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.infrastructure.repository.FuncaoRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FuncaoService {

    private final FuncaoRepositoryJpa funcaoRepository;

    public FuncaoService(FuncaoRepositoryJpa funcaoRepository) {
        this.funcaoRepository = funcaoRepository;
    }

    public Set<Funcao> buscaPorNomes(Set<String> funcoes){
        return funcoes.stream().map(funcao -> funcaoRepository.buscarPorFuncao(funcao)
                .orElseThrow(() -> new ResourceNotFoundException("Função não foi encontrada "+ funcao)))
                .collect(Collectors.toSet());
    }
}
