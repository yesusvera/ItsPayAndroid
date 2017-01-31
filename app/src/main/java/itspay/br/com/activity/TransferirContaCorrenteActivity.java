package itspay.br.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.Locale;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.controller.TransferirContaCorrenteController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Banco;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.Utils;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

public class TransferirContaCorrenteActivity extends AppCompatActivity {

    private EditText agencia;
    private EditText conta;
    private EditText cpf;
    private EditText favorecido;
    private CurrencyEditText valor;
    private EditText senhaCartao;
    public  Button transferirButton;

    private Credencial credencialDetalhe;

    private Banco[] bancos;
    private Banco bancoSelecionado;

    public LinearLayout mainLayout;
    public ProgressBar progress;

    private SearchableSpinner bancoFavorecidoSpinner;
    private TransferirContaCorrenteController controller = new TransferirContaCorrenteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir_conta_corrente);

        credencialDetalhe =  CartaoActivity.credencialDetalhe;

        setTitle("Saldo R$ "+ Utils.formataMoeda(credencialDetalhe.getSaldo()));

        agencia = (EditText)findViewById(R.id.agencia);
        conta = (EditText)findViewById(R.id.conta);
        cpf = (EditText)findViewById(R.id.cpf);
        favorecido = (EditText)findViewById(R.id.favorecido);
        valor = (CurrencyEditText)findViewById(R.id.valor);
        senhaCartao = (EditText)findViewById(R.id.senhaCartao);
        transferirButton = (Button)findViewById(R.id.transferir_button);
        bancoFavorecidoSpinner = (SearchableSpinner)findViewById(R.id.banco_favorecido_spinner);

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        progress = (ProgressBar)findViewById(R.id.progress);

        bancoFavorecidoSpinner.setTitle(getString(R.string.prompt_banco_favorecido));
        bancoFavorecidoSpinner.setPositiveButton("Ok");

        bancoFavorecidoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  bancoSelecionado = bancos[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                bancoSelecionado = null;
            }
        });

        transferirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TransferirContaCorrenteActivity.this);
                builder.setMessage(getString(R.string.mensagem_confirmacao_transf))
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                controller.transferir();
                            }
                        })
                        .setNegativeButton("Não", null);
                builder.create().show();
            }
        });

        valor.setLocale(new Locale("pt", "BR"));
        agencia.addTextChangedListener(new MaskEditTextChangedListener("####", agencia));
        conta.addTextChangedListener(new MaskEditTextChangedListener("#####-#", conta));
        cpf.addTextChangedListener(new MaskEditTextChangedListener("###.###.###-##", cpf));
        cpf.setText(IdentityItsPay.getInstance().getLoginPortador().getCpf());
        favorecido.setText(credencialDetalhe.getNomeImpresso());

        controller.carregarListaBancos();
    }

    public void setLoading(boolean loading){
        if(loading){
            mainLayout.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.VISIBLE);
        }else{
            mainLayout.setVisibility(View.VISIBLE);
            progress.setVisibility(View.INVISIBLE);
        }
    }

    public SearchableSpinner getBancoFavorecidoSpinner() {
        return bancoFavorecidoSpinner;
    }

    public void setBancoFavorecidoSpinner(SearchableSpinner bancoFavorecidoSpinner) {
        this.bancoFavorecidoSpinner = bancoFavorecidoSpinner;
    }

    public Banco[] getBancos() {
        return bancos;
    }

    public void setBancos(Banco[] bancos) {
        this.bancos = bancos;
    }

    public EditText getAgencia() {
        return agencia;
    }

    public void setAgencia(EditText agencia) {
        this.agencia = agencia;
    }

    public EditText getConta() {
        return conta;
    }

    public void setConta(EditText conta) {
        this.conta = conta;
    }

    public EditText getCpf() {
        return cpf;
    }

    public void setCpf(EditText cpf) {
        this.cpf = cpf;
    }

    public EditText getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(EditText favorecido) {
        this.favorecido = favorecido;
    }

    public CurrencyEditText getValor() {
        return valor;
    }

    public void setValor(CurrencyEditText valor) {
        this.valor = valor;
    }

    public EditText getSenhaCartao() {
        return senhaCartao;
    }

    public void setSenhaCartao(EditText senhaCartao) {
        this.senhaCartao = senhaCartao;
    }

    public Button getTransferirButton() {
        return transferirButton;
    }

    public void setTransferirButton(Button transferirButton) {
        this.transferirButton = transferirButton;
    }

    public Credencial getCredencialDetalhe() {
        return credencialDetalhe;
    }

    public void setCredencialDetalhe(Credencial credencialDetalhe) {
        this.credencialDetalhe = credencialDetalhe;
    }

    public Banco getBancoSelecionado() {
        return bancoSelecionado;
    }

    public void setBancoSelecionado(Banco bancoSelecionado) {
        this.bancoSelecionado = bancoSelecionado;
    }
}
