//import Jama.Matrix;
//
///**
// * Created by Michał on 2017-12-13.
// */
//public class Simplex {
//
//    private double[][] a;   // tableaux
//    private int M;          // number of constraints
//    private int N;          // number of original variables
//
//    public Simplex(Matrix A, double[] c, double b[], boolean[] isSmaler, boolean isMax) {
//        Matrix table = new Matrix(A.getRowDimension() + 1, A.getColumnDimension() + 1);
//        for(int i = 0; i < A.getRowDimension(); i++) {
//            for(int j = 0; j < A.getColumnDimension(); j++) {
//                table.set(i, j, A.get(i, j));
//            }
//        }
//        for (int i = 0; i < b.length; i++) {
//            table.set(i, table.getColumnDimension() - 1, b[i]);
//        }
//
//        for (int i = 0; i < c.length; i++) {
//            table.set(table.getRowDimension() - 1, i, c[i]);
//        }
//
//        table.print(table.getRowDimension(), table.getColumnDimension());
//
//        table = table.transpose();
//
//        table.print(table.getRowDimension(), table.getColumnDimension());
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
//    private Matrix getTableaux(Matrix table, boolean[] isSmaler) {
//        Matrix tableaux = new Matrix(table.getRowDimension(), table.getColumnDimension() + table.getRowDimension(), 0.0);
//
//        for(int i = 0; i < table.getRowDimension(); i++) {
//            for(int j = 0; j < table.getColumnDimension() - 1; j++) {
//                if(i == table.getRowDimension() - 1)  {
//                    tableaux.set(i, j, -table.get(i, j));
//                }
//                else {
//                    tableaux.set(i, j, table.get(i, j));
//                }
//            }
//        }
////        for(int i = 0; i < tableaux.getRowDimension(); i++) { --commented
////           tableaux.set(i, i + table.getRowDimension() - 1, 1.0);
////        }
//
//        for(int i = 0; i < tableaux.getRowDimension() - 1; i++) {
//            if(isSmaler[i]) {
//                tableaux.set(i, i + table.getRowDimension() - 1, 1.0); // tu
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
//
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
//
//        }
//    }
//}
