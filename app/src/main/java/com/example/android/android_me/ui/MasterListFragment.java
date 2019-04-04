package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    // For calling new interface to trigger callback in host activity
    // Contains info about which position on grid of images a user has clicked
    OnImageClickListener mCallback;

    // Interface for calling method in host activity named onImageSelected
    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Attach to host activity and ensure container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Ensures host activity implemented callback interface, otherwise, throw an exception
        try{
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnImageClickListener");
        }
    }

    // Inflate the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get reference to GridView in fragment_master_list layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        // Create the adapter
        // Adapter will take in the context + ArrayList of all image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        /* Set click listener on the GridView and trigger callback onImageSelected when item is clicked */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Trigger callback method and pass in position that was clicked
                mCallback.onImageSelected(position);
            }
        });


        // Return root view
        return rootView;
    }
}
