package com.example.vanki.learnsql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    EditText edtName,edtPhone,edtAddress;
    RadioGroup radioGender;
    RadioButton rbGender;
    Button btnAdd,btnCancel;
    MyDatabase db = new MyDatabase(this);
    Bundle b;
    Contact contact = new Contact();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add Contact");
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setmName(edtName.getText().toString());
                contact.setmPhone(edtPhone.getText().toString());
                contact.setmAddress(edtAddress.getText().toString());
                long day = System.currentTimeMillis();
                String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(day));
                contact.setmDate(date);
                String h = new SimpleDateFormat("hh:mm:ss").format(new Date(day));
                contact.setmTime(h);
                int idrad = radioGender.getCheckedRadioButtonId();
                rbGender = (RadioButton) findViewById(idrad);
                contact.setmGender(rbGender.getText().toString());

                if (b!=null){
                    db.updateContact(contact);
                } else {
                    contact.setmId(db.addContact(contact));
                }

                db.close();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putSerializable("RETURN",contact);
                intent.putExtras(b);
                setResult(RESULT_OK,intent);

                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addControls() {
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);

        radioGender = findViewById(R.id.radioGender);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        b = getIntent().getExtras();
        if (b!=null){
            contact.setmId(b.getInt("ID"));
            contact = db.getContact(contact.getmId());
            setData();
        }
    }

    private void setData() {
        getSupportActionBar().setTitle("Update Contact");
        btnAdd.setText("UPDATE");
        edtName.setText(contact.getmName());
        edtAddress.setText(contact.getmAddress());
        edtPhone.setText(contact.getmPhone());
        if (contact.getmGender().equals("Male")) {
            radioGender.check(R.id.radioMale);
        } else radioGender.check(R.id.radioFemale);
    }


}
