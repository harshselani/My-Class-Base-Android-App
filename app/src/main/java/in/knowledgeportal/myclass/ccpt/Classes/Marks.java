package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Marks {

    private Integer id;
    private Integer score;
    private String test_name;
    private String topic;
    private String date_test;
    private Integer activeflag;
    private Integer highest;
    private Integer total;
    private Integer average;
    private Integer testType;

    public Marks(Integer id, Integer score, String test_name, String topic, String date_test, Integer activeflag, Integer highest, Integer total, Integer average, Integer testType) {
        this.id = id;
        this.score = score;
        this.test_name = test_name;
        this.topic = topic;
        this.date_test = date_test;
        this.activeflag = activeflag;
        this.highest = highest;
        this.total = total;
        this.average = average;
        this.testType = testType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate_test() {
        return date_test;
    }

    public void setDate_test(String date_test) {
        this.date_test = date_test;
    }

    public Integer getActiveflag() {
        return activeflag;
    }

    public void setActiveflag(Integer activeflag) {
        this.activeflag = activeflag;
    }

    public Integer getHighest() {
        return highest;
    }

    public void setHighest(Integer highest) {
        this.highest = highest;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }
}
