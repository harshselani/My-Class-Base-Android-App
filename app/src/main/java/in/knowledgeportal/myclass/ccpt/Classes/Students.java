package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Students {

    private Integer id;
    private String name;
    private Integer activeflag;
    private Integer batchID;

    public Students(Integer id, String name, Integer activeflag, Integer batchID) {
        this.id = id;
        this.name = name;
        this.activeflag = activeflag;
        this.batchID = batchID;
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

    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }


}
