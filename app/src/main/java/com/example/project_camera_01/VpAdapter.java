package com.example.project_camera_01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class VpAdapter extends FragmentPagerAdapter {

    /**
     * variable to store the Arralist of the fragment.
     */
    private final ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
    /**
     * variable to store the ArrayList of the fragmentTitle.
     */
    private final ArrayList<String> fragmentTitle =new ArrayList<>();

    /**
     * Constructor of the VpAdapter
     */
    public VpAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    /**
     * @Brief method to get the item from the arrayList.
     * @param position : position of the list.
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    /**
     * @Brief method to get the size of arrayList.
     * @return fragmentArrayList.
     */
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    /**
     * @Brief Fragment lifecycle method onCreateView
     * @param fragment    :  Fragment
     * @param title       :  name of the fragment.
     */
    public  void addFragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }

    /**
     * @Brief method to add page title.
     * @param position           :  position
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }
}
