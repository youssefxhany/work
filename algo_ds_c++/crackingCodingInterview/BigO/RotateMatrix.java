/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotatematrix;

/**
 *
 * @author youssef hany
 */
//You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
//You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
class Solution {

    public void rotate(int[][] matrix) {
        if (matrix.length <= 1) {
            return;
        }
        this.rotateMatrix(matrix);
        return;
    }

    private void rotateMatrix(int[][] matrix) {
        this.switchMatrixRows(matrix);
        this.transpose(matrix);
        return;
    }

    private void switchMatrixRows(int[][] matrix) {
        for (int i = 0; i < matrix.length/2; i++) {
            this.switchSingleRow(matrix, i, matrix.length - i - 1);
        }
        return;
    }

    private void switchSingleRow(int[][] matrix, int row1, int row2) {
        for (int i = 0; i < matrix.length; i++) 
            this.swapElement(matrix, row1,i,row2,i);
        return;
    }
    
    private void swapElement(int[][] matrix, int row1, int col1, int row2, int col2){
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
        return;
    }
    
    private void transpose(int[][] matrix){
        for(int i=1; i<matrix.length; i++)
            for(int j=0; j<i; j++)
                if(i != j){
                    this.swapElement(matrix, i, j, j, i);
                }
        return;
    }
    
    private void printMatrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
        System.out.println("-----------------------");
    }
}

class SolutionOptimized{
    
    public void rotate(int[][] matrix) {
        if (matrix.length <= 1) {
            return;
        }
        this.rotateMatrix(matrix);
        return;
    }
    
    public void rotateMatrix(int[][] matrix) {
        int left = 0;
        int right = matrix.length -1;
        while (left < right) {
            int top = left, bottom = right;
            for (int i = 0; i < (right - left); i++) {
                int temp = matrix[top][left+i];
                matrix[top][left+i] = matrix[bottom-i][left];
                matrix[bottom-i][left] = matrix[bottom][right-i];
                matrix[bottom][right-i] = matrix[top+i][right];
                matrix[top+i][right] = temp;
            }
            left++;
            right--;
        }
        return;
    }
    
    private void printMatrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
        System.out.println("-----------------------");
    }
}
public class RotateMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int matix[][] = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        solution.rotate(matix);
        
        SolutionOptimized so = new SolutionOptimized();
        so.rotate(matix);
    }
    
}
