package dnyaneshwar.fireapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import dnyaneshwar.fireapp.Authentication.LoginActivity;
import dnyaneshwar.fireapp.Authentication.RegistrationActivity;

public class FirstPage extends AppCompatActivity {
SearchView mSearchView;
    FirebaseAuth firebaseAuth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        getSupportActionBar().setLogo(R.drawable.civil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_first_page);

        if(firebaseAuth.getInstance().getCurrentUser()==null)
        {

            startActivity(new Intent(FirstPage.this, LoginActivity.class));
            finish();

        }
        //Toast.makeText(FirstPage.this,,Toast.LENGTH_LONG).show();
        final Button show=(Button)findViewById(R.id.showproject);
        final Button add=(Button)findViewById(R.id.addproject);
        final Button logout=(Button)findViewById(R.id.logoff);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FirstPage.this,SubmitInfo.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             FirebaseAuth.getInstance().signOut();
                if(firebaseAuth.getInstance().getCurrentUser()==null){
                    Toast.makeText(FirstPage.this,"Logout Succeefullu",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(FirstPage.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(FirstPage.this,firebaseAuth.getInstance().getCurrentUser().getUid().toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FirstPage.this,ShowBranchName
                        .class);
                startActivity(i);
            }
        });

    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Close this Application")
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}
