package h12;

public class TableWithTitle
{
    private final String title;
    private final StudentExamEntry[] entries;

    /**
     * Constructor of a table with title object
     * @param title table title
     * @param entries student exam entry array
     */
    public TableWithTitle(String title, StudentExamEntry[] entries)
    {
        this.title = title;
        this.entries = entries;
    }

    /**
     * Getter method for the table title
     * @return the table title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Getter method for the student exam entry array
     * @return the student exam entry array
     */
    public StudentExamEntry[] getEntries()
    {
        return entries;
    }
}
