package com.example.sistematec.ui.login.Login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.R;
import com.example.sistematec.ui.login.Service.ActivityService;
import com.example.sistematec.ui.login.Student.StudentActivity;
import com.example.sistematec.ui.login.academy.AcademyActivity;
import com.example.sistematec.ui.login.coordinator.CoordinatorActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    public String tipoUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

                //método para obtener tipo de usuario mediante la BD
                tipoUsuario = usernameEditText.getText().toString();
                switch(tipoUsuario){
                    case "Al":{
                        Intent actInicioUsuario = new Intent(getApplicationContext(), StudentActivity.class);
                        actInicioUsuario.putExtra("userEmail", tipoUsuario);
                        startActivity(actInicioUsuario);
                        finish();
                        break;

                    }
                    case "se":{
                        Intent actService = new Intent(getApplicationContext(), ActivityService.class);
                        startActivity(actService);
                        Toast.makeText(getApplicationContext(), "abriendo perfil service", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    }
                    case "Ac": {
                        Intent actAcademy = new Intent(getApplicationContext(), AcademyActivity.class);
                        startActivity(actAcademy);
                        Toast.makeText(getApplicationContext(), "abriendo perfil academia", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    }
                    case "Co":{
                        Intent actCoordinator = new Intent(getApplicationContext(), CoordinatorActivity.class);
                        startActivity(actCoordinator);
                        Toast.makeText(getApplicationContext(), "abriendo perfil coordinador", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    }
                    default:
                        Intent actInicioUsuario = new Intent(getApplicationContext(), StudentActivity.class);
                        startActivity(actInicioUsuario);
                        finish();
                        break;
                }
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        //Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

}
