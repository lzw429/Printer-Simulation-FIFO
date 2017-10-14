import java.io.*;

public class Simulator {
    protected int secPerPage;//打印的速度
    protected Queue<Event> workload;

    void loadWorkLoad(String filename)//从文件装载Event
    {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files/" + filename));//读取.run文件
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(" ");
                Job job = new Job(item[2], Integer.parseInt(item[1]));
                Event event = new Event(job, Integer.parseInt(item[0]));
                workload.enqueue(event);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Simulator() {
        workload = new Queue<Event>();
    }
}
