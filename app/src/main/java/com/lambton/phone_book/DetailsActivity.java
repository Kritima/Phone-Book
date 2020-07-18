package com.lambton.phone_book;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class DetailsActivity extends AppCompatActivity {

    Button add_button;
    TextInputEditText firstName;
    TextInputEditText lastName;
    TextInputEditText address;
    TextInputEditText city;
    TextInputEditText province;
    TextInputEditText postalCode;
    TextInputEditText email;
    TextInputEditText phone;
    TextInputEditText area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        firstName = (TextInputEditText) findViewById(R.id.firstNameTextInputEditText);
        lastName = (TextInputEditText) findViewById(R.id.lastNameTextInputEditText);
        address = (TextInputEditText) findViewById(R.id.AddressTextInputEditText);
        city = (TextInputEditText) findViewById(R.id.CityTextInputEditText);
        province = (TextInputEditText) findViewById(R.id.ProvinceTextInputEditText);
        postalCode = (TextInputEditText) findViewById(R.id.PostalCodeTextInputEditText);
        email = (TextInputEditText) findViewById(R.id.EmailTextInputEditText);
        phone = (TextInputEditText) findViewById(R.id.PhoneTextInputEditText);
        area = (TextInputEditText) findViewById(R.id.AreaTextInputEditText);
        add_button = (Button) findViewById(R.id.btnAdd);

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String fn = firstName.getText().toString();
                        String ln = lastName.getText().toString();
                        String addr = address.getText().toString();
                        String ct = city.getText().toString();
                        String pv = province.getText().toString();
                        String pc = postalCode.getText().toString();
                        String em = email.getText().toString();
                        String pn = phone.getText().toString();
                        String ar = area.getText().toString();
                        MyDatabaseHelper myDB = new MyDatabaseHelper(DetailsActivity.this);
                        myDB.addContact(fn, ln, addr, ct, pv, pc, em, pn, ar);
                        finish();

                    }
                });
    }
}