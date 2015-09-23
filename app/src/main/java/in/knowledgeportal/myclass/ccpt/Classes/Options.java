package in.knowledgeportal.myclass.ccpt.Classes;

/**
 * Created by Harsh on 27-05-2015.
 */
public class Options {

    private Integer id;
    private String name;
    private String value;

    public Options(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
