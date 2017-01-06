package itspay.br.com.activity;

import android.graphics.Color;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;

import itspay.br.com.itspay.R;

/**
 * Created by Yesus Castillo
 */
public class BuilderManagerFloatingButton {

    public static int[] imageResources = new int[]{
            R.drawable.menu_icon1,
            R.drawable.menu_icon2,
            R.drawable.menu_icon3,
            R.drawable.menu_icon4,
            R.drawable.menu_icon5,
            R.drawable.menu_icon6
    };
    public static int[] textResources = new int[]{
            R.string.str_icone_transferir,
            R.string.str_icone_inserir_carga,
            R.string.str_icone_cartoes_virtuais,
            R.string.str_icone_ajustes_seguranca,
            R.string.str_icone_tarifas,
            R.string.str_icone_logout
    };

    public static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex];
    }
    static int getTextResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return textResources[imageResourceIndex++];
    }

    static SimpleCircleButton.Builder getSimpleCircleButtonBuilder() {
        return new SimpleCircleButton.Builder()
                .normalImageRes(getImageResource());
    }

    static TextInsideCircleButton.Builder getTextInsideCircleButtonBuilder() {
        return new TextInsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(getTextResource())
                .pieceColor(Color.WHITE).normalColor(Color.parseColor("#ab772d"));
                //.normalTextRes(R.string.texto_icone);
    }

    static TextInsideCircleButton.Builder getTextInsideCircleButtonBuilderWithDifferentPieceColor() {
        return new TextInsideCircleButton.Builder()
                .normalImageRes(getImageResource())
//                .normalTextRes(R.string.texto_icone)
                .pieceColor(Color.WHITE).unableColor(R.color.primary_bahamas);
    }

    static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilder() {
        return new TextOutsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.texto_icone);
    }

    static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilderWithDifferentPieceColor() {
        return new TextOutsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.texto_icone)
                .pieceColor(Color.WHITE);
    }

    static HamButton.Builder getHamButtonBuilder() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.texto_icone)
                .subNormalTextRes(R.string.texto_icone);
    }

    static HamButton.Builder getHamButtonBuilderWithDifferentPieceColor() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.texto_icone)
                .subNormalTextRes(R.string.texto_icone)
                .pieceColor(Color.WHITE);
    }

    private static BuilderManagerFloatingButton ourInstance = new BuilderManagerFloatingButton();

    public static BuilderManagerFloatingButton getInstance() {
        return ourInstance;
    }

    private BuilderManagerFloatingButton() {
    }
}
