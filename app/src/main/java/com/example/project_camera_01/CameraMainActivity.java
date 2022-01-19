/**
 * @file CameraMainActivity.java
 * @brief CameraMainActivity Class act as the base activity of camera fragments
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */


package com.example.project_camera_01;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.project_camera_01.view.MainFragment;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new MainFragment())
                    .commit();
        }
    }
}