package ua.mentoring.sandbox;


public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("jopa");

        Square square = new Square(5);
        System.out.println("Площадь квадрата со стороной " + square.l + " = " + area(square));

        Rectangle rectangle = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + rectangle.a + " и " + rectangle.b + " = " + area(rectangle));

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area (Square square){ //помни про область видимости
        return square.l * square.l;
    }

    public static double area (Rectangle rectangle){ //переопределение функции
        return rectangle.a * rectangle.b;
    }

}
