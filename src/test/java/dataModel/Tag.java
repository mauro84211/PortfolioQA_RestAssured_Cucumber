package dataModel;

public class Tag {
    private Integer id;
    private String name;

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
