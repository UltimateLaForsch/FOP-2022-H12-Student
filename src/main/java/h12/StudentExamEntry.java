package h12;

import java.util.HashMap;

public class StudentExamEntry
{
    private final String lastName;
    private final String  firstName;
    private final int enrollmentNumber;
    private String mark;

    public StudentExamEntry(String lastName, String firstName, int enrollmentNumber)
    {
        this(lastName, firstName, enrollmentNumber, "n/a");
    }

    public StudentExamEntry(String lastName, String firstName, int enrollmentNumber, String mark)
    {
        if(lastName==null | firstName==null)
        {
            throw new NullPointerException();
        }
        else if(enrollmentNumber < 1)
        {
            throw new BadEnrollmentNumberException(enrollmentNumber);
        }
        else if (lastName.contains(":"))
        {
            throw new BadCharException(':', lastName.indexOf(":"));
        }
        else if (firstName.contains(":"))
        {
            throw new BadCharException(':', firstName.indexOf(":"));
        }
        else if (lastName.contains(Character.toString('!')))
        {
            throw new BadCharException('!', lastName.indexOf(Character.toString('!')));
        }
        else if (firstName.contains(Character.toString('!')))
        {
            throw new BadCharException('!', firstName.indexOf(Character.toString('!')));
        }
        if(mark == null)
        {
            throw new NullPointerException();
        }
        else if(!mark.equals("1,0") && !mark.equals("1,3") && !mark.equals("1,7") && !mark.equals("2,0") &&
                !mark.equals("2,3") && !mark.equals("2,7") && !mark.equals("3,0") && !mark.equals("3,3") &&
                !mark.equals("3,7") && !mark.equals("4,0") && !mark.equals("5,0") && !mark.equals("n/a"))
        {
            throw new BadStudentMarkException(mark);
        }

        this.lastName = lastName;
        this.firstName = firstName;
        this.enrollmentNumber = enrollmentNumber;
        this.mark = mark;
    }


    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public int getEnrollmentNumber()
    {
        return enrollmentNumber;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark) throws NullPointerException, BadStudentMarkException
    {
        if(mark == null)
        {
            throw new NullPointerException();
        }
        else if(!mark.equals("1,0") && !mark.equals("1,3") && !mark.equals("1,7") && !mark.equals("2,0") &&
                !mark.equals("2,3") && !mark.equals("2,7") && !mark.equals("3,0") && !mark.equals("3,3") &&
                !mark.equals("3,7") && !mark.equals("4,0") && !mark.equals("5,0") && !mark.equals("n/a"))
        {
            throw new BadStudentMarkException(mark);
        }
        else this.mark = mark;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass() == StudentExamEntry.class && ((StudentExamEntry) obj).lastName .equals(this.lastName))
            return true;
        else
            return false;
    }
}