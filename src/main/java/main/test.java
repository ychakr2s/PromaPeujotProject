package main;

import controller.Controller;

import java.io.File;

public class test {

    public static void main(String[] args) {

//        Controller cr = new Controller();
////        cr.checkFile("D:\\Kündigung.pdf");
//        boolean bl = cr.checkFile("D:\\musterbrief.pdf");
////        boolean bg =
//        System.out.println("he: " + bl);



        // Get the file
        File f = new File("D:\\test.txt");

        // Check if the specified file
        // can be written or not
        if (f.canWrite())
            System.out.println("Can be written");
        else
            System.out.println("Cannot be written");

    }
}
