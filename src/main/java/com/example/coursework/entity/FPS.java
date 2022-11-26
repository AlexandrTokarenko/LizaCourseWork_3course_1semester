package com.example.coursework.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FPS {

    private BigDecimal maxProfit;
    private BigDecimal[][] matrix;
    private int numberOfMachines;
    private int numberOfDrives;

    public FPS(double lamb, double tcp, double d, double vv, double vn, int k) {

        matrix = new BigDecimal[k+1][k+1];
        calculate(lamb,tcp,d,vv,vn,k);
    }

    public FPS() {

        matrix = new BigDecimal[0][0];
    }

    private void calculate(double lamb, double tcp, double d, double vv, double vn, int k) {

        double p = lamb * tcp;

        BigDecimal max = BigDecimal.ZERO;

        int v = 0;
        int n = 0;

        System.out.println();

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                BigDecimal res = BigDecimal.valueOf(d*lamb).multiply((BigDecimal.ONE.subtract((a(i,j,p,k).
                        multiply(b(i,j,p,k)))))).subtract(BigDecimal.valueOf(vv*i+vn*j));
                if (res.compareTo(max) > 0) {
                    max = res;
                    v = i;
                    n = j;
                }
                if (BigDecimal.ZERO.compareTo(res) > 0) res = BigDecimal.ZERO;
                matrix[i][j] = res;
            }
        }

        this.maxProfit = max;
        this.numberOfDrives = n;
        this.numberOfMachines = v;
    }

    private BigDecimal b(int n, int k, double p, int i) {
        BigDecimal b = BigDecimal.ZERO;
        if ((p - n) < 1.0e-18) {
        }
        else b = (BigDecimal.valueOf(p).pow(n+1).divide(BigDecimal.valueOf(n).multiply(factorial(n)),2, RoundingMode.HALF_UP)).
                multiply(((BigDecimal.ONE.subtract(BigDecimal.valueOf(p/n).pow(i*k))).
                        divide((BigDecimal.ONE.subtract(BigDecimal.valueOf(p/n))),2,RoundingMode.HALF_UP)));
        return BigDecimal.ONE.divide((BigDecimal.ONE.add(sum(n,p).add(b))),100, RoundingMode.HALF_UP);
    }

    private BigDecimal sum(int n, double p) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i <= n; i++) {
            sum.add(BigDecimal.valueOf(p).pow(i).divide(factorial(i)));
        }
        return sum;
    }

    private BigDecimal a(int n, int k, double p, int i) {
        return BigDecimal.valueOf(p).pow(n+i*k).divide((BigDecimal.valueOf(n).pow(i*k).multiply(factorial(n))),2,RoundingMode.HALF_UP);
    }

    private BigDecimal factorial(int n) {
        BigDecimal res = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            res.multiply(BigDecimal.valueOf(i));
        }
        return res;
    }

    public BigDecimal getMaxProfit() {
        return maxProfit;
    }

    public BigDecimal[][] getMatrix() {
        return matrix;
    }

    public int getNumberOfMachines() {
        return numberOfMachines;
    }

    public int getNumberOfDrives() {
        return numberOfDrives;
    }
}
