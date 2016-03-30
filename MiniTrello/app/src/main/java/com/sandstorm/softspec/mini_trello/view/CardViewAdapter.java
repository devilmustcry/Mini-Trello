package com.sandstorm.softspec.mini_trello.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.CustomClickListener;
import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by FTTX on 3/7/2016 AD.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private List<Card> cards;
    private CustomClickListener listener;

    public CardViewAdapter(List<Card> cards,CustomClickListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_card, parent, false);

        final CardViewHolder cvh = new CardViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, cvh.getPosition());
            }
        });

        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.title.setText(cards.get(position).getTitle());
        holder.date.setText(cards.get(position).getCreatedTime());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        public CardViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_card_title);
            date = (TextView) itemView.findViewById(R.id.list_card_date);

        }


    }

}
