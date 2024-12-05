package ru.nsu.pronin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

public class CreatLargeFile {

    public File generate(){
        var data = prepareData(5_000_000);
        File f = new File("C:/Users/kolya/OneDrive/Рабочий стол" +
                "/папки/уроки/НГУ УЧЕБА/2курс/JuniorJavaJeveloper" +
                " (JJJ)/git/OOP/Task_1_3_1/TextFile.txt");
        writeFileNio(f, data);

        return f;
    }

    public String prepareData(int lineCount) {
        var data = new StringBuilder();
        for(int i = 0; i < lineCount; i++ ){
            data.append(UUID.randomUUID()).append(System.lineSeparator());
        }
        return data.toString();
    }


    public void writeFileNio(File f, String content){
        try{
            Files.writeString(Paths.get(f.toURI()),content, StandardOpenOption.CREATE);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
