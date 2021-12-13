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
import android.widget.Toast;

import com.example.project_camera_01.IMyAidlInterface;
import com.example.project_camera_01.R;
import com.example.project_camera_01.VpAdapter;
import com.example.project_camera_01.presenter.CameraPresenter;
import com.example.project_camera_01.presenter.ICameraPresenter;
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
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View v;

    /**
     * @Brief Fragment lifecycle method onCreateView
     * @param inflater           :  Object of LayoutInflater
     * @param container          :  Object of ViewGroup
     * @param savedInstanceState :  Object of Bundle
     * @return
     */
    IMyAidlInterface iMyAidlInterface;
    Boolean connected=true;
    private ICameraPresenter mCameraPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

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


    @Override
    public void updateBindStatus(int bindStatus) {
//        Toast.makeText(getContext(),"HAI",Toast.LENGTH_LONG).show();
        if (bindStatus == BIND_SUCCESS) {
            Toast.makeText(getContext(), "BIND SUCCESS", Toast.LENGTH_LONG).show();
            String previousCamera = mCameraPresenter.getPreviousActiveCamera();
            Toast.makeText(getContext()," "+previousCamera,Toast.LENGTH_LONG).show();
            Log.d(TAG,"updateBindStatus:"+previousCamera);
        }
        else {
            Toast.makeText(getContext(), "BIND Failed", Toast.LENGTH_LONG).show();
        }

    }
}