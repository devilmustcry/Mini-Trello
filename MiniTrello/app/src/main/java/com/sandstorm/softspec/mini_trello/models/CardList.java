package com.sandstorm.softspec.mini_trello.models;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardList implements Serializable {
    private List<Card> cards;
    private Tag tags;
    private String list_title;
//    private static long id = 1;



    public CardList(String title,String tag){

//        System.out.println(id);
        this.list_title = title;
        cards = new ArrayList<Card>();
//        id++;


        String [] c = tag.split(",");
        tags = new Tag(tag);
//        addTag(tag);


    }

//    public void addTag(String tag){
//        String [] c = tag.split(",");
//        for(int i = 0; i < c.length; i++) {
//            Storage.getInstance().addTag(new Tag(c[i]));
//        }
//    }




    public String getListTitle(){
        return this.list_title;
    }

    public Tag getTag() {
        return tags;
    }

    public List<Card> getList() {
        return cards;
    }

    public void addCard(Card newCard){
        this.cards.add(newCard);
    }

    public List<Card> loadList(){
        return cards;
    }

//    public static long getId() {
//        return id;
//    }

    public void clear() {
        cards.clear();
    }

    public Card findCard(Card other) {

        for(Card card : cards) {
            if(card.equals(other))
                return card;
        }
        return null;
    }

    public void deleteCard(Card other){
        cards.remove(other);
    }

    public void setListTitle(String newListTitle){
        this.list_title = newListTitle;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardList cardList = (CardList) o;

        return this.hashCode() == cardList.hashCode();

    }

    @Override
    public int hashCode() {
        return list_title != null ? list_title.hashCode() : 0;
    }
}
