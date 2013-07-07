/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encodeproject;

/**
 *
 * @author XiaoxuShen
 */

// based on https://code.google.com/p/dct-watermark/source/browse/trunk/src/net/watermark/DCT.java?r=2
class DCT {
    
    static private int N = EncodeProject.blockSize;

    static private double C[][] = new double[N][N];

    static private double Ct[][] = new double[N][N];

    static {
        int ii;
        int jj;
        final double pi = Math.atan(1.0) * 4.0;
        for (jj = 0; jj < N; jj++) {
            C[0][jj] = 1.0 / Math.sqrt(N);
            Ct[jj][0] = C[0][jj];
        }
        for (ii = 1; ii < N; ii++) {
            for (jj = 0; jj < N; jj++) {
                C[ii][jj] = Math.sqrt(2.0 / N) * Math.cos(pi * (2 * jj + 1) * ii / (2.0 * N));
                Ct[jj][ii] = C[ii][jj];
            }
        }
    }

    static int[][] FDCT(final int input[][]) 
    {
        final double temp[][] = new double[N][N];
        int output[][] = new int[N][N]; 
        double temp1;

        for (int ii = 0; ii < N; ii++) {
            for (int jj = 0; jj < N; jj++) {
                temp[ii][jj] = 0.0;
                for (int kk = 0; kk < N; kk++) {
                    temp[ii][jj] += ((int)(input[ii][kk]) - 128) * Ct[kk][jj];             
                }
            }
        }

        for (int ii = 0; ii < N; ii++) {
            for (int jj = 0; jj < N; jj++) {
                temp1 = 0.0;
                for (int kk = 0; kk < N; kk++) {
                    temp1 += C[ii][kk] * temp[kk][jj];
                }
                output[ii][jj] = (int) Math.round(temp1);
            }
        }
        return output;
    }

    static int[][] IDCT(final int input[][]) 
    {
        final double temp[][] = new double[N][N];
        int output[][] = new int[N][N];
        double temp1;
        int ii, jj, kk;

        for (ii = 0; ii < N; ii++) {
            for (jj = 0; jj < N; jj++) {
                temp[ii][jj] = 0.0;
                for (kk = 0; kk < N; kk++) {
                    temp[ii][jj] += input[ii][kk] * C[kk][jj];
                }
            }
        }

        for (ii = 0; ii < N; ii++) {
            for (jj = 0; jj < N; jj++) {
                temp1 = 0.0;
                for (kk = 0; kk < N; kk++) {
                    temp1 += Ct[ii][kk] * temp[kk][jj];
                }
                temp1 += 128;
                if (temp1 < 0) {
                    output[ii][jj] = 0;
                } else if (temp1 > 255) {
                    output[ii][jj] = 255;
                } else {
                    output[ii][jj] = (int) Math.round(temp1);
                }
            }
        }
        return output;
    }
}

