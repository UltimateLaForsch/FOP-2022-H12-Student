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

    /**
     * JUnit test of method WriteStudentExamTable
     * @throws IOException If an I/O error occurs
     */
    @Test
    public void testWriteStudentExamTable() throws IOException
    {
        assumeTrue(ioFactory.supportsWriter());

        try(BufferedWriter bw = ioFactory.createWriter("Weihe_Team_WriteTab1.txt");
            BufferedWriter bw2 = ioFactory.createWriter("Weihe_Team_WriteTab2.txt"))
        {
            long seed = 10;

            TableWithTitle table1 = TableGenerator.createTable(50, seed);
            StudentExamEntry[] seeArray = TableGenerator.createEntries(50, seed);

            assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw, seeArray));
            assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw2, table1.getEntries(), "FOP rules"));
        }
    }

    /**
     * JUnit test of method ReadStudentExamTable
     * @throws IOException If an I/O error occurs
     */
    @Test
    public void testReadStudentExamTable() throws IOException
    {
        assumeTrue(ioFactory.supportsReader());

        try(BufferedWriter bw1 = ioFactory.createWriter("Weihe_Team_ReadTab1.txt");
            BufferedWriter bw2 = ioFactory.createWriter("Weihe_Team_ReadTab2.txt"))
        {
            long seed = 10;

            TableWithTitle table1 = TableGenerator.createTable(50, seed);
            StudentExamEntry[] seeArray = TableGenerator.createEntries(50, seed);

            StudentExamTableIO.writeStudentExamTable(bw1, seeArray);
            StudentExamTableIO.writeStudentExamTable(bw2, table1.getEntries(), "FOP rules");
        }

        try(BufferedReader bw3 = ioFactory.createReader("Weihe_Team_ReadTab1.txt");
            BufferedReader bw4 = ioFactory.createReader("Weihe_Team_ReadTab2.txt"))
        {
            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw3));
            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw4));
        }
    }

    /**
     * JUnit test of method ReadStudentExamEntry
     */
    @Test
    public void testReadStudentExamEntry()
    {
        StudentExamEntry seeBenchmark = new StudentExamEntry("Kantner", "Henning",
                123321, "1,3");
        String simStream = "Henning:Kantner:123321:1,3";
        StudentExamEntry seeTest = StudentExamTableIO.readStudentExamEntry(simStream);
        // seeTest.equals(seeBenchmark);

        assert(seeBenchmark.equals(seeTest));

        StudentExamEntry seeBenchmark2 = new StudentExamEntry("Kantner", "Henning",
                123321);
        String simStream2 = "Henning:Kantner:123321:n/a";
        StudentExamEntry seeTest2 = StudentExamTableIO.readStudentExamEntry(simStream2);

        assert(seeBenchmark.equals(seeTest));
    }

    /**
     * JUnit test of method WriteStudentExamTable
     * @throws IOException If an I/O error occurs
     */
    @Test
    public void testWriteStudentExamTableComplex() throws IOException
    {
        int size = 50;
        TableWithTitle twtIn = TableGenerator.createTable(size, 50);

        try(BufferedWriter bw = ioFactory.createWriter("Weihe_Team_WriteComplex1.txt"))
        {
            StudentExamTableIO.writeStudentExamTable(bw, twtIn.getEntries(), twtIn.getTitle());
        }

        try(BufferedReader bw1 = ioFactory.createReader("Weihe_Team_WriteComplex1.txt"))
        {
            StudentExamEntry[] seeArray = twtIn.getEntries();
            // check title
            String title = "!" + twtIn.getTitle();
            assertEquals(title, bw1.readLine());
            // check length info
            int noEntries = seeArray.length;
            assertEquals(String.valueOf(noEntries), bw1.readLine());
            // check entries
            for (int i = 0; i < noEntries; i++)
            {
                String entry = seeArray[i].getFirstName() + ":" + seeArray[i].getLastName() + ":" +
                        seeArray[i].getEnrollmentNumber() + ":" + seeArray[i].getMark();
                assertEquals(entry, bw1.readLine());
            }
        }
    }

    /**
     * JUnit combined and large (100 tables) test of methods Write / Read - StudentExamTable
     * @throws IOException If an I/O error occurs
     */
    @Test
    public void testWriteAndReadStudentExamTable() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < 100; i++)
        {
            long seed = i;

            TableWithTitle twt = TableGenerator.createTable(200, seed);

            try(BufferedWriter bw1 = ioFactory.createWriter("Team_Weihe_Write_And_Read_1.txt");
                BufferedWriter bw2WoHeader = ioFactory.createWriter("Team_Weihe_Write_And_Read_2.txt"))
            {
                StudentExamTableIO.writeStudentExamTable(bw1,twt.getEntries(),twt.getTitle());
                StudentExamTableIO.writeStudentExamTable(bw2WoHeader,twt.getEntries());
            }

            try(BufferedReader br1 = ioFactory.createReader("Team_Weihe_Write_And_Read_1.txt");
                BufferedReader br2 = ioFactory.createReader("Team_Weihe_Write_And_Read_2.txt"))
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
        }
    }
}
