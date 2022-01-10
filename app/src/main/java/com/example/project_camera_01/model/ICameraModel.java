package com.example.project_camera_01.model;

import android.content.Context;

import java.util.HashMap;

public interface ICameraModel  {
    /**
     * @brief Method to initialize Context
     * @param context : context
     */
    void initialize(Context context);

    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */

    String getPreviousActiveCamera();

    /**
     * @brief Method to start camera
     */

    void startCamera();

}
