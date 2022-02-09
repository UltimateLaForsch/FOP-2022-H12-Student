package h12;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class TableGenerator_added
{
    static StudentExamEntry[] createEntries(int size, long seed)
    {
        Random random = new Random();
        random.setSeed(seed);

        String[] grades = new String[]{"1,0", "1,3", "1,7", "2,0", "2,3", "2,7", "3,0", "3,3", "3,7", "4,0", "5,0"};

        StudentExamEntry[] seeArray = new StudentExamEntry[size];

        for (int i = 0; i < size; i++)
        {
            int lenLastName = random.nextInt(25 - 5 + 1) + 5;
            int lenFirstName = random.nextInt(25 - 5 + 1) + 5;
            int enNo = random.nextInt(9999999 - 1000 + 1) + 1000;
            int ranMark = random.nextInt( 10 - 0 + 1) + 0;
            // LastName
            String lastName = "";
            for (int j = 0; j < lenLastName; j++)
            {
                int ranUnicode = random.nextInt(122 - 97 + 1) + 97;
                char lowerCaseChar = (char) ranUnicode;
                lastName += lowerCaseChar;
            }
            // FirstName
            String firstName = "";
            for (int j = 0; j < lenFirstName; j++)
            {
                int ranUnicode = random.nextInt(122 - 97 + 1) + 97;
                char lowerCaseChar = (char) ranUnicode;
                firstName += lowerCaseChar;
            }
            // Mark
            String mark = grades[ranMark];
            StudentExamEntry see = new StudentExamEntry(lastName, firstName, enNo, mark);
            seeArray[i] = see;
        }

        return seeArray;
    }

    static TableWithTitle createTable(int size, long seed)
    {
        Random random = new Random();
        random.setSeed(seed);

        StudentExamEntry[] seeArray = createEntries(size, seed);
        int lenTitle = random.nextInt(25 - 5 + 1) + 5;
        String title = "";
        for (int j = 0; j < lenTitle; j++)
        {
            int ranUnicode = random.nextInt(122 - 97 + 1) + 97;
            char lowerCaseChar = (char) ranUnicode;
            title += lowerCaseChar;
        }
        TableWithTitle tableWithTitle = new TableWithTitle(title, seeArray);
        return tableWithTitle;

    }
}
