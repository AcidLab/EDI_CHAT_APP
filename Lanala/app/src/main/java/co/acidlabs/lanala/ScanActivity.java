package co.acidlabs.lanala;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.amirarcane.lockscreen.activity.EnterPinActivity;
import com.google.android.gms.vision.barcode.Barcode;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

import co.acidlabs.lanala.helpers.Utils;
import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);


    }


    @Override
    public void onScanned(Barcode barcode) {

        String result = barcode.displayValue;
        boolean valid = EmailValidator.getInstance(true).isValid(result);

        if (valid) {

            barcodeReader.playBeep();
            Utils.login = result;
            Intent intent = new Intent(ScanActivity.this, PasswordActivity.class);
            startActivity(intent);
            finish();

        }




    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }
}
