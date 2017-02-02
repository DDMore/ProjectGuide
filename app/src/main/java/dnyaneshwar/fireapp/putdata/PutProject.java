package dnyaneshwar.fireapp.putdata;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dnyaneshwar.fireapp.ProjectInfo;
//import dnyaneshwar.fireapp.Student;

/**
 * Created by dmm on 31/1/17.
 */

public class PutProject {     //puts the inforamtion about the project
    private FirebaseAuth firebaseAuth;
    public void putproject(ProjectInfo projectInfo){
            String branch=projectInfo.Branch;
            String year=projectInfo.Year;
            String project_name=projectInfo.Project_title;
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-d33a0.firebaseio.com/");
        dbRef.child("Projects/"+branch+"/"+year+"/"+project_name+"/").setValue(projectInfo);
    }
}