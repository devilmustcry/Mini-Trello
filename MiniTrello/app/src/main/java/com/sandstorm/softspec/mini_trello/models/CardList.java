package com.sandstorm.softspec.mini_trello.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardList implements Serializable {
    private static List<Card> cards;
    private String list_title;
    private static long id = 1;


    public CardList(String title){

        System.out.println(id);
        this.list_title = title;
        cards = new ArrayList<Card>();
        id++;

    }

    public String getListTitle(){
        return this.list_title;
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

    public static long getId() {
        return id;
    }

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
        int index = 0;
        for(Card card : cards){
            if(card.equals(other)){
                break;
            }
            index++;
        }
        cards.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardList cardList = (CardList) o;

        return this.id == cardList.getId();

    }

    @Override
    public int hashCode() {
        return list_title != null ? list_title.hashCode() : 0;
    }
}
