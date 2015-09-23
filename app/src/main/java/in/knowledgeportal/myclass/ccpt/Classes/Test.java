package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Test {

    private Integer id;
    private String name;
    private String topic;
    private String date_test;
    private Integer activeflag;
    private Integer maximum;
    private Integer average;
    private Integer testType;

    public Test(Integer id, String name, String topic, String date_test, Integer activeflag, Integer maximum, Integer average, Integer testType) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.date_test = date_test;
        this.activeflag = activeflag;
        this.maximum = maximum;
        this.average = average;
        this.testType = testType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
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
