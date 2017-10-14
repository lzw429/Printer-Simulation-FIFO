import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Fifo extends Simulator {
    private Queue<Event> workflow;//打印机工作队列
    private int totalJobs;//worklaod中任务总数
    private double aggregateLatency;//总等待时间
    private double meanLatency;//平均等待时间=总等待时间/任务总数
    private int curTime;//当前时间
    private int totalTime = 1000;
    private int nextServiceTime;//下次服务时间

    public void simulate(String input, String output) throws IOException {
        loadWorkLoad(input);
        BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + output));
        writer.write("FIFO Simulation \n\n");
        secPerPage = 2;  // 1页需要打印2秒
        totalJobs = workload.getSize();
        if (totalJobs == 0) //workload为空
        {
            writer.write("      Total jobs: 0\n      Aggregate latency: 0 seconds \n      Mean latency: 0 seconds \n");
            writer.close();
            return;
        }

        //workload非空
        curTime = 0;
        nextServiceTime = 0;
        while (curTime <= totalTime) {
            while (workload.getSize() != 0 && workload.front.next.data.getArrivalTime() == curTime)//网络打印机收到请求
            {
                Node<Event> front = workload.front.next;
                writer.write("      Arriving: " + front.data.getJob().getPageNum() + " pages from " + front.data.getJob().getUser() + " at " + curTime + " seconds\n");
                workflow.enqueue(workload.dequeue());//进入打印机工作队列
            }

            if (curTime >= nextServiceTime && workflow.getSize() != 0) {
                Event event = workflow.front.next.data;
                writer.write("      Servicing: " + event.getJob().getPageNum() + " pages from " + event.getJob().getUser() + " at " + curTime + " seconds\n");
                nextServiceTime = curTime + secPerPage * event.getJob().getPageNum();
                workflow.dequeue();
            }

            aggregateLatency += workflow.getSize();
            curTime++;//时间推移
        }

        meanLatency = aggregateLatency / totalJobs;
        DecimalFormat df = new DecimalFormat("#.0000");//保留四位小数
        writer.write("\n      Total jobs: " + totalJobs + "\n      Aggregate latency: " + (int) aggregateLatency + " seconds \n" + "      Mean latency: " + df.format(meanLatency) + " seconds \n");
        writer.close();

    }

    public Fifo() {
        workflow = new Queue<Event>();
    }

    public static void main(String[] args) throws IOException {
        Fifo fifo = new Fifo();
        fifo.simulate("a.run", "a.out");
    }
}

