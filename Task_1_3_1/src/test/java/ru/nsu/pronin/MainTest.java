package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса FindSubString.
 * Проверяет правильность работы метода Find по нахождению всех вхождений подстроки в строке.
 */
class FindSubStringTest {

    private final FindSubString finder = new FindSubString();

    /**
     * Тест для случая, когда подстрока найдена ровно один раз.
     * Проверяет, что метод находит все вхождения подстроки "bra" в строке "abracadabra".
     */
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

    /**
     * Тест для случая, когда подстрока не найдена.
     * Проверяет, что метод возвращает пустой список, если подстрока не найдена в строке.
     */
    @Test
    void testSubStringNotFound() {
        String mainString = "abracadabra";
        String subString = "xyz";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Подстрока, которой нет, не должна быть найдена");
    }

    /**
     * Тест для случая, когда основная строка пуста.
     * Проверяет, что метод возвращает пустой список, если основная строка пуста.
     */
    @Test
    void testEmptyMainString() {
        String mainString = "";
        String subString = "abc";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "В пустой строке не должно быть вхождений");
    }

    /**
     * Тест для случая, когда обе строки пусты.
     * Проверяет, что метод возвращает пустой список для двух пустых строк.
     */
    @Test
    void testBothStringsEmpty() {
        String mainString = "";
        String subString = "";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> result = finder.Find(mainString, subString);

        assertEquals(expected, result, "Две пустые строки не должны давать вхождений");
    }

    /**
     * Тест для случая, когда подстрока имеет несколько перекрывающихся вхождений в строку.
     * Проверяет, что метод корректно находит все вхождения подстроки "aa" в строке "aaaaa".
     */
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


    /**
     * Тест для проверки создания большого файла и корректности его содержимого.
     * <p>
     * Этот тест генерирует файл с помощью {@link CreatLargeFile}, проверяет его создание,
     * что файл не пустой, и что первая строка в файле является валидным UUID.
     * </p>
     */
    @Test
    void testWithCreateLargeFile() {
        // Генерация большого файла
        File testFile = new CreatLargeFile().generate();

        // Проверяем, что файл был создан
        assertTrue(testFile.exists(), "Файл не был создан.");

        // Проверяем, что файл не пустой
        assertTrue(testFile.length() > 0, "Файл пустой.");

        // Читаем первую строку и проверяем, что она содержит UUID
        String firstLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            firstLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Проверяем, что первая строка не пустая
        assertNotNull(firstLine, "Первая строка пустая.");

        // Проверяем, что строка действительно является UUID
        try {
            UUID.fromString(firstLine);  // Проверка, что строка является валидным UUID
        } catch (IllegalArgumentException e) {
            fail("Первая строка не является валидным UUID.");
        }
    }
}
