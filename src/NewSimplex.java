//import Jama.Matrix;
//
///**
// * Created by Michał on 2017-12-13.
// */
//public class NewSimplex {
//
//    private double[][] a;   // tableaux
//    private int M;          // number of constraints
//    private int N;          // number of original variables
//
//    //public NewSimplex(Matrix A, double[] c, double b[], boolean[] isSmaler, boolean isMax) {
//    public NewSimplex(double[][] A, double[] c, double b[], boolean[] isSmaler, boolean isMax) {
//        //Matrix table = new Matrix(A.getRowDimension() + 1, A.getColumnDimension() + 1);
//        int rows = A.length + 1;
//        int columns = A[0].length + 1;
//        double[][] table = new double[rows][columns];
//        for(int i = 0; i < A.length; i++) {
//            for(int j = 0; j < A[0].length; j++) {
//                table[i][j] = A[i][j];
//            }
//        }
//        for (int i = 0; i < b.length; i++) {
//            table[i][columns - 1] = b[i];
//        }
//
//        for (int i = 0; i < c.length; i++) {
//            table[rows - 1][i] = c[i];
//        }
//
//        //table.print(table.getRowDimension(), table.getColumnDimension());
//
//        table = transposeMatrix(table);
//
//        table = getTableaux(table, isSmaler);
//        int pivotColumn = getPivotColumn(table);
//        while (pivotColumn != -1) {
//            int pivotRow = getPivotRow(table, pivotColumn);
//            computePivotRow(table, pivotRow, pivotColumn);
//            computeNotPivotRow(table, pivotRow, pivotColumn);
//            table.print(table.getRowDimension(), 4);
//            pivotColumn = getPivotColumn(table);
//        }
//        table.print(table.getRowDimension(), 4);
//        getSolution(table);
//    }
//
//    private double[][] getTableaux(double[][] table, boolean[] isSmaler) {
//        double[][] tableaux = new double[table.length][table[0].length + table.length]; // zainicjować zerami
//
//        for(int i = 0; i < table.length; i++) {
//            for(int j = 0; j < table[0].length - 1; j++) {
//                if(i == table.length - 1)  {
//                    tableaux[i][j] = - table[i][j];
//                }
//                else {
//                    tableaux[i][j] = table[i][j];
//                }
//            }
//        }
////        for(int i = 0; i < tableaux.getRowDimension(); i++) {
////           tableaux.set(i, i + table.getRowDimension() - 1, 1.0);
////        }
//
//        for(int i = 0; i < tableaux.length - 1; i++) {
//            if(isSmaler[i]) {
//                tableaux.set(i, i + table.length - 1, 1.0); // tu
//            }
//            else {
//                tableaux.set(i, i + table.getRowDimension() - 1, -1.0);
//            }
//        }
//        tableaux.set(tableaux.getRowDimension() - 1, (2*table.getRowDimension()) - 2, 1.0);
//
//        for(int i = 0; i < tableaux.getRowDimension(); i++) {
//            tableaux.set(i, tableaux.getColumnDimension() - 1, table.get(i, table.getColumnDimension() - 1));
//        }
//        tableaux.print(2, 2);
//
//        return tableaux;
//    }
//
//    private int getPivotColumn(Matrix tableaux) {
//        double min = 0;
//        int index = -1;
//        for (int i = 0; i < tableaux.getColumnDimension(); i++) {
//            if(tableaux.get(tableaux.getRowDimension() - 1, i) < 0 && tableaux.get(tableaux.getRowDimension() - 1, i) != (-0.0)) {
//                if(tableaux.get(tableaux.getRowDimension() - 1, i) < min) {
//                    min = tableaux.get(tableaux.getRowDimension() - 1, i);
//                    index = i;
//                }
//            }
//        }
//        return index;
//    }
//
//    private int getPivotRow(Matrix tableaux, int pivotColumn) {
//        double min = tableaux.get(0, tableaux.getColumnDimension() - 1) / tableaux.get(0, pivotColumn);
//        int returnRow = 0;
//        for (int i = 1; i < tableaux.getRowDimension() - 1; i++) {
//            double value =  tableaux.get(i, tableaux.getColumnDimension() - 1) / tableaux.get(i, pivotColumn);
//            if(value < min) {
//                min = value;
//                returnRow = i;
//            }
//        }
//        return returnRow;
//    }
//
//    private void computePivotRow(Matrix tableaux, int pivotRow, int pivotColumn) {
//        double pivot = tableaux.get(pivotRow, pivotColumn);
//        for (int i = 0; i < tableaux.getColumnDimension(); i++) {
//            tableaux.set(pivotRow, i, tableaux.get(pivotRow, i) / pivot);
//        }
//    }
//
//    private void computeNotPivotRow(Matrix tableaux, int pivotRow, int pivotColumn) {
//        double a = tableaux.get(tableaux.getRowDimension() - 1, pivotColumn) * (-1);
//        for (int i = 0; i < tableaux.getRowDimension(); i++) {
//            double value = tableaux.get(i, pivotColumn);
//            for(int j = 0; j < tableaux.getColumnDimension(); j++) {
//                if(i == pivotRow) {
//                }
//                else if(i == tableaux.getRowDimension() - 1) {
//                    tableaux.set(i, j, tableaux.get(i, j) + tableaux.get(pivotRow, j) * a);
//                }
//                else {
////                    tableaux.set(i, j, (-1*tableaux.get(pivotRow, j)) + tableaux.get(i, j));
//                    tableaux.set(i, j, tableaux.get(i, j) - value * tableaux.get(pivotRow, j));
//                }
//            }
//        }
//    }
//
//    private void getSolution(Matrix tableaux) {
//        for (int i = 0; i < tableaux.getColumnDimension() -1 - tableaux.getRowDimension(); i++) {
//            int b = -1;
//            int idx = 0;
//            System.out.print("x"+i+ " = ");
//            for(int j = 0; j < tableaux.getRowDimension() -1; j++) {
//                if(tableaux.get(j, i) == 1) {
//                    if (b == -1) {
//                        b = 1;
//                        idx = j;
//                    }
//                    else if (b == 1)
//                        b = 2;
//                }
//            }
//            if(b == 2 || b == -1){
//                System.out.println("0");
//            } else if (b == 1) {
//                System.out.println(tableaux.get(idx, tableaux.getColumnDimension()-1));
//            }
//        }
//    }
//
//    public static double[][] transposeMatrix(double [][] m){
//        double[][] temp = new double[m[0].length][m.length];
//        for (int i = 0; i < m.length; i++)
//            for (int j = 0; j < m[0].length; j++)
//                temp[j][i] = m[i][j];
//        return temp;
//    }
//}
