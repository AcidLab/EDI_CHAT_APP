package co.acidlabs.lanala;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.alimuzaffar.lib.pin.PinEntryEditText;

import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.User;
import co.acidlabs.lanala.networking.NetworkingAsyncResponse;
import co.acidlabs.lanala.networking.NetworkingHelper;
import info.androidhive.barcode.BarcodeReader;

public class PasswordActivity extends AppCompatActivity implements NetworkingAsyncResponse {

    private Button login;
    private PinEntryEditText pinCode;
    private String password;
    private RelativeLayout loaderView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        login = findViewById(R.id.login);
        pinCode = findViewById(R.id.txt_pin_entry2);
        loaderView = findViewById(R.id.loaderView);
        login.setVisibility(View.GONE);

        pinCode.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {

                if (str.length() < 3) {

                    login.setVisibility(View.GONE);
                }

                else {

                    login.setVisibility(View.VISIBLE);
                    password = str.toString();
                }

            }
        });

        pinCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() < 3) {

                    login.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onLoginClicked();

            }
        });
    }

    private void onLoginClicked () {

        loaderView.setVisibility(View.VISIBLE);
        NetworkingHelper api = new NetworkingHelper(PasswordActivity.this);
        api.login(Utils.login,password);

    }

    @Override
    public void onLoginSuccess (User user) {

        user.saveUser(getApplicationContext());
        loaderView.setVisibility(View.GONE);
        Intent mainIntent = new Intent(PasswordActivity.this,ContractActivity.class);
        startActivity(mainIntent);
        finishAffinity();


    }

    @Override
    public void onLoginError (String message) {

        loaderView.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }


}
