import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    private static final Scanner console = new Scanner(System.in);
    public static void main(String[] args)
    {
        String fileName = SafeInput.getNonZeroLenString(console, "Enter the text file name");

        ArrayList<String> PersonList = new ArrayList<>();

        boolean done = false;
        while(!done)
        {
            String ID = SafeInput.getNonZeroLenString(console, "Enter the ID");
            String firstName = SafeInput.getNonZeroLenString(console, "Enter the first name");
            String lastName = SafeInput.getNonZeroLenString(console, "Enter the last name");
            String title = SafeInput.getNonZeroLenString(console, "Enter the title");
            int yearOBirth = SafeInput.getInt(console, "Enter the year of birth");

            PersonList.add(ID + "," + firstName + "," + lastName + "," + title + "," + yearOBirth);

            done = SafeInput.getYNConfirm(console, "Are you done adding to the database?");
        }

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : PersonList)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
