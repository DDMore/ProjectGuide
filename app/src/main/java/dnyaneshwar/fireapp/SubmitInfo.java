package dnyaneshwar.fireapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dnyaneshwar.fireapp.putdata.PutProject;

public class SubmitInfo extends AppCompatActivity {
public     EditText Name;
    EditText Email;
    EditText Mobile;
    EditText Rollno;
    EditText ProjectTitle;
    EditText Toolsused;
    EditText Field;
    Spinner Year;
    Spinner Branch;
    Button Send;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ProgressDialog Dialog = new ProgressDialog(SubmitInfo.this);
        Name=(EditText)findViewById(R.id.name);
        Email=(EditText)findViewById(R.id.email);
        Mobile=(EditText)findViewById(R.id.mobile);
        Rollno=(EditText)findViewById(R.id.prn);
        ProjectTitle=(EditText)findViewById(R.id.project);
        Toolsused=(EditText)findViewById(R.id.tools);
        Field=(EditText)findViewById(R.id.field);
        Branch=(Spinner)findViewById(R.id.branch);
        Year=(Spinner)findViewById(R.id.year);
        Send=(Button)findViewById(R.id.Submit);
        Rollno.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        Branch.setPrompt("Select Your Branch");
        Year.setPrompt("Select Year Of Project ");
        Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              //  String name,String rollno,String project_title,String mobile_no,String year,String project_area,String email_id,String branch,String tools
            if(Name.getText().length()==0)
            {
                Name.setError("Name Can Not Be Empty");
                return;
            }
                if(Rollno.getText().length()<10)
                {
                    Rollno.setError("Enter Correct PRN NO ");
                    Rollno.requestFocus();
                    return;

                }
                if(ProjectTitle.getText().length()==0)
                {
                ProjectTitle.setError("This Field Can Not Be Empty");
                    ProjectTitle.requestFocus();
                    return;

                }
                if(isValidMobile(Mobile.getText().toString())==false)
                {
                    Mobile.setError("Enter Valid Mobile No");
                    Mobile.requestFocus();
                    return;

                }
                if(Field.getText().length()==0)
                {
                    Field.setError("This Can Not Be Empty");
                    Field.requestFocus();
                    return;

                }
                if(isEmailValid(Email.getText().toString())==false)
                {
                    Email.setError("Enter Valid Email");
                    Email.requestFocus();
                    return;

                }
                if(Toolsused.getText().length()==0)
                {
                    Toolsused.setError("It Can Not be Empty");
                    Toolsused.requestFocus();
                    return;

                }

                new MyTask().execute();




            }
        });}
    public  boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    private boolean isValidMobile(String phone2)
    {
        boolean check=false;
        if(phone2.matches("[0-9]+"))
        {
            if(phone2.length() < 6 || phone2.length() > 13)
            {
                check = false;
                //txtPhon.setError("Not Valid Number");
            }
            else
            {
                check = true;
            }
        }
        else
        {
            check=false;
        }
        return check;
    }

    //String project_title, String year, String project_area, String branch, String tools, String description
   /* public String Project_title;

    public String Year;
    public String Project_area;

    public String Branch;
    public String Tools_used;
    public String Description;
*/
    class MyTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog Asycdialog = new ProgressDialog(SubmitInfo.this);
        ProjectInfo projectInfo=new ProjectInfo(ProjectTitle.getText().toString()
                ,Year.getSelectedItem().toString(),Field.getText().toString().trim(),Branch.getSelectedItem().toString(),Toolsused.getText().toString(),"Descptrin", firebaseAuth.getCurrentUser().getUid().toString());

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Asycdialog.setMessage("Loading...");
           if(!Asycdialog.isShowing())
            Asycdialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            PutProject data=new PutProject();
            Log.v("ssss","sss");
     data.putproject(projectInfo);
            // do the task you want to do. This will be executed in background.

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            if(Asycdialog.isShowing())
            Asycdialog.dismiss();
        }
    }


}

