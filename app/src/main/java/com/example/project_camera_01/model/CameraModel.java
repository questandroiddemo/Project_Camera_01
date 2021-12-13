package com.example.project_camera_01.model;

import android.content.ComponentName;
import android.content.Context;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.CameraPresenter;
import com.example.project_camera_01.presenter.ICameraPresenter;
import static com.example.project_camera_01.common.CameraConstants.BIND_FAIL;
import static com.example.project_camera_01.common.CameraConstants.BIND_SUCCESS;
import static com.example.project_camera_01.common.CameraConstants.TAG;

public class CameraModel implements ICameraModel {
    private IServiceCameraInterface mServiceCameraInterface;

    private ICameraPresenter mCameraPresenter;

    private CameraServiceInterface mCameraServiceInterface;


    private CameraServiceInterface.IServiceConnectionCallback mConnectionCallBack = new CameraServiceInterface.IServiceConnectionCallback() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mServiceCameraInterface = mCameraServiceInterface.getCameraInterface();
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
        Log.d(TAG,"getPreviousActiveCamera:"+camera);    return camera;
    }
}
