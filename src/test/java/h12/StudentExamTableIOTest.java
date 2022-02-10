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
    public void testWriteStudentExamTable() throws IOException
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
    }

    @Test
    public void testReadStudentExamTable() throws IOException
    {
        assumeTrue(ioFactory.supportsReader());

        // :::::::::   neuen writer erstellen: 2 dateien w and wo title

        try(BufferedReader bw3 = ioFactory.createReader("Weihe_team1.txt"))
        {
            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw3));
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

        assert(seeBenchmark.equals(seeTest));

        // :::::::::::: zweites ohne note

        assertEquals(StudentExamEntry.class, seeTest.getClass());
        assertEquals(seeBenchmark.getLastName(), seeTest.getLastName());
        assertEquals(seeBenchmark.getFirstName(), seeTest.getFirstName());
        assertEquals(seeBenchmark.getEnrollmentNumber(), seeTest.getEnrollmentNumber());
        assertEquals(seeBenchmark.getMark(), seeTest.getMark());

    }

    @Test
    public void testWriteStudentExamTableComplex() throws IOException
    {
        int size = 50;
        TableGenerator.createTable(size, 50);
        // :::: writer erstellen
        FileWriter fileWriter = new FileWriter("Weihe_team555.txt");
        BufferedWriter bw = new BufferedWriter(fileWriter);
        // :::: reader , readline
        BufferedReader bw3 = ioFactory.createReader("Weihe_team555.txt");
        // :::: vergleichsstring mit loop erstellen

    }

    @Test
    public void testWriteAndReadStudentExamTable() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < 100; i++)
        {
            long seed = i;

            TableWithTitle twt = TableGenerator.createTable(200, seed);

            try(BufferedWriter bw1 = ioFactory.createWriter("Weihe_666.txt");
                BufferedWriter bw2WoHeader = ioFactory.createWriter("Weihe_667.txt"))
            {
                StudentExamTableIO.writeStudentExamTable(bw1,twt.getEntries(),twt.getTitle());
                StudentExamTableIO.writeStudentExamTable(bw2WoHeader,twt.getEntries());
            }

            try(BufferedReader br1 = ioFactory.createReader("Weihe_666.txt");
                BufferedReader br2 = ioFactory.createReader("Weihe_667.txt"))
            {
                TableWithTitle tw3 = StudentExamTableIO.readStudentExamTable(br1);
                TableWithTitle tw4 = StudentExamTableIO.readStudentExamTable(br2);

                assertEquals(twt.getTitle(), tw3.getTitle());

                for (int j = 0; j < 200 ; j++)
                {
                    StudentExamEntry bmEntry = twt.getEntries()[i];
                    StudentExamEntry testEntry = tw3.getEntries()[i];
                    assertEquals(bmEntry, testEntry);
                    assertEquals(twt.getEntries()[i], tw4.getEntries()[i]);
                }
            }
            // ::::

            // BufferedReader br = new BufferedReader();
        }
    }

    // ::: private static
}
