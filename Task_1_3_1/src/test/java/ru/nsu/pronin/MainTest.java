package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class FindSubStringTest {

    private final FindSubString finder = new FindSubString();

    @Test
    void testSubStringFoundOnce() {
        String mainString = "abracadabra";
        String subString = "bra";
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1); // Первое вхождение "bra" начинается с индекса 1
        expected.add(8); // Второе вхождение "bra" начинается с индекса 8

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Не удалось найти все вхождения подстроки");
    }

    @Test
    void testSubStringNotFound() {
        String mainString = "abracadabra";
        String subString = "xyz";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Подстрока, которой нет, не должна быть найдена");
    }

    @Test
    void testEmptySubString() {
        String mainString = "abracadabra";
        String subString = "";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Пустая подстрока не должна находиться");
    }

    @Test
    void testEmptyMainString() {
        String mainString = "";
        String subString = "abc";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "В пустой строке не должно быть вхождений");
    }

    @Test
    void testBothStringsEmpty() {
        String mainString = "";
        String subString = "";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Две пустые строки не должны давать вхождений");
    }

    @Test
    void testMultipleOccurrences() {
        String mainString = "aaaaa";
        String subString = "aa";
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0); // Вхождение 1
        expected.add(1); // Вхождение 2
        expected.add(2); // Вхождение 3
        expected.add(3); // Вхождение 4

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Не удалось корректно найти все перекрывающиеся вхождения");
    }
}
