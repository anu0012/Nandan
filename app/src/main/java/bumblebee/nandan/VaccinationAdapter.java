package bumblebee.nandan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class VaccinationAdapter extends ArrayAdapter<MyVaccination> {

    public VaccinationAdapter(Context context, ArrayList<MyVaccination> venues) {
        super(context, 0, venues);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.myvaccination_list_item, parent, false);
        }

        MyVaccination myVaccination = getItem(position);
        TextView t = (TextView) listItemView.findViewById(R.id.medicine_name);
        t.setText(myVaccination.getName());

        TextView desc_eng = (TextView) listItemView.findViewById(R.id.medicine_description);
        desc_eng.setText(myVaccination.getLocation());

        return listItemView;
    }
}
