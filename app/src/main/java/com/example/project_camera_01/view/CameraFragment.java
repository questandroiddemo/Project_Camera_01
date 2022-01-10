/**
 * @file CameraFragment.java
 * @brief CameraFragment is the first fragment which displays camera preview
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */

package com.example.project_camera_01.view;

/**
 * @file CameraFragment.java
 * @brief CameraFragment is the first fragment which displays camera preview
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */



import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
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

import com.example.project_camera_01.CustomAdapter;
import com.example.project_camera_01.DataModel;
import com.example.project_camera_01.R;
import com.example.project_camera_01.presenter.ICameraPresenter;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;

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


public class CameraFragment extends Fragment implements View.OnClickListener{


    /**
     * variable to store the value of request code.
     */
    private static final int REQUEST_CAMERA_PERMISSION_RESULT = 0;

    /**
     * variables to store the object for Buttons.
     */
    Button mRvc, mFfc, mCargo, mAux;

    /**
     * variable to store the object of title and helptext.
     */
    TextView mTitle, mHelptext;//

    /**
     * variable to store Textureview object.
     */
    private TextureView mTextureView;

    /**
     * variable to store CameraDivice object.
     */

    private CameraDevice mCameraDevice;

    /**
     * variable to store the value of capture request builder.
     */
    private CaptureRequest.Builder mCaptureRequestBuilder;

    /**
     * variable to store Framelayout object
     */
    private FrameLayout mFrameLayout;


    /**
     * variable to store Handler Thread object.
     */

    private HandlerThread mBackgroundHandlerThread;


    /**
     * variable to store Handler object.
     */
    private Handler mBackgroundHandler;


    /**
     * variable to store cameraid object
     */
    private String mCameraId;


    /**
     * variable to store preview size.
     */

    private Size mPreviewSize;

    ICameraSettingPresenter mCameraSettingPresenter;


    /**
     * variable to store a value which is used for specifying the camera (front and back) based on its value.
     */
    public static String mRotate;

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
        /**
         * variable to store view.
         */
        View mView = inflater.inflate(R.layout.fragment_camera, container, false);



        mTitle = mView.findViewById(R.id.title);
        mHelptext = mView.findViewById(R.id.helptext);
        mRvc = mView.findViewById(R.id.rvc);
        mFfc = mView.findViewById(R.id.ffc);
        mCargo = mView.findViewById(R.id.cargo);
        mAux = mView.findViewById(R.id.aux);
        mRvc.setOnClickListener(this);
        mFfc.setOnClickListener(this);
        mCargo.setOnClickListener(this);
        mAux.setOnClickListener(this);
        mTextureView = (TextureView) mView.findViewById(R.id.textureView);
        mTextureView.setRotation(-90);
        mFrameLayout = mView.findViewById(R.id.frameLayout);
        mFrameLayout.setRotation(-90);



        return mView;
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
                mRvc.setBackgroundColor(getResources().getColor(R.color.primary1));
                mFfc.setBackgroundColor(getResources().getColor(R.color.primary));
                mCargo.setBackgroundColor(getResources().getColor(R.color.primary));
                mAux.setBackgroundColor(getResources().getColor(R.color.primary));
                mFrameLayout.setBackground(null);
                mRotate = "null";


                if (mTextureView.isAvailable()) {
                    setupCamera(mTextureView.getWidth(), mTextureView.getHeight());
                    connectCamera("1");
                    Toast.makeText(getContext(), "camera "+mCameraId+"  connected", Toast.LENGTH_SHORT).show();


                } else {
                    mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
                }

                break;
            case R.id.ffc:
                mTitle.setText("FORWARD FACING CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                mRvc.setBackgroundColor(getResources().getColor(R.color.primary));
                mFfc.setBackgroundColor(getResources().getColor(R.color.primary1));
                mCargo.setBackgroundColor(getResources().getColor(R.color.primary));
                mAux.setBackgroundColor(getResources().getColor(R.color.primary));
                mFrameLayout.setBackground(null);
                mRotate = "fulfilled";
                if (mTextureView.isAvailable()) {
                    setupCamera(mTextureView.getWidth(), mTextureView.getHeight());

                    connectCamera("0");
                    Toast.makeText(getContext(), "camera "+mCameraId+"  connected", Toast.LENGTH_SHORT).show();
                } else {
                    mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);

                }

