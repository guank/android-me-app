package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    // Variables to store values of list index of selected images
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Acts like a custom click listener for fragment and defines behavior for onImageSelected*/
    public void onImageSelected(int position) {

        // bodyPartNumber will be = 0 for head fragment, = 1 for body, = 2 for leg
        // Division by 12 gives these values since each list of images resources has a size of 12
        int bodyPartNumber = position / 12;

        // Store list index and ensure index is a value between 0-11
        int listIndex = position - 12 * bodyPartNumber;

        // Set current displayed item for correct body fragment
        switch(bodyPartNumber) {
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;
        }

        // Add info to Bundle and attach to an Intent that will launch an AndroidMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        // Attach Bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        // The "Next" button will launch a new AndroidMeActivity
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
