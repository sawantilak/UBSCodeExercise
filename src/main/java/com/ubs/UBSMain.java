package com.ubs;

public class UBSMain {

    public static void main(String[] args) {
        try {
            StringCalculator sc = new StringCalculator();

            System.out.println(sc.add("//,|;\n1,2;3,-6;-6"));
//            System.out.println(sc.add("//*|%\n1*2%3"));
//            System.out.println(sc.add("//***\n3***3***3"));
//            System.out.println(sc.add("//***|%%\n4%%4***4"));
//            System.out.println(sc.add("//***|%%|&&\n4%%4***4&&20***50"));

        } catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();    // TODO: Write to logs
        }
    }

}
