package dnyaneshwar.fireapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TextView;
import  java.util.*;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class ShowInfo extends AppCompatActivity {
    TextView Name;
    TextView Email;
    TextView Mobile;
    TextView Rollno;
    TextView ProjectTitle;
    TextView Toolsused;
    TextView Field;
   TextView Year;
    TextView Branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_info);
        getSupportActionBar().setHomeButtonEnabled(true);
        Name=(TextView)findViewById(R.id.name);
        Email=(TextView)findViewById(R.id.email);
        Rollno=(TextView)findViewById(R.id.prnno);
        Mobile=(TextView)findViewById(R.id.number);
        Branch=(TextView)findViewById(R.id.branch);
        Year=(TextView)findViewById(R.id.year);
        ProjectTitle=(TextView)findViewById(R.id.title);
        Field=(TextView)findViewById(R.id.textfield);
        Toolsused=(TextView)findViewById(R.id.tools);





    //    tv=(TextView)findViewById(R.id.text);
        Bundle bundle=getIntent().getExtras();
        final String title=bundle.getString("Title");
        final String branch=bundle.getString("Branch");
        String year=bundle.getString("Year");
        Log.v("DNYANESHWAR:",title);
        Log.v("DNYANESHWAR:",branch);
        Log.v("DNYANESHWAR:",year);
        String path="https://fireapp-d33a0.firebaseio.com/"+branch+"/"+year;
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl(path);

        Query q=dbRef.orderByChild("Project_title").equalTo(title);
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Please Wait");
        pd.setCancelable(true);
        if(!pd.isShowing())
                   pd.show();
        q.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
           @Override
           public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
               for (com.google.firebase.database.DataSnapshot data : dataSnapshot.getChildren()) {

                   Student studentt = data.getValue(Student.class);
                  // tv.setText(studentt.Branch);
                    Name.setText(studentt.Name);

                    Email.setText(studentt.Email_id);
                    Mobile.setText(studentt.Mobile_no);
                    Rollno.setText(studentt.Rollno);
                //   ProjectTitle.setText(studentt.Project_title);
                  //  Toolsused.setText(studentt.Tools_used);
                   // Field.setText(studentt.Project_area);
                    //Year.setText(studentt.Year);
                   Branch.setText(studentt.Branch);


                   break;
               }
             if (pd.isShowing())
                            pd.dismiss();
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item=menu.add("Title"); //your desired title here
        item.setIcon(R.drawable.homm); //your desired icon here
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(ShowInfo.this,FirstPage.class);
                startActivity(intent);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
