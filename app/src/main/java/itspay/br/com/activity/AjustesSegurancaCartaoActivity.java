package itspay.br.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import itspay.br.com.controller.AjustesSegurancaoCartaoController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;

public class AjustesSegurancaCartaoActivity extends AppCompatActivity {

    public Switch switchAvisosNotificacoes;
    public Switch switchBloqueioCartao;
    public Switch switchUsoExterior;
    public Switch switchUsoInternet;
    public Switch switchSaque;

    public TextView textAvisosNotificacoes;
    public TextView textBloqueioCartao;
    public TextView textUsoExterior;
    public TextView textUsoInternet;
    public TextView textSaque;

    private Button trocarSenhaButton;
    private Button comunicarPerdaButton;

    public SwipeRefreshLayout swipeRefreshLayout;


    private AjustesSegurancaoCartaoController controller = new AjustesSegurancaoCartaoController(this);

    public Credencial credencialDetalhe;

    public LinearLayout layoutMenuProduto;
    public LinearLayout layoutSwitchInternet;


    public CompoundButton.OnCheckedChangeListener changeListenerSwitch = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            try {
                Integer estado = new Integer(compoundButton.getTag().toString());
                controller.trocaEstado(estado);
            }catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
    };


    public CompoundButton.OnCheckedChangeListener changeListenerSwitchBloqueioCartao = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final CompoundButton compoundButton, boolean b) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(AjustesSegurancaCartaoActivity.this);
                builder.setMessage(getString(R.string.mensagem_confirma_alteracao_bloqueio))
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Integer estado = new Integer(compoundButton.getTag().toString());
                                controller.trocaEstado(estado);
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                controller.removeChangeListener();
                                compoundButton.setChecked(!compoundButton.isChecked());
                                controller.configuraChangeListener();
                            }
                        });
                builder.create().show();

            }catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_seguranca_cartao);

        credencialDetalhe = CartaoActivity.credencialDetalhe;

        switchAvisosNotificacoes = (Switch) findViewById(R.id.switch_avisos_notificacoes);
        switchBloqueioCartao = (Switch) findViewById(R.id.switch_bloqueio_cartao);
        switchUsoExterior = (Switch) findViewById(R.id.switch_uso_exterior);
        switchUsoInternet = (Switch) findViewById(R.id.switch_uso_internet);
        switchSaque = (Switch) findViewById(R.id.switch_saque);

        layoutMenuProduto = (LinearLayout)findViewById(R.id.layoutMenuProduto);
        layoutSwitchInternet = (LinearLayout)findViewById(R.id.layoutSwitchInternet);

        textAvisosNotificacoes = (TextView) findViewById(R.id.subtitle_avisos_notificacoes);
        textBloqueioCartao = (TextView) findViewById(R.id.subtitle_bloqueio_cartao);
        textUsoExterior = (TextView) findViewById(R.id.subtitle_uso_exterior);
        textUsoInternet = (TextView) findViewById(R.id.subtitle_uso_internet);
        textSaque = (TextView) findViewById(R.id.subtitle_saque);

        trocarSenhaButton = (Button) findViewById(R.id.trocar_senha_cartao_button);
        comunicarPerdaButton = (Button) findViewById(R.id.comunicar_perda_button);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.carregaStatusServico();
            }
        });

        trocarSenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AjustesSegurancaCartaoActivity.this, TrocarSenhaCartaoActivity.class);
                AjustesSegurancaCartaoActivity.this.startActivity(intent);
            }
        });


        comunicarPerdaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comunicarPerdaOuRoubo();
            }
        });

        if(credencialDetalhe.getIdProdutoPlataforma()==2 || credencialDetalhe.getIdProdutoPlataforma()==3){
            layoutMenuProduto.setVisibility(View.GONE);
            layoutSwitchInternet.setVisibility(View.GONE);
        }else if(credencialDetalhe.getIdProdutoPlataforma()==4){
            layoutMenuProduto.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.carregaStatusServico();
    }

    private void comunicarPerdaOuRoubo(){
        final CharSequence[] items = {"Comunicar Perda do cartão", "Comunicar Roubo do cartão"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ItsPay").setCancelable(true).setNegativeButton("Cancelar", null)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 : {
                                controller.comunicarPerda();
                                break;
                            }
                            case 1 : {
                                controller.comunicarRoubo();
                                break;
                            }
                        }
                    }
                });
        builder.create().show();
    }
}