package itspay.br.com.model;

/**
 * Created by yesus on 26/12/16.
 */

public class GetExtratoCredencialResponse {
    private LinhaExtratoCredencial[] extrato;

    public GetExtratoCredencialResponse(LinhaExtratoCredencial[] extrato) {
        this.extrato = extrato;
    }

    public GetExtratoCredencialResponse() {
    }

    public LinhaExtratoCredencial[] getExtrato() {
        return extrato;
    }

    public void setExtrato(LinhaExtratoCredencial[] extrato) {
        this.extrato = extrato;
    }
}
