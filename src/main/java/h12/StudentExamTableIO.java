package h12;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class StudentExamTableIO
{
    public void writeStudentExamEntry(Writer writer, StudentExamEntry see) throws IOException
    {
        String mark = (Objects.equals(see.getMark(), "n/a")) ? "" : see.getMark();
        writer.write(see.getFirstName() + ":"+see.getLastName() + ":"+see.getEnrollmentNumber() +
                ":" + mark);
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] seeArray) throws IOException
    {
        writer.write(seeArray.length + "\n");
        for (var see:seeArray)
        {
            new StudentExamTableIO().writeStudentExamEntry(writer, see);
            writer.write("\n");
        }
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] seeArray, String tableHeader) throws IOException
    {
        writer.write("!" + tableHeader + "\n");
        writer.write(seeArray.length + "\n");
        for (var see:seeArray)
        {
            new StudentExamTableIO().writeStudentExamEntry(writer, see);
            writer.write("\n");
        }
    }

}
