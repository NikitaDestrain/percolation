package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chess {
    private int[][] massiv;
    File file = new File("Chess.txt");

    public Chess() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 != 0) && (i % 2 != 0))
                ) {
                    massiv[i][j] = 0;
                } else massiv[i][j] = 1;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("Chess.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        Chess test = new Chess();


        test.WriteFile();
    }
}
