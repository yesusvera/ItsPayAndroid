package itspay.br.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.aplicationlib.util.mask.MaskEditTextChangedListener;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import itspay.br.com.controller.CadastroLoginController;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.Utils;


/**
 * Created by yesus on 14/12/16.
 */
public class CadastroLoginActivity extends AppCompatActivity {

    private TextWatcher cpfMask;
    private TextWatcher cnpjMask;

    private EditText numeroCartao;
    private EditText dataNascimento;
    private EditText cpf;
    private EditText numerocelular;
    private ImageButton scanButton;
    private Button proximaPagina;

    CadastroSingleton mCadastroSingleton;

    private int MY_SCAN_REQUEST_CODE = 100; // arbitrary int

    private CadastroLoginController mCadastroLoginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        setTitle(R.string.title_activity_cadastro_login);

        initView();

        proximaPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                validView();
                mCadastroLoginController.verificarLogin(CadastroLoginActivity.this);
            }
        });
    }

    private void initView(){

//        initMask
        mCadastroLoginController = new CadastroLoginController(this);
        mCadastroSingleton = CadastroSingleton.getInstance();

        // Attach the page change listener inside the activity

        numeroCartao = (EditText)findViewById(R.id.numeroCartao);
        dataNascimento = (EditText)findViewById(R.id.dataNascimento);
        cpf = (EditText)findViewById(R.id.cpf);
        numerocelular = (EditText)findViewById(R.id.numero_celular);
        scanButton = (ImageButton) findViewById(R.id.scanCardButton);
        proximaPagina = (Button)findViewById(R.id.btn_next_page);

        cpfMask = new MaskEditTextChangedListener("###.###.###-##", cpf);
        cnpjMask = new MaskEditTextChangedListener("##.###.###/####-##", cpf);


        //        (61)99542-1414

        cpf.setHint("CPF/CNPJ");

        Utils.nextInputOnMaxLength(this,numeroCartao,dataNascimento,19);
        Utils.nextInputOnMaxLength(this,dataNascimento,cpf,10);

        configMask(cpf,numerocelular,cpfMask,cnpjMask,getString(R.string.prompt_cpf));

        Utils.hideSoftKeyboardOnMaxLength(this,numerocelular,14);

        numerocelular.addTextChangedListener(new MaskEditTextChangedListener("(##)#####-####", numerocelular));
        numeroCartao.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartao));
        dataNascimento.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataNascimento));

    }

    public void onScanPress(View v) {
        // This method is set up as an onClick handler in the layout xml
        // e.g. android:onClick="onScanPress"onScanPress
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_cpf:
                if (checked)
                    configMask(cpf,numerocelular,cpfMask,cnpjMask,getString(R.string.prompt_cpf));
                break;
            case R.id.rb_cnpj:
                if (checked)
                    configMask(cpf,numerocelular,cnpjMask,cpfMask,getString(R.string.prompt_cnpj));
                break;
        }
    }


    public void configMask(EditText editText,EditText nextEditText,
                           TextWatcher add, TextWatcher remove ,
                            String hint){

        editText.removeTextChangedListener(remove);
        editText.addTextChangedListener(add);
        editText.setHint(hint);

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
