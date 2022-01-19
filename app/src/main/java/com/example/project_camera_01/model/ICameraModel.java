package com.example.project_camera_01.model;

public interface ICameraModel {
    /**
     * @brief Method to set the last active camera.
     * @param camId : camera ID
     */
    void setCamera(String camId);
    /**
     * @brief Method to get previous active camera
     * @return camera : camera
     */
    String getCamera();
}
