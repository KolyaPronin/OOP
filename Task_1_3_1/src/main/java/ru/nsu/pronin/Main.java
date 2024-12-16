package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Главный класс программы, который выполняет поиск всех вхождений подстроки
 * в большом текстовом файле и выводит абсолютные позиции этих вхождений.
 * <p>
 * Программа генерирует текстовый файл с помощью {@link LargeFileCreator}, а затем
 * ищет все вхождения подстроки в файле. Абсолютные индексы каждого вхождения
 * выводятся на экран.
 * </p>
 */
public class Main {

    /**
     * Главный метод программы.
     * <p>
     * Программа читает строку из стандартного ввода и генерирует файл с случайнымиv
     * UUID с помощью {@link LargeFileCreator}. Затем для каждой строки файла
     * ищет вхождения подстроки и выводит их абсолютные позиции.
     * </p>
     *
     * @param args параметры командной строки (не используются)
     */
    public static void main(String[] args) {

        // Создание файла в указанной директории
        File f = new File("TextFile.txt");

        // Создание сканнера для ввода подстроки
        Scanner scan = new Scanner(System.in);

        // Ввод подстроки для поиска
        String subString = scan.nextLine();

        // Генерация большого текстового файла
        File textFile = new LargeFileCreator().generate(f);

        int filePosition = 0;

        // Чтение файла построчно
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Создание экземпляра класса FindSubString для поиска подстроки
                SubstringFinder exemplar = new SubstringFinder();
                ArrayList<Integer> positions = exemplar.find(line, subString);

                // Вывод абсолютных позиций каждого вхождения
                for (int index : positions) {
                    int absoluteIndex = filePosition + index;
                    System.out.print(absoluteIndex + " ");
                }

                // Обновляем filePosition для следующей строки
                filePosition += line.length() + 1;
            }
        } catch (IOException ex) {
            // Обработка исключений при чтении файла
            System.out.println(ex.getMessage());
        }
    }


}
