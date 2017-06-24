package com.example.aplicationlib.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by juniorbraga on 12/04/17.
 */

public class ProgresDialogUtil {
    private Context mContext;
    public ProgressDialog progress;

    public ProgresDialogUtil(Activity context){
        mContext = context;
    }

    public ProgresDialogUtil(Context context){
        mContext = context;
    }

    public void show(){
        createProgress("Aguarde","");
    }

    public void show(String title){
        createProgress(title,"");
    }

    public void show(String title,String menssage){
        createProgress(title,menssage);
    }

    public void dismiss(){
        if (progress != null) {
            progress.dismiss();
        }
    }

    private void createProgress(String title,String menssage){
        if(progress !=null) {
            this.progress.setTitle(title);
            this.progress.setMessage(menssage);
            this.progress.show();
        }else{
            progress = ProgressDialog.show(mContext, title,
                    menssage, true);
        }

    }

    public ProgressDialog getProgress() {
        return progress;
    }

    public void setProgress(ProgressDialog progress) {
        this.progress = progress;
    }
}

