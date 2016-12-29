package itspay.br.com.util;

import android.app.AlertDialog;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by yesus on 29/12/16.
 */

public class UtilsActivity {

    public static void alertIfError(ResponseBody errorBody, Context context){
        if(errorBody != null){
            try {
                JSONObject jsonObject = new JSONObject(errorBody.string());
                String msg = jsonObject.getString("msg");

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(msg)
                        .setPositiveButton("OK", null);
                builder.create().show();
            }catch (IOException ex){
                ex.printStackTrace();
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
    }

}
