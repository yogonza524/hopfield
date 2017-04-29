/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.hopfield;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
public class Hopfield {

    private int dimension;
    private List<Double[]> patterns;
    private Double[][] W;

    public Double[][] getW() {
        return W;
    }

    public int getDimension() {
        return dimension;
    }
    
    public boolean removePatternAt(int index){
        try {
            this.patterns.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean addPattern(Double[] pattern){
        return this.patterns.add(pattern);
    }
    
    private Hopfield(){}
    
    /**
     * Train the network, after train process the matrix W
     * is loaded with weights, this is needed to test process later
     */
    public void train(){
        
        W = Matrix.initMatrix(dimension, dimension);
        
        Double[][] identity = Matrix.identity(dimension);
        
        for(Double[] pattern : patterns){
            Double[][] patternMatrix = Matrix.toMatrix(pattern);
            W = Matrix.add(W, patternMatrix);
            W = Matrix.subtract(W, identity);
        }
    }
    
    /**
     * Check if a pattern learned by de network is similar
     * to a pattern passed as param
     * @param pattern param patern to find in network
     * @return a learned pattern if the network converge or null if not
     */
    public Result test(Double[] pattern){
        if (pattern == null) {
            throw new NullPointerException("Pattern to test must be not null");
        }
        
        if (pattern.length != dimension) {
            throw new RuntimeException("Dimension of pattern mus be " + dimension);
        }
        
        Double[] result = null;
        List<Double[]> stack = new ArrayList<>();
        
        stack.add(pattern);
        
        boolean loop = false;
        
        while(!loop){
            
            pattern = applyFunction(Matrix.multiply(pattern, W));
            boolean isConainted = contains(pattern, stack) && contains(pattern, patterns);
            if (isConainted && Arrays.deepEquals(pattern, stack.get(stack.size() - 1))) {
                result = pattern;
                loop = true;
                break;
            }
            else{

                //Add to stack
                stack.add(pattern);

                //Find a loop in stack
                for (int i = 0; i < stack.size(); i++) {
                    for (int j = i + 1; j < stack.size(); j++) {
                        if (Arrays.deepEquals(stack.get(i), stack.get(j))) {
                            loop = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return new Result(result, stack);
    }
    
    /**
     * Check if the patterns to learn are orthogonls
     * @return true if the learned patterns are orthogonals, false otherwise
     */
    public boolean orthogonality(){
        if (patterns == null) {
            throw new NullPointerException("Patterns null");
        }
        if (patterns.isEmpty()) {
            throw new RuntimeException("There are not patterns to decide");
        }
        boolean result = true;
        for (int i = 0; i < patterns.size(); i++) {
            int sum = 0;
            for (int j = i + 1; j < patterns.size(); j++) {
                for (int k = 0; k < patterns.get(0).length; k++) {
                    sum = (int)(patterns.get(i)[k] * patterns.get(j)[k]);
                }
                if (sum > 0) {
                    result = false;
                    break;
                }
            }
        }
        
        return result;
    }
    
    
    /**
     * Max number of patterns that can learn the network 
     * without errors
     * @return double number, represents the number of patterns that can learn
     * good the network
     */
    public double goodCapacityStorage(){
        return 0.138 * dimension;
    }
    
    public double perfectCapacityStorage(){
        return dimension / (4 * Math.log(dimension));
    }
    
    public double goodCapacityStorage(int neurons){
        return 0.138 * neurons;
    }
    
    public double perfectCapacityStorage(int neurons){
        if (neurons > 0) {
            return neurons / (4 * Math.log(neurons));
        }
        throw new RuntimeException("number of neurons must be positive");
    }
    
    private Double[] applyFunction(Double[] pattern){
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] >= 0.0) {
                pattern[i] = 1.0;
            }
            else{
                pattern[i] = -1.0;
            }
        }
        return pattern;
    }

    private boolean contains(Double[] u, List<Double[]> stack) {
        boolean result = false;
        for(Double[] pattern : stack){
            if (Arrays.deepEquals(u, pattern)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public static class HopfieldBuilder{
        private int dimension;
        private final List<Double[]> patterns;

        public HopfieldBuilder() {
            patterns = new ArrayList<>();
        }
        
        /**
         * Set dimension (number of variables of entries)
         * @param dimension int number that say how many variables has patterns
         * @return HopfieldBuilder
         */
        public HopfieldBuilder dimension(int dimension){
            if (dimension < 1) {
                throw new IllegalStateException("Dimension must be positive");
            }
            this.dimension = dimension;
            return this;
        }
        
        public HopfieldBuilder addPattern(Double[] pattern){
            if (pattern.length != dimension) {
                throw new RuntimeException("Dimension of pattern not valid, must be " + dimension + "-dimension");
            }
            this.patterns.add(pattern);
            return this;
        }
        
        public Hopfield build(){
            if (dimension == 0) {
                throw new IllegalStateException("Dimension must be positive, is not setted");
            }
            Hopfield h = new Hopfield();
            h.dimension = dimension;
            h.patterns = patterns;
            return h;
        }
    }
    
    private static class Matrix {

        // return n-by-n identity matrix I
        public static Double[][] identity(int n) {
            Double[][] a = new Double[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        a[i][i] = 1.0;
                    }
                    else{
                        a[i][j] = 0.0;
                    }
                }
            return a;
        }

        // return x^T y
        public static Double dot(Double[] x, Double[] y) {
            if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
            Double sum = 0.0;
            for (int i = 0; i < x.length; i++)
                sum += x[i] * y[i];
            return sum;
        }

        // return B = A^T
        public static Double[][] transpose(Double[][] a) {
            int m = a.length;
            int n = a[0].length;
            Double[][] b = new Double[n][m];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    b[j][i] = a[i][j];
            return b;
        }

        // return c = a + b
        public static Double[][] add(Double[][] a, Double[][] b) {
            int m = a.length;
            int n = a[0].length;
            Double[][] c = new Double[m][n];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    c[i][j] = a[i][j] + b[i][j];
            return c;
        }

        // return c = a - b
        public static Double[][] subtract(Double[][] a, Double[][] b) {
            int m = a.length;
            int n = a[0].length;
            Double[][] c = new Double[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = 0.0;
                }
            }
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    c[i][j] = a[i][j] - b[i][j];
            return c;
        }

        // return c = a * b
        public static Double[][] multiply(Double[][] a, Double[][] b) {
            int m1 = a.length;
            int n1 = a[0].length;
            int m2 = b.length;
            int n2 = b[0].length;
            if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
            Double[][] c = new Double[m1][n2];
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < n2; j++) {
                    c[i][j] = 0.0;
                }
            }
            for (int i = 0; i < m1; i++)
                for (int j = 0; j < n2; j++)
                    for (int k = 0; k < n1; k++)
                        c[i][j] += a[i][k] * b[k][j];
            return c;
        }

        // matrix-vector multiplication (y = A * x)
        public static Double[] multiply(Double[][] a, Double[] x) {
            int m = a.length;
            int n = a[0].length;
            if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
            Double[] y = new Double[m];
            for (int i = 0; i < m; i++) {
                y[i] = 0.0;
            }
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    y[i] += a[i][j] * x[j];
            return y;
        }


        // vector-matrix multiplication (y = x^T A)
        public static Double[] multiply(Double[] x, Double[][] a) {
            int m = a.length;
            int n = a[0].length;
            if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
            Double[] y = new Double[n];
            for (int i = 0; i < n; i++) {
                y[i] = 0.0;
            }
            for (int j = 0; j < n; j++)
                for (int i = 0; i < m; i++)
                    y[j] += a[i][j] * x[i];
            return y;
        }
        
        public static Double[][] vectorToMatrix(Double[] vector){
            Double[][] result = new Double[vector.length][vector.length];
            for (int i = 0; i < vector.length; i++) {
                result[i] = vector;
            }
            return result;
        }
        
        public static Double[][] toMatrix(Double[] vector){
            Double[][] result = new Double[vector.length][vector.length];
            for (int i = 0; i < vector.length; i++) {
                for (int j = 0; j < vector.length; j++) {
                    result[i][j] = vector[i] * vector[j];
                }
            }
            return result;
        }
        
        public static Double[][] initMatrix(int dimension, int dimension0) {
            Double[][] output = new Double[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    output[i][j] = 0.0;
                }
            }
            return output;
        }
    }

    public static class Result{
        private final Double[] pattern;
        private final List<Double[]> history;

        public Result(Double[] pattern, List<Double[]> history) {
            this.pattern = pattern;
            this.history = history;
        }

        public Double[] getPattern() {
            return pattern;
        }

        public List<Double[]> getHistory() {
            return history;
        }
    }
}
