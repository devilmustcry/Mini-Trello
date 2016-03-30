package com.sandstorm.softspec.mini_trello.models;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zenza007 on 2/27/16.
 */
public class Storage {

    private static Storage instance;
    private List<CardList> mainList;
    private List<Tag> tagList;



    private Storage(){
        mainList = new ArrayList<CardList>();
        tagList = new ArrayList<Tag>();
        tagList.add(new Tag("None"));
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

    public int findListIndex(CardList other) {

        for(int i = 0;i < mainList.size(); i++) {
            if(mainList.get(i).equals(other)) {
                Log.i("Return Index",i+"");
                return i;
            }
        }
        return -1;
    }

    public void deleteList(CardList cardList){

        mainList.remove(cardList);
    }

    public boolean tagExistence(Tag tag){
//        Log.i("Tag","Check");
        if(tagList.size()==0)
            return false;
        for(Tag t : tagList){
            if(t.getTagName().equalsIgnoreCase(tag.getTagName())){
                return true;
            }
        }
        return false;
    }

    public void addTag(Tag tag){
        if(!tagExistence(tag)){
            tagList.add(tag);
        }
    }

    public List<Tag> getTagList(){
        return tagList;
    }



    public List<CardList> getListFromTag(Tag tag){
        List<CardList> tempList = new ArrayList<CardList>();
        for(CardList l : mainList){
            if(l.getTag().equals(tag)){
                tempList.add(l);
            }
        }
        return tempList;
    }



}
