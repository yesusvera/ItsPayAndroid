package itspay.br.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import itspay.br.com.itspay.R;

/**
 * Created by yesus on 16/12/16.
 */
public class TermosDeUsoActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_de_uso);
        setTitle(R.string.title_activity_termos_de_uso);

        webview = (WebView)findViewById(R.id.webView);


        String doc="<iframe src=\"http://docs.google.com/gview?embedded=true&url=http://www.pc-hardware.hu/PDF/konfig.pdf\" " +
                "width='100%' height='100%' " +
                "style='border: none;'></iframe>";
//        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.setVisibility(WebView.VISIBLE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setDisplayZoomControls(true);
//        webview.setWebViewClient(new WebViewClient());
//        webview.loadUrl(doc);
//        webview.loadData(doc, "text/html", "UTF-8");

        webview.loadUrl("https://docs.google.com/gview?embedded=true&url="+ "http://www.financialcartoes.com.br/Download/CONTRATO_DE_CONCESSAO_DE_CREDITO_CARTAO_INDIVIDUAL_COMPLETO_080115.pdf");
//
//        webview.loadUrl("http://www.financialcartoes.com.br/Download/CONTRATO_DE_CONCESSAO_DE_CREDITO_CARTAO_INDIVIDUAL_COMPLETO_080115.pdf");

    }
}
