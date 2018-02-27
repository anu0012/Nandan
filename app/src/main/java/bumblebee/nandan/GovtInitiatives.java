package bumblebee.nandan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GovtInitiatives extends AppCompatActivity {
    private GovtSchemesAdapter mAdapter;
    private ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_initiatives);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        l = (ListView) findViewById(R.id.govt_init_listview);

        final List<GovtSchemeObjectClass> schemes = new ArrayList<>();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

               // Toast.makeText(GovtInitiatives.this,dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                if (dataSnapshot.getKey().equals("GovtSchemes")) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        //Toast.makeText(GovtInitiatives.this,messageSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                        String name = (String) messageSnapshot.getKey();
                        String desc = (String) messageSnapshot.child("description").getValue();

                        GovtSchemeObjectClass d = new GovtSchemeObjectClass(name, desc, messageSnapshot.getKey());
                        schemes.add(d);
                        mAdapter = new GovtSchemesAdapter(GovtInitiatives.this, schemes);
                        l.setAdapter(mAdapter);
                    }
                    View loadingIndicator = findViewById(R.id.schemes_loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);
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

                Toast.makeText(GovtInitiatives.this, R.string.toast_unable_to_fetch,
                        Toast.LENGTH_SHORT).show();
            }
        };
        myRef.addChildEventListener(childEventListener);

        mAdapter = new GovtSchemesAdapter(this, schemes);
        l.setAdapter(mAdapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GovtSchemeObjectClass b = mAdapter.getItem(position);
                String arr[] = {"Scheme name: " + b.getName(), "Description: " + b.getDetails()};
                AlertDialog.Builder builder = new AlertDialog.Builder(GovtInitiatives.this);
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
    }

}
