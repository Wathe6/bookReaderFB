package HTMLtoFB2Conerter;

import java.util.Vector;

public class HTMLCleaner extends HTMLTask
{
    public HTMLCleaner()
    {
        super();
    }

    @Override
    public Vector<StringBuilder> call()
    {
        return chapterCleaner();
    }
    private Vector<StringBuilder> chapterCleaner()
    {
        long startTime = System.currentTimeMillis();
        Vector<StringBuilder> outVector = new Vector<>();
        for (StringBuilder text : inVector)
        {
            if (!text.isEmpty())
            {
                StringBuilder chapter = new StringBuilder();
                number++;
                //System.out.println(_text.substring(0, 256));
                chapter.append("<section id=\"_bookmark").append(number).append("\">\n<title><p>");
                //Title
                int jStart = text.indexOf("<title>") + 7;
                int jEnd = text.indexOf("|");
                if (jStart != -1 & jEnd != -1)
                    chapter.append(text.substring(jStart, jEnd)).append("</p></title>\n");
                else
                    System.out.println("Title of chapter #" + number + " can't be found.");
                //The main chapter
                //Собирает всю главу по абзацам, не подтягивая рекламу
                int pStart = text.indexOf("<p>", jEnd);
                int pEnd = text.indexOf("</p>", pStart);
                //If there's no </p> in the chapter
                if (pEnd == -1)
                {
                    chapter.append(text.substring(text.indexOf("<p>"), text.indexOf("</div", text.indexOf("<p>"))));
                    while (chapter.toString().contains("<script"))
                        chapter.replace(chapter.indexOf("<script"), chapter.indexOf("</script>") + 9, "");
                    while (chapter.toString().contains("<ins"))
                        chapter.replace(chapter.indexOf("<ins"), chapter.indexOf("</ins>") + 7, "");
                } else
                {
                    while (pStart != -1 & pEnd != -1)
                    {
                        chapter.append(text.substring(pStart, pEnd + 4));// length of </p> is 4
                        pStart = text.indexOf("<p>", pEnd + 4);
                        pEnd = text.indexOf("</p>", pStart);
                    }
                }
                chapter.append("\n</section>\n");
                while(chapter.toString().contains("<script"))
                {
                    chapter.replace(
                            chapter.indexOf("<div"),
                            chapter.indexOf("</div>") + 12,
                            ""
                    );
                }
                outVector.add(chapter);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (endTime - startTime) + " мс.");
        return outVector;
    }
}
