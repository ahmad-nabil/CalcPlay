package com.ahmad.calcplay.HelperAndAdabter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ahmad.calcplay.customData.User;
import com.ahmad.calcplay.mainmenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FireBaseHelper {
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    Context context;

    public FireBaseHelper(Context context) {
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        this.context=context;
    }

    public void signup(String name,String Email,String password,String phone) {
        auth.createUserWithEmailAndPassword(Email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveuserdata(name, Email, password, phone);
                            Toast.makeText(context.getApplicationContext(), "signed up", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context.getApplicationContext(), mainmenu.class));
                        }                    }
                });
    }
    //save useer data in database Real time we use this method and this method connectedee with the peervious method signup
    public void saveuserdata(String name, String email, String password, String phone) {

        String userid= auth.getCurrentUser().getUid();
        User user=new User(name,password,email,phone);
        databaseReference.child(userid).setValue(user);
    }
//use this method to login
    public void signin(String Email,String password) {
        auth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    context.startActivity(new Intent(context.getApplicationContext(),mainmenu.class));
                }
            }
        });
    }


    public void updatedata(String name, String email, String password, String phone) {

        String userid= auth.getCurrentUser().getUid();
        Map<String, Object> update_data = new HashMap<>();
        update_data.put("name", name);
        update_data.put("email", email);
        update_data.put("password", password);
        update_data.put("phone", phone);
        auth.getCurrentUser().updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                auth.getCurrentUser().updatePassword(password).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        databaseReference.child(userid).updateChildren(update_data);
                        Toast.makeText(context, "updated data", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, mainmenu.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "failed update data resign please", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "failed update data resign please", Toast.LENGTH_SHORT).show();
                Log.e("email",  e.getMessage());
            }
        });


     }

//using for rest password
    public void remeberpassword(String Email) {
        auth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                    Toast.makeText(context.getApplicationContext(), "sent link to your email to rest password", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }


//Method to get data for Ui  we use also another meeethod to fetch data from it without update any data
public void getdata( EditText names,  EditText emails,  EditText passwords, EditText phones ) {
    fetchData("name", names);
    fetchData("email", emails);
    fetchData("phone", phones);
    fetchData("password", passwords);
}
    public void fetchData(String fieldName, EditText editText) {
        String userid = auth.getCurrentUser().getUid();

        databaseReference.child(userid).child(fieldName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                editText.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //for header navigation
    public void headervalue(final  TextView name, final TextView email) {
        String userid = auth.getCurrentUser().getUid();

        databaseReference.child(userid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                name.setText(value);
                Log.e("name", value );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        databaseReference.child(userid).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                email.setText(value);
                Log.e("email", value );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
