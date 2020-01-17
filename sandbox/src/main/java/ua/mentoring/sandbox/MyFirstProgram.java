package ua.mentoring.sandbox;


public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("jopa");

        double l = 5;
        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами" + a + " и " + b + "=" + area(a,b));

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area (double len){ //помни про область видимости
        return len * len;
    }

    public static double area (double a, double b){ //переопределение функции
        return a * b;
    }

}
