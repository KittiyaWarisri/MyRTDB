package com.example.saiby.myrtdb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }


  


    public void saveRecord(View view) {

        EditText message = (EditText) findViewById(R.id.et_message);
        EditText username = (EditText) findViewById(R.id.et_user);
        String stringMessage = message.getText().toString();
        String stringUser = username.getText().toString();

        if(stringMessage.isEmpty() || stringUser.isEmpty()){
            return;
        }
        // TODO: RTDB SaveMessage

        DatabaseReference mNameRef = mRootRef.child(username.getText().toString());
        DatabaseReference mMessageRef = mRootRef.child(username.getText().toString()).child("message");
        mNameRef.setValue(stringUser);
        mMessageRef.setValue(stringMessage);


        // Write a message to the database
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/
    }



    public void fetchQuote (View view) {
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView tvMessage = (TextView) findViewById(R.id.tv_message);
                String stringMessage = dataSnapshot.child("myrtdb").getValue().toString();
                String stringName = dataSnapshot.child("message").getValue().toString();
                tvMessage.setText("\"" + stringMessage + "\"" + "__" + stringName + "ไม่ได้กล่าวไว้");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
