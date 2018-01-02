/**
 * Created by Michał on 2017-12-13.
 */
public class NewSimplex {

    private double[][] a;   // tableaux
    private int M;          // number of constraints
    private int N;          // number of original variables
    private double[][] tableau;

    public NewSimplex(double[][] A, double[] c, double b[], boolean[] isSmaler, boolean isMax) {
        int rows = A.length + 1;
        int columns = A[0].length + 1;
        double[][] table = new double[rows][columns];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                table[i][j] = A[i][j];
            }
        }
        for (int i = 0; i < b.length; i++) {
            table[i][columns - 1] = b[i];
        }

        for (int i = 0; i < c.length; i++) {
            table[rows - 1][i] = c[i];
        }

        System.out.println("-----");
        table = getTableaux(table, isSmaler);
        int pivotColumn = getPivotColumn(table);
        while (pivotColumn != -1) {
            int pivotRow = getPivotRow(table, pivotColumn);
            computePivotRow(table, pivotRow, pivotColumn);
            computeNotPivotRow(table, pivotRow, pivotColumn);
            printTable(table);
            pivotColumn = getPivotColumn(table);
        }

        printTable(table);
        this.tableau = table;
    }

    private double[][] getTableaux(double[][] table, boolean[] isSmaler) {
        double[][] tableaux = new double[table.length][table[0].length + table.length];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length - 1; j++) {
                if (i == table.length - 1) {
                    tableaux[i][j] = table[i][j];
                } else {
                    tableaux[i][j] = table[i][j];
                }
            }
        }

        for (int i = 0; i < tableaux.length - 1; i++) {
            if (isSmaler[i]) {
                tableaux[i][i + table[0].length - 1] = 1.0;
            } else {
                tableaux[i][i + table[0].length - 1] = -1.0;
            }
        }
        tableaux[tableaux.length - 1][tableaux[0].length - 2] = 1.0;

        for (int i = 0; i < tableaux.length; i++) {
            tableaux[i][tableaux[0].length - 1] = table[i][table[0].length - 1];
        }
        printTable(tableaux);
        return tableaux;
    }

    private int getPivotColumn(double[][] tableaux) {
        double min = 0;
        int index = -1;
        for (int i = 0; i < tableaux[0].length; i++) {
            if (tableaux[tableaux.length - 1][i] < 0 && tableaux[tableaux.length - 1][i] != (-0.0)) {
                if (tableaux[tableaux.length - 1][i] < min) {
                    min = tableaux[tableaux.length - 1][i];
                    index = i;
                }
            }
        }
        return index;
    }

    private int getPivotRow(double[][] tableaux, int pivotColumn) {
        double min = 1000000;
        int returnRow = 0;
        boolean haveValue = false;
        for (int i = 1; i < tableaux.length - 1; i++) {
            double value =  tableaux[i][tableaux[0].length - 1] / tableaux[i][pivotColumn];
            if(value > 0) {
                if (value < min || !haveValue) {
                    min = value;
                    returnRow = i;
                    haveValue = true;
                }
            }
        }
        return returnRow;
    }

    private void computePivotRow(double[][] tableaux, int pivotRow, int pivotColumn) {
        double pivot = tableaux[pivotRow][pivotColumn];
        for (int i = 0; i < tableaux[0].length; i++) {
            tableaux[pivotRow][i] = tableaux[pivotRow][i] / pivot;
        }
    }

    private void computeNotPivotRow(double[][] tableaux, int pivotRow, int pivotColumn) {
        double a = tableaux[tableaux.length - 1][pivotColumn] * (-1);
        for (int i = 0; i < tableaux.length; i++) {
            double value = tableaux[i][pivotColumn];
            for (int j = 0; j < tableaux[0].length; j++) {
                if (i == pivotRow) {
                } else if (i == tableaux.length - 1) {
                    tableaux[i][j] = tableaux[i][j] + tableaux[pivotRow][j] * a;
                } else {
                    tableaux[i][j] = tableaux[i][j] - value * tableaux[pivotRow][j];
                }
            }
        }
    }

    public double[] getSolution() {
        double[] result = new double[tableau[0].length - 1 - tableau.length];
        for (int i = 0; i < tableau[0].length - 1 - tableau.length; i++) {
            int b = -1;
            int idx = 0;
            for (int j = 0; j < tableau.length - 1; j++) {
                if (tableau[j][i] == 1) {
                    if (b == -1) {
                        b = 1;
                        idx = j;
                    } else if (b == 1)
                        b = 2;
                } else if (tableau[j][i] != 0) {
                    if(b != -1 || b != 1) {
                        b = -2;
                    }
                }
            }
            if(b == 2 || b == -1 || b == -2){
                result[i] = 0;
            } else if (b == 1) {
                result[i] = tableau[idx][tableau[0].length - 1];
            }
        }
        return result;
    }

    public static double[][] transposeMatrix(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static void printTable(double[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(String.format("%.2f", table[i][j]) + " ");
            }
            System.out.println("");
        }
        System.out.println("-----");
    }
}
