package ru.nsu.pronin;

public class ZFunction {

    int[] Zfunk(String array){
        char[] charArray = array.toCharArray();
        int len = charArray.length;
        int[] Zarray = new int[len];

        for(int i = 1, l = 0, r = 0; i < len; i++){
            if(i <= r)
                Zarray[i] = Math.min(r - i + 1, Zarray[i - l]);
            while (i + Zarray[i] < len && charArray[Zarray[i]] == charArray[i + Zarray[i]])
                Zarray[i]++;

            if (i + Zarray[i] - 1 > r){
                r = i + Zarray[i] - 1;
                l = i;
            }
        }

        return Zarray;
    }

}