package h12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
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

//        List<StudentExamEntry> listOfEntries = new ArrayList<>();
//
//        while ((row = br.readLine()) != null)
//        {
//            StudentExamEntry studentExamEntry = readStudentExamEntry(row);
//            listOfEntries.add(studentExamEntry);
//        }
//
//        StudentExamEntry[] seeArray = listOfEntries.toArray(new StudentExamEntry[listOfEntries.size()]);
//        TableWithTitle twt = new TableWithTitle(title, seeArray);
//        return twt;
    }
}
