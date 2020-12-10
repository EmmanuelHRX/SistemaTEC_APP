package com.example.sistematec.ui.login.Login;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
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

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.BackgroundTask.BackgroundTaks;
import com.example.sistematec.ui.login.Coordinator.ActivityCoordinator;
import com.example.sistematec.ui.login.DatabaseConection.CheckedCredentialsList;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.Service.ActivityService;
import com.example.sistematec.ui.login.Student.StudentActivity;
import com.example.sistematec.ui.login.academy.AcademyActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    public String userId;
    public String userPass;

    //Notification data
    private static PendingIntent pendingIntent;
    private static String CHANNEL_ID = "NOTIFICATION";
    private static int NOTIFICATION_ID = 0;
    private Intent intentService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        setIntentService();

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
                //finish();
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
                userId = usernameEditText.getText().toString();
                userPass = passwordEditText.getText().toString();
                getUser();
            }
        });

        checkLoggedUser(usernameEditText, passwordEditText, loginButton);

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        //Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void checkLoggedUser(EditText username, EditText password, Button login) {
        if (Data.getLoggedUser() != 0 && Data.getLoginId() != null && Data.getLoginPass() != null) {
            username.setText(Data.getLoginId());
            password.setText(Data.getLoginPass());

            loginViewModel.login(username.getText().toString(),
                    password.getText().toString());

            //método para obtener tipo de usuario mediante la BD
            userId = username.getText().toString();
            userPass = password.getText().toString();
            getUser();

        }
    }

    private void getUser() {
        Call<List<CheckedCredentialsList>> call = RetrofitClient.getInstance().getApi().getCheckedCredentials(userId, userPass);
        call.enqueue(new Callback<List<CheckedCredentialsList>>() {
            @Override
            public void onResponse(Call<List<CheckedCredentialsList>> call, Response<List<CheckedCredentialsList>> response) {
                if (response.body() != null) {
                    if (response.body().get(0).getOcupacion().equals("NO")) {
                        secondCheck();
                    } else {
                        System.out.println(response.body().get(0).getOcupacion());
                        String user = response.body().get(0).getOcupacion();
                        checkUser(user);
                    }
                } else {
                    secondCheck();
                }

            }

            @Override
            public void onFailure(Call<List<CheckedCredentialsList>> call, Throwable t) {
                secondCheck();
            }
        });
    }

    private void secondCheck() {
        Call<List<CheckedCredentialsList>> call = RetrofitClient.getInstance().getApi().getPersonalCheckedCredentials(userId, userPass);
        call.enqueue(new Callback<List<CheckedCredentialsList>>() {
            @Override
            public void onResponse(Call<List<CheckedCredentialsList>> call, Response<List<CheckedCredentialsList>> response) {
                if (response.body() != null) {
                    if (response.body().get(0).getOcupacion().equals("NO")) {
                        Toast.makeText(getApplicationContext(), "Datos inválidos 1", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println(response.body().get(0).getOcupacion());
                        String user = response.body().get(0).getOcupacion();
                        checkUser(user);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Datos inválidos 2", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<CheckedCredentialsList>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR: Datos inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkUser(String user) {
        switch(user) {
            case "Alumno":{
                Data.setStudentId(userId);
                Data.setLoggedUser(1);
                Data.setLoginId(userId);
                Data.setLoginPass(userPass);
                Intent actInicioUsuario = new Intent(getApplicationContext(), StudentActivity.class);
                actInicioUsuario.putExtra("userEmail", user);
                startActivity(actInicioUsuario);
                finish();
                break;

            }
            case "Servicios":{
                Intent actService = new Intent(getApplicationContext(), ActivityService.class);
                startActivity(actService);
                Toast.makeText(getApplicationContext(), "abriendo perfil service", Toast.LENGTH_LONG).show();
                finish();
                break;
            }
            case "Coordinacion":{
                //"21354612" - ednacontrasenia
                Data.setCoordAcId(userId);
                Data.setLoggedUser(2);
                Data.setLoginId(userId);
                Data.setLoginPass(userPass);
                Intent actCoordinator = new Intent(getApplicationContext(), ActivityCoordinator.class);
                startActivity(actCoordinator);
                Toast.makeText(getApplicationContext(), "abriendo perfil coordinador", Toast.LENGTH_LONG).show();
                finish();
                break;
            }
            case "Academia": {
                //"21354687" - pedrocontrasenia
                Data.setCoordAcId(userId);
                Data.setLoggedUser(3);
                Data.setLoginId(userId);
                Data.setLoginPass(userPass);
                Intent actAcademy = new Intent(getApplicationContext(), AcademyActivity.class);
                startActivity(actAcademy);
                Toast.makeText(getApplicationContext(), "abriendo perfil academia", Toast.LENGTH_LONG).show();
                finish();
                break;
            }
            default:
                Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //métodos de implementación de notificaciones
    private void setIntentService(){
        intentService = new Intent(getApplicationContext(), new BackgroundTaks().getClass());
        startService(intentService);
    }

    private void startService() {
        startService(intentService);
    }

    private void stopService() {
        stopService(intentService);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("REGRESA");
            createPendingIntent();
            createNotification(intent.getIntExtra("count", -1));
            //restartIntentService();
        }
    };

    private void createPendingIntent() {
        Intent intent = new Intent (this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this,0,intent,0);
    }

    private void createNotification(int count) {

        String plural1 = "ÓN";
        String plural2 = "";

        if (count > 1) {
            plural1 = "ONES";
            plural2 = "S";
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notification";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setContentTitle("SISTEMA TEC");
        builder.setContentText("TIENE " + count + " NOTIFICACI" + plural1 + " NUEVA" + plural2);
        builder.setColor(152370);
        builder.setSmallIcon(R.mipmap.logotecnm2017);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setLights(152370,1000,1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("broadcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

}
