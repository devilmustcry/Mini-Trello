package com.sandstorm.softspec.mini_trello.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by FTTX on 3/7/2016 AD.
 */
public class CardViewAdapter {

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        public CardViewHolder(View itemView) {
            super(itemView);

        }
    }

}
