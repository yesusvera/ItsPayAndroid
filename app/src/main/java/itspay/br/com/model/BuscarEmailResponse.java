package itspay.br.com.model;

/**
 * Created by yesus on 23/12/16.
 */

public class BuscarEmailResponse {
    private String email;

    public BuscarEmailResponse(String email) {
        this.email = email;
    }

    public BuscarEmailResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BuscarEmailResponse{" +
                "email='" + email + '\'' +
                '}';
    }
}
