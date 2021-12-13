package com.example.cameraserviceinterface;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CameraServiceInterface {

    private Context mContext;

    private Intent mIntent;

    private static final String SERVICE_PKG_NAME = "com.example.myservicedemo";

    private static final String SERVICE_ACTION = "com.example.myservicedemo.start";

    private IServiceCameraInterface mServiceCameraInterface;

    private IBaseAidlInterface mBaseAidlInterface;

    private IServiceConnectionCallback mServiceConnectionCallback;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBaseAidlInterface = IBaseAidlInterface.Stub.asInterface(service);
            if (mBaseAidlInterface != null)
            {
                try {
                    mServiceCameraInterface = mBaseAidlInterface.getSyncConnection();
                } catch (RemoteException e) {
                    Log.e("onServiceConnected", e.toString());
                }
            }
            mServiceConnectionCallback.onServiceConnected(name,service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceConnectionCallback.onServiceDisconnected(name);
        }
    };

    public void initialize(Context context) {
        mContext = context;
        mIntent = new Intent();
        mIntent.setPackage(SERVICE_PKG_NAME);
        mIntent.setAction(SERVICE_ACTION);
    }

    public void startService() {
        mContext.startService(mIntent);
    }

    public void bindServiceApp() {
        mContext.bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindRvcAppService() {
        mContext.unbindService(mServiceConnection);
    }

    public void setServiceConnectionCallback(
            IServiceConnectionCallback serviceConnectionCallback) {
        mServiceConnectionCallback = serviceConnectionCallback;
    }

    public IServiceCameraInterface getCameraInterface() {
        return mServiceCameraInterface;
    }

    public interface IServiceConnectionCallback{
        void onServiceConnected(ComponentName name, IBinder service);

        void onServiceDisconnected(ComponentName name);
    }
}
