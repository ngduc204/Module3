package com;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double getRoot1() {
        double discriminant = getDiscriminant();
        if (discriminant >= 0) {
            return (-b + Math.sqrt(discriminant)) / (2 * a);
        }
        return Double.NaN; // Nếu không có nghiệm thực
    }

    public double getRoot2() {
        double discriminant = getDiscriminant();
        if (discriminant >= 0) {
            return (-b - Math.sqrt(discriminant)) / (2 * a);
        }
        return Double.NaN;
    }

    public static void main(String[] args) {
        QuadraticEquation equation = new QuadraticEquation(1, -3, 2);
        double root1 = equation.getRoot1();
        double root2 = equation.getRoot2();

        if (!Double.isNaN(root1)) {
            System.out.println("Nghiệm 1: " + root1);
            if (!Double.isNaN(root2)) {
                System.out.println("Nghiệm 2: " + root2);
            }
        } else {
            System.out.println("Phương trình không có nghiệm thực.");
        }
    }
}