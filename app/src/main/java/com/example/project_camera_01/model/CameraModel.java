package com.example.project_camera_01.model;

import static com.example.project_camera_01.common.CameraConstants.BIND_FAIL;
import static com.example.project_camera_01.common.CameraConstants.BIND_SUCCESS;
import static com.example.project_camera_01.common.CameraConstants.TAG;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IBaseAidlInterface;
import com.example.cameraserviceinterface.ICameraListener;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.CameraPresenter;
import com.example.project_camera_01.presenter.ICameraPresenter;

import java.util.HashMap;

public class CameraModel implements ICameraModel {
    private IServiceCameraInterface mServiceCameraInterface;

    private ICameraPresenter mCameraPresenter;

    private CameraServiceInterface mCameraServiceInterface;

    private IBaseAidlInterface mBaseAidlInterface;
    private static int Cstatus = 1;

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

    public CameraModel(CameraPresenter cameraPresenter) {

        mCameraPresenter = cameraPresenter;
    }
    @Override
    public void initialize(Context context) {

        mCameraServiceInterface = new CameraServiceInterface();
        mCameraServiceInterface.initialize(context);
        mCameraServiceInterface.setServiceConnectionCallback(mConnectionCallBack);
        mCameraServiceInterface.bindServiceApp();

    }


    @Override
    public String getPreviousActiveCamera() {
        String camera = null;
        try {
            camera = mServiceCameraInterface.getPreviousActiveCamera();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"getPreviousActiveCamera:"+camera);
        return camera;
    }

    @Override
    public void startCamera() {
        try {
            mServiceCameraInterface.startCamera();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



//    @Override
//    public String getSetting(String title) {
//        String set = null;
//        try {
//            set = mServiceCameraInterface.getSetting(title);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        return set;
//    }


}
