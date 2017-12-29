import Jama.Matrix;

/**
 * Created by Michał on 2017-12-13.
 */
public class Main {



    private double d1 = 12.0; // ładowność cysterny 1
    private double d2 = 8.0; // ładowność cysterny 2

    private double z1 = 5.0; // koszt transportu cystarny 1
    private double z2 = 3.0; // koszt transportu cystarny 2

    //private double c = 200.0; //ilość towaru do przewiezienia
    private double w = 150; // maksymalny koszt
    private double g = 100.0; // minimalna ilość towaru przewiezionego
//EXAMPLE 1
//    private static double[][] A = {{2.0, 1.0 },
//            {1.0,  1.0 }};
//    private static double[] c = {  3.0,  2.0};
//    private static double[] b = { 6.0, 4.0 };
//    private static boolean[] isSmaler = {true, true};

//    //EXAMPLE 2 przy dualnej tabeli za duzo kolumn !!!!!!!!!!!!
    private static double[][] A = {{1.0, 1.0, 1.0 },
                                    {0.0, 1.0, 2.0 },
                                    {-1.0,  2.0, 2.0 }};
    private static double[] c = { 2.0, 10.0, 8.0};
    private static double[] b = { 6.0, 8.0, 4.0 };
    private static boolean[] isSmaler = {true, true, true};

    //EXAMPLE YT przy dualnej tabeli za duzo kolumn !!!!!!!!!!!!
//    private static double[][] A = {{2.0, 3.0, 2.0 },
//                                    {1.0, 1.0, 2.0 }};
//    private static double[] c = { 7.0, 8.0, 10.0};
//    private static double[] b = { 1000.0, 800.0 };
//    private static boolean[] isSmaler = {true, true, true};

    public static void main(String[] args) {
//        Simplex simplex = new Simplex(new Matrix(A), c, b, isSmaler, true);
        Solve s = new Solve(args);
        s.makeOneCriterion();
        s.solve();
//        String message = "wiadomość";
//        byte[] bytes = message.getBytes();
//        for (byte b: bytes
//             ) {
//            System.out.println(b);
//        }
//        String string = Arrays.toString(bytes);
//        System.out.println(string);
        //Matrix matrix = new Matrix();
    }

//    // pivot on entry (p, q) using Gauss-Jordan elimination
//    private void pivot(int p, int q) {
//
//        for (int i = 0; i <= M; i++)
//            for (int j = 0; j <= M + N; j++)
//                if (i != p && j != q) { // everything but row p and column q
//                    a[i][j] -= a[p][j] * a[i][q] / a[p][q];
//                }
//
//        // zero out column q
//        for (int i = 0; i <= M; i++)
//            if (i != p) a[i][q] = 0.0;
//
//        // scale row p
//        for (int j = 0; j <= M + N; j++)
//            if (j != q) a[p][j] /= a[p][q];
//        a[p][q] = 1.0;
//    }
}
