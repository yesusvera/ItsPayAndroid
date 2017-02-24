package itspay.br.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import itspay.br.com.itspay.R;
import itspay.br.com.util.notification.CustomNotification;


public class TokenActivity extends AppCompatActivity {

    private Button mButonOk;
    private EditText mEdToken;
    private TextView mTxtLinkNovoToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tokenfragment);

        mButonOk = (Button)findViewById(R.id.btn_confirma_token);
        mEdToken = (EditText)findViewById(R.id.ed_token);
        mTxtLinkNovoToken = (TextView)findViewById(R.id.txtViewTermosDeUso);

            moke();

        mTxtLinkNovoToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moke();
            }
        });
        mButonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEdToken.getText().toString().equals("123")){
                    Intent intent = new Intent(getBaseContext(),CadastroLoginPage2Activity.class);
                    startActivity(intent);
                }else {
                    mEdToken.setError("Chave Invalida");
                }
            }
        });
    }

    private void moke(){
        CustomNotification.getInstance().notificationBuilder(getBaseContext(), R.mipmap.ic_launch_financial, R.color.red_itspay_bkp, getString(R.string.app_name), "A Chave de acesso é: 123");

    }

}
