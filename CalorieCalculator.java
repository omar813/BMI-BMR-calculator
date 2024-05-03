import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalorieCalculator {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        System.out.println("Enter N: ");
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            System.out.println("Name: ");
            String name = scanner.next();
            System.out.println("Age: ");
            int age = scanner.nextInt();
            System.out.println("Weight: ");
            double weight = scanner.nextDouble();
            System.out.println("Height: ");
            double height = scanner.nextDouble();
            System.out.println("Gender (M/F): ");
            String gender = scanner.next();
            persons.add(new Person(name, age, weight, height, gender));
        }

        try {
            FileWriter fileWriter = new FileWriter("CalorieReport.txt");
            for (Person person : persons) {
                fileWriter.write("Name: " + person.getName() + "\n");
                fileWriter.write("Age: " + person.getAge() + "\n");
                fileWriter.write("Weight: " + person.getWeight() + "\n");
                fileWriter.write("Height: " + person.getHeight() + "\n");
                fileWriter.write("Gender: " + person.getGender() + "\n");
                fileWriter.write("BMI: " + person.Bmicalc() + "\n");
                fileWriter.write("BMR: " + person.Bmrcalc() + "\n");
                fileWriter.write("Weight Status: " + person.stat() + "\n\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double sumOfBmrForMale = 0;
        double sumOfBmrForFemale = 0;
        int menCounter = 0;
        int womenCounter = 0;

        for (Person person : persons) {
            if (person.stat().equalsIgnoreCase("Optimal weight")) {
                if (person.getGender().equalsIgnoreCase("M")) {
                    menCounter++;
                    sumOfBmrForMale +=person.Bmrcalc();

                } else if (person.getGender().equalsIgnoreCase("F")) {
                    womenCounter++;
                    sumOfBmrForFemale += person.Bmrcalc();
                }
            }
        }
        double menAvg = menCounter > 0 ? sumOfBmrForMale / menCounter : 0;
        double womenAvg = womenCounter > 0 ? sumOfBmrForFemale / womenCounter : 0;

        System.out.println("Average BMR for optimal weight males: " + menAvg);

        System.out.println("Average BMR for optimal weight females: " + womenAvg);
        Person personWithHighestBMI = null;
        double highestBMI = 0.0;


        for (Person person : persons) {
            double bmi = person.Bmicalc();
            if (bmi > highestBMI) {
                highestBMI = bmi;
                personWithHighestBMI = person;
            }
        }


        if (personWithHighestBMI != null) {
            System.out.println("highest BMI:");
            System.out.println("THE Name: " + personWithHighestBMI.getName());
            System.out.println("THE Age: " + personWithHighestBMI.getAge());
            System.out.println("THE Weight: " + personWithHighestBMI.getWeight());
            System.out.println("THE Height: " + personWithHighestBMI.getHeight());
            System.out.println("THE Gender: " + personWithHighestBMI.getGender());
            System.out.println("THE BMI: " + highestBMI);
            System.out.println("THE Weight Stat: " + personWithHighestBMI.stat());
        } else {
            System.out.println("Not found.");
        }

        try {
            FileOutputStream fileOutputStream=new FileOutputStream("bin.dat");
            DataOutputStream dataOutputStream=new DataOutputStream(fileOutputStream);
            dataOutputStream.write("highest BMI: ".getBytes());
            dataOutputStream.write(("THE Name: " + personWithHighestBMI.getName()+"\n").getBytes());
            dataOutputStream.write(("THE Age: " + personWithHighestBMI.getAge()+"\n").getBytes());
            dataOutputStream.write(("THE Weight: " + personWithHighestBMI.getWeight()+"\n").getBytes());
            dataOutputStream.write(("THE Height: " + personWithHighestBMI.getHeight()+"\n").getBytes());
            dataOutputStream.write(("THE Gender: " + personWithHighestBMI.getGender()+"\n").getBytes());
            dataOutputStream.write(("THE BMI: " + highestBMI+"\n").getBytes());
            dataOutputStream.write(("THE Weight Stat: " + personWithHighestBMI.stat()+"\n").getBytes());
            dataOutputStream.write("the avg BMR".getBytes());
            dataOutputStream.write("\n".getBytes());
            dataOutputStream.write(("Average BMR for optimal weight males: "+ menAvg+"\n").getBytes());
            dataOutputStream.write(("Average BMR for optimal weight females: " + womenAvg+"\n").getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        }
