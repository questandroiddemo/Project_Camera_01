package com.example.project_camera_01.presenter;

import com.example.project_camera_01.model.CameraSettingModel;
import com.example.project_camera_01.model.ICameraModel;
import com.example.project_camera_01.model.ICameraSettingModel;
import com.example.project_camera_01.view.CameraFragment;
import com.example.project_camera_01.view.ICameraSetting;
import com.example.project_camera_01.view.ICameraView;

public class CameraSettingPresenter implements ICameraSettingPresenter{

    private ICameraSetting mCameraSettingInterface;
    private ICameraSettingModel mCameraSettingModel;
    private ICameraView mCameraView;
    private CameraFragment cameraFragment;


    public CameraSettingPresenter(ICameraSetting cameraSettingInterface) {
            this.mCameraSettingInterface = cameraSettingInterface;
            mCameraSettingModel = new CameraSettingModel(this);

    }

    @Override
    public void setSetting(boolean status) {
        mCameraSettingModel.setSetting(status);
    }

    @Override
    public boolean getSettings(int status) {
        return mCameraSettingModel.getSettings(status);
    }

//    @Override
//    public void notifyCameraStatus(boolean status) {
//
//        mCameraView.notifyCameraStatus(status);
//    }
//
//    @Override
//    public void startCamera() {
//           mCameraSettingModel.startCamera();
//    }


//    @Override
//    public void getSetting(String name, Boolean check) {
//
//       String mName =  mCameraSettingModel.getSettingName(name);
//       if (mName != null){
//            cameraFragment.getValue(mName);
//       }
//    }
}
