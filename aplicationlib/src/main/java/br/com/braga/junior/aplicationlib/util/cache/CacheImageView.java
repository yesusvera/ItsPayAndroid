package br.com.braga.junior.aplicationlib.util.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yesus on 13/03/17.
 */

public class CacheImageView {

    public static BitmapDrawable salvarCache(Context context, String nomeArquivo, final InputStream inputStream) {
        try {
            byte [] bytes = streamToByteArray(inputStream);
            Log.i("CHCIMGITSPAY","SALVANDO CACHE DE IMAGEM ITSPAY " + nomeArquivo );
            FileOutputStream fOut = context.openFileOutput(nomeArquivo, context.MODE_PRIVATE);
            fOut.write(bytes);
            fOut.close();

            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return new BitmapDrawable(bmp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean temCache(Context context, String nomeArquivo){
        return lerDoCache(context, nomeArquivo) != null;
    }

    public static byte[] lerDoCache(Context context, String nomeArquivo) {
        byte[] retorno = null;

        Log.i("CHCIMGITSPAY","LENDO O CACHE DE IMAGEM ITSPAY " + nomeArquivo );

        try {
            FileInputStream fin = context.openFileInput(nomeArquivo);
            retorno = streamToByteArray(fin);
        } catch (Exception e) {
        }

        return retorno;
    }

    public static BitmapDrawable lerCacheBitmapDraw(Context context, String nomeArquivo){
        byte [] bytes = lerDoCache(context, nomeArquivo);

        try {
            if (bytes != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                return new BitmapDrawable(bmp);
            }
        }catch(OutOfMemoryError ome){
            ome.printStackTrace();
        }

        return null;

    }

    /**
     * method converts {@link InputStream} Object into byte[] array.
     *
     * @param stream the {@link InputStream} Object.
     * @return the byte[] array representation of received {@link InputStream} Object.
     * @throws IOException if an error occurs.
     */
    public static byte[] streamToByteArray(InputStream stream) throws IOException {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        int line = 0;
        // read bytes from stream, and store them in buffer
        while ((line = stream.read(buffer)) != -1) {
            // Writes bytes from byte array (buffer) into output stream.
            os.write(buffer, 0, line);
        }
        stream.close();
        os.flush();
        os.close();
        return os.toByteArray();
    }
}
