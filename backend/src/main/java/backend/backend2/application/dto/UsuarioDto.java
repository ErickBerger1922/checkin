package backend.backend2.application.dto;

import backend.backend2.domain.model.shared.enums.TipoUsuario;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDto {

    private Long id;
    private String email;
    private String senha;

    private String nome;
    private String cpf;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String razaoSocial;
    private String cnpj;
    private String codigoAtivacao;
    private TipoUsuario tipoUsuario;

    private Set<String> funcoes = new HashSet<>();

    public UsuarioDto() {
    }

    public UsuarioDto(Long id, String email, String senha, String nome, String cpf, String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String razaoSocial, String cnpj, String codigoAtivacao, TipoUsuario tipoUsuario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.codigoAtivacao = codigoAtivacao;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigoAtivacao() {
        return codigoAtivacao;
    }

    public void setCodigoAtivacao(String codigoAtivacao) {
        this.codigoAtivacao = codigoAtivacao;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<String> getFuncoes() {
        return funcoes;
    }
}
