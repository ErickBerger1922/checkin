package backend.backend2.domain.model.vo;

import java.util.Objects;

public class Cpf {

    private final String numero;

    public Cpf(String numero) {
        if(!isCpfValido(numero)){
            throw new IllegalArgumentException("CPF inválido");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    private static boolean isCpfValido(String numero) {
        return numero != null && numero.matches("\\d{11}") && numero.chars().distinct().count() != 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return Objects.equals(numero, cpf.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return numero;
    }
}
