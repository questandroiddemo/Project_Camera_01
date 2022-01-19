package com.example.project_camera_01.view;

import java.util.HashMap;

public interface ICameraSettingView {

      /**
       *  @brief : function used to notify status to the HMI from service.
       *
       */
      void notifyCameraSetting(String setId,boolean status);
}
