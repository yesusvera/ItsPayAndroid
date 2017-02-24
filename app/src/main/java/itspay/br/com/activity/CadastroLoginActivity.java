package itspay.br.com.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import itspay.br.com.controller.CadastroLoginController;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.mask.MaskEditTextChangedListener;

/**
 * Created by yesus on 14/12/16.
 */
public class CadastroLoginActivity extends AppCompatActivity {

    private EditText numeroCartao;
    private EditText dataNascimento;
    private EditText cpf;
    private EditText numerocelular;
    private ImageButton scanButton;
    private Button proximaPagina;

    CadastroSingleton mCadastroSingleton;

    private int MY_SCAN_REQUEST_CODE = 100; // arbitrary int

    private CadastroLoginController mCadastroLoginController;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        setTitle(R.string.title_activity_cadastro_login);

        initView();

        proximaPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validView();
            }
        });
    }

    private void validView(){
        if (mCadastroLoginController.validaFormulario1(this)){
            Intent intent = new Intent(this,TokenActivity.class);
            startActivity(intent);
        }
    }

    private void initView(){

        mCadastroLoginController = new CadastroLoginController(getBaseContext());
        mCadastroSingleton = CadastroSingleton.getInstance();

        // Attach the page change listener inside the activity

        numeroCartao = (EditText)findViewById(R.id.numeroCartao);
        dataNascimento = (EditText)findViewById(R.id.dataNascimento);
        cpf = (EditText)findViewById(R.id.cpf);
        numerocelular = (EditText)findViewById(R.id.numero_celular);
        scanButton = (ImageButton) findViewById(R.id.scanCardButton);
        proximaPagina = (Button)findViewById(R.id.btn_next_page);


        numeroCartao.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartao));
        cpf.addTextChangedListener(new MaskEditTextChangedListener("###.###.###-##", cpf));
        dataNascimento.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataNascimento));

    }

    public void onScanPress(View v) {
        // This method is set up as an onClick handler in the layout xml
        // e.g. android:onClick="onScanPress"onScanPress
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false); // default: false

        // hides the manual entry button
        // if set, developers should provide their own manual entry mechanism in the app
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false); // default: false

        // matches the theme of your application
        scanIntent.putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, true); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultStr;
//        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
        if (data != null){
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

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

    public EditText getNumerocelular() {
        return numerocelular;
    }

    public void setNumerocelular(EditText numerocelular) {
        this.numerocelular = numerocelular;
    }
}
