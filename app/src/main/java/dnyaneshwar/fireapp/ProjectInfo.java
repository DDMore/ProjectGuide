package dnyaneshwar.fireapp;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by dmm on 31/1/17.
 */


public class ProjectInfo {
    public  String email;
    public String Project_title;

    public String Year;
    public String Project_area;

    public String Branch;
    public String Tools_used;
    public String Description;


    public ProjectInfo() {

    }


    public ProjectInfo(String project_title, String year, String project_area, String branch, String tools, String description,String Uid) {

        this.Project_title = project_title;
        // Mobile_no = mobile_no;
        this.Year = year;
        this.Project_area = project_area;
        // Email_id = email_id;
       this.Branch = branch;
        this.Tools_used = tools;
        this.Description=description;
        this.email=Uid;
    }
}
