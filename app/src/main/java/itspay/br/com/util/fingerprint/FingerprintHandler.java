package itspay.br.com.util.fingerprint;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import itspay.br.com.activity.LoginActivity;
import itspay.br.com.util.usersharepreferences.SharedPreferenceUtil;

/**
 * Created by juniorbraga on 03/03/17.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;

    ObserverResultFingerPrintInterface mCallback;

    private Context appContext;
    private String mCpf;
    private String mPassword;
    private LoginActivity mLoginActivity;

    public FingerprintHandler(LoginActivity loginActivity, Context context) {
        appContext = context;
        mLoginActivity = loginActivity;
        mCpf = SharedPreferenceUtil.getStringPreference(context, "lastCPFLogged");
        mPassword = SharedPreferenceUtil.getStringPreference(context, "lastPasswordLogged");
    }


    public FingerprintHandler(Context context,ObserverResultFingerPrintInterface observerResultFingerPrintInterface) {
        appContext = context;
        mCallback = (ObserverResultFingerPrintInterface) observerResultFingerPrintInterface;
    }

    public void startAuth(FingerprintManager manager,
                          FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

//        mLoginActivity.showProgress(true);
    }

    @Override
    public void onAuthenticationError(int errMsgId,
                                      CharSequence errString) {
        Toast.makeText(appContext,
                "Authentication error\n" + errString,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId,
                                     CharSequence helpString) {
        Toast.makeText(appContext,
                "Authentication help\n" + helpString,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(appContext,
                "Authentication failed.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {
        mCallback.showResultFingerPrint(true);

    }

}
