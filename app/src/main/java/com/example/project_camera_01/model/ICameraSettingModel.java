package com.example.project_camera_01.model;

import java.util.HashMap;

public interface ICameraSettingModel {


    /**
     * @brief Method to set the value of setting.
     * @param status : status of setting.
     */
   void setSetting(String setId,boolean status);

    /**
     * @brief Method to get the value of setting.
     *
     * @return hashMap.
     */
    HashMap<String,Boolean> getSettings();
}
