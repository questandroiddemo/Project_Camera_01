package com.example.project_camera_01.model;

import android.os.RemoteException;
import android.util.Log;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IBaseAidlInterface;
import com.example.cameraserviceinterface.ICameraListener;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.CameraSettingPresenter;
import com.example.project_camera_01.presenter.IMainPresenter;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;

import java.util.HashMap;

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
    private IMainPresenter mCameraPresenter;

   private ConnectUtil mConnectUtil = new ConnectUtil();

    /**
     * @brief Constructor of CameraSettingModel
     */
    public CameraSettingModel(CameraSettingPresenter cameraSettingPresenter) {

        mCameraSettingPresenter = cameraSettingPresenter;
        mBaseAidlInterface = ConnectUtil.getmBaseAidlInterface();
        mServiceCameraInterface = ConnectUtil.getmServiceCameraInterface();
        try {
            mServiceCameraInterface.registerAsyncConnection(mCameraListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d("HMI1","mBaseAidl"+mBaseAidlInterface);
        Log.d("HMI2", "mServiceInterface"+mServiceCameraInterface);
    }

    private ICameraListener mCameraListener = new ICameraListener.Stub() {
        @Override
        public void notifyCameraSetting(String setId, boolean status) throws RemoteException {
            Log.d("CameraSetting", "Inside notifyCameraStatus:" + status);

            mCameraSettingPresenter.notifyCameraSetting(setId,status);

        }

    };
    /**
     * @brief Method to set the value of setting.
     * @param status : status of the setting
     * @return
     */
    @Override
    public void setSetting(String setId, boolean status) {
        try {
            mServiceCameraInterface.setSetting(setId,status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Method to get the value of settings.
     * @return setStatus : status
     */
    @Override
    public HashMap<String, Boolean> getSettings() {
        HashMap<String,Boolean> hashMap = new HashMap<>();
        try {
            hashMap = (HashMap<String, Boolean>) mServiceCameraInterface.getSettings();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}

