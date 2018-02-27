package bumblebee.nandan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class hospitalListActivity extends AppCompatActivity {
    private ArrayList<Hospital> hospitalArrayList;
    private HospitalAdapter hospitalAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.hospital_listview);
        hospitalArrayList = new ArrayList<>();
        hospitalArrayList = (ArrayList<Hospital>) intent.getSerializableExtra("search_result");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(hospitalListActivity.this, "Hello world!!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(hospitalListActivity.this, HospitalDetail.class);
                i.putExtra("object", hospitalAdapter.getItem(position));
                startActivity(i);
            }
        });

        hospitalAdapter = new HospitalAdapter(this, hospitalArrayList);
        listView.setAdapter(hospitalAdapter);

        Button showMoreButton = (Button) findViewById(R.id.show_more_button);
        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospitalAdapter.i += 20;
                hospitalAdapter.notifyDataSetChanged();
            }
        });

    }
}
