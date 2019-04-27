package com.percolation;

import com.percolation.domain.matrix.Matrix;
import com.percolation.generator.UniformDistributionMatrixGenerator;

public class Main {

    private static UniformDistributionMatrixGenerator udg = new UniformDistributionMatrixGenerator();

    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);

        // required parameters
        int C, N;
        double P;

        // todo move this action to UI
        System.out.println("*******************************DEMO***************************************");
        System.out.println("[INFO]: This version generates C (count) matrix of NxN size and produces uniformly distributed seeding");
        System.out.println("[INFO]: Please, provide the next required parameters for test run:");
        System.out.println("[INFO]: C - matrix count (Example: 3)");
        System.out.println("[INFO]: N - matrix size (Example: 10)");
        System.out.println("[INFO]: p - cell conductivity probability (between 0 and 1) (Example: 0,35)");
        System.out.println("[INFO]: For this parameters you will have 3 matrix of 10x10 size");
        System.out.println("******************************READING*************************************");

        System.out.println("[INFO]: If you want to start test with default parameters");
        System.out.println("[INFO]: C = 3");
        System.out.println("[INFO]: N = 10");
        System.out.println("[INFO]: p = 0,35");
        System.out.println("[INFO]: You should type \'yes\', otherwise just type \'no\' or something else");
        System.out.print("yes or no? Answer: ");
        String answer = scanner.next();


        List<Matrix> matrices = new ArrayList<>();
        if (answer.equalsIgnoreCase("yes")) {
            C = 3;
            N = 10;
            P = 0.35;

            System.out.println("****************************GENERATING************************************");
            for (int i = 0; i < C; i++) {
                Matrix matrix = generateStep(N, P, i);
                matrices.add(matrix);
            }
        } else {
            // read count of matrix
            System.out.print("C: ");
            C = scanner.nextInt();

            System.out.println("****************************GENERATING************************************");
            for (int i = 0; i < C; i++) {
                // read matrix size
                System.out.println("Run " + (i + 1));
                System.out.print("N: ");
                N = scanner.nextInt();

                // read probability
                System.out.print("p: ");
                P = scanner.nextDouble();

                Matrix matrix = generateStep(N, P, i);
                matrices.add(matrix);
            }
        }*/

    }
    /*
    private static Matrix generateStep(int N, double P, int i) {
        Matrix matrix = udg.generateRandomMatrix(N, P);
        System.out.println(matrix.getHumanReadableMatrix());
        System.out.println("**************************************************************************");
        return matrix;
    }*/
}
