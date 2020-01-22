package ua.mentoring.sandbox;

public class Equation {


  private double a;
  private double b;
  private double c;

  private int number;

  public Equation(double a, double b, double c) {

    this.a = a;
    this.b = b;
    this.c = c;

    double d = b * b - 4 * a * c;
    if (d > 0)
      number = 2;
    else {
      if (d == 0)
        number = 1;
      else
        number = 0;
    }
  }

  public int rootNumber() {
    return number;
}
}

