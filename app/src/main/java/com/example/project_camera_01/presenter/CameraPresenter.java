package com.example.project_camera_01.presenter;

import com.example.project_camera_01.model.CameraModel;
import com.example.project_camera_01.model.ICameraModel;
import com.example.project_camera_01.view.ICameraView;

public class CameraPresenter implements ICameraPresenter{
    /**
     * variable to store object of ICameraView.
     */
   ICameraView mCameraView;
    /**
     * variable to store object of ICameraModel.
     */
   ICameraModel mCameraModel;
    /**
     * @brief Constructor of CameraPresenter.
     */
    public CameraPresenter(ICameraView cameraView) {
        this.mCameraView = cameraView;
        mCameraModel = new CameraModel(this);
    }
    /**
     * @brief Method to set the last active camera.
     * @param camId : camera ID
     */
    @Override
    public void setCamera(String camId) {
        mCameraModel.setCamera(camId);
    }
    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */
    @Override
    public String getCamera() {
        return mCameraModel.getCamera();
    }
}
