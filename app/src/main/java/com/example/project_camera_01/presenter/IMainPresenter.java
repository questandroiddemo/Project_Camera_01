package com.example.project_camera_01.presenter;

import android.content.Context;

public interface IMainPresenter {

    /**
     * @brief Method to initialize Context
     * @param context : context
     */
    void initialize(Context context);
    /**
     * @brief Method for get bind status.
     * @param bindStatus : bind status
     */
    void updateBindStatus(int bindStatus);
}
