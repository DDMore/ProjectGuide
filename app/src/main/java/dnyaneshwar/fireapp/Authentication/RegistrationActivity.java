package dnyaneshwar.fireapp.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dnyaneshwar.fireapp.R;
import dnyaneshwar.fireapp.Student;
import dnyaneshwar.fireapp.putdata.Registeruser;

public class RegistrationActivity extends AppCompatActivity  implements View.OnClickListener{

    Button register;
    EditText email,password,name,mobileno,rollno;
    TextView textsign;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    private Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressBar=new ProgressDialog(this);
        register =(Button)findViewById(R.id.registeruser);
        email=(EditText)findViewById(R.id.emailid);
        name=(EditText)findViewById(R.id.nameid);
        mobileno=(EditText)findViewById(R.id.mobileid);
        password=(EditText)findViewById(R.id.password);
        textsign=(TextView)findViewById(R.id.textsigin);
        register.setOnClickListener(this);
        rollno=(EditText)findViewById(R.id.roll);
        textsign.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if(view==register){
            registeruser();

        }
        if(view==textsign){
            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        }


    }
    private  void registeruser(){


        final String emailid,passid;
        emailid=email.getText().toString().trim();
        passid=password.getText().toString().trim();
        if(TextUtils.isEmpty(emailid)){return;}
        if(TextUtils.isEmpty(passid)){return;}
        progressBar.setCancelable(false);
        progressBar.setMessage("registering");
        progressBar.show();
        final Registeruser registeruser=new Registeruser();
        final Student student=new Student(name.getText().toString(),rollno.getText().toString(),mobileno.getText().toString(),email.getText().toString(),"IT");
        firebaseAuth.createUserWithEmailAndPassword(emailid,passid).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    registeruser.put(student);
                    Toast.makeText(RegistrationActivity.this,"registration successfull",Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();

                }
                if(!task.isSuccessful())
                {
                    Toast.makeText(RegistrationActivity.this,"registration failure",Toast.LENGTH_SHORT).show();
                    Log.v("RRRRRRRRRRRR",rollno.getText().toString());

                    progressBar.dismiss();
                }
            }
        });











    }
}
