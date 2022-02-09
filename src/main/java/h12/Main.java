package h12;

import java.io.*;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) throws IOException
    {
        System.out.println("Hello World!");
        StudentExamEntry see1 = new StudentExamEntry("HaveSeenItAll", "Dave", 123456, "1,7");
        StudentExamEntry see2 = new StudentExamEntry("HQ", "Revan", 3456, "4,0");
        StudentExamEntry see3 = new StudentExamEntry("Schink", "Finn", 87456, "1,0");
        StudentExamEntry see4 = new StudentExamEntry("Beautiful", "Sofia", 398711, "2,3");
        StudentExamEntry[] seeArray = new StudentExamEntry[4];
        seeArray[0] = see1;
        seeArray[1] = see2;
        seeArray[2] = see3;
        seeArray[3] = see4;

        TableWithTitle twt = TableGenerator_added.createTable(20, 10);
        FileSystemIOFactory ff = new FileSystemIOFactory();

        File file = new File("weihe.txt");
        FileWriter fileReader = new FileWriter(file);
        ResourceIOFactory rf = new ResourceIOFactory();
        BufferedWriter bw2 = rf.createWriter("weihe2.txt");
        BufferedWriter bw = new BufferedWriter(fileReader);

        StudentExamTableIO.writeStudentExamTable(bw2, seeArray, "Top of Team Weihe");


    }
}
