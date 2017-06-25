package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import itspay.br.com.controller.TrocarEmailController;
import itspay.br.com.itspay.R;

public class TrocarEmailActivity extends AppCompatActivity {

    private Button btnTrocarEmail;
    private EditText txtEmail;

    private TrocarEmailController trocarEmailController = new TrocarEmailController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_email);

        btnTrocarEmail = (Button)findViewById(R.id.trocar_email_button);
        txtEmail = (EditText)findViewById(R.id.email);


        btnTrocarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trocarEmailController.trocarEmail();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        trocarEmailController.buscarEmail();
    }

    public Button getBtnTrocarEmail() {
        return btnTrocarEmail;
    }

    public void setBtnTrocarEmail(Button btnTrocarEmail) {
        this.btnTrocarEmail = btnTrocarEmail;
    }

    public EditText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(EditText txtEmail) {
        this.txtEmail = txtEmail;
    }
}
