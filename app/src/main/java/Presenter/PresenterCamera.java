package Presenter;

import com.example.project_camera_01.DataModel;

public class PresenterCamera implements DataModel.presenter {
    DataModel.view V;

    @Override
    public void getCamera(int viewstatus) {
        V.getCameraView(1,1);

    }
}
