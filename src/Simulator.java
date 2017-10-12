import java.io.*;

public class Simulator {
    private int secPerPage;
    private Queue<Event> workload;

    public void loadWorkLoad(String filename)//从文件装载Event
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("files/" + filename));//读取.run文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line;
        String item[] = new String[5];
        try {
            while ((line = reader.readLine()) != null) {
                item = line.split(" ");
                Job job = new Job(item[2], Integer.parseInt(item[1]));
                Event event = new Event(job, Integer.parseInt(item[0]));
                workload.enqueue(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Simulator() {
        workload = new Queue<Event>();
    }
}
