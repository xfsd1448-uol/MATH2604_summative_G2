import static java.lang.Math.*;

class Tridiagonals
{
static double[][] exampleMatrix(int n) 
{
    if (n <= 0) {
        return null;
    }

    double[][] result = new double[3][n];

    for (int i = 0; i < n; i++) {
        double value = i + 1.0;
        result[1][i] = -(value * value);
    }

    for (int i = 0; i < n - 1; i++) {
        result[0][i] = 1.0;
        result[2][i] = i + 2.0;
    }

    return result;
}


/**
* Computes the sum of two tridiagonal matrices.
*
* A tridiagonal matrix is represented by a 3 x n array:
* - a[0] stores the entries above the diagonal
* - a[1] stores the entries on the diagonal
* - a[2] stores the entries below the diagonal
*
* If either input matrix is invalid, the method returns null.
* If the matrix sizes are incompatible, the method returns null.
* The input arrays are not modified.
*
* @param A the first tridiagonal matrix
* @param B the second tridiagonal matrix
* @return a new 3 x n array representing the sum of A and B,
*         or null if the inputs are invalid or incompatible
*/

public static double[][] sum(double[][] A, double[][] B) 
    {
        // Validate the input matrices
        if (!isValidTridiagonal(A) || !isValidTridiagonal(B)) {
            return null;
        }
        
        // check if the sizes are compatible
        int n = A[1].length;
        if (B[1].length != n) {
            return null;
        }
        
        double[][] result = new double[3][n];

        // Compute the sum of the two tridiagonal matrices
        for (int row = 0; row < 3; row++) {
            for (int i = 0; i < n; i++) {
                result[row][i] = A[row][i] + B[row][i];
            }
        }

        return result;
    }


}
