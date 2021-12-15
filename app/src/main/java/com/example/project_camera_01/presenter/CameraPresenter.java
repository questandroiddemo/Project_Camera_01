package com.example.project_camera_01.presenter;

import android.content.Context;
import android.util.Log;


import com.example.project_camera_01.model.CameraModel;
import com.example.project_camera_01.model.ICameraModel;
import com.example.project_camera_01.view.ICameraView;

public class CameraPresenter implements ICameraPresenter{
    private ICameraView mCameraView;

    private ICameraModel mCameraModel;

    public CameraPresenter(ICameraView mCameraView) {
        this.mCameraView = mCameraView;
        mCameraModel=new CameraModel(this);
    }

    @Override
    public void initialize(Context context) {
        mCameraModel.initialize(context);

    }

    @Override
    public void updateBindStatus(int bindStatus) {
        mCameraView.updateBindStatus(bindStatus);
        Log.d("CameraService","OnStart");


    }

    @Override
    public String getPreviousActiveCamera() {
        return mCameraModel.getPreviousActiveCamera();
    }
}
