package fr.ynov.applpc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nathan on 03/03/2016.
 */
public class CustomAdapter extends ArrayAdapter<String[]> {
    private int list_item_view;
    private int[] list_item_views;

    public CustomAdapter(Context context, ArrayList<String[]> param, int list_item_view, int[] list_item_views) {
        super(context, 0, param);
        this.list_item_view = list_item_view;
        this.list_item_views = list_item_views;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String[] content = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(list_item_view, parent, false);
        }

        // Fill the TextView
        for(int i =0; i<list_item_views.length; i++){
            ((TextView) convertView.findViewById(list_item_views[i])).setText(content[i]);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
