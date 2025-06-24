package com.bornfire.services;

import java.io.*;

public class FileHandler {
    public static void main(String[] args) {
        try {
            // Trying to open a file that might not exist
            FileInputStream file = new FileInputStream("nonexistentfile.txt");
            
            // Read file data or other operations
            System.out.println("File opened successfully");
            
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist or can't be opened
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // Handle other IO exceptions
            System.out.println("An I/O error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

