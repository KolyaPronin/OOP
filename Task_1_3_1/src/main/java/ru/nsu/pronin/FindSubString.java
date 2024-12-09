package ru.nsu.pronin;

import java.util.ArrayList;

public class FindSubString {

    public ArrayList<Integer> Find(String Str, String SubStr){

        ArrayList<Integer> answer = new ArrayList<>();

        ZFunction exempl = new ZFunction();
        String finalStr =  SubStr + '#' + Str;
        int[] Zarray = exempl.Zfunk(finalStr);

        int lenSubStr = SubStr.length();

        for (int i = lenSubStr + 1;i < Zarray.length; i++){
            if (Zarray[i] == lenSubStr) {
                answer.add(i - (lenSubStr) - 1);
            }
        }

        return answer;
    }
}
