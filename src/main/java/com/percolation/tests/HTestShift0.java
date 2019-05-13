package com.percolation.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HTestShift0 {
    private int[][] massiv;
    File file = new File("HTestShift0.txt");

    public HTestShift0() throws IOException {
        file.createNewFile();
        massiv = new int[50][50];
        CreateArray();
    }

    private void CreateArray() {
        for (int i = 0; i < massiv.length; i++) {
            for (int j = 0; j < massiv.length; j++) {
                if ((i == 5 && (j == 5 || j == 9 || j == 15 || j == 19 || j == 25 || j == 29 || j == 35 || j == 39 || j == 45 || j == 49)) ||
                        (i == 6 && (j == 5 || j == 9 || j == 15 || j == 19 || j == 25 || j == 29 || j == 35 || j == 39 || j == 45 || j == 49)) ||
                        (i == 7 && (j == 5 || j == 6 || j == 7 || j == 8 || j == 9 || j == 15 || j == 16 || j == 17 || j == 18 || j == 19 || j == 25 || j == 26 || j == 27 || j == 28 || j == 29 || j == 35 || j == 36 || j == 37 || j == 38 || j == 39 || j == 45 || j == 46 || j == 47 || j == 48 || j == 49)) ||
                        (i == 8 && (j == 5 || j == 9 || j == 15 || j == 19 || j == 25 || j == 29 || j == 35 || j == 39 || j == 45 || j == 49)) ||
                        (i == 9 && (j == 5 || j == 9 || j == 15 || j == 19 || j == 25 || j == 29 || j == 35 || j == 39 || j == 45 || j == 49)) ||

                        (i == 15 && (j == 1 || j == 7 || j == 11 || j == 17 || j == 21 || j == 27 || j == 31 || j == 37 || j == 41 || j == 47)) ||
                        (i == 16 && (j == 1 || j == 7 || j == 11 || j == 17 || j == 21 || j == 27 || j == 31 || j == 37 || j == 41 || j == 47)) ||
                        (i == 17 && (j == 0 || j == 1 || j == 7 || j == 8 || j == 9 || j == 10 || j == 11 || j == 17 || j == 18 || j == 19 || j == 20 || j == 21 || j == 27 || j == 28 || j == 29 || j == 30 || j == 31 || j == 37 || j == 38 || j == 39 || j == 40 || j == 41 || j == 47 || j == 48 || j == 49 || j == 50)) ||
                        (i == 18 && (j == 1 || j == 7 || j == 11 || j == 17 || j == 21 || j == 27 || j == 31 || j == 37 || j == 41 || j == 47)) ||
                        (i == 19 && (j == 1 || j == 7 || j == 11 || j == 17 || j == 21 || j == 27 || j == 31 || j == 37 || j == 41 || j == 47)) ||

                        (i == 25 && (j == 3 || j == 9 || j == 13 || j == 19 || j == 23 || j == 29 || j == 33 || j == 39 || j == 43 || j == 49)) ||
                        (i == 26 && (j == 3 || j == 9 || j == 13 || j == 19 || j == 23 || j == 29 || j == 33 || j == 39 || j == 43 || j == 49)) ||
                        (i == 27 && (j == 0 || j == 1 || j == 2 || j == 3 || j == 9 || j == 10 || j == 11 || j == 12 || j == 13 || j == 19 || j == 20 || j == 21 || j == 22 || j == 23 || j == 29 || j == 30 || j == 31 || j == 32 || j == 33 || j == 39 || j == 40 || j == 41 || j == 42 || j == 43 || j == 49 || j == 50)) ||
                        (i == 28 && (j == 3 || j == 9 || j == 13 || j == 19 || j == 23 || j == 29 || j == 33 || j == 39 || j == 43 || j == 49)) ||
                        (i == 29 && (j == 3 || j == 9 || j == 13 || j == 19 || j == 23 || j == 29 || j == 33 || j == 39 || j == 43 || j == 49)) ||

                        (i == 35 && (j == 1 || j == 5 || j == 11 || j == 15 || j == 21 || j == 25 || j == 31 || j == 35 || j == 41 || j == 45)) ||
                        (i == 36 && (j == 1 || j == 5 || j == 11 || j == 15 || j == 21 || j == 25 || j == 31 || j == 35 || j == 41 || j == 45)) ||
                        (i == 37 && (j == 1 || j == 2 || j == 3 || j == 4 || j == 5 || j == 11 || j == 12 || j == 13 || j == 14 || j == 15 || j == 21 || j == 22 || j == 23 || j == 24 || j == 25 || j == 31 || j == 32 || j == 33 || j == 34 || j == 35 || j == 41 || j == 42 || j == 43 || j == 44 || j == 45)) ||
                        (i == 38 && (j == 1 || j == 5 || j == 11 || j == 15 || j == 21 || j == 25 || j == 31 || j == 35 || j == 41 || j == 45)) ||
                        (i == 39 && (j == 1 || j == 5 || j == 11 || j == 15 || j == 21 || j == 25 || j == 31 || j == 35 || j == 41 || j == 45)) ||

                        (i == 45 && (j == 3 || j == 7 || j == 13 || j == 17 || j == 23 || j == 27 || j == 33 || j == 37 || j == 43 || j == 47)) ||
                        (i == 46 && (j == 3 || j == 7 || j == 13 || j == 17 || j == 23 || j == 27 || j == 33 || j == 37 || j == 43 || j == 47)) ||
                        (i == 47 && (j == 3 || j == 4 || j == 5 || j == 6 || j == 7 || j == 13 || j == 14 || j == 15 || j == 16 || j == 17 || j == 23 || j == 24 || j == 25 || j == 26 || j == 27 || j == 33 || j == 34 || j == 35 || j == 36 || j == 37 || j == 43 || j == 44 || j == 45 || j == 46 || j == 47)) ||
                        (i == 48 && (j == 3 || j == 7 || j == 13 || j == 17 || j == 23 || j == 27 || j == 33 || j == 37 || j == 43 || j == 47)) ||
                        (i == 49 && (j == 3 || j == 7 || j == 13 || j == 17 || j == 23 || j == 27 || j == 33 || j == 37 || j == 43 || j == 47))
                ) {
                    massiv[i][j] = 1;
                } else massiv[i][j] = 0;
            }
        }
    }

    public void WriteFile() throws IOException {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("HTestShift0.txt"));

        for (int i = 0; i < 50; ++i) {

            for (int j = 0; j < 50; ++j)
                filewriter.write(massiv[i][j] + "");
            filewriter.write("\r\n");
        }
        filewriter.flush();
    }

    public static void main(String[] args) throws IOException {
        HTestShift0 test = new HTestShift0();


        test.WriteFile();
    }
}