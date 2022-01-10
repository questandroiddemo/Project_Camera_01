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

    /**
     * variable to store object of IServiceCameraInterface.
     */
    private IServiceCameraInterface mServiceCameraInterface;
    /**
     * variable to store object of ICameraPresenter.
     */
    private ICameraPresenter mCameraPresenter;
    /**
     * variable to store object of CameraServiceInterface.
     */
    private CameraServiceInterface mCameraServiceInterface;
    /**
     * variable to store object of IBaseAidlInterface.
     */
    private IBaseAidlInterface mBaseAidlInterface;
    /**
     * variable to store object of ICameraListener.
     */
    private ICameraListener mCameraListener = new ICameraListener.Stub() {
        @Override
        public void notifyCameraStatus(boolean status) throws RemoteException {
            Log.d(TAG, "Inside notifyCameraStatus:" + status);
            mCameraPresenter.notifyCameraStatus(status);
        }
    };

    /**
     * variable to store object of ServiceConnection.
     */

    private CameraServiceInterface.IServiceConnectionCallback mConnectionCallBack = new CameraServiceInterface.IServiceConnectionCallback() {

        /**
         * @brief Call back method onServiceConnected, for bind service call
         * @param name : component name
         * @param service : service
         */
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

        /**
         * @brief Call back method onServiceDisconnected
         * @param name : component name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCameraPresenter.updateBindStatus(BIND_FAIL);

        }


    };

    /**
     * @brief Constructor of CameraRvcModelImpl
     */
    public CameraModel(CameraPresenter cameraPresenter) {

        mCameraPresenter = cameraPresenter;
    }

    /**
     * @brief Method to initialize this class.
     * @param context : context
     */
    @Override
    public void initialize(Context context) {

        mCameraServiceInterface = new CameraServiceInterface();
        mCameraServiceInterface.initialize(context);
        mCameraServiceInterface.setServiceConnectionCallback(mConnectionCallBack);
        mCameraServiceInterface.bindServiceApp();

    }


    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */
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
    /**
     * @brief Method to start camera.
     */
    @Override
    public void startCamera() {
        try {
            mServiceCameraInterface.startCamera();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
