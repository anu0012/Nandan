package bumblebee.nandan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;


public class HospitalAdapter extends ArrayAdapter<Hospital> {
    static int i = 0;
    public HospitalAdapter(Context context, ArrayList<Hospital> venues){
        super(context, 0, venues);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.hospital_list_item, parent, false);
        }

        Hospital hospital = getItem(position);
        TextView t = (TextView) listItemView.findViewById(R.id.hospital_name_textView);
        t.setText(hospital.getName());

        TextDrawable drawable = null;
        ColorGenerator generator = ColorGenerator.MATERIAL;

        char ch = Character.toUpperCase(hospital.getName().charAt(0));
        if (ch == 'a' || ch == 'A' || ch == 'd' || ch == 'D' || ch == 'F' || ch == 'f')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xffffd54f); //yellow
        else if (ch == 'b' || ch == 'B' || ch == 'c' || ch == 'C' || ch == 'e' || ch == 'E')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xffba68c8);
        else if (ch == 'g' || ch == 'G' || ch == 'x' || ch == 'X' || ch == 'z' || ch == 'Z')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xffffb74d);
        else if (ch == 'y' || ch == 'Y' || ch == 'o' || ch == 'O' || ch == 'h' || ch == 'H')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xff81c784); //green
        else if (ch == 'm' || ch == 'M' || ch == 'n' || ch == 'N' || ch == 'j' || ch == 'J')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xffff8a65);
        else if (ch == 'p' || ch == 'P' || ch == 'u' || ch == 'U' || ch == 'w' || ch == 'W')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xff64b5f6); //light blue
        else if (ch == 'i' || ch == 'I' || ch == 'k' || ch == 'K' || ch == 'l' || ch == 'L')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xffa1887f); //gray
        else if (ch == 'q' || ch == 'Q' || ch == 't' || ch == 'T' || ch == 's' || ch == 'S')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xffe57373); //salmon
        else if (ch == 'r' || ch == 'R' || ch == 'v' || ch == 'V')
            drawable = TextDrawable.builder()
                    .buildRound(Character.toString(ch), 0xff90a4ae); //brown
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.text_drawable_imageView);
        imageView.setImageDrawable(drawable);
        return listItemView;
    }



//    @Override
//    public int getCount() {
//        return 20 + i;
//    }
}