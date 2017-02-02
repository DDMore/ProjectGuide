package dnyaneshwar.fireapp.Authentication;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import dnyaneshwar.fireapp.R;

public class resetpasswd extends AppCompatActivity  {
Button submit;
EditText emial;
    private FirebaseAuth auth;
    TextView backtext;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpasswd);
        submit=(Button)findViewById(R.id.sendEmail);
        emial=(EditText)findViewById(R.id.emailid);
        backtext=(TextView) findViewById(R.id.sendmail);
        auth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(resetpasswd.this);

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        progressDialog.setMessage("Sending Email Please wait");
        progressDialog.show();
        String Email=emial.getText().toString().trim();
        auth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(resetpasswd.this,"reset password link has been sent to your email",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(resetpasswd.this,"Email is not  Valid",Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }
        });
        backtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
});
            }




}
