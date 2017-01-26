package itspay.br.com.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itspay.br.com.activity.LoginActivity;
import itspay.br.com.itspay.R;
import okhttp3.ResponseBody;

/**
 * Created by yesus on 29/12/16.
 */

public class UtilsActivity {

    public static void alertMsg(ResponseBody body, final Context context, DialogInterface.OnClickListener onClickListener) {
        if (body != null) {
            try {
                JSONObject jsonObject = new JSONObject(body.string());

                if(!jsonObject.isNull("msg")) {
                    String msg = jsonObject.getString("msg");

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(msg)
                            .setPositiveButton("OK", onClickListener);
                    builder.create().show();
                }else{
                    if(!jsonObject.isNull("status")){
                        long status = jsonObject.getLong("status");
                        if(status == 403){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage(context.getString(R.string.mensagem_sessao_expirou))
                                    .setPositiveButton("OK", null)
                                    .setNegativeButton("Fazer Login", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent it = new Intent(context.getApplicationContext(), LoginActivity.class);
                                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            context.startActivity(it);
                                        }
                                    });
                            builder.create().show();
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void alertMsg(ResponseBody body, Context context) {
        alertMsg(body, context, null);
    }

    public static void alertIfSocketException(Throwable t, final Context context) {
        t.printStackTrace();
        if(context==null) return;
        if (t instanceof java.net.SocketException) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(context.getString(R.string.mensagem_sem_internet))
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Sair do aplicativo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent it = new Intent(context.getApplicationContext(), LoginActivity.class);
                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            it.putExtra("SAIR", true);
                            context.startActivity(it);
                        }
                    });
            builder.create().show();
        }else if(t instanceof  java.net.SocketTimeoutException){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(context.getString(R.string.mensagem_timeout))
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Sair do aplicativo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent it = new Intent(context.getApplicationContext(), LoginActivity.class);
                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            it.putExtra("SAIR", true);
                            context.startActivity(it);
                        }
                    });
            builder.create().show();
        }
    }
}
