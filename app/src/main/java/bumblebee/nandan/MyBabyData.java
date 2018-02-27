package bumblebee.nandan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyBabyData extends AppCompatActivity {
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_baby_data);

        Button b = (Button) findViewById(R.id.baby_record_submit_button);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        final EditText name = (EditText) findViewById(R.id.person_name);
        final EditText contact = (EditText) findViewById(R.id.person_contact);
        final EditText disease = (EditText) findViewById(R.id.disease_baby);
        final EditText loc = (EditText) findViewById(R.id.person_location);
        final EditText date = (EditText) findViewById(R.id.date_time_baby);
        final EditText weight = (EditText) findViewById(R.id.weight_description);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String titleString = name.getText().toString();
                String dateString = date.getText().toString();
                String locString = loc.getText().toString();
                String diseaseString = disease.getText().toString();
                String contactString = contact.getText().toString();
                String weightString = weight.getText().toString();


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url

                    email = user.getEmail();


                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getToken() instead.
                    String uid = user.getUid();
                }


                if (titleString.isEmpty() || dateString.isEmpty() || locString.isEmpty()) {
                    Toast.makeText(MyBabyData.this, R.string.toast_empty_fields,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyBabyData.this, R.string.toast_sent, Toast.LENGTH_SHORT).show();
                    BabyData f = new BabyData(titleString,contactString,diseaseString,weightString, locString,dateString,email);
                    myRef.child("baby_record").push().setValue(f);
                    finish();
                }

            }
        });

    }
}
