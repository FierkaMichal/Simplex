import Jama.Matrix;

public class Solve {

    private static double y1 = 100; // ilosc dostepnych cystern 1,
    private static double y2 = 50; // ilosc dostepnych cystern 2

    private static double d1 = 12.0; // ładowność cysterny 1
    private static double d2 = 8.0; // ładowność cysterny 2

    private static double z1 = 6.0; // koszt transportu cystarny 1
    private static double z2 = 8.0; // koszt transportu cystarny 2

    private double v = 200.0; //ilość towaru do przewiezienia
    private static double w = 1500; // maksymalny koszt
    private static double g = 100.0; // minimalna ilość towaru przewiezionego

    private double[][] A;
    private double[] b;
    private double[] c1;
    private double[] c2;
    private double[] c;
    private boolean[] isSmaller;

    public Solve(String[] args) {
        if(args.length != 5) {
            exampleData();
        } else {
            try {
                getDataFromArgs(args);
            } catch (NumberFormatException e) {
                exampleData();
            } catch (IndexOutOfBoundsException e) {
                exampleData();
            }
        }
    }

    private void exampleData() {
        this.A = new double[][]{{1, 0}, {0, 1}, {z1, z2}, {d1, d2}, {d1, d2}};
        this.b = new double[]{y1, y2, w, g, v};
        this.c1 = new double[]{-d1, -d2};
        this.c2 = new double[]{z1, z2};
        this.isSmaller = new boolean[]{true, true, true, false, true};
    }

    private void getDataFromArgs(String[] args) throws NumberFormatException, IndexOutOfBoundsException{
        String[] truck1 = args[0].split(";");
        String[] truck2 = args[1].split(";");
        double[] t1 = new double[truck1.length];
        double[] t2 = new double[truck2.length];
        for(int i = 0; i < truck1.length; i++) {
            t1[i] = Double.parseDouble(truck1[i]);
        }
        for(int i = 0; i < truck2.length; i++) {
            t2[i] = Double.parseDouble(truck2[i]);
        }
        double v = Double.parseDouble(args[2]);
        double w = Double.parseDouble(args[3]);
        double g = Double.parseDouble(args[4]);

        this.A = new double[][]{{1, 0}, {0, 1}, {t1[2], t2[2]}, {t1[1], t2[1]}, {t1[1], t2[1]}};
        this.b = new double[]{t1[0], t2[0], w, g, v};
        this.c1 = new double[]{-t1[1], -t2[1]};
        this.c2 = new double[]{t1[2], t2[2]};
        this.isSmaller = new boolean[]{true, true, true, false, true};
    }

    public void makeOneCriterion() {
        this.c = new double[c1.length];
        for(int i = 0; i < c1.length; i++) {
            c[i] = (c1[i] + c2[i])/2;
        }
    }

    public double[] solve() {
        if(c == null || A == null || b == null || isSmaller == null) {
            return null;
        }
        double[] result = new double[this.c.length];
        NewSimplex simplex = new NewSimplex(A, c, b, isSmaller, true);
        result = simplex.getSolution();
        return result;
    }

    public void printData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Samochod 1: ilosc = ");
        sb.append(b[0]); sb.append(" pojemnosc = ");
        sb.append(-c1[0]); sb.append(" koszt = "); sb.append(c2[0]);
        sb.append("\nSamochod 2: ilosc = ");
        sb.append(b[1]); sb.append(" pojemnosc = ");
        sb.append(-c1[1]); sb.append(" koszt = "); sb.append(c2[1]);
        sb.append("\nMaksymalny koszt = "); sb.append(b[2]);
        sb.append("\nMaksymalna ilosc towaru do transportu = "); sb.append(b[4]);
        sb.append("\nMinimalna ilosc towaru do przewiezienia = "); sb.append(b[3]);
        System.out.println(sb.toString() + "\n");
    }

    public void printSolution(double[] result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Liczba samochodow 1 = "); sb.append(Math.floor(result[0]));
        sb.append("\nLiczba samochodow 2 = "); sb.append(Math.floor(result[1]));
        sb.append("\nIlosc towaru do transportu = "); sb.append((-c1[0]*Math.floor(result[0])) + (-c1[1]*Math.floor(result[1])));
        sb.append("\nKoszt = "); sb.append((c2[0]*Math.floor(result[0])) + (c2[1]*Math.floor(result[1])));
        System.out.println(sb.toString());
    }
}
