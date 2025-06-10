package backend.backend2.application.converters;

import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.infrastructure.entity.FuncaoJpa;
import org.springframework.stereotype.Component;

@Component
public class FuncaoConverter {

    public FuncaoJpa funcaoParaJpa(Funcao funcao){
        FuncaoJpa funcaoJpa = new FuncaoJpa();

        funcaoJpa.setAuthority(funcao.getAuthority());

        return funcaoJpa;
    }

    public Funcao jpaParaFuncao(FuncaoJpa funcaoJpa){
        return new Funcao(
            funcaoJpa.getId(),
            funcaoJpa.getAuthority()
        );
    }
}
