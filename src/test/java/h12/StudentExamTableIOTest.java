package h12;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;

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
        BufferedWriter bw = ioFactory.createWriter("resourceWriter");

        long seed = 10;

        TableWithTitle table1 = TableGenerator.createTable(50,seed);
        StudentExamEntry[] seeArray = TableGenerator.createEntries(50, seed);

        assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw, seeArray));
        assertDoesNotThrow( () -> StudentExamTableIO.writeStudentExamTable(bw, table1.getEntries()));

    }




}
