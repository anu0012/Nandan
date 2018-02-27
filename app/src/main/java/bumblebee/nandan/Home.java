package bumblebee.nandan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<Hospital> hospitalArrayList=new ArrayList<>();
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new HospitalAsyncTask().execute();
        Button searchHospitalButton = (Button) findViewById(R.id.search_hospital_button);
        Button medicineButton = (Button) findViewById(R.id.medicine_button);
        Button rankingButton = (Button) findViewById(R.id.ranking_button);
        Button vacUploadButton = (Button) findViewById(R.id.vacc_upload_button);
        Button findOnMapButton = (Button) findViewById(R.id.search_on_map_button);
//        Button feedbackButton = (Button) findViewById(R.id.feedback_button);
//        Button aboutButton = (Button) findViewById(R.id.about_button);
        Button govtInitiatives = (Button) findViewById(R.id.govt_initiatives_button);

        searchHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                intent.putExtra("objects", hospitalArrayList);
                startActivity(intent);
            }
        });

        vacUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, vacc_activity.class);
                startActivity(intent);
            }
        });

//        feedbackButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, Feedback.class);
//                startActivity(intent);
//            }
//        });

        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Terms.class);
                startActivity(intent);
            }
        });

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Nutrition.class);
                startActivity(intent);
            }
        });

        findOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MapMedi.class);
                intent.putExtra("objects", hospitalArrayList);
                //Toast.makeText(Home.this,hospitalArrayList.size()+"", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        govtInitiatives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, GovtInitiatives.class);
                startActivity(intent);
            }
        });
//
//        aboutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String arr[] = {"This app helps you to locate nearest hospitals and medical centers. Also you can find details about medicines in both Hindi and English. " +
//                        "Feedback forms are given so that you can share your experience with others also. \n"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
//                builder.setTitle("About the app")
//                        .setItems(arr, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // The 'which' argument contains the index position
//                                // of the selected item
//                            }
//
//                            ;
//                        });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private class HospitalAsyncTask extends AsyncTask<URL,Void,ArrayList<Hospital>> {
        ArrayList<Hospital> list = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            // Showing progress dialog before sending http request
            pDialog = new ProgressDialog(
                    Home.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected ArrayList<Hospital> doInBackground(URL... urls) {

            AssetManager assetManager = getApplicationContext().getAssets();
            try {

                InputStream csvStream = assetManager.open("datafile.csv");
                InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
                CSVReader reader = new CSVReader(csvStreamReader);
                String[] nextLine;
                int count = 1;
                while ((nextLine = reader.readNext()) != null && count <= 200) {    // TODO: Increase Tuples
                    // nextLine[] is an array of values from the line
                    if (count == 1) {
                        //Toast.makeText(Home.this, "Hello world!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Hospital hospital = new Hospital(nextLine);
                        list.add(hospital);
                    }
                    count++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            hospitalArrayList = list;
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<Hospital> hospital) {
            //hospitalArrayList = hospital;
            //Toast.makeText(Home.this,list.size()+"", Toast.LENGTH_SHORT).show();
            pDialog.dismiss();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_signout) {
            //mAuth.signOut();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this, Login.class));
            finish();
        } else if (id == R.id.nav_about){
            String arr[] = {"This app helps you to locate nearest hospitals and medical centers. Also you can find guidelines for both mother and child. " +
                    "Vaccination forms are given so that you can keep the vaccination record of your child. \n"};
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle("About the app")
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
        else if (id == R.id.nav_settings) {
            startActivity(new Intent(Home.this, MyBabyData.class));

        }
        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Share this app with your friends and family. Download Nandan App";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
