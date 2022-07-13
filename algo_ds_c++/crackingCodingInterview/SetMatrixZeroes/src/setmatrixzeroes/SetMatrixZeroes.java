/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setmatrixzeroes;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author youssef hany
 */
//Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
//You must do it in place.
class Solution {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> columns = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        this.setZeroRows(matrix, rows);
        this.setZeroColumns(matrix, columns);
        return;
    }
    
    private void setZeroRows(int[][] matrix, Set<Integer> rows){
        for(Integer row: rows){
            for(int i=0; i<matrix[0].length; i++)
                matrix[row][i] = 0;
        }
        return;
    }
    
    private void setZeroColumns(int[][] matrix, Set<Integer> columns){
        for(Integer column: columns){
            for(int i=0; i<matrix.length; i++)
                matrix[i][column] = 0;
        }
        return;
    }
    
    private void printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j<matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
    }
}

class EnhacedSolution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> columns = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0 && !rows.contains(i) && !columns.contains(j)) {
                    rows = this.setZeroRow(matrix, i, j, rows, columns);
                    columns = this.setZeroColumn(matrix, i, j, rows, columns);
                }
            }
        }
        return;
    }
    
    private Set<Integer> setZeroRow(int[][] matrix, int row, int column, Set<Integer> rows, Set<Integer> columns){
        boolean breakFlag = false;
        if(rows.contains(row))
            return rows;
        rows.add(row);
        for (int j = 0; j < matrix[0].length; j++) {
            if(j == column) continue;
            if(matrix[row][j] == 0){
                columns = this.setZeroColumn(matrix, row, j, rows, columns);
                continue;
            }
            matrix[row][j] = 0;
        }
        return rows;
    }
    
    private Set<Integer> setZeroColumn(int[][] matrix, int row, int column, Set<Integer> rows, Set<Integer> columns){
        if(columns.contains(column))
            return columns;
        columns.add(column);
        for (int i = 0; i < matrix.length; i++) {
            if(i == row) continue;
            if(matrix[i][column] == 0){
                rows = this.setZeroRow(matrix, i, column, rows, columns);
                continue;
            }
            matrix[i][column] = 0;
        }
        return columns;
    }
    
    private void printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j<matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
        System.out.println("------------------------");
    }
}

class OtherSolution{
    public void setZeroes(int[][] matrix) {
        boolean nuliffyFlags[] = this.checkFirstColRow(matrix);
        this.flagRowsColumns(matrix);
        this.nullifyRowsColumns(matrix, nuliffyFlags);
        return;
    }
    
    private boolean[] checkFirstColRow(int[][] matrix){
        // 0 index for rows and 1 index for columns
        boolean flags[] = new boolean[2];
        flags[0] = false;
        flags[1] = false;
        
        for(int j=0; j<matrix[0].length; j++)
            if(matrix[0][j] == 0)
                flags[0] = true;
        
        for(int i=0; i<matrix.length; i++)
            if(matrix[i][0] == 0)
                flags[1] = true;
        
        return flags;
    }
    
    private void flagRowsColumns(int[][] matrix){
        for(int i=1; i<matrix.length; i++)
            for(int j=1; j<matrix[0].length; j++)
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
    }
    
    private void nullifyRowsColumns(int[][] matrix, boolean[] zeroColRowFlags){
        for(int j=1; j<matrix[0].length; j++)
            if(matrix[0][j] == 0)
                this.setZeroColumn(matrix, j);
        
        for(int i=1; i<matrix.length; i++)
            if(matrix[i][0] == 0)
                this.setZeroRow(matrix, i);
        
        if(zeroColRowFlags[0])
            this.setZeroRow(matrix, 0);
        
        if(zeroColRowFlags[1])
            this.setZeroColumn(matrix, 0);
        
        return;
    }
    
    private void setZeroRow(int[][] matrix, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
        return;
    }
    
    private void setZeroColumn(int[][] matrix, int column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
        return;
    }
    
    private void printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j<matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
    }
}

public class SetMatrixZeroes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int matrix[][] = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        
        OtherSolution solution = new OtherSolution();
        solution.setZeroes(matrix);
    }

}
