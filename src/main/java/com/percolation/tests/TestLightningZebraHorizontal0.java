package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestLightningZebraHorizontal0 {
    private int[][] massiv;
    File file = new File("TestLightningZebraHorizontal0.txt");

    public TestLightningZebraHorizontal0() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if (j == 0 ||
                        j == 1 ||
                        j == 12 ||
                        j == 13 ||
                        j == 24 ||
                        j == 25 ||
                        j == 36 ||
                        j == 37 ||
                        j == 48 ||
                        j == 49) {
                    massiv[i][j] = 0;
                } else massiv[i][j] = 1;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("TestLightningZebraHorizontal0.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        TestLightningZebraHorizontal0 test = new TestLightningZebraHorizontal0();


        test.WriteFile();
    }
}
