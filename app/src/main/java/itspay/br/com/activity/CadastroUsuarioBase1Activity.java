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
import itspay.br.com.controller.CadastroUsuarioBase1Controller;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CadastroSingleton;
import itspay.br.com.util.Utils;

import static itspay.br.com.util.Utils.configMask;
import static itspay.br.com.util.Utils.onScanPressUtils;

/**
 * Created by juniorbraga on 08/05/17.
 */

public class CadastroUsuarioBase1Activity extends AppCompatActivity {


    private TextWatcher cpfMask;
    private TextWatcher cnpjMask;

    private android.widget.EditText numerocelular;
    private android.widget.EditText numeroresidencial;
    private android.widget.EditText numerocomercial;
    private EditText numeroCartao;
    private EditText dataNascimento;
    private EditText cpf;
    private ImageButton scanButton;
    private Button proximaPagina;

    CadastroUsuarioBase1Controller mCadastroUsuarioBase1Controller = new CadastroUsuarioBase1Controller(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_base1);

        initView();

        proximaPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCadastroUsuarioBase1Controller.nextPage();
            }
        });


        mCadastroUsuarioBase1Controller.mCadastroBaseSingleton.setmTipoPessoa(1);
    }

    private void initView() {

        this.numerocelular = (EditText) findViewById(R.id.numero_celular);
        this.numerocomercial = (EditText) findViewById(R.id.numero_comercial);
        this.numeroresidencial = (EditText) findViewById(R.id.numero_residencial);
        this.numeroCartao = (EditText)findViewById(R.id.numeroCartao);
        this.dataNascimento = (EditText)findViewById(R.id.dataNascimento);
        this.cpf = (EditText)findViewById(R.id.cpf);
        this.numerocelular = (EditText)findViewById(R.id.numero_celular);
        this.scanButton = (ImageButton) findViewById(R.id.scanCardButton);
        this.proximaPagina = (Button)findViewById(R.id.btn_next_page);


        // Attach the page change listener inside the activity
        cpfMask = new MaskEditTextChangedListener("###.###.###-##", cpf);
        cnpjMask = new MaskEditTextChangedListener("##.###.###/####-##", cpf);
        numeroCartao.addTextChangedListener(new MaskEditTextChangedListener("####.####.####.####", numeroCartao));
        dataNascimento.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataNascimento));
        numerocomercial.addTextChangedListener(new MaskEditTextChangedListener("(##)####-####", numerocomercial));
        numerocelular.addTextChangedListener(new MaskEditTextChangedListener("(##)#####-####", numerocelular));
        numeroresidencial.addTextChangedListener(new MaskEditTextChangedListener("(##)####-####", numeroresidencial));

        cpf.setHint("CPF/CNPJ");

        Utils.nextInputOnMaxLength(this,numeroCartao,dataNascimento,19);
        Utils.nextInputOnMaxLength(this,dataNascimento,cpf,10);
        Utils.nextInputOnMaxLength(this,numerocelular,this.numeroresidencial,14);
        Utils.nextInputOnMaxLength(this,numeroresidencial,this.numerocomercial,13);
        Utils.hideSoftKeyboardOnMaxLength(this,numerocomercial,13);

        configMask(cpf,numerocelular,cpfMask,cnpjMask,getString(R.string.prompt_cpf));
    }

    public void onScanPress(View v) {
        onScanPressUtils(v,this);
    }

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
                if (checked) {
                    configMask(cpf,numerocelular,cpfMask,cnpjMask,getString(R.string.prompt_cpf));
                    mCadastroUsuarioBase1Controller.mCadastroBaseSingleton.setmTipoPessoa(1);
                }
                break;
            case R.id.rb_cnpj:
                if (checked)
                    configMask(cpf,numerocelular,cnpjMask,cpfMask,getString(R.string.prompt_cnpj));
                mCadastroUsuarioBase1Controller.mCadastroBaseSingleton.setmTipoPessoa(2);
                break;
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

    public EditText getNumeroresidencial() {
        return numeroresidencial;
    }

    public void setNumeroresidencial(EditText numeroresidencial) {
        this.numeroresidencial = numeroresidencial;
    }

    public EditText getNumerocomercial() {
        return numerocomercial;
    }

    public void setNumerocomercial(EditText numerocomercial) {
        this.numerocomercial = numerocomercial;
    }
}
