package com.example.project_camera_01.model;

import android.content.Context;

import java.util.HashMap;

public interface ICameraModel  {
    void initialize(Context context);

    String getPreviousActiveCamera();

    void startCamera();




}
