package com.example.project_camera_01.presenter;

import android.util.Log;

import com.example.project_camera_01.model.CameraSettingModel;
import com.example.project_camera_01.model.ICameraModel;
import com.example.project_camera_01.model.ICameraSettingModel;
import com.example.project_camera_01.view.CameraFragment;
import com.example.project_camera_01.view.CameraSettingInterface;
import com.example.project_camera_01.view.CameraSettingsFragment;
import com.example.project_camera_01.view.ICameraView;

public class CameraSettingPresenter implements ICameraSettingPresenter{

    private CameraSettingInterface mCameraSettingInterface;
    private ICameraSettingModel mCameraSettingModel;
    ICameraModel mCameraModel;
    private ICameraView mCameraView;
    private CameraFragment cameraFragment;


    public CameraSettingPresenter(CameraSettingInterface cameraSettingInterface) {
            this.mCameraSettingInterface = cameraSettingInterface;
            mCameraSettingModel = new CameraSettingModel(this);

    }


    @Override
    public void getSetting(String name, Boolean check) {

       String mName =  mCameraSettingModel.getSettingName(name);
       if (mName != null){
            cameraFragment.getValue(mName);
       }
    }
}
