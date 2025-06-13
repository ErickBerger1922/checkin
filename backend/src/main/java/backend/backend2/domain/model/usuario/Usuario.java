package backend.backend2.domain.model.usuario;

import backend.backend2.domain.exception.DadosInconsistentesException;
import backend.backend2.domain.model.evento.Evento;
import backend.backend2.domain.model.funcao.Funcao;
import backend.backend2.domain.model.shared.enums.TipoUsuario;
import backend.backend2.domain.model.valueobjects.Cpf;
import backend.backend2.domain.model.valueobjects.Email;
import backend.backend2.domain.model.valueobjects.Endereco;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario {

    private Long id;
    private Email email;
    private String senha;
    private String nome;
    private Cpf cpf;
    private Endereco endereco;
    private String razaoSocial;
    private String cnpj;
    private String codigoAtivacao;

    private TipoUsuario tipoUsuario;

    private Set<Evento> eventos = new HashSet<>();

    private Set<Funcao> funcoes = new HashSet<>();

    public Usuario(Long id, Email email, String senha, String nome, Cpf cpf, Endereco endereco, String razaoSocial, String cnpj, String codigoAtivacao, TipoUsuario tipoUsuario) {
        if(tipoUsuario == TipoUsuario.PESSOA_FISICA && cpf == null){
            throw new DadosInconsistentesException("Pessoa física deve ter CPF");
        }
        if (tipoUsuario == TipoUsuario.EMPRESA && cnpj == null) {
            throw new DadosInconsistentesException("Empresa deve ter CNPJ");
        }
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.codigoAtivacao = codigoAtivacao;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Endereco getEndereco(){
        return endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCodigoAtivacao() {
        return codigoAtivacao;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public void adicionaEventos(Evento evento){
        this.eventos.add(evento);
    }

    public void removeEventos(Evento evento){
        this.eventos.remove(evento);
    }

    public void adicionaFuncao(Funcao funcao){
        funcoes.add(funcao);
    }

    public boolean hasRole(String nomeFuncao){
        for (Funcao funcao : funcoes) {
            if(funcao.getAuthority().equals(nomeFuncao)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
