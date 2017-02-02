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

import dnyaneshwar.fireapp.FirstPage;
import dnyaneshwar.fireapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button register;
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
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(LoginActivity.this, FirstPage.class));

        }
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

        String emailid,passid;
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
                    Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT);
                    progressBar.dismiss();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Email or password is wrong",Toast.LENGTH_SHORT);
                }
            }

        });











    }
}
