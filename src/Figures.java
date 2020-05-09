import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figures {
    public interface Shape {
        double getPerimeter();
    }

    public static class Triangle implements Shape {
        private double a;
        private double b;
        private double c;

        public Triangle(double a, double b, double c) {
            if (a + b <= c || a + c <= b || b + c <= a || Math.min(Math.min(a, b), c) <= 0) {
                throw new IllegalArgumentException("Triangle init error: illegal edge length");
            }
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public double getPerimeter() {
            return a + b + c;
        }
    }

    public static class Rectangle implements Shape {
        private double a;
        private double b;

        public Rectangle(double a, double b) {
            if (a <= 0 || b <= 0) {
                throw new IllegalArgumentException("Rectangle init error: illegal edge length");
            }
            this.a = a;
            this.b = b;
        }

        @Override
        public double getPerimeter() {
            return 2 * a + 2 * b;
        }
    }

    public static class Circle implements Shape{
        private double r;

        public Circle(double r) {
            if (r <= 0) {
                throw new RuntimeException("Rectangle init error: illegal radius");
            }
            this.r = r;
        }

        @Override
        public double getPerimeter() {
            return 4 * Math.PI * r;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            double a = random.nextDouble() + 0.01;
            double b = random.nextDouble() + 0.01;
            double c = a + b - 0.2;
            shapes.add(new Triangle(a, b, c));
            shapes.add(new Rectangle(random.nextDouble() + 0.01, random.nextDouble() + 0.01));
            shapes.add(new Circle(random.nextDouble() + 0.01));
        }
        System.out.println("Минимальный периметр: " + shapes.stream().mapToDouble(Shape::getPerimeter).min().getAsDouble());
        System.out.println("Максимальный периметр: " + shapes.stream().mapToDouble(Shape::getPerimeter).max().getAsDouble());

    }
}
