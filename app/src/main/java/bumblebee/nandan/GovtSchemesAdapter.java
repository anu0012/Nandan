package bumblebee.nandan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuragsharma on 07/10/17.
 */

public class GovtSchemesAdapter extends ArrayAdapter<GovtSchemeObjectClass> {

    public GovtSchemesAdapter(Context context, List<GovtSchemeObjectClass> venues) {
        super(context, 0, venues);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.govt_init_list_item, parent, false);
        }

        GovtSchemeObjectClass govtSchemeObjectClass = getItem(position);
        TextView t = (TextView) listItemView.findViewById(R.id.govt_scheme_name_textview);
        t.setText(govtSchemeObjectClass.getName());

        TextView desc_eng = (TextView) listItemView.findViewById(R.id.govt_scheme_detail_textview);
        desc_eng.setText(govtSchemeObjectClass.getDetails());

        return listItemView;
    }
}