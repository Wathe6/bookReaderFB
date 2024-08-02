package HTMLtoFB2Conerter;

import HTMLtoFB2Conerter.HTMLTask;

import java.util.Vector;
import java.lang.StringBuilder;
import java.util.concurrent.*;

public class StreamFactory
{
    private Vector<StringBuilder> outVector = new Vector<>();
    private HTMLTask task;
    public StreamFactory(HTMLTask task)
    {
        this.task = task;
    }

    public Vector<StringBuilder> execute(Vector<StringBuilder> inVector, int CH_PER_THREAD, int THREAD_COUNT) throws CloneNotSupportedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Vector<Future<Vector<StringBuilder>>> futures = new Vector<>();

        for (int i = 0; i < THREAD_COUNT * CH_PER_THREAD; i += CH_PER_THREAD)
        {
            Vector<StringBuilder> buffer = new Vector<>(inVector.subList(i, i + CH_PER_THREAD));
            HTMLTask tempTask = task.clone();
            tempTask.setInVector(buffer);
            tempTask.setNumber(i);
            futures.add(executorService.submit(tempTask));
        }
        try
        {
            for(Future<Vector<StringBuilder>> future : futures)
                outVector.addAll(future.get());
        } catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        executorService.shutdown();

        return outVector;
    }
/*  Версия с ожиданием всех потоков
    public <T extends HTMLtoFB2Conerter.HTMLTask> Vector<StringBuilder> execute(Vector<StringBuilder> inVector, int CH_PER_THREAD, int THREAD_COUNT) throws CloneNotSupportedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Callable<Vector<StringBuilder>>> tasks = new Vector<>();

        for (int i = 0; i < THREAD_COUNT * CH_PER_THREAD; i += CH_PER_THREAD)
        {
            Vector<StringBuilder> buffer = new Vector<>(inVector.subList(i, i + CH_PER_THREAD));
            HTMLtoFB2Conerter.HTMLTask tempTask = task.clone();
            tempTask.setInVector(buffer);
            tempTask.setNumber(i);
            tasks.add(tempTask);
        }

        try
        {
            List<Future<Vector<StringBuilder>>> futures = executorService.invokeAll(tasks);

            for (Future<Vector<StringBuilder>> future : futures) {
                outVector.addAll(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return outVector;
    }
*/
}
