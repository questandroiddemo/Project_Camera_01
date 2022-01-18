package com.example.project_camera_01.model;

import android.os.RemoteException;
import android.util.Log;

import com.example.cameraserviceinterface.IBaseAidlInterface;
import com.example.cameraserviceinterface.IServiceCameraInterface;

public final class ConnectUtil {

   static IBaseAidlInterface mBaseAidlInterface;
   static IServiceCameraInterface mServiceCameraInterface;

    public static IBaseAidlInterface getmBaseAidlInterface() {
        Log.d("SKZ1","connect interface1"+mBaseAidlInterface);

        return mBaseAidlInterface;
    }

    public static void setmBaseAidlInterface(IBaseAidlInterface sBaseAidlInterface) {
        mBaseAidlInterface = sBaseAidlInterface;
        Log.d("SKZ2","setmBaseAidlInterface"+mBaseAidlInterface);
    }

    public static IServiceCameraInterface getmServiceCameraInterface() {

        Log.d("SKZ3","IServiceCameraInterface"+mServiceCameraInterface);
        return mServiceCameraInterface;
    }

    public static void setmServiceCameraInterface(IServiceCameraInterface sServiceCameraInterface) {


        mServiceCameraInterface = sServiceCameraInterface;
        Log.d("SKZ4","setmServiceCameraInterface"+mServiceCameraInterface);
    }
}
