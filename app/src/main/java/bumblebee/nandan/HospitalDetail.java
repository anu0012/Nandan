package bumblebee.nandan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HospitalDetail extends AppCompatActivity {
    TextView textViewName, textViewAddress, textViewPincode, textViewTelephone, textViewMobile,
            textViewEmergency, textViewAmbulance, textViewTollfree, textViewHelpLine, textViewEmail, textViewWebsite
            , textViewBads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        Hospital hospital = (Hospital) getIntent().getSerializableExtra("object");

        textViewName = (TextView) findViewById(R.id.details_name_textView);
        textViewName.setText(hospital.getName());

        textViewAddress = (TextView) findViewById(R.id.details_address_textView);
        textViewAddress.setText(hospital.getAddress());

        textViewPincode = (TextView) findViewById(R.id.details_pincode_textView);
        textViewPincode.setText(hospital.getPincode());

        textViewTelephone = (TextView) findViewById(R.id.details_telephone_textView);
        textViewTelephone.setText(hospital.getTelephone().split(",")[0]);

        textViewMobile = (TextView) findViewById(R.id.details_mobile_textView);
        textViewMobile.setText(hospital.getMobilenumber().split(",")[0]);

        textViewEmergency = (TextView) findViewById(R.id.details_emergency_textView);
        textViewEmergency.setText(hospital.getEmergencynum());

        textViewAmbulance = (TextView) findViewById(R.id.details_ambulance_textView);
        textViewAmbulance.setText(hospital.getAmbulancenum());

        textViewTollfree = (TextView) findViewById(R.id.details_tollfree_textView);
        textViewTollfree.setText(hospital.getTollfree());

        textViewHelpLine = (TextView) findViewById(R.id.details_helpline_textView);
        textViewHelpLine.setText(hospital.getHelpline());

        textViewEmail = (TextView) findViewById(R.id.details_email_textView);
        textViewEmail.setText(hospital.getPrimaryemail());

        textViewWebsite = (TextView) findViewById(R.id.details_website_textView);
        textViewWebsite.setText(hospital.getWebsite());

        textViewBads = (TextView) findViewById(R.id.details_numofbads_textView);
        textViewBads.setText(hospital.getTotalnumofbads());


    }

    public void doActivity(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) v;
                if(t.getText().equals("Address")){
                    String map = "http://maps.google.co.in/maps?q=" + textViewAddress.getText();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                    startActivity(intent);
                }
                else if(t.getText().equals("Telephone") || t.getText().equals("Mobile") || t.getText().equals("Emergency No.") || t.getText().equals("Ambulance")
                        || t.getText().equals("TollFree") || t.getText().equals("Helpline")){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + textViewTelephone.getText()));
                    startActivity(intent);
                }
                else if(t.getText().equals("Email")){
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{textViewEmail.getText().toString()});
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(HospitalDetail.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(t.getText().equals("Website")){
                    String url = textViewWebsite.getText().toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });
    }
}
