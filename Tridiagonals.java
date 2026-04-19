import static java.lang.Math.*;

class Tridiagonals
{
static double[][] exampleMatrix(int n) 
    {
        double m[][] = new double [1][1]; //dummy code: write your own
        return m; //dummy code: write your own
    }











public static double[][] sum(double[][] A, double[][] B) 
    {

        if (!isValidTridiagonal(A) || !isValidTridiagonal(B)) {
            return null;
        }
        
        int n = A[1].length;
        if (B[1].length != n) {
            return null;
        }
        
        double[][] result = new double[3][n];

        for (int row = 0; row < 3; row++) {
            for (int i = 0; i < n; i++) {
                result[row][i] = A[row][i] + B[row][i];
            }
        }

        return result;
    }

    
}
