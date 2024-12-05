package ru.nsu.pronin;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        String subString = scan.nextLine();
        File TextFile = new File("C:/Users/kolya/OneDrive/Рабочий стол" +
                "/папки/уроки/НГУ УЧЕБА/2курс/JuniorJavaJeveloper" +
                " (JJJ)/git/OOP/Task_1_3_1/TextFile.txt") ;

        // File TextFile = new CreateReallyLargeFile().generate();

        var filePosition = 0;
        try(BufferedReader br = new BufferedReader (new FileReader(TextFile))) {
            String s;
            while((s=br.readLine()) != null){

                FindSubString exemplar = new FindSubString();
                ArrayList<Integer> arr = exemplar.Find(s, subString);

                for (int index : arr) {
                    int absoluteIndex = filePosition + index;
                    System.out.print(absoluteIndex + " ");
                }
                filePosition += s.length() + 1;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }
}