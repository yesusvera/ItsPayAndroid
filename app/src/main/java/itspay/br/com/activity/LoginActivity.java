package itspay.br.com.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.fingerprint.FingerprintManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import itspay.br.com.controller.LoginController;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.FingerprintHandler;
import itspay.br.com.util.Utils;
import itspay.br.com.util.mask.MaskEditTextChangedListener;
import itspay.br.com.util.usersharepreferences.SharedPreferenceUtil;
import itspay.br.com.util.validations.ValidationsForms;

/**
 * Created by yesus on 12/12/16.
 */

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, LocationListener {


    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 312;
    // UI references.
    private EditText mCpfView;
    private EditText mPasswordView;
    private TextView txtViewCriaLogin;
    private View mProgressView;
    private View mLoginFormView;
    private LinearLayout mLlFingerPrint;
    private Switch mSwLoginFingerPrint;
    private Button mEmailSignInButton;
    private LinearLayout mLlInputLayoutPassword;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private double latitude, longitude;
    private String mCpf;

    public static final String SERIALIZED_PASSWORD = "serialized_password";
    public static final String IS_FINGER_PRINT_CHECKED = "is_finger_print_checked";
    public static final String IS_SECOND_LOGIN_FINGER_PRINT = "is_second_login_finger_print";


    //    Fingher Print
    boolean mIsFeatureEnabled = false;
    boolean mSecondLogin = false;
    private KeyGenerator keyGenerator;
    private static final String KEY_NAME = "example_key";
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    private AlertDialog mAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Layout
        initView();

        initListeners();


//      Last Cpf Logged
        mCpf = SharedPreferenceUtil.getStringPreference(this, "lastCPFLogged");

        if (mCpf != null && mCpf != "") {
            mCpfView.setText(mCpf);
            mPasswordView.requestFocus();
        }

        MaskEditTextChangedListener maskCPF = new MaskEditTextChangedListener("###.###.###-##", mCpfView);
        mCpfView.addTextChangedListener(maskCPF);
        Utils.nextInputOnMaxLength(this, mCpfView, mPasswordView, 14);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }

//        Regra de View FingerPrint
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);


        if (fingerprintManager.isHardwareDetected()) {
            mLlFingerPrint.setVisibility(View.VISIBLE);
        } else {
            mLlFingerPrint.setVisibility(View.GONE);

        }
    }

    /**
     * Listeners do Layout
     * */
    private void initListeners() {

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {

                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


//        Swipe FingerPrint

        mSwLoginFingerPrint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mSecondLogin = SharedPreferenceUtil.getBooleanPreference(getBaseContext(), IS_SECOND_LOGIN_FINGER_PRINT, false);
                if (isChecked) {
                    if (checkedRegisterFingerPrint()){
                        if (!mSecondLogin) {
                            showAlert("Informação","Para uso do fingerPint você deve acessar com sua senha uma vez.", "OK",false);
                        }
                        SharedPreferenceUtil.setBooleanPreference(getBaseContext(), IS_FINGER_PRINT_CHECKED, true);
                    }
                } else {
                    SharedPreferenceUtil.setBooleanPreference(getBaseContext(), IS_FINGER_PRINT_CHECKED, false);
                    mLlInputLayoutPassword.setVisibility(View.VISIBLE);
                }
            }
        });

