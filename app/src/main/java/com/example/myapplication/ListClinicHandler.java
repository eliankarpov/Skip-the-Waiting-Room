package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListClinicHandler extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public ListClinicHandler(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView (final int position, View convertView, ViewGroup parent){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_list_clinic, null);
        }
        TextView listItemText = (TextView)view.findViewById(R.id.clinicName);
        listItemText.setText(list.get(position));
        Button viewClinics = (Button)view.findViewById(R.id.viewClinicButton);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        viewClinics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ClinicDetails.class);
                Bundle b = new Bundle();
                b.putString("name", list.get(position));
                i.putExtras(b);
                context.startActivity(i);
            }
        });


//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id = list.get(position);
//                //database.collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
//                database.collection("services").document(id).get().addOnCompleteListener((@NonNull Task<DocumentSnapshot> task)->{
//                    if (task.isSuccessful()) {
//                        String roleName = task.getResult().get("role").toString();
//                        String nameService = task.getResult().get("Name").toString();
//                        //deleteButton.setText(roleName);
//                        //docData.put("sun","closed");
//                        FirebaseUser user = mAuth.getCurrentUser();
//
//                        Map<String, Object> docData = new HashMap<>();
//                        docData.put("name",nameService);
//                        docData.put("role",roleName);
//
//                        database.collection("users").document(user.getEmail()).collection("services").document(nameService).set(docData);
//
//
//                    }
//                });
//
//
//
//                Intent intent = new Intent(context, EmployeeEditService.class);
//                context.startActivity(intent);
//
//
//                //});
//            }
//        });


        return view;

    }

}
