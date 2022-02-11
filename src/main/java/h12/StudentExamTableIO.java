package h12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class StudentExamTableIO
{
    /**
     * Writes a studentExamEntry object into a writer object
     * @param writer the writer
     * @param see the studentExamEntry
     * @throws IOException if a problem during the writing occurs
     */
    public static void writeStudentExamEntry(Writer writer, StudentExamEntry see) throws IOException
    {
        String mark = (Objects.equals(see.getMark(), "n/a")) ? "" : see.getMark();
        writer.write(see.getFirstName() + ":" + see.getLastName() + ":" + see.getEnrollmentNumber() +
                ":" + mark + "\n");
        // writer.flush();
    }

    /**
     * 1st version: Writes a studentExamEntry array into a writer object
     * @param writer the writer
     * @param seeArray studentExamEntry array
     * @throws IOException if a problem during the writing occurs
     */
    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] seeArray) throws IOException
    {
        writer.write(seeArray.length + "\n");
        for (var see:seeArray)
        {
            writeStudentExamEntry(writer, see);
        }
        // writer.flush();
    }

    /**
     * 2nd version including header: Writes a studentExamEntry array into a writer object
     * @param writer the writer
     * @param seeArray studentExamEntry array
     * @param tableHeader the table header
     * @throws IOException if a problem during the writing occurs
     */
    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] seeArray, String tableHeader) throws IOException
    {
        writer.write("!" + tableHeader + "\n");
        writer.write(seeArray.length + "\n");
        for (var see:seeArray)
        {
            writeStudentExamEntry(writer, see);
        }
        // writer.flush();
    }

    /**
     * Reads a row and delivers a studentExamEntry object
     * @param row input row
     * @return a studentExamEntry object
     */
    public static StudentExamEntry readStudentExamEntry(String row)
    {

        String[] rowContentArray = row.split(":");
        String mark = (Objects.equals(rowContentArray[3], "")) ? rowContentArray[3]="n/a" : rowContentArray[3];
        String lastName = rowContentArray[1];
        String firstName = rowContentArray[0];
        int enrollNo = Integer.parseInt(rowContentArray[2]);
        StudentExamEntry studentExamEntry = new StudentExamEntry(lastName, firstName, enrollNo, mark);
        return studentExamEntry;

    }

    /**
     * Reads a buffered reader and returns a table with title object
     * @param br the buffered reader
     * @return table with title object
     * @throws IOException if a problem during the reading occurs
     */
    public static TableWithTitle readStudentExamTable(BufferedReader br) throws IOException
    {
        String row = br.readLine();
        String title;
        int length;
        if(row.charAt(0) == '!')
        {
            title = row.substring(1);
            row = br.readLine();
        }
        else
        {
            title = null;
        }
        length = Integer.parseInt(row);

        StudentExamEntry[] seeArray = new StudentExamEntry[length];
        int counter = 0;
        while ((row = br.readLine()) != null)
        {
            StudentExamEntry studentExamEntry = readStudentExamEntry(row);
            seeArray[counter] = studentExamEntry;
            counter++;
        }
        TableWithTitle twt = new TableWithTitle(title, seeArray);
        return twt;
    }
}
