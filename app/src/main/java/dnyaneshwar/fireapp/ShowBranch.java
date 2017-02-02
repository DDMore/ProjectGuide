package dnyaneshwar.fireapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowBranch extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_branch);
        ActionBar actionBar=getActionBar();


        final ImageView IT,CSE,MECH,EN,ELE,CIVIL;
        IT=(ImageView)findViewById(R.id.it);
        CSE=(ImageView)findViewById(R.id.cse);
        MECH=(ImageView)findViewById(R.id.mech);
        CIVIL=(ImageView)findViewById(R.id.civil);
        ELE=(ImageView)findViewById(R.id.ele);
        EN=(ImageView)findViewById(R.id.en);
        IT.setClickable(true);
        CSE.setClickable(true);
        IT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                IT.setSelected(arg1.getAction()== MotionEvent.ACTION_DOWN);
                Toast.makeText(ShowBranch.this,"Yell Ya Fucking Right",Toast.LENGTH_LONG).show();
                show(IT,"IT");            //PopupMenu p=new PopupMenu(getBaseContext());

                return true;
            }
        });
        CSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(CSE,"CSE");
            }
        });
        MECH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(MECH,"MECHANICAL");

            }
        });
        CIVIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(CIVIL,"CIVIL");
            }

        });
        EN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(EN,"ELECTRONICS");

            }
        });
        ELE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(ELE,"ELECTRICAL");
            }
        });
    }
public void show(ImageView img, final String bb){
    PopupMenu popup = new PopupMenu(ShowBranch.this, img);

    /** Adding menu items to the popumenu */
    popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

    /** Defining menu item click listener for the popup menu */
    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            changeActivity(bb,item.getTitle().toString());
            return true;
        }
    });

    /** Showing the popup menu */
    popup.show();
}
   public void changeActivity(String branch,String Year)
   {

       Bundle b=new Bundle();
    Intent i=new Intent(ShowBranch.this,MainActivity.class);
       b.putString("Branch",branch);
       b.putString("Year",Year);
       i.putExtras(b);
       startActivity(i);


   }

}
