package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Notes {

    private Integer id;
    private String name;
    private String url;
    private Integer activeflag;
    private String datecreated;
    private Integer downloadFlag;

    public Notes(Integer id, String name, String url, Integer activeflag, String datecreated, Integer downloadFlag) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.activeflag = activeflag;
        this.datecreated = datecreated;
        this.downloadFlag = downloadFlag;
    }

    public Integer getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(Integer downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
