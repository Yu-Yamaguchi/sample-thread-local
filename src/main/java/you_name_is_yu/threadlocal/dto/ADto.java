package you_name_is_yu.threadlocal.dto;

public class ADto {

    private String id;
    private String name;

    public ADto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + Thread.currentThread().getName() + "]" + "ADto [id=" + id + ", name=" + name + "]";
    }

}
