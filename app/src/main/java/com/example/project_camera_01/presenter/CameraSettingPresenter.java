package com.example.project_camera_01.presenter;

import com.example.project_camera_01.model.CameraSettingModel;
import com.example.project_camera_01.model.ICameraModel;
import com.example.project_camera_01.model.ICameraSettingModel;
import com.example.project_camera_01.view.CameraFragment;
import com.example.project_camera_01.view.ICameraSetting;
import com.example.project_camera_01.view.ICameraView;

public class CameraSettingPresenter implements ICameraSettingPresenter{

    /**
     * variable to store object of ICameraSetting.
     */
    private ICameraSetting mCameraSettingInterface;
    /**
     * variable to store object of ICameraSettingModel.
     */
    private ICameraSettingModel mCameraSettingModel;

    /**
     * @brief Constructor of CameraSettingPresenter.
     */
    public CameraSettingPresenter(ICameraSetting cameraSettingInterface) {
            this.mCameraSettingInterface = cameraSettingInterface;
            mCameraSettingModel = new CameraSettingModel(this);

    }
    /**
     * @brief Method to set the value of setting.
     * @param status : status of setting.
     */

    @Override
    public void setSetting(Boolean status) {
       mCameraSettingModel.setSetting(status);
    }

    /**
     * @brief Method to get the value of setting.
     *
     */
    @Override
    public boolean getSettings(int status) {
        return mCameraSettingModel.getSettings(status);
    }

}
