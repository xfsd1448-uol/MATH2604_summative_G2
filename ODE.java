import static java.lang.Math.*;

/**
 * Provides a method for approximating the solution of the differential equation
 * using a finite-difference method on the interval [0,1].
 *
 * The method builds and solves a tridiagonal linear system to obtain
 * approximate values at the grid points.
 */

class ODE
{

/**
* Approximates the value of f(0.5) for the boundary value problem
* f''(x) = cos(x) f(x) + a x^2 on 0 <= x <= 1 with f(0) = f(1) = 0.
*
* The method uses the finite-difference construction described in the
* project brief and solves the resulting tridiagonal linear system.
*
* If 0.5 is not a grid point, the result is obtained by averaging
* the values at the nearest grid points.
*
* It is assumed that n > 0.
*
* @param a the constant in the differential equation
* @param n the number of interior grid points
* @return an approximation to f(0.5)
*/

public static double solve(double a, int n) 
    {

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
