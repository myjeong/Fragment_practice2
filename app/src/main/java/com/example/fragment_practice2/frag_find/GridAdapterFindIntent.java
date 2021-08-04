package com.example.fragment_practice2.frag_find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragment_practice2.R;

import java.util.List;

public class GridAdapterFindIntent extends ArrayAdapter<GridItemFindIntent> {

    List<GridItemFindIntent> items_list;
    int custom_layout_id;

    public GridAdapterFindIntent(@NonNull Context context, int resource, @NonNull List<GridItemFindIntent> objects) {
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

        TextView intent = v.findViewById(R.id.intent);
        TextView feature = v.findViewById(R.id.feature);
        TextView good = v.findViewById(R.id.good);
        TextView bad = v.findViewById(R.id.bad);

        GridItemFindIntent item = items_list.get(position);

        intent.setText(item.getIntent());
        feature.setText(item.getFeature());
        good.setText(item.getGood());
        bad.setText(item.getBad());

        return v;
    }
}
