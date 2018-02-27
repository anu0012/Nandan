package bumblebee.nandan;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyVaccinationDetails extends AppCompatActivity {

    private ArrayList<MyVaccination> myVaccinationArrayList = new ArrayList<>();
    private VaccinationAdapter mAdapter;
    private ListView l;
    private String name="";
    private String user_email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvaccination_info);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            user_email = user.getEmail();


            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }


        l = (ListView) findViewById(R.id.medicine_listview);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                // Toast.makeText(GovtInitiatives.this,dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                if (dataSnapshot.getKey().equals("vacc_record")) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        //Toast.makeText(GovtInitiatives.this,messageSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                        String email = (String) messageSnapshot.child("mEmail").getValue();

                        if(email.equals(user_email)){
                            String date = (String) messageSnapshot.child("mDate").getValue();
                            String name = (String) messageSnapshot.child("mName").getValue();
                            String loc = (String) messageSnapshot.child("mLocation").getValue();
                            MyVaccination d = new MyVaccination(name, date, loc);
                            myVaccinationArrayList.add(d);
                            mAdapter = new VaccinationAdapter(MyVaccinationDetails.this, myVaccinationArrayList);
                            l.setAdapter(mAdapter);
                        }

                    }

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

                String key = dataSnapshot.getKey();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

                String key = dataSnapshot.getKey();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MyVaccinationDetails.this, R.string.toast_unable_to_fetch,
                        Toast.LENGTH_SHORT).show();
            }
        };
        myRef.addChildEventListener(childEventListener);

        mAdapter = new VaccinationAdapter(this, myVaccinationArrayList);
        l.setAdapter(mAdapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyVaccination b = mAdapter.getItem(position);
                String arr[] = {"Vaccine name: " + b.getName(), "Location: " + b.getLocation(), "Date: " + b.getDate()};
                AlertDialog.Builder builder = new AlertDialog.Builder(MyVaccinationDetails.this);
                builder.setTitle(b.getName())
                        .setItems(arr, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                            }

                            ;
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        mAdapter.clear();


        VaccinationAdapter vaccinationAdapter = new VaccinationAdapter(this, myVaccinationArrayList);
        ListView listView = (ListView) findViewById(R.id.medicine_listview);
        listView.setAdapter(vaccinationAdapter);

    }
}
