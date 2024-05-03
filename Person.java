import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    private String name;
    private int age;
    private double weight;
    private double height;
    private String gender;

    public Person(String name, int age, double weight, double height, String gender) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public double Bmrcalc() {
        if (gender.equalsIgnoreCase("male")) {
            return 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else {
            return 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
    }

    public double Bmicalc() {
        return weight / ((height / 100) * (height / 100));
    }

    public String stat() {
        double bmi = Bmicalc();
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 25) {
            return "Optimal weight";
        } else {
            return "Overweight";
        }
    }
}