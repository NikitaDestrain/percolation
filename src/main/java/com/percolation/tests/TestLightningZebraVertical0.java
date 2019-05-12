package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestLightningZebraVertical0 {
    private int[][] massiv;
    File file = new File("TestLightningZebraVertical0.txt");

    public TestLightningZebraVertical0() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if (i == 0 ||
                        i == 1 ||
                        i == 12 ||
                        i == 13 ||
                        i == 24 ||
                        i == 25 ||
                        i == 36 ||
                        i == 37 ||
                        i == 48 ||
                        i == 49) {
                    massiv[i][j] = 0;
                } else massiv[i][j] = 1;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("TestLightningZebraVertical0.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        TestLightningZebraVertical0 test = new TestLightningZebraVertical0();


        test.WriteFile();
    }
}
