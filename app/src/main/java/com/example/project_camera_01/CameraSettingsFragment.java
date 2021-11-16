package com.example.project_camera_01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraSettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CameraSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CameraSettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CameraSettingsFragment newInstance(String param1, String param2) {
        CameraSettingsFragment fragment = new CameraSettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList dataModels;
    ListView listView;
    private CustomAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_camera_settings, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);

        dataModels = new ArrayList();

        dataModels.add(new DataModel("Camera Delay Settings", false));
        dataModels.add(new DataModel("Camera Static Guideline Settings", false));
        dataModels.add(new DataModel("Swing Door Settings", false));
        dataModels.add(new DataModel("Cargo Cam Dynamic Centerlines", false));
        dataModels.add(new DataModel("Trailer Camera Settings", false));

        adapter = new CustomAdapter(dataModels, getActivity().getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                DataModel dataModel = (DataModel) dataModels.get(position);
                dataModel.checked = !dataModel.checked;
                adapter.notifyDataSetChanged();


            }
        });
        return rootView;
    }
}