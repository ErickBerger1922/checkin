package backend.backend2.domain.repository;

import backend.backend2.domain.model.funcao.Funcao;

import java.util.Optional;

public interface FuncaoRepository {

    Optional<Funcao> buscarPorFuncao(String funcao);
}
