package com.example.project_camera_01.model;

import static com.example.project_camera_01.common.CameraConstants.TAG;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import static com.example.project_camera_01.common.CameraConstants.BIND_FAIL;
import static com.example.project_camera_01.common.CameraConstants.BIND_SUCCESS;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IBaseAidlInterface;
import com.example.cameraserviceinterface.ICameraListener;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.CameraSettingPresenter;
import com.example.project_camera_01.presenter.ICameraPresenter;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;
import com.example.project_camera_01.view.CameraFragment;

public class CameraSettingModel implements ICameraSettingModel{

    private IServiceCameraInterface mServiceCameraInterface;
    private ICameraSettingPresenter mCameraSettingPresenter;
    private CameraServiceInterface mCameraServiceInterface;
    private CameraFragment mCameraFragment;
    private IBaseAidlInterface mBaseAidlInterface;
    private ICameraPresenter mCameraPresenter;

    public CameraSettingModel(CameraSettingPresenter cameraSettingPresenter) {

        mCameraSettingPresenter = cameraSettingPresenter;
    }

    private ICameraListener mCameraListener = new ICameraListener.Stub() {

        @Override
        public void notifyCameraStatus(boolean status) throws RemoteException {
            Log.d(TAG, "Inside notifyCameraStatus:" + status);
            mCameraPresenter.notifyCameraStatus(status);
        }

    };


    private CameraServiceInterface.IServiceConnectionCallback mConnectionCallBack = new CameraServiceInterface.IServiceConnectionCallback() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mBaseAidlInterface = IBaseAidlInterface.Stub.asInterface(service);
            mServiceCameraInterface = mCameraServiceInterface.getCameraInterface();
            if (mBaseAidlInterface != null) {
                try {
                    mBaseAidlInterface.registerAsyncConnection(mCameraListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            mCameraPresenter.updateBindStatus(BIND_SUCCESS);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCameraPresenter.updateBindStatus(BIND_FAIL);
        }
    };


    @Override
    public void setSetting(boolean status) {

        try {
            mServiceCameraInterface.setSetting(status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getSettings(int status) {
        boolean c = false;
        try {
            mServiceCameraInterface.getSettings(status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return c;
    }


//    @Override
//    public void startCamera() {
//        try {
//            mServiceCameraInterface.startCamera();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
}

