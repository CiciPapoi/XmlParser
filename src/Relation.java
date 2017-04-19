/**
 * Created by user on 13.12.2016.
 */
public class Relation {
    private String id;
    private String type;
    private String startComponent;
    private String endComponent;

    public Relation(String id, String type, String startComponent, String endComponent) {
        this.type = type;
        this.startComponent = startComponent;
        this.endComponent = endComponent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartComponent() {
        return startComponent;
    }

    public void setStartComponent(String startComponent) {
        this.startComponent = startComponent;
    }

    public String getEndComponent() {
        return endComponent;
    }

    public void setEndComponent(String endComponent) {
        this.endComponent = endComponent;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", startComponent='" + startComponent + '\'' +
                ", endComponent='" + endComponent + '\'' +
                '}';
    }
}
