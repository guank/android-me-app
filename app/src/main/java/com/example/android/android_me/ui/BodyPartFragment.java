package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    // Final strings for storing state info on image list and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    // Variables for string list of image resources and index of the image the fragment displays
    private List<Integer> mImageIds;
    private int mListIndex;

    /* Mandatory constructor to instantiate fragment */
    public BodyPartFragment(){
    }

    /* Inflate fragment layout and set any image resources */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Load saved state if it exists
        if(savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        // Inflate Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get reference to the ImageView in fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // If list of image ids exists, set image resource to correct item in list
        // Else, create Log statement that states list is not found
        if(mImageIds != null){
            // Set image resource to list item at stored index
            imageView.setImageResource(mImageIds.get(mListIndex));

            // Set click listener for imageview
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Increment position index if it remains greater than size of image id list
                    if(mListIndex < mImageIds.size() - 1){
                        mListIndex++;
                    } else{
                        // Reset list to beginning when reaching end
                        mListIndex = 0;
                    }

                    // Set image resource to new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else{
            Log.v("TEST", "Fragment has null list of image ids");
        }

        // Return root view
        return rootView;
    }

    // Setters for tracking list images that this fragment can display and which image in list
    // is currently displayed
    public void setmImageIds(List<Integer> imageIds){
        mImageIds = imageIds;
    }

    public void setmListIndex(int index){
        mListIndex = index;
    }

    // Saves current state of fragment
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
