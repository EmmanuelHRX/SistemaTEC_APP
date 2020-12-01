package com.example.sistematec.ui.login.BackgroundTask;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.ui.login.DatabaseConection.AcademyNotificationsList;
import com.example.sistematec.ui.login.DatabaseConection.CoordNotificationsList;
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentNotificationsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundTaks extends IntentService {

    int count;
    boolean lock;

    public BackgroundTaks() {

        super("Notify");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while(true){
            try {
                if (Data.getLoggedUser() == 0) {
                    count = 0;
                }
                System.out.println("In cicle");
                switch (Data.getLoggedUser()) {
                    case 1: {
                        if (lock) {
                            break;
                        }
                        lock = true;
                        getStudentNotificationsCount();
                        break;
                    }
                    case 2: {
                        if (lock) {
                            break;
                        }
                        lock = true;
                        getCoordNotificationsCount();
                        break;
                    }
                    case 3: {
                        if (lock) {
                            break;
                        }
                        lock = true;
                        getAcademyNotificationsCount();
                    }
                    default: {

                    }
                }
                Thread.sleep(10000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void getStudentNotificationsCount() {
        Call<List<StudentNotificationsList>> call = RetrofitClient.getInstance().getApi()
                .getStudentNotifications(Data.getStudentId());
        call.enqueue(new Callback<List<StudentNotificationsList>>() {
            @Override
            public void onResponse(Call<List<StudentNotificationsList>> call, Response<List<StudentNotificationsList>> response) {
                if (response.body() != null) {
                    count = response.body().size();
                    setStudentNotification();
                } else {
                    System.out.println("NO INFO");
                    count = 0;
                    lock = false;
                }
            }

            @Override
            public void onFailure(Call<List<StudentNotificationsList>> call, Throwable t) {
                System.out.println("FALLA - Student Notifs" );
                count = 0;
                lock = false;
            }
        });

    }

    private void setStudentNotification() {
        if(count > 0 && Data.getLoggedUser() == 1){
            Intent broadcast = new Intent("broadcast");
            broadcast.putExtra("count", count);
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcast);
            setCheckedStudentNotifications();
            count = 0;
        }
    }

    private void getCoordNotificationsCount() {

        Call<List<CoordNotificationsList>> call = RetrofitClient.getInstance().getApi()
                .getCoordNotifications(Data.getCoordAcDepId());
        call.enqueue(new Callback<List<CoordNotificationsList>>() {
            @Override
            public void onResponse(Call<List<CoordNotificationsList>> call, Response<List<CoordNotificationsList>> response) {
                if (response.body() != null) {

                    count = response.body().size();
                    setCoordNotification();
                } else {
                    System.out.println("NO INFO");
                    count = 0;
                    lock = false;
                }
            }

            @Override
            public void onFailure(Call<List<CoordNotificationsList>> call, Throwable t) {
                System.out.println("FALLA - Coord notifs" );
                count = 0;
                lock = false;
            }
        });

    }

    private void setCoordNotification() {
        if(count > 0 && Data.getLoggedUser() == 2){
            Intent broadcast = new Intent("broadcast");
            broadcast.putExtra("count", count);
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcast);
            System.out.println("FINAL LOOP");
            setCheckedCoordNotifications();
            count = 0;
        }
    }

    private void getAcademyNotificationsCount() {

        Call<List<AcademyNotificationsList>> call = RetrofitClient.getInstance().getApi()
                .getAcademyNotifications(Data.getCoordAcDepId());
        call.enqueue(new Callback<List<AcademyNotificationsList>>() {
            @Override
            public void onResponse(Call<List<AcademyNotificationsList>> call, Response<List<AcademyNotificationsList>> response) {
                if (response.body() != null) {

                    count = response.body().size();
                    setAcademyNotification();
                } else {
                    System.out.println("NO INFO");
                    count = 0;
                    lock = false;
                }
            }

            @Override
            public void onFailure(Call<List<AcademyNotificationsList>> call, Throwable t) {
                System.out.println("FALLA - Academy notifs" );
                count = 0;
                lock = false;
            }
        });

    }

    private void setAcademyNotification() {
        if(count > 0 && Data.getLoggedUser() == 3){
            Intent broadcast = new Intent("broadcast");
            broadcast.putExtra("count", count);
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcast);
            System.out.println("FINAL LOOP");
            setCheckedAcademyNotifications();
            count = 0;
        }
    }

    private void setCheckedStudentNotifications() {
        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().setCheckedStudentNotifications(Data.getStudentId());
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                System.out.println(response.body().getRemarks());
                lock = false;
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                System.out.println("FALLA CHECKED STUDENT NOTIFS");

                t.printStackTrace();
                lock = false;
            }
        });
    }

    private void setCheckedCoordNotifications() {
        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().setCheckedCoordNotifications(Data.getCoordAcDepId());
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                System.out.println(response.body().getRemarks());
                lock = false;
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                System.out.println("FALLA CHECKED STUDENT NOTIFS");

                t.printStackTrace();
                lock = false;
            }
        });
    }

    private void setCheckedAcademyNotifications() {
        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().setCheckedAcademyNotifications(Data.getCoordAcDepId());
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                System.out.println(response.body().getRemarks());
                lock = false;
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                System.out.println("FALLA CHECKED STUDENT NOTIFS");

                t.printStackTrace();
                lock = false;
            }
        });
    }


}
