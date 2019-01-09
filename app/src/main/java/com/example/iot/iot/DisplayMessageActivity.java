package com.example.iot.iot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //things i know nothing about
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //something about getting data from the previous activity
        Intent intent = getIntent();
        String ip=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //getting the text box and putting what i typed inside of it
        final TextView viewBox=findViewById(R.id.ipDisp);
        viewBox.setText(ip);

        //getting the firebase database and creating a reference to my location
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Test001/str");

        //setting the input value from before to the database
        myRef.setValue(ip);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                viewBox.setText(value);
                //Color Norm = getResources().getColor(R.color.Norm);
                viewBox.setTextColor(getResources().getColor(R.color.Norm));
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
