package com.example.project_camera_01.model;

import android.os.RemoteException;

import com.example.cameraserviceinterface.CameraServiceInterface;
import com.example.cameraserviceinterface.IBaseAidlInterface;
import com.example.cameraserviceinterface.IServiceCameraInterface;
import com.example.project_camera_01.presenter.ICameraPresenter;

public class CameraModel implements ICameraModel {

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
     * variable to store object of IServiceCameraInterface.
     */
    private IServiceCameraInterface mServiceCameraInterface;
    public CameraModel(ICameraPresenter cameraPresenter) {

        mCameraPresenter = cameraPresenter;
        mBaseAidlInterface = ConnectUtil.getmBaseAidlInterface();
        mServiceCameraInterface = ConnectUtil.getmServiceCameraInterface();
    }
    /**
     * @brief Method to set the last active camera.
     * @param camId : camera ID
     */

    @Override
    public void setCamera(String camId) {
        try {
            mServiceCameraInterface.setCamera(camId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */
    @Override
    public String getCamera() {
        String activeCamera = null;
        try {
            activeCamera = mServiceCameraInterface.getCamera();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return activeCamera;
    }
}
