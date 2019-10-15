package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {
    private EditText edtId, edtName, edtPhone, edtMail;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        edtId = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtSDT);
        edtMail = findViewById(R.id.edtEmail);
        btnAdd = findViewById(R.id.btnSubmit);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
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
                } catch (Exception ex) {
                    Toast.makeText(AddUserActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
