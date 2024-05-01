package nl.rug.oop.introduction;

/**
 * Person class (superclass of TA, Lecturer and Student).
 */

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
