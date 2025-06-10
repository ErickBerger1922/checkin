package backend.backend2.application.dto;

public class FuncaoDto {

    private Long id;
    private String authority;

    public FuncaoDto() {
    }

    public FuncaoDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
