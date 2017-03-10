package itspay.br.com.util;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Set;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.services.ConnectPortadorService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {

    public static String formataMoeda(double valor){
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal(true);
        String valorFormatado = formatoDois.format(valor);

        return valorFormatado;
    }

    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("["+symbol+"]","");

        return s;
    }

    public static String mask(String format, String text){
        String maskedText="";
        int i =0;
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
        String saldo = "Saldo: R$"+ cred.getSaldo();

        final Card card =  new Card.Builder(context)
                .setTag(cred)
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_card)
                .setTitle(cred.getNomeProduto())
                .setTitleColor(Color.parseColor("#F5F5F5"))
                .setSubtitle(cred.getCredencialMascarada())
                .setSubtitle2(saldo)
                .setSubtitleColor(Color.parseColor("#F5F5F5"))
                .setDescription(cred.getNomeImpresso())
                .setDescriptionColor(Color.parseColor("#F5F5F5"))
                .endConfig()
                .build();


        Call<ResponseBody> call = ConnectPortadorService
                .getService()
                .abrirPlastico(
                        cred.getIdPlastico(),
                        IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.body()!=null && response.body().byteStream() != null) {
                    card.getProvider().setDrawable(new BitmapDrawable(response.body().byteStream()));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    UtilsActivity.alertIfSocketException(t, context);

            }
        });

        return card;

    }


    public static Card novoCartaoVirtual(Credencial cred, final Context context) {
        String saldo = "Saldo: R$"+ cred.getSaldo();

        final Card card =  new Card.Builder(context)
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

        Call<ResponseBody> call = ConnectPortadorService
                .getService()
                .abrirPlastico(
                        cred.getIdPlastico(),
                        IdentityItsPay.getInstance().getToken());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.body()!=null && response.body().byteStream()!=null) {
                    card.getProvider().setDrawable(new BitmapDrawable(response.body().byteStream()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                UtilsActivity.alertIfSocketException(t, context);
            }
        });


        return card;

    }

    public static String getCredencialComEspacos(String numeroCredencialVirtual){
        if(numeroCredencialVirtual.length() >= 16) {
            String str = numeroCredencialVirtual.substring(0, 4)
                    + " "
                    + numeroCredencialVirtual.substring(4, 8)
                    + " "
                    + numeroCredencialVirtual.substring(8, 12)
                    + " "
                    + numeroCredencialVirtual.substring(12, 16);

            return str;
        }else{
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
}