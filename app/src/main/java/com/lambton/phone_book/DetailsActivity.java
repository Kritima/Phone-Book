package com.lambton.phone_book;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class DetailsActivity extends AppCompatActivity {

    Button add;
    TextInputEditText firstName;
    TextInputEditText lastName;
    TextInputEditText address;
    TextInputEditText city;
    TextInputEditText province;
    TextInputEditText postalCode;
    TextInputEditText email;
    TextInputEditText phone;
    TextInputEditText area;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        firstName = (TextInputEditText) findViewById(R.id.firstNameTextInputEditText);
        lastName = (TextInputEditText) findViewById(R.id.lastNameTextInputEditText);
        address = (TextInputEditText) findViewById(R.id.AddressTextInputEditText);
        city = (TextInputEditText) findViewById(R.id.CityTextInputEditText);
        province = (TextInputEditText) findViewById(R.id.ProvinceTextInputEditText);
        postalCode = (TextInputEditText) findViewById(R.id.PostalCodeTextInputEditText);
        email = (TextInputEditText) findViewById(R.id.EmailTextInputEditText);
        phone = (TextInputEditText) findViewById(R.id.PhoneTextInputEditText);
        area = (TextInputEditText) findViewById(R.id.AreaTextInputEditText);

        helper = new myDbAdapter(this);

        public void addContact(View view)
        {
            String fn = firstName.getText().toString();
            String ln = lastName.getText().toString();
            String addr = address.getText().toString();
            String ct = city.getText().toString();
            String pv = province.getText().toString();
            String pc = postalCode.getText().toString();
            String em = email.getText().toString();
            String pn = phone.getText().toString();
            String ar = area.getText().toString();

            if(fn.isEmpty() || ln.isEmpty())
            {
               // Message.message(getApplicationContext(),"Enter Both Name and Password");
            }
            else
            {
                long id = helper.insertData(fn,ln,addr,ct,pv,pc,em,pn,ar);
                if(id<=0)
                {
                //    Message.message(getApplicationContext(),"Insertion Unsuccessful");
                    firstName.setText("");
                    lastName.setText("");
                    address.setText("");
                } else
                {
                 //   Message.message(getApplicationContext(),"Insertion Successful");
                    firstName.setText("");
                    lastName.setText("");
                }
            }
        }

        public void viewdata(View view)
        {
            String data = helper.getData();
            Message.message(this,data);
        }

        public void update( View view)
        {
            String u1 = updateold.getText().toString();
            String u2 = updatenew.getText().toString();
            if(u1.isEmpty() || u2.isEmpty())
            {
                Message.message(getApplicationContext(),"Enter Data");
            }
            else
            {
                int a= helper.updateName( u1, u2);
                if(a<=0)
                {
                    Message.message(getApplicationContext(),"Unsuccessful");
                    updateold.setText("");
                    updatenew.setText("");
                } else {
                    Message.message(getApplicationContext(),"Updated");
                    updateold.setText("");
                    updatenew.setText("");
                }
            }

        }
        public void delete( View view)
        {
            String uname = delete.getText().toString();
            if(uname.isEmpty())
            {
                Message.message(getApplicationContext(),"Enter Data");
            }
            else{
                int a= helper.delete(uname);
                if(a<=0)
                {
                    Message.message(getApplicationContext(),"Unsuccessful");
                    delete.setText("");
                }
                else
                {
                    Message.message(this, "DELETED");
                    delete.setText("");
                }
            }
        }

    }
}