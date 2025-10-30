package CWH;
import java.util.*;


public class OBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) keys[i] = sc.nextInt();

        double[] p = new double[n + 1]; 
        for (int i = 1; i <= n; i++) p[i] = sc.nextDouble();

        double[] q = new double[n + 1]; 
        for (int i = 0; i <= n; i++) q[i] = sc.nextDouble();

        double[][] e = new double[n + 2][n + 2];
        double[][] w = new double[n + 2][n + 2];

       
        for (int i = 0; i <= n; i++) {
            e[i][i] = q[i];
            w[i][i] = q[i];
        }

        for (int l = 1; l <= n; l++) { 
            for (int i = 0; i <= n - l; i++) {
                int j = i + l;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];

                for (int r = i + 1; r <= j; r++) {
                    double cost = e[i][r - 1] + e[r][j] + w[i][j];
                    if (cost < e[i][j]) {
                        e[i][j] = cost;
                    }
                }
            }
        }

        System.out.printf("Minimum Expected Cost of OBST: %.4f%n", e[0][n]);
    }
}
