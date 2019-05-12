package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Circles {
    private int[][] massiv;
    File file = new File("Circles.txt");

    public Circles() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if (i == 0 || i == 49 || j == 0 || j == 49) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 3; i < 47; i++) {
            for (int j = 3; j < 47; j++) {
                if (i == 3 || i == 46 || j == 3 || j == 46) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 6; i < 44; i++) {
            for (int j = 6; j < 44; j++) {
                if (i == 6 || i == 43 || j == 6 || j == 43) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 9; i < 41; i++) {
            for (int j = 9; j < 41; j++) {
                if (i == 9 || i == 40 || j == 9 || j == 40) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 12; i < 38; i++) {
            for (int j = 12; j < 38; j++) {
                if (i == 12 || i == 37 || j == 12 || j == 37) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 15; i < 35; i++) {
            for (int j = 15; j < 35; j++) {
                if (i == 15 || i == 34 || j == 15 || j == 34) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 18; i < 32; i++) {
            for (int j = 18; j < 32; j++) {
                if (i == 18 || i == 31 || j == 18 || j == 31) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 21; i < 29; i++) {
            for (int j = 21; j < 29; j++) {
                if (i == 21 || i == 28 || j == 21 || j == 28) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
        for (int i = 24; i < 26; i++) {
            for (int j = 24; j < 26; j++) {
                if (i == 24 || i == 25 || j == 24 || j == 25) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("Circles.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        Circles test = new Circles();


        test.WriteFile();
    }
}
