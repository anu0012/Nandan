package bumblebee.nandan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anuragsharma on 08/10/17.
 */

public class TermAdapter extends ArrayAdapter<TermObj> {

    public TermAdapter(Context context, List<TermObj> venues) {
        super(context, 0, venues);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.term_list_item, parent, false);
        }

        TermObj termObj = getItem(position);
        TextView t = (TextView) listItemView.findViewById(R.id.hospi_name_rank_textview);
        t.setText(termObj.getName());

        TextView desc_eng = (TextView) listItemView.findViewById(R.id.rank_textview);
        desc_eng.setText(termObj.getDesc());

        return listItemView;
    }
}