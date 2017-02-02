package dnyaneshwar.fireapp.putdata;

/**
 * Created by Dnyaneshwar on 12/15/2016.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.firebase.client.Firebase;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import dnyaneshwar.fireapp.ProjectInfo;
import dnyaneshwar.fireapp.Student;

public class Registeruser {       //Function used to put the data of th student after registration
    private FirebaseAuth firebaseAuth;
    public void put(Student student)
    {
       // String uid=firebaseAuth.getCurrentUser().getUid();

     //   String  path=student.Branch+"/"+student.Year;
      //  String xpath="NAMES/"+path+"/";
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-d33a0.firebaseio.com/");
       String emial=student.Email_id;
        dbRef.child("Accounts").child(emial).setValue(student);
        Log.v("Email",student.Email_id.trim());




    }

}
