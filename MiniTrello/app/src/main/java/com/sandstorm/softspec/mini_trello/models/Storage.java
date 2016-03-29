package com.sandstorm.softspec.mini_trello.models;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class Storage {

    private static Storage instance;
    private List<CardList> mainList;
//    private List<Tag> tagList;



    private Storage(){
        mainList = new ArrayList<CardList>();
//        tagList = new ArrayList<Tag>();
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

//    public boolean tagExistence(Tag tag){
////        Log.i("Tag","Check");
//        if(tagList.size()==0)
//            return false;
//        for(Tag t : tagList){
//            if(t.getTagName().equalsIgnoreCase(tag.getTagName())){
//                return true;
//            }
//        }
//        return false;
//    }

//    public void addTag(Tag tag){
//        if(!tagExistence(tag)){
//            tagList.add(tag);
//        }
//    }
//
//    public List<Tag> getTagList(){
//        return tagList;
//    }



}
