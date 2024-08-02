import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NovelTrimer
{
    public static void main(String[] args)
    {
        StringBuffer text = new StringBuffer();
        try(FileReader reader = new FileReader("E:\\FB2 Books\\Blood_Warlock_Succubus_Partner_in_the_Apocalypse.fb2"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                text.append((char) c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        String replace = "<p>Really thank you very much to all those who send gifts to the novel and support with valuable Golden Tickets. I hope we can all keep it up &lt;3</p>";
        try (FileWriter writer = new FileWriter("E:\\FB2 Books\\Blood_Warlock_Succubus_Partner_in_the_Apocalypse.fb2"))
        {
            writer.write(text.toString().replaceAll(replace, ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
