/**
 * @file CameraMainActivity.java
 * @brief MainFragment Class act as the base fragment of camera and camera settings fragments
 * @copyright COPYRIGHT (C) 2018 MITSUBISHI ELECTRIC CORPORATION ALL RIGHTS RESERVED
 * @author Adhithya K C,Ann Jojo,Edwin Jaison C
 */
package com.example.project_camera_01;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

/**
 * @brief Implementation for MainFragment class.
 * MainFragment is the second entry point to the application which extends Fragment.
 * MainFragment is responsible for initial loading of different fragment based on condition.
 */
public class MainFragment extends Fragment {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_main, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        VpAdapter vpAdapter = new VpAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new CameraFragment(),"Camera");
        vpAdapter.addFragment(new CameraSettingsFragment(),"Camera settings");
        viewPager.setAdapter(vpAdapter);
        Intent intent = new Intent("com.example.myservice_camera.AIDL");

        intent.setClassName("com.example.myservice_camera",
                "com.example.myservice_camera.MyService");
        if(getActivity().getBaseContext().getApplicationContext().bindService(intent, serviceCon, Context.BIND_AUTO_CREATE)){
            connected=true;
            Toast.makeText(getContext(), "BindServiceSuccess", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getContext(), "BindServiceFailed", Toast.LENGTH_SHORT).show();
        return v;


    }
    private final ServiceConnection serviceCon=new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}