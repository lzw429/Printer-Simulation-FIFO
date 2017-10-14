public class Event {
    private Job job;
    private int arrivalTime;

    //Getter and Setter
    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    //构造方法
    public Event(Job job, int arrivalTime) {
        this.job = job;
        this.arrivalTime = arrivalTime;
    }

}
