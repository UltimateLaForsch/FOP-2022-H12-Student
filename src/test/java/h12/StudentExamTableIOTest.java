package h12;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class StudentExamTableIOTest
{

    final IOFactory ioFactory = new FileSystemIOFactory();

    @Test
    public void testWriteStudentExamTable()
    {
        assumeTrue(ioFactory.supportsWriter());

        try(FileWriter fileWriter = new FileWriter("Weihe_team1.txt");
            FileWriter fileWriter2 = new FileWriter("Weihe_team2.txt");
            BufferedWriter bw = new BufferedWriter(fileWriter);
            BufferedWriter bw2 = new BufferedWriter(fileWriter2))
            //BufferedWriter bw = ioFactory.createWriter(fileWriter))
        {
            long seed = 10;

            TableWithTitle table1 = TableGenerator_added.createTable(50, seed);
            StudentExamEntry[] seeArray = TableGenerator_added.createEntries(50, seed);

            assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw, seeArray));
            assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw2, table1.getEntries()));
        }
        catch (IOException e) {}


    }

    @Test
    public void testReadStudentExamTable() throws FileNotFoundException
    {
        assumeTrue(ioFactory.supportsReader());

        FileSystemIOFactory fsf = new FileSystemIOFactory();

        try(FileReader fr1 = new FileReader("Weihe_team1.txt");
            FileReader fr2 = new FileReader("Weihe_team2.txt");
            BufferedReader bw1 = new BufferedReader(fr1);
            BufferedReader bw2 = new BufferedReader(fr2))
        {
            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw1));
            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw2));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    @Test
    public void testReadStudentExamEntry()
    {
        StudentExamEntry seeBenchmark = new StudentExamEntry("Kantner", "Henning",
                123321, "1,3");

        String simStream = "Kantner:Henning:123321:1,3";
        StudentExamEntry seeTest = StudentExamTableIO.readStudentExamEntry(simStream);
        // seeTest.equals(seeBenchmark);

        assertEquals(StudentExamEntry.class, seeTest.getClass());
        assertEquals(seeBenchmark.getLastName(), seeTest.getLastName());
        assertEquals(seeBenchmark.getFirstName(), seeTest.getFirstName());
        assertEquals(seeBenchmark.getEnrollmentNumber(), seeTest.getEnrollmentNumber());
        assertEquals(seeBenchmark.getMark(), seeTest.getMark());

    }

    @Test
    public void testWriteStudentExamTableComplex()
    {
        TableGenerator.createTable(50, 50);
    }

    @Test
    public void testWriteAndReadStudentExamTable()
    {
        Random random = new Random();
        for (int i = 0; i < 100; i++)
        {
            long seed = i;

            TableWithTitle twt = TableGenerator.createTable(200, seed);
            // BufferedReader br = new BufferedReader();


        }
    }


}
