package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 31-05-2015.
 */

public class ToppersStudents {


    private Integer mark;
    private String name;

    public ToppersStudents(Integer mark, String name) {
        this.mark = mark;
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
