/**
 * Lumbini Parnas
 * Human Computer Interaction
 * Project 4
 *
 * This class handles the creation of Pages
 *
 * */
public class Page {
    String name;
    String usage;
    String description;
    String references;
    String type;

    public Page(String name, String usage, String description, String references, String type) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.references = references;
        this.type = type;
    }

    public String toString() {
        return "Name: " + this.name + "\nUsage: " + this.usage + "\nDescription: " + this.description + "\nOther References: " + this.references;
    }

    public String getName() {
        return this.name;
    }

    public boolean compareType(String type) {
        return this.type.equals(type);
    }
    public boolean compareName(String name) {
        return this.name.equals(name);
    }
}
