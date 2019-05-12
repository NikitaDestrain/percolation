package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestLightningZebraVertical1 {
    private int[][] massiv;
    File file = new File("TestLightningZebraVertical1.txt");

    public TestLightningZebraVertical1() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if (i == 10 ||
                        i == 11 ||
                        i == 22 ||
                        i == 23 ||
                        i == 34 ||
                        i == 35 ||
                        i == 46 ||
                        i == 47) {
                    massiv[i][j] = 0;
                } else massiv[i][j] = 1;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("TestLightningZebraVertical1.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        TestLightningZebraVertical1 test = new TestLightningZebraVertical1();


        test.WriteFile();
    }
}
