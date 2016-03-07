package com.sandstorm.softspec.mini_trello.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.CustomClickListener;
import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;

import java.util.List;

/**
 * Created by FTTX on 3/7/2016 AD.
 */
public class CardViewListAdapter extends RecyclerView.Adapter<CardViewListAdapter.CardListViewHolder> {

    List<CardList> cardLists;
    private CustomClickListener listener;

    public CardViewListAdapter(List<CardList> cardLists, CustomClickListener listener) {
        this.cardLists = cardLists;
        this.listener = listener;
    }

    @Override
    public CardListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.main_card, parent, false);
        final CardListViewHolder cvh = new CardListViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v,cvh.getPosition());
            }
        });
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardListViewHolder holder, int position) {
        holder.listTitle.setText(cardLists.get(position).getListTitle());
    }

    @Override
    public int getItemCount() {
        return cardLists.size();
    }

    public static class CardListViewHolder extends RecyclerView.ViewHolder {

        TextView listTitle;

        public CardListViewHolder(View itemView) {
            super(itemView);
            listTitle = (TextView)itemView.findViewById(R.id.main_list_title);
        }
    }
}
