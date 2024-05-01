# Question 1

What is the difference between a class and an object?


Answer:

A class is a template where objects are created, so it is a group of objects with similar features. 
Meanwhile, an object is a real-world entity that belongs to a class.

# Question 2

Given are the following three classes:

`Person.java`:

```java
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }
}
```

`PersonModifier.java`:

```java
public class PersonModifier {

    public Person modifyPerson1(Person person) {
        person.setName("Bert");
        return person;
    }

    public Person modifyPerson2(Person person) {
        person.setName("Gerry");
        person = new Person("James");
        return person;
    }
}
```

`Main.java`:

```java
public class Main {

    public static void main(String[] args) {
        Person person1 = new Person("John");
        Person person2 = new Person("Bob");

        PersonModifier personModifier = new PersonModifier();
        Person modifiedPerson1 = personModifier.modifyPerson1(person1);
        Person modifiedPerson2 = personModifier.modifyPerson2(person2);

        person1.printName();
        person2.printName();
        modifiedPerson1.printName();
        modifiedPerson2.printName();
    }
}
```

What is the output of this program? Explain why.

___

Answer:

Bert \
Gerry \
Bert \
James \
When we call modifyPerson 1, we pass as parameter person1 ("John"), it then sets the name to "Bert", so person1.name is Bert and it returns the person so modifiedPerson1.name also becomes "Bert". \
When we call  modifyPerson2, we pass as parameter person2 ("Bob"), it then sets the name to "Gerry", so person2.name is Gerry. Then it creates a new person whose name is James and returns it, thereby setting modifyPerson1 to "James".


