package HTMLtoFB2Conerter;

import java.util.Vector;
import java.util.concurrent.Callable;

public class HTMLTask implements Callable<Vector<StringBuilder>>, Cloneable
{
    protected Vector<StringBuilder> inVector;
    protected int number;
    protected HTMLTask() {    }
    public void setInVector(Vector<StringBuilder> inVector)
    {
        this.inVector = inVector;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }
    public Vector<StringBuilder> call()
    {
        return null;
    }
    public HTMLTask clone() throws CloneNotSupportedException
    {
        HTMLTask temp = (HTMLTask) super.clone();
        temp.setInVector(null);
        temp.setNumber(0);
        return temp;
    }
}