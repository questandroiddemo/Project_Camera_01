package com.example.project_camera_01.presenter;

import android.content.Context;

public interface ICameraPresenter {
    void initialize(Context context);
    /**
     * @brief Method for get bind status.
     * @param bindStatus : bind status
     */
    void updateBindStatus(int bindStatus);
    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */

//    void setSetting(int checkStatus);
    String getPreviousActiveCamera();
//    void getSetting(String title);
void notifyCameraStatus(boolean status);
    void startCamera();


}
