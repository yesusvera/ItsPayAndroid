package itspay.br.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.braga.junior.aplicationlib.util.mask.MaskEditTextChangedListener;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import itspay.br.com.controller.CadastroLoginController;
import itspay.br.com.itspay.R;
import itspay.br.com.util.Utils;


/**
 * Created by yesus on 14/12/16.
 */
public class CadastroLoginActivity extends AppCompatActivity {

    private EditText numeroCartao;
    private EditText dataNascimento;
    private EditText cpf;
    private EditText email;
    private EditText senha;
    private EditText confirmacaoSenha;

    private TextView txtViewTermosDeUso;

    private CheckBox termosDeUso;

    private Button criarLogin;

    private ImageButton scanButton;

    private int MY_SCAN_REQUEST_CODE = 100; // arbitrary int


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        setTitle(R.string.title_activity_cadastro_login);

        numeroCartao = (EditText)findViewById(R.id.numeroCartao);
        dataNascimento = (EditText)findViewById(R.id.dataNascimento);
        cpf = (EditText)findViewById(R.id.cpf);
        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        confirmacaoSenha = (EditText)findViewById(R.id.confirmacaoSenha);
        termosDeUso = (CheckBox)findViewById(R.id.checkTermosDeUso);
        criarLogin = (Button)findViewById(R.id.buttonCriarLogin);
        txtViewTermosDeUso = (TextView) findViewById(R.id.txtViewTermosDeUso);



        Utils.nextInputOnMaxLength(this,numeroCartao,dataNascimento,19);
        Utils.nextInputOnMaxLength(this,dataNascimento,cpf,10);

        Utils.hideSoftKeyboardOnMaxLength(this,cpf,14);

        numeroCartao.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartao));
        cpf.addTextChangedListener(new MaskEditTextChangedListener("###.###.###-##", cpf));
        dataNascimento.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataNascimento));

        scanButton = (ImageButton) findViewById(R.id.scanCardButton);

        criarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CadastroLoginController(CadastroLoginActivity.this).criarLogin();
            }
        });

        txtViewTermosDeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroLoginActivity.this, TermosDeUsoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onScanPress(View v) {
        // This method is set up as an onClick handler in the layout xml
        // e.g. android:onClick="onScanPress"

        Intent intent = new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)
                .putExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false)
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true)
                .putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, false)
                .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "pt_BR")
                .putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, false)
                .putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, false)
                .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, false)
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_SCAN, false)
                .putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)
                .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true);

        try {
            int unblurDigits = Integer.parseInt("4");
            intent.putExtra(CardIOActivity.EXTRA_UNBLUR_DIGITS, unblurDigits);
        } catch(NumberFormatException ignored) {}

        startActivityForResult(intent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultStr = new String();
        if ((requestCode == MY_SCAN_REQUEST_CODE )&& data != null
                && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)){
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
            if (scanResult != null) {

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

                numeroCartao.setText(scanResult.getFormattedCardNumber().replaceAll(" ", "."));

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );r

                if (scanResult.isExpiryValid()) {
                    resultStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultStr += "Postal Code: " + scanResult.postalCode + "\n";
                }

                if (scanResult.cardholderName != null) {
                    resultStr += "Cardholder Name : " + scanResult.cardholderName + "\n";
                }
            } else {
                resultStr = "Scan was canceled.";
            }

        }
    }

    public EditText getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(EditText numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public EditText getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(EditText dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EditText getCpf() {
        return cpf;
    }

    public void setCpf(EditText cpf) {
        this.cpf = cpf;
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getSenha() {
        return senha;
    }

    public void setSenha(EditText senha) {
        this.senha = senha;
    }

    public CheckBox getTermosDeUso() {
        return termosDeUso;
    }

    public void setTermosDeUso(CheckBox termosDeUso) {
        this.termosDeUso = termosDeUso;
    }

    public EditText getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(EditText confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }
}