                break;
            case R.id.cargo:
                mTitle.setText("CARGO CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                mRvc.setBackgroundColor(getResources().getColor(R.color.primary));
                mFfc.setBackgroundColor(getResources().getColor(R.color.primary));
                mCargo.setBackgroundColor(getResources().getColor(R.color.primary2));
                mAux.setBackgroundColor(getResources().getColor(R.color.primary));
                closeCamera();
                mTextureView.setOpaque(false);
                mFrameLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.aux:
                mTitle.setText("AUX CAMERA");
                mHelptext.setText("Check entire Surrondings.");
                mRvc.setBackgroundColor(getResources().getColor(R.color.primary));
                mFfc.setBackgroundColor(getResources().getColor(R.color.primary));
                mCargo.setBackgroundColor(getResources().getColor(R.color.primary));
                mAux.setBackgroundColor(getResources().getColor(R.color.primary2));
                mTextureView.setOpaque(false);
                closeCamera();
                mFrameLayout.setBackgroundColor(Color.BLUE);
                break;


        }
    }


    /**
     *  @brief : function used to pause the application
     *
     */
    @Override
    public void onPause() {
        super.onPause();
        closeCamera();

        stopBackgroundThread();

    }


    /**
     *  @brief : function used to close the camera.
     *
     */
    private void closeCamera() {
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }


    /**
     *  @brief : function used to enters the Resumed state,
     *
     */

    @Override
    public void onResume() {


        super.onResume();
        startBackgroundThread();
        if (mTextureView.isAvailable()) {

            setupCamera(mTextureView.getWidth(), mTextureView.getHeight());
            connectCamera("1");
//            mCameraSettingPresenter.getSettings();
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
        }
    }


    /**
     * Variable to store surfaceTextureListener object
     */


    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {

        /**
         *  @brief : Invoked when a TextureView's SurfaceTexture is ready for use.
         *  @param surfaceTexture : SurfaceTexture
         *                      i : width
         *                      i1 : height
         */

        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
            setupCamera(i, i1);

        }

        /**
         *  @brief : Invoked when the SurfaceTexture's buffers size changed.
         *  @param surfaceTexture : SurfaceTexture
         *                      i : width
         *                      i1 : height
         */

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

        }

        /**
         *  @brief : Invoked when the specified SurfaceTexture is about to be destroyed.
         *  @param surfaceTexture : SurfaceTexture
         *
         */
        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            return false;
        }

        /**
         *  @brief : Invoked when the specified SurfaceTexture is updated
         *  @param surfaceTexture : SurfaceTexture
         *
         */

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

        }
    };


    /**
     * Variable to store callback objects for receiving updates about the state of a camera device.
     */

    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {

        /**
         *  @brief : called when a camera device has finished opening.
         *  @param cameraDevice : camera Device
         *
         */
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            startPreview();
            mTitle.setText("REAR VIEW CAMERA");
            mHelptext.setText("Check entire Surroundings.");
            Toast.makeText(getContext(), "Camera connected!", Toast.LENGTH_LONG).show();


        }

        /**
         *  @brief : called when a camera device is no longer available for use.
         *  @param cameraDevice : camera Device
         *
         */
        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
            mCameraDevice = null;

        }
        /**
         *  @brief : called when a camera device has encountered a serious error.
         *  @param cameraDevice : camera Device
         *
         */

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            mCameraDevice = null;

        }
    };

    /**
     * Variable to store orientation
     */

    private static SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }

    /**
     *  @brief : Function for comparing the size.
     *
     */

    private static class CompareSizeByArea implements Comparator<Size> {

        @Override
        public int compare(Size size, Size t1) {
            return Long.signum((long) t1.getWidth() * t1.getHeight() /
                    (long) t1.getWidth() * t1.getHeight());
        }
    }

    /**
     *  @brief : function used to setting up the camera for both back and front based on the camera id with specific width and height.
     *           ( "0" for front camera
     *             "1" for back camera)
     * @param : width : integer
     *          height : integer
     *
     *
     *
     */
    private void setupCamera(int width, int height) {
        CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {

            String cameraId = "0";

            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            int rotatedWidth = width;
            int rotatedHeight = height;
            mPreviewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class), rotatedWidth, rotatedHeight);


            if (mRotate == null){
                cameraId = cameraManager.getCameraIdList()[1];
                mCameraId = cameraId;
                closeCamera();

            }
            else {
                cameraId = cameraManager.getCameraIdList()[0];
                mCameraId = cameraId;
                mRotate = null;
                closeCamera();
            }

            return;

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     *  @brief : function used to connecting the camera
     * @param  mCameraId : camera Id ("0" or "1")
     *
     */
    private void connectCamera (String mCameraId) {
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


    /**
     *  @brief : function used to start the camera preview which will be displayed on the textureview.
     *
     */
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



    /**
     *  @brief : function used to start the background thread.
     *
     *
     */
    private void startBackgroundThread() {
        mBackgroundHandlerThread = new HandlerThread("PROJETDILEMMA");
        mBackgroundHandlerThread.start();
        mBackgroundHandler = new Handler(mBackgroundHandlerThread.getLooper());
    }


    /**
     *  @brief : function used to stop the background thread.
     */
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


    /**
     *  @brief : function used to get the optimal size.
     */

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

}



