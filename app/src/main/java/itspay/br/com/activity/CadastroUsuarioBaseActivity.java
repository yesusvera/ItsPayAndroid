package itspay.br.com.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.aplicationlib.util.mask.MaskEditTextChangedListener;

import itspay.br.com.controller.CadastroUsuarioBaseController;
import itspay.br.com.itspay.R;
import itspay.br.com.util.Utils;


public class CadastroUsuarioBaseActivity extends AppCompatActivity {


    private String mGenero;
    public boolean isEstrageiro;
    public String mStadoCivil;

    CadastroUsuarioBaseController mCadastroUsuarioBaseController = new CadastroUsuarioBaseController(this);

    private EditText nomeCompleto;
    private EditText nomeMae;
    private EditText nomePai;
    private EditText rg;
    private EditText orgaoEmissor;
    private EditText dataEmissao;
    private RadioButton rbmasculino;
    private RadioButton rbfeminino;
    private Spinner spestadocivil;
    private RadioButton estrangeiro;
    private RadioButton nativo;
    private EditText numeropassaport;
    private TextInputLayout txtpasaporte;
    private EditText nacionalidade;
    private EditText naturalidade;
    private ScrollView cadastrologinform;
    private Button btnnextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_base);
        initViews();

        initClickListener();

        mGenero = "Masculino";
    }

    private void initClickListener() {

        btnnextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCadastroUsuarioBaseController.nextPage();
            }
        });


        spestadocivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                mStadoCivil = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void initViews() {

        this.btnnextpage = (Button) findViewById(R.id.btn_next_page);
        this.cadastrologinform = (ScrollView) findViewById(R.id.cadastro_login_form);
        this.naturalidade = (EditText) findViewById(R.id.naturalidade);
        this.nacionalidade = (EditText) findViewById(R.id.nacionalidade);
        this.txtpasaporte = (TextInputLayout) findViewById(R.id.txt_pasaporte);
        this.numeropassaport = (EditText) findViewById(R.id.numero_passaport);
        this.nativo = (RadioButton) findViewById(R.id.nativo);
        this.estrangeiro = (RadioButton) findViewById(R.id.estrangeiro);
        this.spestadocivil = (Spinner) findViewById(R.id.sp_estado_civil);
        this.rbfeminino = (RadioButton) findViewById(R.id.rb_feminino);
        this.rbmasculino = (RadioButton) findViewById(R.id.rb_masculino);
        this.dataEmissao = (EditText) findViewById(R.id.dataEmissao);
        this.orgaoEmissor = (EditText) findViewById(R.id.orgaoEmissor);
        this.rg = (EditText) findViewById(R.id.rg);
        this.nomePai = (EditText) findViewById(R.id.nomePai);
        this.nomeMae = (EditText) findViewById(R.id.nomeMae);
        this.nomeCompleto = (EditText) findViewById(R.id.nomeCompleto);

        Utils.nextInputOnMaxLength(this, rg, orgaoEmissor, 7);
        Utils.nextInputOnMaxLength(this, orgaoEmissor, dataEmissao, 2);

        dataEmissao.addTextChangedListener(new MaskEditTextChangedListener("##/##/####", dataEmissao));
    }

    public void onGeneroClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_masculino:
                if (checked)
                    mGenero = "Masculino";
                break;
            case R.id.rb_feminino:
                if (checked)
                    mGenero = "Feminino";
                break;
        }
    }

    public void onStangeiroClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.estrangeiro:
                if (checked)
                    txtpasaporte.setVisibility(View.VISIBLE);
                isEstrageiro = true;
                break;
            case R.id.nativo:
                if (checked)
                    txtpasaporte.setVisibility(View.GONE);
                isEstrageiro = false;
                break;
        }
    }

    public EditText getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(EditText nomeMae) {
        this.nomeMae = nomeMae;
    }

    public EditText getNomePai() {
        return nomePai;
    }

    public void setNomePai(EditText nomePai) {
        this.nomePai = nomePai;
    }


    public EditText getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(EditText dataEmissao) {
        this.dataEmissao = dataEmissao;
    }


    public String getmGenero() {
        return mGenero;
    }

    public void setmGenero(String mGenero) {
        this.mGenero = mGenero;
    }

    public EditText getRg() {
        return rg;
    }

    public void setRg(EditText rg) {
        this.rg = rg;
    }

    public EditText getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(EditText orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public EditText getNumeropassaport() {
        return numeropassaport;
    }

    public void setNumeropassaport(EditText numeropassaport) {
        this.numeropassaport = numeropassaport;
    }

    public Spinner getSpestadocivil() {
        return spestadocivil;
    }

    public void setSpestadocivil(Spinner spestadocivil) {
        this.spestadocivil = spestadocivil;
    }

    public EditText getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(EditText nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public EditText getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(EditText naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getmStadoCivil() {
        return mStadoCivil;
    }

    public void setmStadoCivil(String mStadoCivil) {
        this.mStadoCivil = mStadoCivil;
    }

    public EditText getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(EditText nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
