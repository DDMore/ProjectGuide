package dnyaneshwar.fireapp.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dnyaneshwar.fireapp.FirstPage;
import dnyaneshwar.fireapp.R;
import dnyaneshwar.fireapp.Student;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button register,check;
    EditText email,password;
    TextView textsign,forgotpass;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar=new ProgressDialog(this);
        register =(Button)findViewById(R.id.registeruser);
        email=(EditText)findViewById(R.id.emailid);
        password=(EditText)findViewById(R.id.password);
        textsign=(TextView)findViewById(R.id.textsigin);
        register.setOnClickListener(this);
        textsign.setOnClickListener(this);
        forgotpass=(TextView)findViewById(R.id.sendmail);
        check=(Button)findViewById(R.id.checklogin);
        firebaseAuth=FirebaseAuth.getInstance();

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,resetpasswd.class));
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view == register) {
            Loginuser();

        }
        if (view == textsign) {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        }


    }
    private  void Loginuser(){

        final String emailid,passid;
        emailid=email.getText().toString().trim();
        passid=password.getText().toString().trim();
        if(TextUtils.isEmpty(emailid)){return;}
        if(TextUtils.isEmpty(passid)){return;}
        progressBar.setMessage("Login In");
        progressBar.show();
        firebaseAuth.signInWithEmailAndPassword(emailid,passid  ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    DatabaseReference dbRef= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-d33a0.firebaseio.com/Accounts/");
                    dbRef.orderByChild("Email_id").equalTo(emailid);
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data:dataSnapshot.getChildren())
                        {
                            Student student=data.getValue(Student.class);
                            Student.t=student;
                            break;
                        }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT);
                    progressBar.dismiss();
                    Intent i=new Intent(LoginActivity.this,FirstPage.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Email or password is wrong",Toast.LENGTH_SHORT);
                }
            }

        });
    }
}
