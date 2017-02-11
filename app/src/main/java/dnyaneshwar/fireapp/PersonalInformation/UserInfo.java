package dnyaneshwar.fireapp.PersonalInformation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import dnyaneshwar.fireapp.ProjectInfo;
import dnyaneshwar.fireapp.R;
import dnyaneshwar.fireapp.Student;

public class UserInfo extends AppCompatActivity {
    ProjectInfo projectInfo;
    Student student;
    TextView name,email,projectname,mobileno,rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        TextView textView=(TextView)findViewById(R.id.Project_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle=getIntent().getExtras();
        final String title=bundle.getString("Title");
        final String branch=bundle.getString("Branch");
        String year=bundle.getString("Year");
        Log.v("DNYANESHWAR:",title);
        Log.v("DNYANESHWAR:",branch);
        Log.v("DNYANESHWAR:",year);
        String path="https://fireapp-d33a0.firebaseio.com/"+"Projects/"+branch+"/"+year;
        String path1="https://fireapp-d33a0.firebaseio.com/"+"Accounts";
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl(path);
        DatabaseReference dbRef1 = FirebaseDatabase.getInstance().getReferenceFromUrl(path1);

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Please Wait");
        pd.setCancelable(true);
        final CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow=false;
            int scrollRange=-1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Title");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(UserInfo.this,sendmail.class);
                startActivity(i);
            }
        });

        if(!pd.isShowing())
            pd.show();
        Query q=dbRef.orderByChild("Project_title").equalTo(title.trim());
        q.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot data : dataSnapshot.getChildren()) {

                    projectInfo = data.getValue(ProjectInfo.class);
                    // tv.setText(studentt.Branch);
            /*        Name.setText(studentt.Name);

                    Email.setText(studentt.Email_id);
                    Mobile.setText(studentt.Mobile_no);
                    Rollno.setText(studentt.Rollno);
                //   ProjectTitle.setText(studentt.Project_title);
                  //  Toolsused.setText(studentt.Tools_used);
                   // Field.setText(studentt.Project_area);
                    //Year.setText(studentt.Year);
                   Branch.setText(studentt.Branch);


                   break; */
                    break;
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        q=dbRef1.orderByChild("Email_id").equalTo(projectInfo.email);
        q.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                for(com.google.firebase.database.DataSnapshot data: dataSnapshot.getChildren())
                {
                    student=data.getValue(Student.class);
                    break;

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        email.setText(student.Email_id);
        rollno.setText(student.Rollno);
        projectname.setText(projectInfo.Project_title);
        mobileno.setText(student.Mobile_no);

        if (pd.isShowing())
            pd.dismiss();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
