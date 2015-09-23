package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Announcement {

    private Integer id;
    private String name;
    private Integer activeflag;
    private String datecreated;

    public Announcement(Integer id, String name, Integer activeflag, String datecreated) {
        this.id = id;
        this.name = name;
        this.activeflag = activeflag;
        this.datecreated = datecreated;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
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

    public Integer getActiveflag() {
        return activeflag;
    }

    public void setActiveflag(Integer activeflag) {
        this.activeflag = activeflag;
    }
}
