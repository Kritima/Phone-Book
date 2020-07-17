package com.lambton.phone_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList contact_id, contact_firstName, contact_lastName, contact_address, contact_city, contact_province, contact_postalCode, contact_email,
    contact_phone, contact_area;

    ContactAdapter(Activity activity, Context context, ArrayList contact_id, ArrayList contact_firstName, ArrayList contact_lastName,
                  ArrayList contact_address, ArrayList contact_city, ArrayList contact_province, ArrayList contact_postalCode,
                   ArrayList contact_email, ArrayList contact_phone, ArrayList contact_area){
        this.activity = activity;
        this.context = context;
        this.contact_id = contact_id;
        this.contact_firstName = contact_firstName;
        this.contact_lastName = contact_lastName;
        this.contact_address = contact_address;
        this.contact_city = contact_city;
        this.contact_province = contact_province;
        this.contact_postalCode = contact_postalCode;
        this.contact_email = contact_email;
        this.contact_phone = contact_phone;
        this.contact_area = contact_area;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.book_title_txt.setText(String.valueOf(contact_firstName.get(position)) + " " +(contact_lastName.get(position)));
        holder.book_author_txt.setText(String.valueOf(contact_phone.get(position)));
       // holder.book_pages_txt.setText(String.valueOf(contact_address.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("firstname", String.valueOf(contact_firstName.get(position)));
                intent.putExtra("lastname", String.valueOf(contact_lastName.get(position)));
                intent.putExtra("address", String.valueOf(contact_address.get(position)));
                intent.putExtra("city", String.valueOf(contact_city.get(position)));
                intent.putExtra("province", String.valueOf(contact_province.get(position)));
                intent.putExtra("postalcode", String.valueOf(contact_postalCode.get(position)));
                intent.putExtra("email", String.valueOf(contact_email.get(position)));
                intent.putExtra("phone", String.valueOf(contact_phone.get(position)));
                intent.putExtra("area", String.valueOf(contact_area.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}