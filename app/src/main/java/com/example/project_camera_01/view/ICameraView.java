package com.example.project_camera_01.view;

public interface ICameraView {
    void updateBindStatus(int bindStatus);

//    void getSetting(String name);
//    void setSetting(int checkStatus);

    void notifyCameraStatus(boolean status);

}
