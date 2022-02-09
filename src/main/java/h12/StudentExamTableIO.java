package h12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
import java.util.stream.Stream;

public class StudentExamTableIO
{
    public static void writeStudentExamEntry(Writer writer, StudentExamEntry see) throws IOException
    {
        String mark = (Objects.equals(see.getMark(), "n/a")) ? "" : see.getMark();
        writer.write(see.getFirstName() + ":" + see.getLastName() + ":" + see.getEnrollmentNumber() +
                ":" + mark + "\n");
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] seeArray) throws IOException
    {
        writer.write(seeArray.length + "\n");
        for (var see:seeArray)
        {
            writeStudentExamEntry(writer, see);
        }
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] seeArray, String tableHeader) throws IOException
    {
        writer.write("!" + tableHeader + "\n");
        writer.write(seeArray.length + "\n");
        for (var see:seeArray)
        {
            writeStudentExamEntry(writer, see);
        }
    }

    public static StudentExamEntry readStudentExamEntry(String row)
    {

        String[] rowContentArray = row.split(":");
        String mark = (Objects.equals(rowContentArray[3], "")) ? rowContentArray[3]="n/a" : rowContentArray[3];
        String lastNAme = rowContentArray[0];
        String firstName = rowContentArray[1];
        int enrollNo = Integer.parseInt(rowContentArray[2]);
        StudentExamEntry studentExamEntry = new StudentExamEntry(lastNAme, firstName, enrollNo, mark);
        return studentExamEntry;

    }

    public TableWithTitle readStudentExamTable(BufferedReader br) throws IOException
    {
        String row;
        while ((row = br.readLine()) != null)
        {
            String[] rowContentArray = row.split(":");
            String mark = (Objects.equals(rowContentArray[3], "")) ? rowContentArray[3]="n/a" : rowContentArray[3];
            String lastNAme = rowContentArray[0];
            String firstName = rowContentArray[1];
            int enrollNo = Integer.parseInt(rowContentArray[2]);
            StudentExamEntry studentExamEntry = new StudentExamEntry(lastNAme, firstName, enrollNo, mark);
        }





    }
}
