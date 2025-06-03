package backend.backend2.business.services;

import backend.backend2.infrastructure.entities.Funcao;
import backend.backend2.infrastructure.exceptions.ResourceNotFoundException;
import backend.backend2.infrastructure.repositories.FuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FuncaoService {

    @Autowired
    private FuncaoRepository funcaoRepository;

    public Set<Funcao> buscaPorNomes(List<String> funcoes){
        return funcoes.stream().map(nomeDaFuncao -> funcaoRepository.findByAuthority(nomeDaFuncao)
                .orElseThrow(() -> new ResourceNotFoundException("Função não foi encontrada "+ nomeDaFuncao)))
                .collect(Collectors.toSet());
    }
}
