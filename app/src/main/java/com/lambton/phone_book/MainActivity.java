package com.lambton.phone_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContactList;
    private ContactAdapter contactAdapter;
    FloatingActionButton floatingActionButtonAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContactList = findViewById(R.id.rvContactList);
        floatingActionButtonAddContact = findViewById(R.id.floating_action_button_contact);

        contactAdapter = new ContactAdapter(customerListArrayList);
        rvContactList.setLayoutManager(new LinearLayoutManager(this));
        rvContactList.setAdapter(contactAdapter);

        floatingActionButtonAddContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}