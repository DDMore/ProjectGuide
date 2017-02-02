package dnyaneshwar.fireapp;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by dmm on 31/1/17.
 */


public class ProjectInfo {
    public  String uid;
    public String Project_title;

    public String Year;
    public String Project_area;

    public String Branch;
    public String Tools_used;
    public String Description;


    public ProjectInfo() {

    }

    public ProjectInfo(String project_title, String year, String project_area, String branch, String tools, String description,String Uid) {

        Project_title = project_title;
        // Mobile_no = mobile_no;
        Year = year;
        Project_area = project_area;
        // Email_id = email_id;
        Branch = branch;
        Tools_used = tools;
        Description=description;
        uid=Uid;
    }
}
