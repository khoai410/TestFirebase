package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateUserActivity extends AppCompatActivity {
    private EditText edtId, edtName, edtPhone, edtMail;
    private Button btnUpdate;
    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        edtId = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtSDT);
        edtMail = findViewById(R.id.edtEmail);
        btnUpdate = findViewById(R.id.btnSubmit);
        btnDelete=findViewById(R.id.btnDelete);
        getUser();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");
                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String mail = edtMail.getText().toString().trim();
                myRef.child(id).child("name").setValue(name);
                myRef.child(id).child("phone").setValue(phone);
                myRef.child(id).child("email").setValue(mail);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=edtId.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");
                myRef.child(key).removeValue();
                finish();
            }
        });
    }
        private void getUser(){
            Intent intent = getIntent();
            final String key = intent.getStringExtra("KEY");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users");
            myRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    HashMap<String,Object> hashMap=(HashMap<String, Object>)dataSnapshot.getValue();
                    edtId.setText(key);
                    edtName.setText(hashMap.get("name").toString().trim());
                    edtPhone.setText(hashMap.get("phone").toString().trim());
                    edtMail.setText(hashMap.get("email").toString().trim());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
