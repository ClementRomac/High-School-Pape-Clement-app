package fr.ynov.applpc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nathan on 03/03/2016.
 */
public class ModelInfoAdapter extends ArrayAdapter<ModelInfo> {
    public ModelInfoAdapter(Context context, ArrayList<ModelInfo> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ModelInfo modelinfo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        // Populate the data into the template view using the data object
        title.setText(modelinfo.title);
        text.setText(modelinfo.text);
        // Return the completed view to render on screen
        return convertView;
    }
}
