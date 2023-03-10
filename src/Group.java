import java.util.*;

public class Group extends ArrayList<Student> {
    private Assistant assistant;
    private String ID;
    private Comparator<Student> comparator;
    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        super(new ArrayList<Student>());
        this.comparator = comp;
        this.ID = ID;
        this.assistant = assistant;
    }
    public Group(String ID, Assistant assistant) {
        super(new ArrayList<Student>());
        this.ID = ID;
        this.assistant = assistant;
    }
    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public Assistant getAssistant() {
        return assistant;
    }
    public String getID() {
        return ID;
    }
    public void sort() {
        Collections.sort(this, comparator);
    }

}
