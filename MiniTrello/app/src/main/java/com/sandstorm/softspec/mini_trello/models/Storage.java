package com.sandstorm.softspec.mini_trello.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class Storage {

    private static Storage instance;
    private static List<CardList> mainList;



    private Storage(){
        mainList = new ArrayList<CardList>();
    }

    public static Storage getInstance(){
        if(instance == null){

            instance = new Storage();
        }

        return instance;
    }

    public void addList(CardList cardList){
        mainList.add(cardList);

    }

    public List<CardList> loadList() {

        return mainList;
    }

    public void clearMainList(){
        mainList.clear();
    }

    public CardList findList(CardList other) {

        for(CardList list : mainList) {
            if(list.equals(other)) {
                return list;
            }
        }
        return null;
    }

    public void deleteList(CardList cardList){
        mainList.remove(cardList);
    }




}
