package com.example.project_camera_01.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_camera_01.CustomAdapter;
import com.example.project_camera_01.DataModel;
import com.example.project_camera_01.R;
import com.example.project_camera_01.presenter.ICameraSettingPresenter;

import java.util.ArrayList;
import java.util.List;


public class CameraSettingsFragment extends Fragment implements CameraSettingInterface{


    CustomAdapter dataAdapter = null;
    View rootView;
    ListView listView;
    ICameraSettingPresenter mCameraSettingPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_camera_settings, container, false);
        listView = (ListView)rootView.findViewById(R.id.listView);
        displayListView();



        return rootView;
    }
    DataModel dataModel;

    private void displayListView() {

        ArrayList<DataModel> cameraList = new ArrayList<DataModel>();

        cameraList.add(new DataModel("Camera Delay Settings","",false));
        cameraList.add(new DataModel("Camera Static Guideline Settings","",false));
        cameraList.add(new DataModel("Swing Door Settings","",false));
        cameraList.add(new DataModel("Cargo Cam Dynamic Center lines","",false));
        cameraList.add(new DataModel("Trailer Camera Settings","",false));

        dataAdapter = new CustomAdapter(getContext(), R.layout.row_item, cameraList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel = (DataModel) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),
                        "Clicked on Row: " + dataModel.getCode(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void setSettings(String name, Boolean check) {
         String mName = name;
         Boolean mCheck = check;
        mCameraSettingPresenter.getSetting(mName,mCheck);

    }
}