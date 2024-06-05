# Question 1

In step 1, you were asked to create a `Room` class with a description, which will be printed if inspected. Two software developers proposed two different implementations for the `Room` class.

The first developer proposed one implementation:

```java
public class Room {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```

The second developer proposed another implementation:

```java
public class Room {
    public String description;
}
```

Both developers are discussing which implementation is better and why. Please answer the following question:

Which of these two implementations would you select? And why?

Justify your answer in at least 250 words. Please explain the consequences, benefits and drawbacks of each implementation and support it with an example.

___

Answer:

We would choose the first implementation, since in that one 'description' is a private field instead of a public one.
This implementation is better, because it uses encapsulation which prevents users from modifying the internal
description of a room. 

By implementing getter and setter methods, as shown in the first implementation, we are hiding the field's internal 
representation from the caller. Therefore, it provides maintainability since it can change the representation
without having to modify the callers.

Additionally, we can implement other constraints within the setter and getter functions to make sure that the
descriptions of the fields are valid. Plus, by controlling how the description is accessed and modified, 
we can ensure that the object's state remains consistent.

Public fields can have some benefits, for example they are straightforward to use. They can be accessed directly without
any getter or setter methods, and they can also make code easier to read in simple cases or reduce repetitive code.

However, if we made certain fields public (such as in this case 'description'), each time the representation changed,
every call to that field would have to change as well, which would be extremely inconvenient. With public fields we also
lose control over the access and mutation of the data, leaving us without ways of enforcing constraints or performing
actions when a field is accessed or modified.

For instance, with the above former implementation, we could check that the description is not null before updating it.
```java
    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        } else {
            System.out.println("That is not a valid description");
        }
    }
```

___

# Question 2

In step 2, you are asked to create two interfaces: `Inspectable` and `Interactable`.
Interfaces by definition do not have implementations. They consist of method signatures:

```java
interface Inspectable {
    void inspect();
}
```

A software developer claims that interfaces are not useful, because they do not contain implementations. Thus, we should just use classes, and we do not need to define empty interfaces.

What do you think about this opinion? Do you agree or disagree with this opinion?

Please justify your answer in at least 250 words, and support your justifications with an example.

___

Answer:

We think interfaces are extremely useful, especially when we want a class to inherit multiple behaviours that aren't in their
superclasses, associating classes that aren't connected through superclass-subclass links or establishing the name, return value
and parameters of a method without implementing the method itself.

Interfaces can promote polymorphism by implementing one method in different classes that aren't related.
In java, one class can only inherit a method from its superclasses or if the method itself is defined within the class, 
however interfaces allow objects of different classes to be treated interchangeably if they implement the same interface.

Also, Java doesn't support multiple inheritance with classes, which means that a class can only extend one other class. 
However, a class is allowed to implement multiple interfaces, which allows multiple inheritance through interfaces and 
therefore enabling inheritance of a class from multiple sources.

Interfaces also promote abstraction by defining a contract specifying the behaviour that an implementing class must adhere
to, without providing the details of the implementation. It also promotes loose coupling and decoupling by defining a
common interface that classes can implement without needing to know each other's implementation details.

For example, in our code we wanted a player to be able to interact with a door, but also with an NPC. These two classes
were not connected by any superclass. Hence, instead of implementing two different methods 'interact' in 'Player', we
created an interface 'Interactable' that was already defined as void, with the parameter 'player'. This way, both classes
were related by one same method with different implementations in each. Other example is shown in question 3, where interfaces
are used to extend the behaviour of objects in a class without modifying the class itself.

Overall, we disagree with this opinion of interfaces being unuseful because even though interfaces don't contain implementations,
they provide many other benefits such as helping the application of polymorphism, multiple
inheritance, abstraction or decoupling; all of which are key characteristics of java.


___

# Question 3

To save your game state, you were asked to use Java classes `FileOutputStream` and `ObjectOutputStream`.
These two classes are part of the Java libraries, and they are designed based on a specific design pattern.

Which design pattern do `FileOutputStream` and `ObjectOutputStream` implement?

Explain the roles of this design pattern and how `FileOutputStream` and `ObjectOutputStream` implement it. Also explain the benefit of implementing this design pattern.

___

Answer:

Both `FileOutputStream` and `ObjectOutputStream` implement the Decorator pattern in their read and write operations. The 
Decorator pattern allows the addition of behaviors to individual objects dynamically at runtime, with the use of 
inheritance, so that all the instances of that class obtain the extended behaviour without modifying the class itself.

This design pattern consists of an abstract component, an interface. In this case, this role is played by the `OutputStream`
interface. It defines the raw structure of methods for objects that can have their behaviour dynamically extended by implementing
operations such as 'write'.

Then, we need to implement the interface to an object in order to potentially extend its methods. In the `ObjectOutputStream` interface,
this object is `FileOutputStream`, which writes raw bytes to a file. 

Once we have the concrete object and the abstract interface, we want to link them. It is made by means of an abstract decorator class. The 
decorator both implements the interface and has a reference to the object. In this case, the decorator is `FilterOutputStream`, an 
abstract class that implements OutputStream (abstract interface) and contains an instance of this class.

Finally, once we have all of the above, we also need a concrete decorator classes that are subclasses of the abstract decorator. 
They are used to implement its behaviour. Here, `ObjectOutputStream` is one of these subclasses of `FilterOutputStream`. Its role
is to improve functionality by adding abilities for writing objects, data types and strings so that an ObjectInputStream is able to read
them back.

The main benefit of this pattern is the extension of new behaviours to objects without modifying them, making these methods able to be reused. 
This way, when new functionality is implemented, the risk of errors is significantly reduced. Plus, it adds these behaviours dynamically at runtime 
instead of using a hierarchy chain of inheritance, thereby making the code more readable and solid. In short, by implementing the decorator pattern
FileOutputStream and ObjectOutputStream provide a flexible way to enhance I/O stream capabilities.
___
