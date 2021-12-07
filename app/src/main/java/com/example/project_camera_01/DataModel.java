package com.example.project_camera_01;

public class DataModel {


    public String name;
    boolean checked;


    DataModel(String name, boolean checked) {
        this.name = name;
        this.checked = checked;

    }
    public interface view{
        void getCameraView(int i, int i1);
    }
    public interface presenter{
        void getCamera(int viewstatus);
    }
}
