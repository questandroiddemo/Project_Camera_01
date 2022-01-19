package com.example.project_camera_01.presenter;

import android.util.Log;

import com.example.project_camera_01.model.CameraSettingModel;
import com.example.project_camera_01.model.ICameraSettingModel;
import com.example.project_camera_01.view.ICameraSettingView;
import java.util.HashMap;

public class CameraSettingPresenter implements ICameraSettingPresenter{

    /**
     * variable to store object of ICameraSetting.
     */
    private ICameraSettingView mCameraSettingView;
    /**
     * variable to store object of ICameraSettingModel.
     */
    private ICameraSettingModel mCameraSettingModel;
    /**
     * @brief Constructor of CameraSettingPresenter.
     */
    public CameraSettingPresenter(ICameraSettingView cameraFragment) {
        this.mCameraSettingView = cameraFragment;
        mCameraSettingModel = new CameraSettingModel(this);
    }
    /**
     * @brief Method to set the value of setting.
     * @param status : status of setting.
     * @return
     */

    @Override
    public void setSetting(String setId,boolean status) {
       mCameraSettingModel.setSetting(setId,status);
        Log.d("BTS1","Value got?"+setId+" "+status);
    }
    /**
     * @brief Method to notify the hmi.
     *
     */
    @Override
    public void notifyCameraSetting(String setId, boolean status) {
        Log.d("CamSet","hello");
         mCameraSettingView.notifyCameraSetting(setId,status);
    }
    /**
     * @brief Method to get the value of setting.
     *
     */
    @Override
    public HashMap<String, Boolean> getSettings() {
        return mCameraSettingModel.getSettings();
    }

}
