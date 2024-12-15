package ru.nsu.pronin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

/**
 * Класс для создания большого текстового файла со случайными UUID.
 * Метод `generate` создает файл с заданным количеством строк,
 * каждая из которых содержит случайный UUID.
 */
public class CreatLargeFile {

    /**
     * Генерирует файл со случайными UUID.
     * <p>
     * Метод создает файл с указанным путем и заполняет его строками,
     * состоящими из случайных UUID.
     * Количество строк в файле определяется параметром.
     * </p>
     *
     * @return объект {@link File}, который представляет созданный файл
     */
    public File generate() {
        // Генерация данных для записи в файл
        var data = prepareData(5_000_000);

        // Создание файла в указанной директории
        File f = new File("C:/Users/kolya/OneDrive/Рабочий стол"
                + "/папки/уроки/НГУ УЧЕБА/2курс/JuniorJavaJeveloper"
                + " (JJJ)/git/OOP/Task_1_3_1/TextFile.txt");

        // Запись данных в файл
        writeFileNio(f, data);

        // Возвращаем объект файла
        return f;
    }

    /**
     * Подготавливает данные для записи в файл.
     * <p>
     * Метод генерирует строки, содержащие случайные UUID, и возвращает строку, содержащую
     * указанное количество строк с UUID.
     * </p>
     *
     * @param lineCount количество строк, которые необходимо сгенерировать
     * @return строка, содержащая сгенерированные данные для записи в файл
     */
    private String prepareData(int lineCount) {
        // Строка, в которую будут записываться данные
        var data = new StringBuilder();

        // Генерация строки с UUID для каждого числа
        for (int i = 0; i < lineCount; i++) {
            data.append(UUID.randomUUID()).append(System.lineSeparator());
        }

        // Возвращаем сгенерированную строку
        return data.toString();
    }

    /**
     * Записывает данные в файл с использованием NIO.
     * <p>
     * Этот метод использует NIO для записи данных в файл. В случае ошибки выбрасывается
     * {@link RuntimeException}.
     * </p>
     *
     * @param f файл, в который нужно записать данные
     * @param content строка, содержащая данные для записи в файл
     */
    private void writeFileNio(File f, String content) {
        try {
            // Запись данных в файл
            Files.writeString(Paths.get(f.toURI()), content, StandardOpenOption.CREATE);
        } catch (Exception e) {
            // В случае ошибки выбрасывается исключение
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }
}
