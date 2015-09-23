package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Toppers {

    private Integer markID;
    private Integer studentID;
    private Integer testID;
    private Integer mark;
    private Integer activeflag;
    private Integer downloadFlag;

    public Toppers(Integer markID, Integer studentID, Integer testID, Integer mark, Integer activeflag, Integer downloadFlag) {
        this.markID = markID;
        this.studentID = studentID;
        this.testID = testID;
        this.mark = mark;
        this.activeflag = activeflag;
        this.downloadFlag = downloadFlag;
    }

    public Integer getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(Integer downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public Integer getMarkID() {
        return markID;
    }

    public void setMarkID(Integer markID) {
        this.markID = markID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getTestID() {
        return testID;
    }

    public void setTestID(Integer testID) {
        this.testID = testID;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getActiveflag() {
        return activeflag;
    }

    public void setActiveflag(Integer activeflag) {
        this.activeflag = activeflag;
    }
}
