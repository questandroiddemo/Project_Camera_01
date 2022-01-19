package com.example.project_camera_01.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import com.example.project_camera_01.CustomAdapter;
import com.example.project_camera_01.DataModel;
import com.example.project_camera_01.R;
import com.example.project_camera_01.presenter.CameraSettingPresenter;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;
import java.util.ArrayList;
import java.util.HashMap;


public class CameraSettingsFragment extends Fragment implements ICameraSettingView {

    /**
     * variable to store CustomAdapter object.
     */
    CustomAdapter dataAdapter = null;
    /**
     * variable to store View.
     */

    View rootView;

    /**
     * variable to store ListView.
     */
    ListView listView;

    /**
     * variable to store ICameraSettingPresenter object.
     */
    ICameraSettingPresenter mCameraSettingPresenter;
    /**
     * variable to store DataModel object.
     */
    private volatile boolean called;
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

        rootView = inflater.inflate(R.layout.fragment_camera_settings, container, false);
        listView = (ListView)rootView.findViewById(R.id.listView);
        mCameraSettingPresenter = new CameraSettingPresenter(this);

        return rootView;
    }

    /**
     * @brief Function used display the list of settings.
     *
     */
    ArrayList<DataModel> cameraList = new ArrayList<DataModel>();
    private void displayListView() {

        hashMap = mCameraSettingPresenter.getSettings();
        cameraList.add(new DataModel("Camera Delay Settings","",hashMap.get("Camera Delay Settings")));
        cameraList.add(new DataModel("Camera Static Guideline Settings","",hashMap.get("Camera Static Guideline Settings")));
        cameraList.add(new DataModel("Swing Door Settings","",hashMap.get("Swing Door Settings")));
        cameraList.add(new DataModel("Cargo Cam Dynamic Center lines","",hashMap.get("Cargo Cam Dynamic Center lines")));
        cameraList.add(new DataModel("Trailer Camera Settings","", hashMap.get("Trailer Camera Settings")));

        dataAdapter = new CustomAdapter(getContext(), R.layout.row_item, cameraList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel = (DataModel) parent.getItemAtPosition(position);

                CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox);
                cb.setChecked(!cb.isChecked());
                String c = dataModel.getCode();
               mCameraSettingPresenter.setSetting(dataModel.getCode(),cb.isChecked());
               mCameraSettingPresenter.setSetting("Camera Static Guideline Settings",cb.isChecked());

            }
        });

    }
    /**
     *  @brief : function used to enters the Resumed state,
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        if (called) return;
        called = true;
        displayListView();
    }

    HashMap<String, Boolean> hashMap = new HashMap<>();
    /**
     *  @brief : function used to notify status to the HMI from service.
     *
     */
    @Override
    public void notifyCameraSetting(String setId, boolean status) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status ) {

                    Log.d("notifyCameraSetting","Notifying HMI");
                }
                else{
                    Log.d("notifyCameraSetting","cannot Notifying HMI");
                }
            }
        });
    }
}