package com.example.fragment_practice2.frag_find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragment_practice2.R;

import java.util.List;

public class GridAdapterFind extends ArrayAdapter<GridItemFind> {

    List<GridItemFind> items_list;
    int custom_layout_id;

    public GridAdapterFind(@NonNull Context context, int resource, @NonNull List<GridItemFind> objects) {
        super(context, resource, objects);
        items_list = objects;
        custom_layout_id = resource;
    }

    @Override
    public int getCount() {
        return items_list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(custom_layout_id, null);
        }

        ImageView profile = v.findViewById(R.id.profile);
        TextView name = v.findViewById(R.id.name);
        TextView info = v.findViewById(R.id.info);

        GridItemFind item = items_list.get(position);

        profile.setImageResource(item.getImage());
        name.setText(item.getName());
        info.setText(item.getInfo());

        return v;
    }
}
