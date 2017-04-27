package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplicationlib.util.notification.CustomNotification;

import itspay.br.com.controller.TokenController;
import itspay.br.com.itspay.R;



public class TokenActivity extends AppCompatActivity {

    private Button mButonOk;
    private EditText mEdToken;
    private TextView mTxtLinkNovoToken;
    TokenController mTokenController = new TokenController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tokenfragment);

//        mTokenController.requestCode();

        mButonOk = (Button)findViewById(R.id.btn_confirma_token);
        mEdToken = (EditText)findViewById(R.id.ed_token);
        mTxtLinkNovoToken = (TextView)findViewById(R.id.txtViewTermosDeUso);


        mTxtLinkNovoToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTokenController.requestCode();

            }
        });
        mButonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mTokenController.validToken(mEdToken.getText().toString());
            }
        });
    }

    private void moke(){
        CustomNotification.getInstance().notificationBuilder(getBaseContext(), R.mipmap.ic_launch_financial, R.color.red_itspay_bkp, getString(R.string.app_name), "A Chave de acesso é: 123");
    }

}
