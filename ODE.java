import static java.lang.Math.*;

/**
 * Provides a method for approximating the solution of the differential equation
 * described in the project brief using a finite-difference approach.
 *
 * The method constructs a tridiagonal linear system based on the discretisation
 * of the interval [0,1], and solves it to obtain approximate values of the
 * solution at the grid points.
 */

class ODE
{
    public static double solve(double a, int n) {

    // Step size for the grid on [0,1]
    double h = 1.0 / (n + 1);

    // Tridiagonal matrix M and right-hand side vector rhs
    double[][] M = new double[3][n];
    double[] rhs = new double[n];

    // Construct M and rhs 
    for (int i = 0; i < n; i++) {
        double xi = (i + 1) * h;

        // Main diagonal entries
        M[1][i] = 2.0 + h * h * cos(xi);

        // Right-hand side
        rhs[i] = -h * h * a * xi * xi;
    }

    // Fill upper and lower diagonals with -1
    for (int i = 0; i < n - 1; i++) {
        M[0][i] = -1.0;   // upper diagonal
        M[2][i] = -1.0;   // lower diagonal
    }

    // Last entries are unused in the tridiagonal representation
    M[0][n - 1] = 0.0;
    M[2][n - 1] = 0.0;

    // Solve the linear system M w = rhs
    double[] w = Tridiagonals.linearSolve(M, rhs);

    // Determine the position of x = 0.5 in the grid
    double position = 0.5 / h;
    int nearest = (int) position;

    // If 0.5 coincides with a grid point, return directly
    if (Math.abs(position - nearest) < 1e-12) {
        return w[nearest - 1];
    } else {
        // Otherwise averages the two nearest values
        int leftIndex = (int) Math.floor(position) - 1;
        int rightIndex = leftIndex + 1;
        return (w[leftIndex] + w[rightIndex]) / 2.0;
    }
  }
}
