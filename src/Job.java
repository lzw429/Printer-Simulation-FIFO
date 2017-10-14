public class Job {
    private String user;
    private int pageNum;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Job(String user, int pageNum) {
        this.user = user;
        this.pageNum = pageNum;
    }
}
