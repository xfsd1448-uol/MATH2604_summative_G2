import static java.lang.Math.*;

class Diagonals
{




    public static double[] inverse(double[] a) {
        if (a == null) return null;

        int n = a.length;
        double[] inv = new double[n];

        for (int i = 0; i < n; i++) {
            inv[i] = 1.0 / a[i];
        }

        return inv;
    }



    
}
