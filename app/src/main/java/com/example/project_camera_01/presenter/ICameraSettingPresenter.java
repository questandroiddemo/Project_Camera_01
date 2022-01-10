package com.example.project_camera_01.presenter;

import java.util.HashMap;

public interface ICameraSettingPresenter {

    /**
     * @brief Method to set the value of setting.
     * @param status : status of setting.
     */
    void setSetting(Boolean status);
    /**
     * @brief Method to get the value of setting.
     *
     */
    boolean getSettings(int status);
}
