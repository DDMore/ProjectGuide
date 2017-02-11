package dnyaneshwar.fireapp;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import dnyaneshwar.fireapp.PersonalInformation.UserInfo;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private TextView textView;
    final List<Movie> filteredList = new ArrayList<>();    // ListView listVV;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressDialog pd;
    private MoviesAdapter mAdapter;
//    private EditText editText;
    SearchView searchView;
    private String branch ;
    private String year;
    private  DatabaseReference dbRef;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.te);
       // editText = (EditText) findViewById(R.id.editText);
        //  ArrayAdapter<String> adpat;
        // listVV = (ListView) findViewById(R.id.listView);
//        final String[] liststring = new String[1];
        Bundle bundle = getIntent().getExtras();
         branch = bundle.getString("Branch");
         year = bundle.getString("Year");

        //  ProgressDialog D = new ProgressDialog(MainActivity.this);
  //
        String path = "https://fireapp-d33a0.firebaseio.com/Projects/"+branch+"/"+year;
        //final List<String> listdata = new ArrayList<>();
        dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl(path);

        //final DatabaseReference bRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fireapp-d33a0.firebaseio.com/IT/FY");
        pd = new ProgressDialog(this);
        pd.setMessage(" Loading ");
        pd.setCancelable(false);
        if (!pd.isShowing())
            pd.show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListner(getApplicationContext(), recyclerView, new RecyclerTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                Bundle bundle = new Bundle();

                bundle.putString("Title", movie.getTitle());
                bundle.putString("Branch", branch);
                bundle.putString("Year", year);
            //    bundle.putStringArrayList("Email");
             //   bundle.putStringArrayList("");
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


/*
        listVV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sdmm = adapterView.getItemAtPosition(i).toString();
                Log.v("ZZZZZZZZZZZZZZZZ",sdmm);
                Intent intent = new Intent(getApplicationContext(), ShowInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("FUCK", sdmm);
                intent.putExtras(bundle);
                startActivity(intent);
     //           dmm(bRef, liststring[0]);
            }
        });
*/data();

    }
    public void data(){
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //           listdata.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                  //  String k = postSnapshot.getValue(String.class);
                   ProjectInfo k = postSnapshot.getValue(ProjectInfo.class);
                    //String kk = postSnapshot.getKey();
                    //         listdata.add(k);
                    Movie movie = new Movie(k.Project_title, k.email);
                    movieList.add(movie);
                    Log.v("ANS@@@@@@@@@", k.Project_title);
                    Log.v("ANS@@@@@@@@@", k.email);
                }
                // ArrayAdapter<String> adpat = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listdata);
                //   listVV.setAdapter(adpat);
                if(movieList.size()==0)
                {
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setMessage("No Any Projects From "+branch+" "+year);
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    alertDialog.create();
                    alertDialog.show();
                }
                recyclerView.setAdapter(mAdapter);
                if (pd.isShowing())
                    pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





    @Override
    public void onBackPressed() {
        if (pd.isShowing())
            pd.dismiss();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();


      // line 269

        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        setupSearchView(searchView);
        return super.onCreateOptionsMenu(menu);
    }
    public  void setupSearchView(SearchView ss)
    {

        ss.setOnQueryTextListener(this);
        ss.setQueryHint("Search Here");

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filteredList.clear();
        String tt=newText.toLowerCase();
        for (int i = 0; i < movieList.size(); i++) {

            final String text = movieList.get(i).getTitle().toLowerCase();
            if (text.contains(tt)) {

                filteredList.add(movieList.get(i));
            }
        }
        if(filteredList.size()==0)
        {
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            textView.setText("No results Found For '"+newText+"'");
            return false;
        }
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new MoviesAdapter(filteredList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return false;

    }
}

 /*   public void dmm()
    public void dmm(DatabaseReference bRef, final String name)
    {Query q=bRef.orderByChild("Project_title").equalTo(name);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot s :dataSnapshot.getChildren()){
                  Student  student =s.getValue(Student.class);
                    Log.v("SSSSSSSSSSSS",student.Email_id);
                    break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}
}


*/

