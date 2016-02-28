package com.sandstorm.softspec.mini_trello.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardList implements Serializable {
    private List<Card> cards;
    private String list_title;


    public CardList(String title){
        this.list_title = title;
        cards = new ArrayList<Card>();

    }

    public String getListTitle(){
        return this.list_title;
    }

    public void addCard(Card newCard){
        this.cards.add(newCard);
    }

    public List<Card> loadList(){
        return cards;
    }
}
