package itspay.br.com.controller;

import android.app.Activity;

import itspay.br.com.util.ProgresDialogUtil;

/**
 * Created by yesus on 18/12/16.
 */

public abstract class BaseActivityController<T extends Activity> {

    protected T activity;
    ProgresDialogUtil mProgresDialogUtil;

    protected BaseActivityController(T activity){
        this.activity = activity;
        this.mProgresDialogUtil = new ProgresDialogUtil(activity);
    }
}


