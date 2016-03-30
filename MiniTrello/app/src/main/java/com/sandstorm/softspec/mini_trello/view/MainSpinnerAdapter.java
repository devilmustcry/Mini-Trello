package com.sandstorm.softspec.mini_trello.view;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Tag;

import java.util.List;

/**
 * Created by FTTX on 3/30/2016 AD.
 */
public class MainSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<Tag> tags;
    private Activity activity;

    public MainSpinnerAdapter(Activity activity, List<Tag> tags){
        this.activity = activity;
        this.tags = tags;
    }

    public int getCount() {
        return tags.size();
    }

    public Object getItem(int position) {
        return tags.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View spinView;
        if( convertView == null ){
            LayoutInflater inflater = activity.getLayoutInflater();
            spinView = inflater.inflate(R.layout.spinner, null);
        } else {
            spinView = convertView;
        }
        Tag tag = (Tag) getItem(position);
        TextView t1 = (TextView) spinView.findViewById(R.id.field1);
        t1.setText(tag.toString());
        return spinView;
    }
}
