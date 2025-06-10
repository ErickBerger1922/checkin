package backend.backend2.domain.model.vo;

import java.util.Objects;

public class Email {

    private final String endereco;

    public Email(String endereco) {
        if (!isEmailValido(endereco)) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public static boolean isEmailValido(String endereco){
        return endereco != null && endereco.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(endereco, email.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(endereco);
    }

    @Override
    public String toString() {
        return endereco;
    }
}
