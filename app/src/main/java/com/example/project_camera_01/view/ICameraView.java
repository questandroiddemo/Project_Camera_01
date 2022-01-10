package com.example.project_camera_01.view;

public interface ICameraView {

    /**
     * @brief Method for get bind status.
     * @param bindStatus : bind status
     */
    void updateBindStatus(int bindStatus);
    /**
     * @brief Method to notify camera status.
     * @param status : status of camera.
     */
    void notifyCameraStatus(boolean status);

}
