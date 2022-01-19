/**
 * @file CameraMainActivity.java
 * @brief MainFragment Class act as the base fragment of camera and camera settings fragments
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */
package com.example.project_camera_01.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.project_camera_01.R;
import com.example.project_camera_01.VpAdapter;
import com.example.project_camera_01.presenter.MainPresenter;
import com.example.project_camera_01.presenter.IMainPresenter;
import com.google.android.material.tabs.TabLayout;

import static com.example.project_camera_01.common.CameraConstants.BIND_SUCCESS;


/**
 * @brief Implementation for MainFragment class.
 * MainFragment is the second entry point to the application which extends Fragment.
 * MainFragment is responsible for initial loading of different fragment based on condition.
 */
public class MainFragment extends Fragment implements IMainView {
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
    private IMainPresenter mCameraPresenter;

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
         mCameraPresenter=new MainPresenter(this);
         mCameraPresenter.initialize(getContext());

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

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
            tabLayout.setupWithViewPager(viewPager);

            VpAdapter vpAdapter = new VpAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            vpAdapter.addFragment(new CameraFragment(),"Camera");
            vpAdapter.addFragment(new CameraSettingsFragment(),"Camera settings");
            viewPager.setAdapter(vpAdapter);
            Log.d("CameraService","OnBind");


        }
        else {

            Toast.makeText(getContext(), "BIND failed", Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(), "Loading...", Toast.LENGTH_LONG).show();
        }
    }
}