//        Button Login

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferenceUtil.getBooleanPreference(getBaseContext(), IS_FINGER_PRINT_CHECKED, false) &&
                        SharedPreferenceUtil.getBooleanPreference(getBaseContext(), IS_SECOND_LOGIN_FINGER_PRINT, false)) {

                    loginFingerPrint();
                } else {
                    attemptLogin();
                }
            }
        });


        txtViewCriaLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastroLogin = new Intent(LoginActivity.this, CadastroLoginActivity.class);
                startActivity(cadastroLogin);
            }
        });

    }

    /*
    * Inflate Layout component
    * */
    public void initView() {
        // Set up the login form.
        mPasswordView = (EditText) findViewById(R.id.password);
        mCpfView = (EditText) findViewById(R.id.cpf);
        txtViewCriaLogin = (TextView) findViewById(R.id.txtViewCriaLogin);
        mEmailSignInButton = (Button) findViewById(R.id.login_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mLlFingerPrint = (LinearLayout) findViewById(R.id.ll_finger_print);
        mSwLoginFingerPrint = (Switch) findViewById(R.id.login_new_access_sw_finger_print);
        mLlInputLayoutPassword = (LinearLayout) findViewById(R.id.ll_input_layout_password);
    }

    @Override
    protected void onResume() {

        if (getIntent().getBooleanExtra("SAIR", false)) {
            finish();
        }

        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }

//        Regra de view FingerPrint
        mIsFeatureEnabled = SharedPreferenceUtil.getBooleanPreference(getBaseContext(), IS_FINGER_PRINT_CHECKED, false);
        mSecondLogin = SharedPreferenceUtil.getBooleanPreference(getBaseContext(), IS_SECOND_LOGIN_FINGER_PRINT, false);

        if (mIsFeatureEnabled && mSecondLogin) {
            mSwLoginFingerPrint.setChecked(true);
            mLlInputLayoutPassword.setVisibility(View.GONE);
        } else {
            mLlInputLayoutPassword.setVisibility(View.VISIBLE);
        }
    }

    //    Request Localizaçao
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    }
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mCpfView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String cpf = mCpfView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(cpf)) {
            mCpfView.setError(getString(R.string.error_field_required));
            focusView = mCpfView;
            cancel = true;
        } else if (!ValidationsForms.isCPF(cpf.replace(".", "").replace("-", ""))) {
            mCpfView.setError(getString(R.string.error_invalid_cpf));
            focusView = mCpfView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            CarrinhoSingleton.getInstance().esvaziarCarrinho();
            showProgress(true);
            new LoginController(this).login(mCpfView.getText().toString(), mPasswordView.getText().toString());
        }
    }


    /**    FingerPrint
     * Request Login use Finger Print
     * */
    public void loginFingerPrint() {
        // Reset errors.
        mCpfView.setError(null);

        // Store values at the time of the login attempt.
        String cpf = mCpfView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address.
        if (TextUtils.isEmpty(cpf)) {
            mCpfView.setError(getString(R.string.error_field_required));
            focusView = mCpfView;
            cancel = true;
        } else if (!ValidationsForms.isCPF(cpf.replace(".", "").replace("-", ""))) {
            mCpfView.setError(getString(R.string.error_invalid_cpf));
            focusView = mCpfView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

        }
        generateKey();
        showAlert("Informação","Posicione o dedo para acessar.", "Cancel",true);

        if (cipherInit()) {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this, this);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }

    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    public boolean checkedRegisterFingerPrint() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

            return false;
        }
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            showAlert("Alerta","Nenhuma digital regidstrada nesse dispositivo.", "OK",false);
            mSwLoginFingerPrint.setChecked(false);
            return false;
        }
        return true;
    }



    public void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    "Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAlert(String title,String menssage , String nameButon ,boolean isFingerPrint){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

//        dialogBuilder.setTitle(getString(R.string.app_name));
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(menssage);
        if (isFingerPrint) {
            dialogBuilder.setIcon(R.mipmap.ic_fingerprint_id);
        }
        dialogBuilder.setNegativeButton(nameButon, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });

        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 8;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public EditText getmCpfView() {
        return mCpfView;
    }

    public void setmCpfView(EditText mCpfView) {
        this.mCpfView = mCpfView;
    }

    public EditText getmPasswordView() {
        return mPasswordView;
    }

    public void setmPasswordView(EditText mPasswordView) {
        this.mPasswordView = mPasswordView;
    }
}

