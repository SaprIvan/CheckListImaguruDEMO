package com.example.checklistimaguru;

import java.util.ArrayList;

public class CheckListsArray {
   private ArrayList<CheckList> result;
    CheckList get(int i){
        CheckList el;
            el = result.get(i);
        return el;
    }
    Integer size(){
        return result.size();
    }
}
