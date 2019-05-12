package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestLightningZebraHorizontal1 {
    private int[][] massiv;
    File file = new File("TestLightningZebraHorizontal1.txt");

    public TestLightningZebraHorizontal1() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if (j == 10 ||
                        j == 11 ||
                        j == 22 ||
                        j == 23 ||
                        j == 34 ||
                        j == 35 ||
                        j == 46 ||
                        j == 47) {
                    massiv[i][j] = 0;
                } else massiv[i][j] = 1;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("TestLightningZebraHorizontal1.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        TestLightningZebraHorizontal1 test = new TestLightningZebraHorizontal1();


        test.WriteFile();
    }
}
