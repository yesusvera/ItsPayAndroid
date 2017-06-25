package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import itspay.br.com.itspay.R;

/**
 * Created by yesus on 16/12/16.
 */
public class TermosDeUsoCartaoVirtualActivity extends AppCompatActivity {

    private WebView webview;
    private Button concordoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_de_uso_cartao_virtual);
        setTitle(R.string.title_activity_termos_de_uso);

        webview = (WebView)findViewById(R.id.webView);

        webview.setVisibility(WebView.VISIBLE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setDisplayZoomControls(true);

        webview.loadUrl("https://docs.google.com/gview?embedded=true&url="+ "http://www.financialcartoes.com.br/Download/CONTRATO_DE_CONCESSAO_DE_CREDITO_CARTAO_INDIVIDUAL_COMPLETO_080115.pdf");

        concordoButton = (Button)findViewById(R.id.concordo_button);
        concordoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent returnIntent = new Intent();
                setResult(NovoCartaoVirtualActivity.CONCORDO_TERMOS_DE_USO_CODE);
                finish();
            }
        });
    }
}
