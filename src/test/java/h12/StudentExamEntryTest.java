package h12;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentExamEntryTest
{
    /**
     * JUnit test of the student exam entry Constructor (if it works)
     */
    @Test
    public void testConstructorsWork()
    {
        assertDoesNotThrow( () -> new StudentExamEntry("HaveSeenItAll", "Dave", 123456));
        assertDoesNotThrow( () -> new StudentExamEntry("NoGood", "Rewan", 555555, "2,3"));
    }

    /**
     * JUnit test of the student exam entry Constructor (if it throws exceptions on wrong inputs and with the correct message)
     */
    @Test
    public void testConstructorsThrow()
    {

        assertEquals("NullPointerException", assertThrows(NullPointerException.class,
                () -> new StudentExamEntry(null, "Dave", 123456, "1,3")).getMessage());
        assertEquals("NullPointerException", assertThrows(NullPointerException.class,
                () -> new StudentExamEntry("HaveSeenItAll", null, 123456, "1,3")).getMessage());
        assertEquals("NullPointerException", assertThrows(NullPointerException.class,
                () -> new StudentExamEntry("HaveSeenItAll", "Dave", 123456, null)).getMessage());
        assertEquals("Bad enrollment number '-12'", assertThrows(BadEnrollmentNumberException.class,
                () -> new StudentExamEntry("HaveSeenItAll", "Dave", -12, "1,3")).getMessage());
        assertEquals("Bad char ':' at position 4" , assertThrows(BadCharException.class,
                () -> new StudentExamEntry("HaveSeenItAll", "Dave:", 123456)).getMessage());
        assertEquals("Bad char ':' at position 13", assertThrows(BadCharException.class,
                () -> new StudentExamEntry("HaveSeenItAll:", "Dave", 123456)).getMessage());

    }

    /**
     * JUnit test if the student exam entry Constructor handles wrong input grades correctly
     */
    @Test
    public void testMarks()
    {
        StudentExamEntry see = new StudentExamEntry("EvilTwins", "Revan&Dave", 123123);
        assertEquals("n/a", see.getMark());

        String[] grades = new String[]{"1,0", "1,3", "1,7", "2,0", "2,3", "2,7", "3,0", "3,3", "3,7", "4,0", "5,0", "n/a"};

        for (var grade:grades)
        {
            see.setMark(grade);
            assertEquals(grade , see.getMark());
        }

        assertEquals("NullPointerException", assertThrows(NullPointerException.class,
                () -> new StudentExamEntry("HaveSeenItAll", "Dave", 123456, null)).getMessage());
        assertEquals("Bad student mark '1,9'", assertThrows(BadStudentMarkException.class,
                () -> new StudentExamEntry("HaveSeenItAll", "Dave", 123456, "1,9")).getMessage());

    }

}
