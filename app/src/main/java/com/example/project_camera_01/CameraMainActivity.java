/**
 * @file CameraMainActivity.java
 * @brief CameraMainActivity Class act as the base activity of camera fragments
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */


package com.example.project_camera_01;


import androidx.appcompat.app.AppCompatActivity;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;


/**
 * @brief Implementation for CameraMainActivity class.
 * CameraMainActivity is the entry point to the application which extends AppCompatActivity.
 * CameraMainActivity is responsible for initial loading of main fragment.
 */
public class CameraMainActivity extends AppCompatActivity {
    /**
     * @brief Android life cycle function
     * @param savedInstanceState : object of Bundle
     */
    IMyAidlInterface iMyAidlInterface;
    Boolean connected=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new MainFragment())
                    .commit();
        }
        Intent intent = new Intent("com.example.CameraService.AIDL");

        intent.setClassName("com.example.CameraService",
                "com.example.CameraService");
        if(getBaseContext().getApplicationContext().bindService(intent, serviceCon, Context.BIND_AUTO_CREATE)){
            connected=true;
            Toast.makeText(getApplicationContext(), "BindServiceSuccess", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(), "BindServiceFailed", Toast.LENGTH_SHORT).show();


    }





    private final ServiceConnection serviceCon=new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


}