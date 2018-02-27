package bumblebee.nandan;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Complaint extends AppCompatActivity {
    ProgressDialog pDialog;
    String name="John", email="john@xyz.com", contact="45487589", detail="Bad services";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_health_details);

        EditText nameEditText = (EditText) findViewById(R.id.person_name);
        name = nameEditText.getText().toString();

        EditText emailEditText = (EditText) findViewById(R.id.person_email);
        email = emailEditText.getText().toString();

        EditText contactEditText = (EditText) findViewById(R.id.person_contact);
        contact = contactEditText.getText().toString();

        EditText weightEditText = (EditText) findViewById(R.id.weight_description);
        detail = weightEditText.getText().toString();

        Button b = (Button) findViewById(R.id.baby_record_submit_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
