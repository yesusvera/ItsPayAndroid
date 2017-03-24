package itspay.br.com.util.fingerprint;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by juniorbraga on 17/03/17.
 */

public class CustomFingerPrint {
    //    Fingher Print
    private KeyGenerator keyGenerator;
    private static final String KEY_NAME = "example_key";
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    ObserverResultFingerPrintInterface mCallBack;

    Context mContext;

    public CustomFingerPrint(Context context, FingerprintManager mFingerprintManager, KeyguardManager mKeyguardManager, ObserverResultFingerPrintInterface observerResultFingerPrintInterface) {
        mContext = context;
        fingerprintManager = mFingerprintManager;
        keyguardManager = mKeyguardManager;
        mCallBack = (ObserverResultFingerPrintInterface) observerResultFingerPrintInterface;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isFingerPrint() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (fingerprintManager.isHardwareDetected()) {
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void show() {

        generateKey();

        if (cipherInit()) {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(mContext, mCallBack);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkedRegisterFingerPrint() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
}
