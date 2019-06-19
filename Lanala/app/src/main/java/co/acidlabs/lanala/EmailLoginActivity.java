package co.acidlabs.lanala;



        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import co.acidlabs.lanala.models.User;
        import co.acidlabs.lanala.networking.NetworkingAsyncResponse;
        import co.acidlabs.lanala.networking.NetworkingHelper;


public class EmailLoginActivity extends AppCompatActivity implements NetworkingAsyncResponse {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;

    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        ButterKnife.bind(this);

        if (User.getCurrentUser(EmailLoginActivity.this) != null) {

            Intent mainIntent = new Intent(EmailLoginActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finishAffinity();

        }
        progressDialog = new ProgressDialog(EmailLoginActivity.this,
                R.style.AppTheme_Dark_Dialog);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            return;
        }

        _loginButton.setEnabled(false);


        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentification...");
        //progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        NetworkingHelper api = new NetworkingHelper(EmailLoginActivity.this);
        api.login(email,password);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Entrer un email valide");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 3 || password.length() > 10) {
            _passwordText.setError("Mot de passe entre 4 et 10 caractère");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onLoginSuccess(User user){

        _loginButton.setEnabled(true);
        progressDialog.dismiss();

        user.saveUser(getApplicationContext());
        Intent mainIntent = new Intent(EmailLoginActivity.this,ContractActivity.class);
        startActivity(mainIntent);
        finishAffinity();


    }

    @Override
    public void onLoginError(String message){

        _loginButton.setEnabled(true);
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }
}


