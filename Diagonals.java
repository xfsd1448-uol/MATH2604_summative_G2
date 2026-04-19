import static java.lang.Math.*;

/**
 * Utility methods for working with diagonal matrices stored as one-dimensional arrays.
 * In this representation, an n x n diagonal matrix is stored in a double[] array of length n,
 * where a[i] corresponds to the matrix entry A[i][i] on the main diagonal.
 */
class Diagonals
{
    
    /**
     * Returns the one-dimensional array representation of the example diagonal matrix
     * specified in the assignment.
     *
     * The returned array stores the diagonal entries 10, 8, 5, -10, and 7.
     *
     * This method takes no parameters, so no parameter range assumptions or
     * exceptional input cases apply.
     *
     * @return a new array representing the diagonal matrix diag(10, 8, 5, -10, 7)
     */
    public static double[] exampleMatrix() 
    {
        return new double[] {10.0, 8.0, 5.0, -10.0, 7.0};
    }

    /**
     * Computes the sum of two diagonal matrices.
     * The input arrays are not modified.
     *
     * @param a the first diagonal matrix representation; may be {@code null}
     * @param b the second diagonal matrix representation; may be {@code null}
     * @return a new array representing the diagonal entries of {@code a + b};
     *         returns {@code null} if either input is {@code null} or if the
     *         input arrays have different lengths
     */
    public static double[] sum(double[] a, double[] b) 
    {
        if (a == null || b == null) {
            return null;
        }
        if (a.length != b.length) {
            return null;
        }
        
        int n = a.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }
    
    /**
     * Computes the product of two diagonal matrices.
     * The input arrays are not modified.
     *
     * @param a the first diagonal matrix representation; may be {@code null}
     * @param b the second diagonal matrix representation; may be {@code null}
     * @return a new array representing the diagonal entries of {@code a * b};
     *         returns {@code null} if either input is {@code null} or if the
     *         input arrays have different lengths
     */
    public static double[] product(double[] a, double[] b) 
    {
        if (a == null || b == null) {
            return null;
        }
        if (a.length != b.length) {
            return null;
        }

        int n = a.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = a[i] * b[i];
        }
        return result;
    }

    /**
     * Computes the inverse of a diagonal matrix.
     *
     * 
     * The matrix is represented by a one-dimensional array a, where a[i]
     * corresponds to the diagonal entry A(i,i).
     *
     * Each entry of the result is the reciprocal of the corresponding
     * entry in a.
     *
     * If the input array is null, the method returns null.
     * If the input array has length 0, a new empty array is returned.
     *
     * The input array is not modified.
     *
     * It is assumed that the matrix is invertible (i.e. no entry of a is zero).
     *
     * @param a the array representing the diagonal entries of the matrix
     * @return a new array representing the inverse diagonal matrix,
     *         or null if the input is null
     */

    public static double[] inverse(double[] a) 
    {
        if (a == null) return null;

        int n = a.length;
        double[] inv = new double[n];

        for (int i = 0; i < n; i++) {
            inv[i] = 1.0 / a[i];
        }

        return inv;
    }



    
}
