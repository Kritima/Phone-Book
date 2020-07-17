package com.lambton.phone_book;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {

    TextInputEditText firstName;
    TextInputEditText lastName;
    TextInputEditText address;
    TextInputEditText city;
    TextInputEditText province;
    TextInputEditText postalCode;
    TextInputEditText email;
    TextInputEditText phone;
    TextInputEditText area;
    Button update_button, delete_button;


    String id, fn, ln, addr, ct, pv, pc, em, ph, ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        firstName = (TextInputEditText) findViewById(R.id.firstNameTextInputEditText);
        lastName = (TextInputEditText) findViewById(R.id.lastNameTextInputEditText);
        address = (TextInputEditText) findViewById(R.id.AddressTextInputEditText);
        city = (TextInputEditText) findViewById(R.id.CityTextInputEditText);
        province = (TextInputEditText) findViewById(R.id.ProvinceTextInputEditText);
        postalCode = (TextInputEditText) findViewById(R.id.PostalCodeTextInputEditText);
        email = (TextInputEditText) findViewById(R.id.EmailTextInputEditText);
        phone = (TextInputEditText) findViewById(R.id.PhoneTextInputEditText);
        area = (TextInputEditText) findViewById(R.id.AreaTextInputEditText);
        delete_button = (Button) findViewById(R.id.btnDelete);
        update_button = (Button) findViewById(R.id.btnUpdate);

        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(fn);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                fn = firstName.getText().toString().trim();
                ln = lastName.getText().toString().trim();
                addr = address.getText().toString().trim();
                myDB.updateData(id, fn, ln, addr, ct, pv, pc, em, ph, ar);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("firstname") &&
                getIntent().hasExtra("lastname") && getIntent().hasExtra("phone")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            fn = getIntent().getStringExtra("firstname");
            ln = getIntent().getStringExtra("lastname");
            addr = getIntent().getStringExtra("address");
            ct = getIntent().getStringExtra("city");
            pv = getIntent().getStringExtra("province");
            pc = getIntent().getStringExtra("postalcode");
            em = getIntent().getStringExtra("email");
            ph = getIntent().getStringExtra("phone");
            ar = getIntent().getStringExtra("area");


            //Setting Intent Data
            firstName.setText(fn);
            lastName.setText(ln);
            address.setText(addr);
            Log.d("stev", fn+" "+ln+" "+addr);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + fn + " ?");
        builder.setMessage("Are you sure you want to delete " + fn + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
