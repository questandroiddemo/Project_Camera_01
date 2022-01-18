package com.example.project_camera_01.presenter;

import android.content.Context;
import android.util.Log;


import com.example.project_camera_01.model.MainModel;
import com.example.project_camera_01.model.IMainModel;
import com.example.project_camera_01.view.CameraFragment;
import com.example.project_camera_01.view.ICameraView;
import com.example.project_camera_01.view.IMainView;

public class MainPresenter implements IMainPresenter {

    /**
     * variable to store object of ICameraView.
     */
    private IMainView mCameraView;
    /**
     * variable to store object of ICameraModel.
     */
    private IMainModel mCameraModel;
    /**
     * @brief Constructor of CameraPresenter
     */
    ICameraView iCameraView;

    public MainPresenter(IMainView mCameraView) {
        this.mCameraView = mCameraView;
        mCameraModel=new MainModel(this);
    }

    /**
     * @brief Method to initialize this class.
     * @param context : context
     */
    @Override
    public void initialize(Context context) {
        mCameraModel.initialize(context);

    }

    /**
     * @brief Method for get bind status.
     * @param bindStatus : bind status
     */
    @Override
    public void updateBindStatus(int bindStatus) {
        mCameraView.updateBindStatus(bindStatus);
        Log.d("CameraService","OnStart");


    }
    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */

    @Override
    public String getPreviousActiveCamera() {
        return mCameraModel.getPreviousActiveCamera();
    }

//    /**
//     * @brief Method to notify camera status.
//     * @param status : status of camera.
//     */
//    @Override
//    public void notifyCameraStatus(boolean status) {
//        mCameraView.notifyCameraStatus(status);
//    }
//
//    /**
//     * @brief Method to start camera
//     */
//    @Override
//    public void startCamera() {
//        mCameraModel.startCamera();
//    }


}
