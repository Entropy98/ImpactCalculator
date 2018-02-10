import java.util.concurrent.TimeUnit;

public class Formatting
{
    private int wordNum = 0;
    private String displayWord = "";
    private static String[] words;
    private int maxSpeed = 400;     //in words per minute
    private double wpmsMax;
    private double wpms;            //words per millisecond
    private long delay;
    private static String x;
    private int size;               //number of words in array

    public Formatting()
    {
        getText();
        size = words.length;
        for (int k = 0; k < size; k++)
        {
            update();
        }
    }

    public void update()
    {
        draco();
    }

    public static void getText()
    {
        x = ApplicationMain.getText();
        words = x.split("\\s+");
    }

    public void draco()
    {
        wpmsMax = maxSpeed / 60000;
        if(wordNum <= 1000)
        {
            displayWord = words[wordNum];
            if (wpms <= wpmsMax)
            {
                wpms = 1.44 * Math.pow(10, -5) + 0.0016;
            }
        }
        delay = (long)-0.5375*wordNum + 600;
        wordNum++;
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
