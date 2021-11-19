package com.example.project_camera_01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class cameraFragment extends Fragment {
    Button rvc, ffc, cargo, aux;
    TextView title, helptext;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        // connecting buttons with the
        // layout using findViewById()


        view.findViewById(R.id.rvc).setOnClickListener(mListener);
        view.findViewById(R.id.ffc).setOnClickListener(mListener);
        view.findViewById(R.id.cargo).setOnClickListener(mListener);
        view.findViewById(R.id.aux).setOnClickListener(mListener);
        title = view.findViewById(R.id.title);
        helptext = view.findViewById(R.id.helptext);


        return view;
    }

    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rvc:
                    title.setText("REAR VIEW CAMERA");
                    helptext.setText("Check entire Surrondings.");
                    break;
                case R.id.ffc:
                    title.setText("FORWARD FACING CAMERA");
                    helptext.setText("Check entire Surrondings.");
                    break;
                case R.id.cargo:
                    title.setText("CARGO CAMERA");
                    helptext.setText("System Unavailbe");
                    break;
                case R.id.aux:
                    title.setText("AUX CAMERA");
                    helptext.setText("System Unavailble");
                    break;
            }
        }

    };
}

