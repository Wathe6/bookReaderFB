package HTMLtoFB2Conerter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class HTMLFileReader extends HTMLTask
{
    public HTMLFileReader()
    {
        super();
    }
    @Override
    public Vector<StringBuilder> call()
    {
        return fileParser();
    }
    private Vector<StringBuilder> fileParser()
    {
        long startTime = System.currentTimeMillis();
        Vector<StringBuilder> outVector = new Vector<>();
        for(StringBuilder name : inVector)
        {
            //System.out.println(name);
            StringBuilder text = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("E:\\FB2 Books\\The Perfect Run\\" + name)))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    text.append(line);
                }
            }
            catch (IOException ex)
            {

            }
            outVector.add(text);
            number++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения считывания: " + (endTime - startTime) + " мс.");
        return outVector;
    }
}