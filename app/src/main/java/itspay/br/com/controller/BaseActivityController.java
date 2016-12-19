package itspay.br.com.controller;

import android.app.Activity;

/**
 * Created by yesus on 18/12/16.
 */

public abstract class BaseActivityController<T extends Activity> {

    protected T activity;


    protected BaseActivityController(T activity){
        this.activity = activity;
    }
}


