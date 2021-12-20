package com.example.project_camera_01.model;

import static com.example.project_camera_01.common.CameraConstants.TAG;

import android.os.RemoteException;
import android.util.Log;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.CameraSettingPresenter;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;
import com.example.project_camera_01.view.CameraFragment;

public class CameraSettingModel implements ICameraSettingModel{

    private IServiceCameraInterface mServiceCameraInterface;
    private ICameraSettingPresenter mCameraSettingPresenter;
    private CameraServiceInterface mCameraServiceInterface;
    private CameraFragment mCameraFragment;


    public CameraSettingModel(CameraSettingPresenter cameraSettingPresenter) {

        mCameraSettingPresenter = cameraSettingPresenter;
    }

    @Override
    public String getSettingName(String name) {
        String mName = null;
        try {
            mName = mServiceCameraInterface.getSetting(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return mName;
    }


}

