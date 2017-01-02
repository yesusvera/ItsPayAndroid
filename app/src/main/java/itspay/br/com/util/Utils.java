package itspay.br.com.util;


import android.content.Context;
import android.graphics.Color;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;

import java.util.Set;

import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;

public abstract class Utils {

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

    public static Card novoCartaoCredencial(Credencial cred, Context context) {
        String saldo = "Saldo: R$"+ cred.getSaldo();

        return new Card.Builder(context)
                .setTag("CARD_ITSPAY")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_card)
                .setTitle(cred.getNomeProduto())
                .setTitleColor(Color.parseColor("#F5F5F5"))
                .setSubtitle(cred.getCredencialMascarada())
                .setSubtitle2(saldo)
                .setSubtitleColor(Color.parseColor("#F5F5F5"))
                .setDescription(cred.getNomeImpresso())
                .setDescriptionColor(Color.parseColor("#F5F5F5"))
                .setDrawable(cred.getDrawable())
                .endConfig()
                .build();

    }


    public static Card novoCartaoVirtual(Credencial cred, Context context) {
        String saldo = "Saldo: R$"+ cred.getSaldo();

        return new Card.Builder(context)
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
                .setDrawable(cred.getDrawable())
                .endConfig()
                .build();

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


}