package itspay.br.com.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dexafree.materialList.card.Action;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.example.aplicationlib.model.Credencial;
import com.example.aplicationlib.util.cache.CacheImageView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Set;

import io.card.payment.CardIOActivity;
import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.services.ConnectPortadorService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {

    public static String formataMoeda(double valor) {
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal(true);
        String valorFormatado = formatoDois.format(valor);

        return valorFormatado;
    }

    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("[" + symbol + "]", "");

        return s;
    }

    public static String mask(String format, String text) {
        String maskedText = "";
        int i = 0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText += m;
                continue;
            }
            try {
                maskedText += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText;
    }

    public Card novoCartaoCredencial(final Credencial cred, final Context context) {


        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);
        String valorTransacaoFormatado = formatoDois.format(cred.getSaldo());

        String saldo = "Saldo: R$" + valorTransacaoFormatado;

        final Card card = new Card.Builder(context)
                .setTag(cred)
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_card)
                .setTitle(cred.getNomeProduto())
                .setTitleColor(Color.parseColor("#FFFFFFFF"))
                .setSubtitle(cred.getCredencialMascarada())
                .setSubtitle2(saldo)
                .setSubtitleColor(Color.parseColor("#FFFFFFFF"))
                .setDescription(cred.getNomeImpresso())
                .setDescriptionColor(Color.parseColor("#FFFFFFFF"))
                .endConfig()
                .build();


        if (CacheImageView.temCache(context, cred.getIdPlastico() + "")) {
            card.getProvider().setDrawable(CacheImageView.lerCacheBitmapDraw(context, cred.getIdPlastico()+""));
        } else {

            Call<ResponseBody> call = ConnectPortadorService
                    .getService()
                    .abrirPlastico(
                            cred.getIdPlastico(),
                            IdentityItsPay.getInstance().getToken());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.body() != null && response.body().byteStream() != null) {

                        try {
                            CacheImageView.salvarCache(context, cred.getIdPlastico() + "", response.body().byteStream());
                        } catch (Exception e) {
                        }

                        card.getProvider().setDrawable(CacheImageView.lerCacheBitmapDraw(context, cred.getIdPlastico()+""));
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    UtilsActivity.alertIfSocketException(t, context);

                }
            });

        }

        return card;

    }


    public static Card novoCartaoVirtual(final Credencial cred, final Context context) {

        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);
        String valorTransacaoFormatado = formatoDois.format(cred.getSaldo());

        String saldo = "Saldo: R$" + valorTransacaoFormatado;


        final Card card = new Card.Builder(context)
                .setTag("VIRTUAL CARD_ITSPAY")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_virtual_card)
                .setTitle(cred.getApelidoVirtual())
                .setTitleColor(Color.parseColor("#F5F5F5"))
                .setSubtitle(getCredencialComEspacos(cred.getCredencialVirtual()))
                .setSubtitle2(cred.getDataValidadeFmt())
                .setSubtitle3(cred.getCodigoSeguranca())
                .setSubtitleColor(Color.parseColor("#F5F5F5"))
                .setDescription(cred.getNomeImpresso())
                .setDescriptionColor(Color.parseColor("#F5F5F5"))
                .endConfig()
                .build();


        if (CacheImageView.temCache(context, cred.getIdPlastico() + "")) {
            card.getProvider().setDrawable(CacheImageView.lerCacheBitmapDraw(context, cred.getIdPlastico()+""));
        } else {
            Call<ResponseBody> call = ConnectPortadorService
                    .getService()
                    .abrirPlastico(
                            cred.getIdPlastico(),
                            IdentityItsPay.getInstance().getToken());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.body() != null && response.body().byteStream() != null) {
                        try {
                            CacheImageView.salvarCache(context, cred.getIdPlastico() + "", response.body().byteStream());
                        } catch (Exception e) {
                        }

                        card.getProvider().setDrawable(CacheImageView.lerCacheBitmapDraw(context, cred.getIdPlastico()+""));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    UtilsActivity.alertIfSocketException(t, context);
                }
            });
        }

        return card;
    }

    public static String getCredencialComEspacos(String numeroCredencialVirtual) {
        if (numeroCredencialVirtual.length() >= 16) {
            String str = numeroCredencialVirtual.substring(0, 4)
                    + " "
                    + numeroCredencialVirtual.substring(4, 8)
                    + " "
                    + numeroCredencialVirtual.substring(8, 12)
                    + " "
                    + numeroCredencialVirtual.substring(12, 16);

            return str;
        } else {
            return "";
        }
    }


    public static void hideSoftKeyboardOnMaxLength(final Context context, final EditText ed, final int fieldLength) {
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == fieldLength - 1 && before != 1) {
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            ed.getWindowToken(), 0);
                }
            }
        });
    }

    public static void nextInputOnMaxLength(final Context context, final EditText ed, final View viewNext, final int fieldLength) {
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, final int start, final int before, int count) {
                if (start == fieldLength - 1 && before != 1) {
                    viewNext.requestFocus();
                }
            }
        });
    }

    public static void configMask(EditText editText,EditText nextEditText,
                           TextWatcher add, TextWatcher remove ,
                           String hint){

        editText.removeTextChangedListener(remove);
        editText.addTextChangedListener(add);
        editText.setHint(hint);
    }

    public static void onScanPressUtils(View v , Activity context) {
         int MY_SCAN_REQUEST_CODE = 100; // arbitrary int

        // This method is set up as an onClick handler in the layout xml
        // e.g. android:onClick="onScanPress"onScanPress
        Intent intent = new Intent(context, CardIOActivity.class)
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

        context.startActivityForResult(intent, MY_SCAN_REQUEST_CODE);
    }
}