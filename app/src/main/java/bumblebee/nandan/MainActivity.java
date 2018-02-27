package bumblebee.nandan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Hospital> hospitalArrayList=new ArrayList<>();

    ArrayList<String> hospitalTypeArray=new ArrayList<>();
    ArrayList<String> systemOfMachinesList=new ArrayList<>();
    ArrayList<String> stateList=new ArrayList<>();
    ArrayList<String> districtList=new ArrayList<>();
    ArrayList<String> specialitiesList=new ArrayList<>();
    ArrayList<String> facilitiesList=new ArrayList<>();
    String type = "";
    String system_medi = "";
    String state = "";
    String district = "";
    String speciality = "";
    String facility = "";
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hospitalTypeArray.add("All");
        systemOfMachinesList.add("All");
        stateList.add("All");
        districtList.add("All");
        specialitiesList.add("All");
        facilitiesList.add("All");

        new HospitalAsyncTask().execute();
//        AssetManager assetManager = getApplicationContext().getAssets();
//        try {
//
//            InputStream csvStream = assetManager.open("datafile.csv");
//            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
//            CSVReader reader = new CSVReader(csvStreamReader);
//            String [] nextLine;
//            int count = 1;
//            while ((nextLine = reader.readNext()) != null && count <= 200) {    // TODO: Increase Tuples
//                // nextLine[] is an array of values from the line
//                if (count == 1)
//                {
//                    Toast.makeText(this, "Hello world!!!", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Hospital hospital = new Hospital(nextLine);
//                    hospitalArrayList.add(hospital);
//
//                    for(int i=0; i<getSpinnerItems(nextLine[2]).length;i++)
//                    {
//                        if(!hospitalTypeArray.contains(getSpinnerItems(nextLine[2])[i])){
//                            hospitalTypeArray.add(getSpinnerItems(nextLine[2])[i]);
//                        }
//                    }
//
//                    // systems_of_medicine
//                    for(int i=0; i<getSpinnerItems(nextLine[4]).length;i++)
//                    {
//                        if(!systemOfMachinesList.contains(getSpinnerItems(nextLine[4])[i])){
//                            systemOfMachinesList.add(getSpinnerItems(nextLine[4])[i]);
//                        }
//                    }
//
//                    // states
//                    for(int i=0; i<getSpinnerItems(nextLine[6]).length;i++)
//                    {
//                        if(!stateList.contains(getSpinnerItems(nextLine[6])[i])){
//                            stateList.add(getSpinnerItems(nextLine[6])[i]);
//                        }
//                    }
//
//                    // district
//                    for(int i=0; i<getSpinnerItems(nextLine[7]).length;i++)
//                    {
//                        if(!districtList.contains(getSpinnerItems(nextLine[7])[i])){
//                            districtList.add(getSpinnerItems(nextLine[7])[i]);
//                        }
//                    }
//
//                    // specialities
//                    for(int i=0; i<getSpinnerItems(nextLine[20]).length;i++)
//                    {
//                        if(!specialitiesList.contains(getSpinnerItems(nextLine[20])[i])){
//                            specialitiesList.add(getSpinnerItems(nextLine[20])[i]);
//                        }
//                    }
//
//                    // facilities
//                    for(int i=0; i<getSpinnerItems(nextLine[23]).length;i++)
//                    {
//                        if(!facilitiesList.contains(getSpinnerItems(nextLine[23])[i])){
//                            facilitiesList.add(getSpinnerItems(nextLine[23])[i]);
//                        }
//                    }
//
//                }
//                count++;
//
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
//        }


        // Hospital Type
        final Spinner type_spinner = (Spinner) findViewById(R.id.hospital_type);
        ArrayAdapter<String> hospiTypeArray= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item, hospitalTypeArray);
        hospiTypeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(hospiTypeArray);

        // systems_of_medicine_
        final Spinner system_of_medicines_spinner = (Spinner) findViewById(R.id.systems_of_medicine_spinner);
        ArrayAdapter<String> system_of_medicines_adapter= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item, systemOfMachinesList);
        system_of_medicines_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        system_of_medicines_spinner.setAdapter(system_of_medicines_adapter);

        // state
        final Spinner states_spinner = (Spinner) findViewById(R.id.state_spinner);
        ArrayAdapter<String> states_adapter= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item, stateList);
        states_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        states_spinner.setAdapter(states_adapter);

        // district
        final Spinner district_spinner = (Spinner) findViewById(R.id.district_spinner);
        ArrayAdapter<String> district_adapter= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item, districtList);
        district_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district_spinner.setAdapter(district_adapter);

        // specialities
        final Spinner specialities_spinner = (Spinner) findViewById(R.id.specialities_spinner);
        ArrayAdapter<String> specialities_adapter= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item, specialitiesList);
        specialities_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialities_spinner.setAdapter(specialities_adapter);

        // facilities
        final Spinner facilities_spinner = (Spinner) findViewById(R.id.facilities_spinner);
        ArrayAdapter<String> facilities_adapter= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item, facilitiesList);
        facilities_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facilities_spinner.setAdapter(facilities_adapter);

        // type_spinner item
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // system_medi spinner item
        system_of_medicines_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                system_medi = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // state spinner item
        states_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // district spinner item
        district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // speciality spiner item
        specialities_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                speciality = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // facility spinner item
        facilities_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                facility = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Search
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Hello world!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, hospitalListActivity.class);
                intent.putExtra("search_result",searchResult(hospitalArrayList));
                //Toast.makeText(MainActivity.this, type + " " + system_medi + " " + state + " " + district, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });



    }

    public String[] getSpinnerItems(String s){
        String arr[] = s.split(",|\\/");
        for(int i=0; i< arr.length; i++){
            if(arr[i].length() >  0) {
                arr[i] = arr[i].trim();
                if(arr[i].length() >  0)
                    arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
            }
        }
        return arr;
    }

    public ArrayList<Hospital> searchResult(ArrayList<Hospital> hospitalArrayList){
        ArrayList<Hospital> result = new ArrayList<>();

        for(int i=0; i< hospitalArrayList.size(); i++){
            Hospital currHospital = hospitalArrayList.get(i);


//            if((currHospital.getCategory().contains(type) ||  type.equals("All")) && (currHospital.getSystemsOfMedicine().contains(system_medi) ||  system_medi.equals("All"))
//                    && (currHospital.getState().contains(state) ||  state.equals("All")) && (currHospital.getDistrict().contains(district) ||  district.equals("All"))
//                    && (currHospital.getSpecialities().contains(speciality) ||  speciality.equals("All")) && (currHospital.getFacilities().contains(facility)) ||  facility.equals("All")) {
//                result.add(currHospital);
//            }


                        if((currHospital.getCategory().contains(type) ||  type.equals("All")) && (currHospital.getSystemsOfMedicine().contains(system_medi) ||  system_medi.equals("All"))
                     && (currHospital.getDistrict().contains(district) ||  district.equals("All"))
                    && (currHospital.getSpecialities().contains(speciality) ||  speciality.equals("All"))) {
                result.add(currHospital);
            }
            }

       // Toast.makeText(MainActivity.this, result.size()+"", Toast.LENGTH_SHORT).show();

        return result;
    }

    private class HospitalAsyncTask extends AsyncTask<URL,Void,ArrayList<Hospital>> {
        ArrayList<Hospital> list = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            // Showing progress dialog before sending http request
            pDialog = new ProgressDialog(
                    MainActivity.this);
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
                while ((nextLine = reader.readNext()) != null && count <= 301) {    // TODO: Increase Tuples
                    // nextLine[] is an array of values from the line
                    if (count == 1) {
                        //Toast.makeText(Home.this, "Hello world!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Hospital hospital = new Hospital(nextLine);
                        list.add(hospital);

                        for(int i=0; i<getSpinnerItems(nextLine[2]).length;i++)
                        {
                            if(!hospitalTypeArray.contains(getSpinnerItems(nextLine[2])[i])){
                                hospitalTypeArray.add(getSpinnerItems(nextLine[2])[i]);
                            }
                        }

                        // systems_of_medicine
                        for(int i=0; i<getSpinnerItems(nextLine[4]).length;i++)
                        {
                            if(!systemOfMachinesList.contains(getSpinnerItems(nextLine[4])[i])){
                                systemOfMachinesList.add(getSpinnerItems(nextLine[4])[i]);
                            }
                        }

                        // states
                        for(int i=0; i<getSpinnerItems(nextLine[6]).length;i++)
                        {
                            if(!stateList.contains(getSpinnerItems(nextLine[6])[i])){
                                stateList.add(getSpinnerItems(nextLine[6])[i]);
                            }
                        }

                        // district
                        for(int i=0; i<getSpinnerItems(nextLine[7]).length;i++)
                        {
                            if(!districtList.contains(getSpinnerItems(nextLine[7])[i])){
                                districtList.add(getSpinnerItems(nextLine[7])[i]);
                            }
                        }

                        // specialities
                        for(int i=0; i<getSpinnerItems(nextLine[20]).length;i++)
                        {
                            if(!specialitiesList.contains(getSpinnerItems(nextLine[20])[i])){
                                specialitiesList.add(getSpinnerItems(nextLine[20])[i]);
                            }
                        }

                        // facilities
                        for(int i=0; i<getSpinnerItems(nextLine[23]).length;i++)
                        {
                            if(!facilitiesList.contains(getSpinnerItems(nextLine[23])[i])){
                                facilitiesList.add(getSpinnerItems(nextLine[23])[i]);
                            }
                        }
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

}
