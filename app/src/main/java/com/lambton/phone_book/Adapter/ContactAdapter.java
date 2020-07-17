package com.lambton.phone_book.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lambton.phone_book.Contact;
import com.lambton.phone_book.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> personsList;
    Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public List<Contact> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Contact> personsList) {
        this.personsList = personsList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.contact_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Contact currPerson = personsList.get(position);

        holder.name.setText(currPerson.getFirstname() + " " + currPerson.getLastname());
        holder.address.setText( currPerson.getAddress() );
        holder.phone.setText( currPerson.getPhone() );
        holder.deletePerson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete contact")
                        .setMessage("Are you sure you want to delete this contact?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteItem( position );
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        } );

        holder.mycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myintent = new Intent(context, AddContactActivity.class);
                myintent.putExtra("contact", currPerson);
                context.startActivity(myintent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }

    public void filterList(List<Contact> filteredList) {
        personsList =  filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, address, phone;
        CardView mycardview;
        ImageView deletePerson;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycardview = itemView.findViewById(R.id.newcardNote);
            name = itemView.findViewById( R.id.textView1 );
            address = itemView.findViewById( R.id.textView2 );
            phone = itemView.findViewById( R.id.textView3 );
            deletePerson = itemView.findViewById( R.id.delete_person );

        }
    }
    public void deleteItem(int position) {
        Contact person = personsList.get(position);
        ContactDB userDatabase = ContactDB.getInstance(getContext());
        userDatabase.daoObject().delete(person);
        personsList.remove(position);
        notifyDataSetChanged();

    }
}


