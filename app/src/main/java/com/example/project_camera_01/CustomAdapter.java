package com.example.project_camera_01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel>{


    /**
     * variable to store ArrayList.
     */
    private ArrayList<DataModel> cameraList;
    /**
     * @brief Constructor of CustomAdapter
     */

    public CustomAdapter(Context context,int textViewResourceId,ArrayList<DataModel> countryList){
       super(context,textViewResourceId,countryList);

       this.cameraList = new ArrayList<DataModel>();
       this.cameraList.addAll(countryList);
   }
    /**
     * @brief ViewHolder class
     */

    private class ViewHolder {
        TextView code;
        CheckBox name;
    }


    /**
     * @brief Method to get the view.
     * @param position           :  position
     * @param convertView          :  view
     * @param parent :  ViewGroup
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        if (convertView == null){
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.row_item, null);
            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.txtName);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);


            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    DataModel dataModel = (DataModel) cb.getTag();
                    String set = dataModel.getCode();
                    Toast.makeText(getContext(),
                            ""+dataModel.getCode() + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    dataModel.setSelected(cb.isChecked());

                    Log.d("CamSet", "Inside notifyCameraStatus:" + cb.isChecked());
                }

            });
        }
       else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataModel dataModel = cameraList.get(position);
        holder.code.setText(dataModel.getCode());
        holder.name.setText(dataModel.getName());
        holder.name.setChecked(dataModel.isSelected());
        holder.name.setTag(dataModel);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}