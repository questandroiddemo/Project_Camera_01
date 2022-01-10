package com.example.project_camera_01;

public class DataModel {

    /**
     * variable to store code name of the setting
     */
    String code = null;
    /**
     * variable to store name of checkbox
     */
    String name = null;
    /**
     * variable to store the status of checkbox
     */
    boolean selected = false;

    /**
     * Constructor for the DataModel.
     */
    public DataModel(String code, String name, boolean selected) {
        super();
        this.code = code;
        this.name = name;
        this.selected = selected;
    }

    /**
     * variable to get the names in the setting list.
     */
    public String getCode() {
        return code;
    }
    /**
     * variable to set the names in the setting list.
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * variable to get the names of the checkbox.
     */
    public String getName() {
        return name;
    }
    /**
     * variable to set the names of the checkbox.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * variable to get the status of the checkbox.
     */
    public boolean isSelected() {
        return selected;
    }
    /**
     * variable to set the status of the checkbox.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
