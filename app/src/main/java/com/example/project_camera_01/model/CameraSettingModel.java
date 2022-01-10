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


    /**
     * variable to store object of IServiceCameraInterface.
     */
    private IServiceCameraInterface mServiceCameraInterface;

    /**
     * variable to store object of ICameraSettingPresenter.
     */
    private ICameraSettingPresenter mCameraSettingPresenter;

    /**
     * variable to store object of CameraServiceInterface.
     */
    private CameraServiceInterface mCameraServiceInterface;

    /**
     * variable to store object of IBaseAidlInterface.
     */
    private IBaseAidlInterface mBaseAidlInterface;

    /**
     * variable to store object of ICameraPresenter.
     */
    private ICameraPresenter mCameraPresenter;

    /**
     * @brief Constructor of CameraSettingModel
     */
    public CameraSettingModel(CameraSettingPresenter cameraSettingPresenter) {

        mCameraSettingPresenter = cameraSettingPresenter;
    }

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
     * @brief Method to set the value of setting.
     * @param status : status of the setting
     */

    @Override
    public void setSetting(boolean status) {

        try {
            mServiceCameraInterface.setSetting(status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getSettings() {
        boolean setStatus = false;
        try {
            mServiceCameraInterface.getSettings();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return setStatus;
    }


    /**
     * @brief Method to get previous active camera
     * @return setStatus : status
     */

}

