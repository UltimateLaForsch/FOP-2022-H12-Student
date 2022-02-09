package h12;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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

        try(BufferedWriter bw = ioFactory.createWriter("resourceWriter"))
        {
            long seed = 10;

            TableWithTitle table1 = TableGenerator_added.createTable(50, seed);
            StudentExamEntry[] seeArray = TableGenerator_added.createEntries(50, seed);

            assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw, seeArray));
            assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw, table1.getEntries()));
        }
        catch (IOException e) {}


    }

    @Test
    public void testReadStudentExamTable()
    {
        assumeTrue(ioFactory.supportsReader());

        FileSystemIOFactory fsf = new FileSystemIOFactory();
        try(
                BufferedReader bw1 = fsf.createReader("table1_title.txt");
                BufferedReader bw2 = fsf.createReader("table1_notitle.txt");
        )
        {

            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw1));
            assertDoesNotThrow( () -> StudentExamTableIO.readStudentExamTable(bw2));
        }
        catch (IOException e) {}


    }

    @Test
    public void testReadStudentExamEntry()
    {
        StudentExamEntry seeBenchmark = new StudentExamEntry("Kantner", "Henning",
                123321, "1,3");

        String simStream = "Kantner:Henning:123321:1,3";
        StudentExamEntry seeTest = StudentExamTableIO.readStudentExamEntry(simStream);
        assertEquals(seeBenchmark.getLastName(), seeTest.getLastName());
        assertEquals(seeBenchmark.getFirstName(), seeTest.getFirstName());
        assertEquals(seeBenchmark.getEnrollmentNumber(), seeTest.getEnrollmentNumber());
        assertEquals(seeBenchmark.getMark(), seeTest.getMark());

    }




}
