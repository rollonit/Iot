package com.example.iot.iot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="com.example.iot.MESSAGE";
    Switch lightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Test001/str");

        lightSwitch=findViewById(R.id.lightSwitch);
        lightSwitch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(lightSwitch.isChecked()){
                    myRef.setValue("1");
                }
                else{
                    myRef.setValue("0");
                }
            }
        });
    }

    public void sendMessage(View view){
        Intent intent=new Intent(this, DisplayMessageActivity.class);
        EditText ipField =(EditText)findViewById(R.id.ipField);
        String ip=ipField.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, ip);
        startActivity(intent);
    }





}
