package HTMLtoFB2Conerter;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

class Main
{
    final static int CHAPTER_COUNT = 130+1;                                                         /*CHAPTER COUNT*/
    final static int CH_PER_THREAD = 200;
    final static String BOOK_INFO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                    "<FictionBook xmlns=\"http://www.gribuser.ru/xml/fictionbook/2.0\"\n" +
                                    "  xmlns:l=\"http://www.w3.org/1999/xlink\">\n" +
                                    "   <description>\n" +
                                    "    <title-info>\n" +
                                    "     <genre>science</genre>\n" +
                                    "     <author>\n" +
                                    "      <first-name>Void Herald</first-name>\n" +                /*AUTHOR NAME*/
                                    "     </author>\n" +
                                    "     <book-title>The Perfect Run</book-title>\n" +             /*BOOK TITLE*/
                                    "     <lang>en</lang>\n" +
                                    "     <src-lang>en</src-lang>\n" +
                                    "    </title-info>\n" +
                                    "    <document-info>\n" +
                                    "     <author>\n" +
                                    "      <nickname></nickname>\n" +
                                    "      <email></email>\n" +
                                    "     </author>\n" +
                                    "    </document-info>\n" +
                                    "    <publish-info>\n" +
                                    "     <book-name>The Perfect Run</book-name>\n" +               /*Book name*/
                                    "     <publisher>ME</publisher>\n" +
                                    "     <city>None</city>\n" +
                                    "     <year>2024</year>\n" +
                                    "    </publish-info>\n" +
                                    "   </description>\n" +
                                    "  <body>\n";
    public static void main(String[] args) throws CloneNotSupportedException
    {
        String path = "E://FB2 Books/The_Perfect_Run.fb2";                                          /*CHANGE FILE NAME*/
        StringBuilder book = new StringBuilder();

        int numberOfThreads  = CHAPTER_COUNT / CH_PER_THREAD;
        if(CHAPTER_COUNT % CH_PER_THREAD != 0)
            numberOfThreads++;

        Vector<StringBuilder> fileNames = new Vector<>(fileParser());
        fileNames.setSize(numberOfThreads * CH_PER_THREAD);
        StreamFactory sf = new StreamFactory(new HTMLFileReader());

        Vector<StringBuilder> pages = sf.execute(fileNames, CH_PER_THREAD, numberOfThreads);

        sf = new StreamFactory(new HTMLCleaner());

        Vector<StringBuilder> chapters = sf.execute(pages, CH_PER_THREAD, numberOfThreads);
        for (StringBuilder chapter : chapters)
        {
            book.append(chapter);
        }
        try (FileWriter writer = new FileWriter(path))//New FileName
        {
            writer.write(BOOK_INFO);
            writer.write(String.valueOf(book));
            writer.write("</body>\n" + "</FictionBook>");
            writer.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static Vector<StringBuilder> fileParser()
    {
        String path = "E:\\FB2 Books\\The Perfect Run";
        File folder = new File(path);

        String[] files = folder.list((folder1, name) ->
        {
            return name.endsWith(".html"); // ищет конкретно нужный файл
        });
        //Сортирует файлы в правильном порядке
        files = stringNaturalOrdering(files);
        //Преобразует String[] в Vector<StringBuilder>
        return StringArrayIntoVectorSB(files);
    }

    public static Vector<StringBuilder> StringArrayIntoVectorSB(String[] text)
    {
        StringBuilder[] sb = new StringBuilder[text.length];
        System.arraycopy(text, 1, text, 0, text.length - 1);
        Arrays.setAll(sb, i -> new StringBuilder(text[i]));
        return new Vector<>(Arrays.stream(sb).toList());
    }

    public static String[] stringNaturalOrdering(String[] strings)
    {
        String[] answer = new String[CHAPTER_COUNT];
        for (String str: strings)
        {
            StringBuilder name = new StringBuilder(str);
            name.replace(0, 8,"");
            String sb = name.substring(0, name.indexOf("_"));
            int numb = Integer.parseInt(sb);
            answer[numb] = str;
        }
        return answer;
    }
}