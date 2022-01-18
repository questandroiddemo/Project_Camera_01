package com.example.project_camera_01.model;

import android.os.RemoteException;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IBaseAidlInterface;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.ICameraPresenter;

public class CameraModel implements ICameraModel {


    private ICameraPresenter mCameraPresenter;

    private CameraServiceInterface mCameraServiceInterface;
    private IBaseAidlInterface mBaseAidlInterface;
    private IServiceCameraInterface mServiceCameraInterface;
    public CameraModel(ICameraPresenter cameraPresenter) {

        mCameraPresenter = cameraPresenter;
        mBaseAidlInterface = ConnectUtil.getmBaseAidlInterface();
        mServiceCameraInterface = ConnectUtil.getmServiceCameraInterface();
    }


    @Override
    public void startCamera(String camId) {
        try {
            mServiceCameraInterface.startCamera(camId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
