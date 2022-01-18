package com.example.project_camera_01.presenter;

import com.example.project_camera_01.model.CameraModel;
import com.example.project_camera_01.model.ICameraModel;
import com.example.project_camera_01.view.CameraFragment;
import com.example.project_camera_01.view.ICameraView;

public class CameraPresenter implements ICameraPresenter{

   ICameraView mCameraView;

   ICameraModel mCameraModel;

    public CameraPresenter(ICameraView cameraView) {
        mCameraView = cameraView;
        mCameraModel = new CameraModel(this);
    }

    @Override
    public void startCamera(String camId) {
      mCameraModel.startCamera(camId);
    }
}
