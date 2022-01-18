package com.example.project_camera_01.presenter;

import java.util.HashMap;

public interface ICameraSettingPresenter {

    /**
     * @brief Method to set the value of setting.
     * @param status : status of setting.
     * @return
     */
    void setSetting(String setId,boolean status);
    /**
     * @brief Method to notify HMI.
     *
     */
    void notifyCameraSetting(String setId,boolean status);
    /**
     * @brief Method to get the value of setting.
     *
     */
    HashMap<String, Boolean> getSettings();
}
