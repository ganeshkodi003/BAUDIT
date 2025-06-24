package com.bornfire.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MainApplication {
    public String nApplication() {
        String filePath = "D:/Att.txt";
        
        TextFileReader fileReader = new TextFileReader();
        DatabaseConnector databaseConnector = new DatabaseConnector();
 
        try {
            List<String> fileData = fileReader.readTextFile(filePath);
            databaseConnector.insertData(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return filePath;
    }
}