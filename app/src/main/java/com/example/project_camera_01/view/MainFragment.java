/**
 * @file CameraMainActivity.java
 * @brief MainFragment Class act as the base fragment of camera and camera settings fragments
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */
package com.example.project_camera_01.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.project_camera_01.R;
import com.example.project_camera_01.VpAdapter;
import com.example.project_camera_01.presenter.CameraPresenter;
import com.example.project_camera_01.presenter.ICameraPresenter;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;
import com.google.android.material.tabs.TabLayout;
import static com.example.project_camera_01.common.CameraConstants.BIND_FAIL;
import static com.example.project_camera_01.common.CameraConstants.BIND_SUCCESS;
import static com.example.project_camera_01.common.CameraConstants.TAG;


/**
 * @brief Implementation for MainFragment class.
 * MainFragment is the second entry point to the application which extends Fragment.
 * MainFragment is responsible for initial loading of different fragment based on condition.
 */
public class MainFragment extends Fragment implements  ICameraView{
    /**
     * variable to store the value of TabLayout.
     */
    private TabLayout tabLayout;
    /**
     * variable to store the value of ViewPager.
     */
    private ViewPager viewPager;

    /**
     * variable to store object of ICameraPresenter.
     */
    private ICameraPresenter mCameraPresenter;

    /**
     * variable to store the view.
     */
    View v;

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

        v=inflater.inflate(R.layout.fragment_main, container, false);
         mCameraPresenter=new CameraPresenter(this);
         mCameraPresenter.initialize(getContext());

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        VpAdapter vpAdapter = new VpAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new CameraFragment(),"Camera");
        vpAdapter.addFragment(new CameraSettingsFragment(),"Camera settings");
        viewPager.setAdapter(vpAdapter);

        return v;


    }

    /**
     * @brief Method to get bind status from service.
     * @param bindStatus : bind status
     */
    @Override
    public void updateBindStatus(int bindStatus) {

        if (bindStatus == BIND_SUCCESS) {
            Toast.makeText(getContext(), "BIND SUCCESS", Toast.LENGTH_LONG).show();
            String previous = mCameraPresenter.getPreviousActiveCamera();
            Log.d("CameraService","OnBind");
            mCameraPresenter.startCamera();

        }
    }


    /**
     * @brief Method to notify camera status.
     * @param status : status of camera.
     */
    @Override
    public void notifyCameraStatus(boolean status) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status) {
                    Toast.makeText(getContext(), "Camera started", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getContext(), "Camera stopped" , Toast.LENGTH_LONG).show();
            }
        });
    }
}