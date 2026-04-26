import static java.lang.Math.*;

/**
 * Utility methods for working with tridiagonal matrices stored in a 3 x n array.
 *
 * <p>All methods in this class are static.
 *
 * <p>A valid tridiagonal matrix representation has length 3 at the first level and
 * length n at the second level for some {@code n >= 1}. Ragged arrays (where rows
 * have unequal lengths) and {@code null} arrays at any level are considered invalid.
 *
 * <p>The storage convention is:
 * <ul>
 *   <li>{@code a[0][i]} stores the entry above the diagonal at matrix position {@code (i, i + 1)}</li>
 *   <li>{@code a[1][i]} stores the diagonal entry at matrix position {@code (i, i)}</li>
 *   <li>{@code a[2][i]} stores the entry below the diagonal at matrix position {@code (i + 1, i)}</li>
 * </ul>
 *
 * <p>The entries {@code a[0][n - 1]} and {@code a[2][n - 1]} are present but ignored.
 */
class Tridiagonals
{
    /**
     * Creates the example tridiagonal matrix specified in the brief.
     *
     * <p>The returned matrix has diagonal entries {@code -1^2, -2^2, ..., -n^2},
     * upper diagonal entries equal to {@code 1}, and lower diagonal entries
     * {@code 2, 3, ..., n}.
     *
     * <p>The returned matrix uses the tridiagonal storage convention of this class.
     *
     * @param n the matrix size; the brief assumes {@code n > 0}
     * @return a new 3 x n array representing the example tridiagonal matrix;
     *         returns {@code null} if {@code n <= 0}
     */
static double[][] exampleMatrix(int n) 
{
    if (n <= 0) {
        return null;
    }

    double[][] result = new double[3][n];
    // Fill the main diagonal with -1^2, -2^2, ..., -n^2.
    for (int i = 0; i < n; i++) {
        double value = i + 1.0;
        result[1][i] = -(value * value);
    }

    // Fill the upper diagonal with 1.0 and the lower diagonal with 2, 3, ..., n.
    for (int i = 0; i < n - 1; i++) {
        result[0][i] = 1.0;
        result[2][i] = i + 2.0;
    }

    // The entries result[0][n - 1] and result[2][n - 1] remain 0.0 and are ignored by convention.
    return result;
}


public static boolean isValidTridiagonal(double[][] a) 
{
    if (a == null || a.length != 3) 
	{
        return false;
    }

    if (a[0] == null || a[1] == null || a[2] == null) 
	{
        return false;
    }

    int n = a[1].length;

    if (n < 1) 
	{
        return false;
    }

    return a[0].length == n && a[2].length == n;
}


    /**
     * Computes the product {@code D * T}, where {@code D} is a diagonal matrix and
     * {@code T} is a tridiagonal matrix.
     *
     * <p>Left-multiplication by {@code D} scales the rows of {@code T}. In this
     * representation, entries above and on the diagonal are scaled by {@code d[i]},
     * while entries below the diagonal are scaled by {@code d[i + 1]}.
     *
     * <p>The input arrays are not modified.
     *
     * @param d the array representing the diagonal matrix
     * @param t the array representing the tridiagonal matrix
     * @return a new tridiagonal matrix representing {@code D * T}, or {@code null}
     *         if {@code d} is {@code null}, if {@code t} is not a valid tridiagonal
     *         matrix, or if the dimensions are incompatible
     */
static double[][] productWithDiagonal(double[] d, double[][] t) 
    {
        if (d == null || !isValidTridiagonal(t)) 
        {
            return null;
        }

        int n = t[1].length;
        if (d.length != n) 
        {
            return null;
        }

        double[][] result = new double[3][n];
        // The upper diagonal entry at matrix position (i, i + 1) lies in row i, so it is scaled by d[i].
        for (int i = 0; i < n - 1; i++) 
        {
            result[0][i] = d[i] * t[0][i];
        }

        // The diagonal entry at matrix position (i, i) lies in row i, so it is scaled by d[i].
        for (int i = 0; i < n; i++) 
        {
            result[1][i] = d[i] * t[1][i];
        }

        // The lower diagonal entry at matrix position (i + 1, i) lies in row i + 1, so it is scaled by d[i + 1].
        for (int i = 0; i < n - 1; i++)
        {
            result[2][i] = d[i + 1] * t[2][i];
        }

        // The entries result[0][n - 1] and result[2][n - 1] remain 0.0 and are ignored by convention.
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
        if (!isValidTridiagonal(A) || !isValidTridiagonal(B)) 
        {
            return null;
        }
        
        // check if the sizes are compatible
        int n = A[1].length;
        if (B[1].length != n) 
        {
            return null;
        }
        
        double[][] result = new double[3][n];

        // Compute the sum of the two tridiagonal matrices
        for (int row = 0; row < 3; row++) 
        {
            for (int i = 0; i < n; i++) 
            {
                result[row][i] = A[row][i] + B[row][i];
            }
        }

        return result;
    }
}
