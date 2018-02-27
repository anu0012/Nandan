package bumblebee.nandan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Nutrition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        Button btnmonth1_3 = (Button) findViewById(R.id.btn_month1_3);
        Button btnmonth4 = (Button) findViewById(R.id.btn_month4);
        Button btnmonth5 = (Button) findViewById(R.id.btn_month5);
        Button btnmonth6 = (Button) findViewById(R.id.btn_month6);
        Button btnmonth7 = (Button) findViewById(R.id.btn_month7);
        Button btnmonth8 = (Button) findViewById(R.id.btn_month8);
        Button btnmonth9 = (Button) findViewById(R.id.btn_month9);
        Button btnmonth10 = (Button) findViewById(R.id.vacc_guide_btn);




        btnmonth1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth1_3.class);
                startActivity(myIntent);
            }
        });

        btnmonth4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth4.class);
                startActivity(myIntent);
            }
        });

        btnmonth5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth5.class);
                startActivity(myIntent);
            }
        });

        btnmonth6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth6.class);
                startActivity(myIntent);
            }
        });

        btnmonth7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth7.class);
                startActivity(myIntent);
            }
        });

        btnmonth8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth8.class);
                startActivity(myIntent);
            }
        });

        btnmonth9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), ActivityMonth9.class);
                startActivity(myIntent);
            }
        });

        btnmonth10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), vacc_guide.class);
                startActivity(myIntent);
            }
        });


    }
}
