package com.example.project_camera_01.view;

import java.util.HashMap;

public interface ICameraSettingView {


//    /**
//     * @brief Method to set the value of setting.
//     * @param status : status of setting.
//     */
//     void setSetting(boolean status);
      void notifyCameraSetting(String setId,boolean status);
}
