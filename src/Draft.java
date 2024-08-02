import java.io.*;
import java.util.Arrays;

public class Draft
{
    public static void main(String[] args)
    {
        StringBuilder text = new StringBuilder();

        File folder = new File("C:\\Users\\pusto\\Desktop\\The First Order"); // ваш путь папки

        String[] files = folder.list();

        System.out.println(Arrays.toString(files));
    }
}
