/**
 * @file CameraFragment.java
 * @brief CameraFragment is the first fragment which displays camera preview
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */

package com.example.project_camera_01;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @brief Implementation for CameraFragment class.
 * CameraFragment is the first fragment of the application which extends Fragment.
 * CameraFragment is responsible for initial loading the camera preview based on condition.
 */


public class CameraFragment extends Fragment implements View.OnClickListener {
    Button rvc, ffc, cargo, aux;
    TextView mTitle, mHelptext;
    View v;
    private static final int REQUEST_CAMERA_PERMISSION_RESULT = 0;
    private TextureView mTextureView;
    private CameraDevice mCameraDevice;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private FrameLayout frameLayout;

    /**
     * @Brief Fragment lifecycle method onCreateView
     * @param inflater           :  Object of LayoutInflater
     * @param container          :  Object of ViewGroup
     * @param savedInstanceState :  Object of Bundle
     * @return
     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);



        mTitle = view.findViewById(R.id.title);
        mHelptext = view.findViewById(R.id.helptext);
        rvc = view.findViewById(R.id.rvc);
        ffc = view.findViewById(R.id.ffc);
        cargo = view.findViewById(R.id.cargo);
        aux = view.findViewById(R.id.aux);
        rvc.setOnClickListener(this);
        ffc.setOnClickListener(this);
        cargo.setOnClickListener(this);
        aux.setOnClickListener(this);
        mTextureView = (TextureView) view.findViewById(R.id.textureView);
        frameLayout = view.findViewById(R.id.frameLayout);


        return view;
    }

    /**
     * @brief Function used to set the button click
     * @param : v:View
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rvc:
                mTitle.setText("REAR VIEW CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                rvc.setBackgroundColor(getResources().getColor(R.color.primary1));
                ffc.setBackgroundColor(getResources().getColor(R.color.primary));
                cargo.setBackgroundColor(getResources().getColor(R.color.primary));
                aux.setBackgroundColor(getResources().getColor(R.color.primary));
                frameLayout.setBackground(null);

                break;
            case R.id.ffc:
                mTitle.setText("FORWARD FACING CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                rvc.setBackgroundColor(getResources().getColor(R.color.primary));
                ffc.setBackgroundColor(getResources().getColor(R.color.primary1));
                cargo.setBackgroundColor(getResources().getColor(R.color.primary));
                aux.setBackgroundColor(getResources().getColor(R.color.primary));
                frameLayout.setBackground(null);
                break;
            case R.id.cargo:
                mTitle.setText("CARGO CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                rvc.setBackgroundColor(getResources().getColor(R.color.primary));
                ffc.setBackgroundColor(getResources().getColor(R.color.primary));
                cargo.setBackgroundColor(getResources().getColor(R.color.primary2));
                aux.setBackgroundColor(getResources().getColor(R.color.primary));
                mTextureView.setOpaque(false);
                frameLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.aux:
                mTitle.setText("AUX CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                rvc.setBackgroundColor(getResources().getColor(R.color.primary));
                ffc.setBackgroundColor(getResources().getColor(R.color.primary));
                cargo.setBackgroundColor(getResources().getColor(R.color.primary));
                aux.setBackgroundColor(getResources().getColor(R.color.primary2));
                mTextureView.setOpaque(false);
                frameLayout.setBackgroundColor(Color.BLUE);
                break;


        }
    }

    /**
     *
     */

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
            setupCamera(i, i1);
            connectCamera();


        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

        }
    };
    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            startPreview();
            mTitle.setText("REAR VIEW CAMERA");
            mHelptext.setText("Check entire Surroundings.");
            Toast.makeText(getContext(), "Camera connected!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
            mCameraDevice = null;

        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            mCameraDevice = null;

        }
    };
    private static SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }

    private static class CompareSizeByArea implements Comparator<Size> {


        @Override
        public int compare(Size size, Size t1) {
            return Long.signum((long) t1.getWidth() * t1.getHeight() /
                    (long) t1.getWidth() * t1.getHeight());
        }
    }


    private static int sensorToDeviceRotation(CameraCharacteristics cameraCharacteristics, int deviceOrientation) {
        int sensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        deviceOrientation = ORIENTATIONS.get(deviceOrientation);
        return (sensorOrientation + deviceOrientation + 360) % 360;
    }

    @Override
    public void onPause() {
        super.onPause();
        closeCamera();

        stopBackgroundThread();

    }

    private void closeCamera() {
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    private HandlerThread mBackgroundHandlerThread;
    private Handler mBackgroundHandler;

    private String mCameraId;

    private Size mPreviewSize;

    @Override
    public void onResume() {


        super.onResume();
        startBackgroundThread();

        if (mTextureView.isAvailable()) {
            setupCamera(mTextureView.getWidth(), mTextureView.getHeight());
            connectCamera();
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.getActivity().onWindowFocusChanged(hasFocus);
        View decorView = getActivity().getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    private void setupCamera(int width, int height) {
        CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                if (cameraCharacteristics.get(cameraCharacteristics.LENS_FACING) ==
                        CameraCharacteristics.LENS_FACING_FRONT) {
                    continue;
                }
                StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                int deviceOrientation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
                int totalRotation = sensorToDeviceRotation(cameraCharacteristics, deviceOrientation);
                boolean swapRotation = totalRotation == 90 || totalRotation == 270;
                int rotatedWidth = width;
                int rotatedHeight = height;
                if (swapRotation) {
                    rotatedWidth = height;
                    rotatedHeight = width;
                }
                mPreviewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class), rotatedWidth, rotatedHeight);
                mCameraId = cameraId;
                return;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }



    }

    private Size chooseOptimalSize(Size[] choices, int width, int height) {
        List<Size> bigEnough = new ArrayList<Size>();
        for(Size option : choices){
            if(option.getHeight() == option.getWidth() * height/width &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        if(bigEnough.size() > 0){
            return Collections.min(bigEnough, new CompareSizeByArea());
        } else {
            return choices[0];
        }
    }

    private void connectCamera () {
        CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {
                    cameraManager.openCamera(mCameraId, mCameraDeviceStateCallback, mBackgroundHandler);
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        Toast.makeText(getActivity(), "This app requires access to camera", Toast.LENGTH_LONG).show();
                    }
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION_RESULT);
                }
            } else {cameraManager.openCamera(mCameraId, mCameraDeviceStateCallback, mBackgroundHandler);
            }
        }catch(CameraAccessException e){
            e.printStackTrace();
        }
    }
    private void startPreview() {
        SurfaceTexture surfaceTexture = mTextureView.getSurfaceTexture();
        surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        Surface previewSurface = new Surface(surfaceTexture);
        try {
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.addTarget(previewSurface);
            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface),
                    new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                            try {
                                cameraCaptureSession.setRepeatingRequest(mCaptureRequestBuilder.build(),
                                        null, mBackgroundHandler);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                            Toast.makeText(getActivity(), "Unable to connect to camera", Toast.LENGTH_LONG).show();
                        }

                        },null);
                    }catch (CameraAccessException e){
            e.printStackTrace();
        }



        }
    private void startBackgroundThread() {
        mBackgroundHandlerThread = new HandlerThread("PROJETDILEMMA");
        mBackgroundHandlerThread.start();
        mBackgroundHandler = new Handler(mBackgroundHandlerThread.getLooper());
    }
    private void stopBackgroundThread(){
        mBackgroundHandlerThread.quitSafely();
        try {
            mBackgroundHandlerThread.join();
            mBackgroundHandlerThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}







//    private final View.OnClickListener mListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.rvc:
//                    title.setText("REAR VIEW CAMERA");
//                    helptext.setText("Check entire Surrondings.");
//                    rvc.setBackgroundColor(getResources().getColor(R.color.primary1));
//
//                    break;
//                case R.id.ffc:
//                    title.setText("FORWARD FACING CAMERA");
//                    helptext.setText("Check entire Surrondings.");
//                    break;
//                case R.id.cargo:
//                    title.setText("CARGO CAMERA");
//                    helptext.setText("System Unavailbe");
//                    break;
//                case R.id.aux:
//                    title.setText("AUX CAMERA");
//                    helptext.setText("System Unavailble");
//                    break;
//            }
//        }
//
//    };
//
//
//}


