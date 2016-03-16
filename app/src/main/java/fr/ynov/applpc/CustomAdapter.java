package fr.ynov.applpc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nathan on 03/03/2016.
 */
public class CustomAdapter {
    private int list_item_view;
    private int[] list_item_views;
    private int[] list_item_views_colors;
    private LinearLayout list_view;
    private Context context;

    public CustomAdapter(Context context, LinearLayout list_view, int list_item_view, int[] list_item_views, int[] list_item_views_colors) {
        this.list_item_view = list_item_view;
        this.list_item_views = list_item_views;
        this.list_item_views_colors = list_item_views_colors;
        this.list_view = list_view;
        this.context = context;
    }

    public void addAll(ArrayList<String[]> params){

        // For each element
        for(int i=0; i<params.size(); i++) {
            View item_view = View.inflate(context, list_item_view, null); // Inflate an item view
            String[] currentArray = params.get(i); // Get current element
            for(int j =0; j<currentArray.length; j++) { // For each text
                ((TextView) item_view.findViewById(list_item_views[j])).setText(currentArray[j]);//Set text

                if (j < list_item_views_colors.length) {
                    ((TextView) item_view.findViewById(list_item_views[j])).setTextColor(list_item_views_colors[j]);//Set color
                }
            }

            if(i == params.size() - 1){
                ((LinearLayout)item_view).removeView(item_view.findViewById(R.id.list_layer)); // If it's the last item remove the bottom layer
            }

            list_view.addView(item_view);//Add the view in the Layout
        }
    }
}
