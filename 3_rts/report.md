# Report

Belén Hidalgo Odria (s5562821) & Virág Bognár (s5492645)

## Introduction

> Our programme is a battle simulator in which the user/player has the ability to add, delete or modify cities, the paths between them, as well as armies and events residing in them.
> Then they can see how a fight would take place between the opposing teams composed by men, elves and dwarves against mordors and isengardians. This can be seen in three steps, 
> in which the teams first battle, the winning one then moves to a random edge, where another battle takes place as it comes accross an army of the opposite team, and then
> goes to the next city.

## Program design

> 
> Our programme implements the Model-View-Controller and Observer patterns. The model consists of the `graph`, which is 
> made up of `nodes`, `edges`, `armies` and `events`. We decided to only instantiate 
> one single graph and build upon it to avoid further complications and making all observers reflect only one object. 
> The `graph` is the only observable class so that the view can update itself based on the status changes occurring in this class.
> The `graph` implements some methods to notify the Observers of new modifications, as well as adding and removing Observers. 
> 
> The `observers` consist of the classes in the `view` - the `panel`, the `menu`, the `main frame` and the `option menu`. Within the 
> overridden `update()` method of the option menu, the edge and node menus are already notified of the changes, thereby not 
> needing to implement the `observer` interface. The `view` is implemented in a tree-like structure with the main frame as root.
> It contains a `splitPane`, the `option menu`, the `panel` and the `main menu`. The `option menu` is then divided into five panels which, 
> depending on whether a node or an edge is selected, display information and contain buttons to make changes to the given location.
> The `panel` is the graphic display of the graph, to which we added textures for a nicer GUI. Finally, the `main menu` gives the
> user the different possibilities that can be done to the graph itself.
> 
> The `view` should have no knowledge of the `model`, hence the usage of `controllers`. They process the user's input independently from
> the `view` and change the `graph` accordingly. The `view` is then notified via the `observer-observable` pattern, and updates itself. These 
> controllers are the `action listeners`, the `map controller` and the `mouse control`. The `action listeners` implement the functionality of
> the buttons on the panels when clicked. For the simulation step we decided to create a new `thread` with pauses between each battle. This 
> ensures that the UI remains responsive while the simulation runs on a separate thread. The `map controller` takes care of selecting nodes 
> and edges, while the `mouse control` handles the user's mouse input.
> 
> We decided to structure our classes into packages for a neater, more readable appearance. Additionally, we used `enums` for the cases
> where there were only some fixed values to choose from (in the `factions` or the `possible events`) as well as to avoid the utilization of magic
> numbers. Finally, we made an abstract superclass `event` from which the events would inherit the graph and override the method `occur()` of their implementation.
>

## Evaluation of the program

> *Discuss the stability of your implementation. What works well? Are there any bugs? Is everything tested properly? Are there still features that have not been implemented? Also, if you had the time, what improvements would you make to your implementation? Are there things which you would have done completely differently?*
> 
> We implemented all features (as far as we're aware) and tested them methodically and for all edge cases we could think of.
> Through this, we were able to fix some bugs such as throwing an error when the user didn't select the expected component 
> after pressing a button (e.g. pressing Add Edge button then clicking on another button instead of a node). However, there
> may still be possible bugs that we were unable to test for, for example in cases where there are a large number of armies, nodes and 
> edges.
> 
> Our programme raised some issues when we were selecting an option and we clicked elsewhere, however that was fixed by checking if the 
> selected option was null. Another slight design issue is raised when the simulation is run whilst a node or edge is selected. In
> that case, in the option menu some kind of buttons seem to appear behind the text field.Due to a lack of time, we were
> unable to find a solution to this slight glitch, although we think it might be caused by the initialization of buttons elsewhere or not 
> having added them correctly to the panel.
> 
> If we had had more time, we would have liked to implement animations for the armies to move through the screen using timers and extending JComponent; as well as 
> to make a flashing image appear whenever a battle occurs. For a better design, we were also planning to implement an interface
> for the saving and loading functionalities from JSON, such that every "Savable" and "Loadable" class would have a method `save()` and `load()`. This way, the 
> `GraphSaveState` and `GraphLoadState` would only have to go through each class implementing the interface and call the given function. 
> It would have also been nice to prevent the user from adding a route more than once, and checking if the input name for a node already existed. Finally, we would have
> liked to show a popup message whenever the game was saved/loaded successfully to/from JSON.


## Questions

Please answer the following questions:

1. In this assignment, the program should follow the Model View Controller (MVC) pattern. Please explain the design of the program in terms of the MVC pattern. Specifically try to answer the following questions:
   - MVC consists of three components: Model, view and controller. Can you please explain the role of each component? Please provide examples of these roles from the assignment. How are these three roles (i.e. Model, view and controller) are implemented in the assignment?
   - MVC enforces special constraints on the dependencies between its three components: Model, view and controller. Please explain these constraints, and why are they important?

___

Answer: 

The Model component in the MVC is responsible for representing the data and the business logic of the programme.
It manages the data, processes the business rules and responds to requests for information from the other components of the model.
In this assignment, the `Model` component was the `Graph` class as it holds all relevant data about the graph and is 
responsible for carrying out methods that change the status of the `View` without accessing it such as the `notifyObservers()` method.

The `View` component is responsible for displaying the data received from the `Model` to the user and send user input to the Controller.
In this case, the View component consists of the panel, menu, main frame and option menus. The panel is responsible for 
displaying the image and drawing on each component (e.g. nodes, edges, armies, etc.) whilst the menu contains buttons to collect user 
input through mouse clicks (action listeners) and movements handled by the Controller.

The Controller acts as a middle ground between the Model and the View as it handles user input to update the Model
and update the View in a way to reflect changes in the Model. In our case, the Controller handles the input from the Menu
by implementing the functionalities of each button by calling specific methods in the Graph.

The Model should not have direct knowledge of either the View or the Controller. It should purely encapsulate the 
application's data and business logic. Therefore, it is integral for the Model to provide a mechanism (such as the Observer pattern in this case)
in which it notifies the Views about changes in its state. This is important so that changes to the data layer (Model) 
do not affect the presentation layer (View) or the input handling layer (Controller).

The View should be aware of the Model to display data, however it should be read-only. It should not be able to modify the 
Model's state directly.
Also, The View should not have direct knowledge of the Controller, it should only communicate with it through user events - in
our case through user actions such as mouse interactions or the buttons.

The Controller should know about both the Model and the View and it should be able to directly change the state of the Model
based on the user input - in our case based on which button has been pressed, the state of the graph will change (new, removed or
modified components such as nodes, edges or armies).

___

2. The Swing library provides the ability to create nested user interface components. In this assignment, you created multiple JPanel components on the user interface. These contain other user interface components to build-up a tree of user interface components.
Which design pattern does Swing implement to create a hierarchy of user interface components? Please explain this pattern and how it is implemented in Swing.

___

The design pattern used is the composite pattern which lets clients treat individual objects and compositions of objects uniformly.
It is used to compose objects into tree structures to represent part-whole hierarchies.It consists of a component, a leaf, a composite
and a client. The component declares the interface for objects in the composition, including methods for managing child components. In 
the case of java swing, it is java.awt.Component. It acts as the base class for all UI elements, defining methods for drawing the component and handling events.
The leaf represents leaf objects in the composition (that is, objects that cannot contain other objects). In this case, they are the buttons,
and the text fields. The composite implements child-related operations in the Component interface and it can contain other composites and leafs. 
In our programme, the main composite is the main frame, and inside it contains the panel, the menu and the options menu. The options menu and the menu are then subdivided
into Jpanels with leafs (buttons and text fields). Finally, the client is the initialization class, which creates the main frame.

___

3. The Observer pattern is useful to implement the MVC pattern. Can you please explain the relationship between the Observer pattern and the MVC pattern?
Please provide an example from the assignment on how the Observer pattern supports implementing the MVC pattern.

___

Answer: 

The Model in the MVC pattern acts as the Subject in the Observer pattern while the View acts as an Observer. When the 
Model's state changes, it notifies all registered Views (observers) through the `notifyObservers()` method without engaging 
with the view, which then
retrieves the updated data and refreshes the display. By using the Observer pattern, we can also reduce coupling  as the Model
does not need to know about the specifics of the View. It only needs to maintain a list of observers and notify them when changes occur.
This decoupling simplifies adding or removing Views without altering the Model.

___

## Process evaluation

> *Describe shortly the process that led to the final code and the report. What was easy, what was difficult? Did you make interesting mistakes? What have you learned from this assignment?*
> 
> We first read through all the criteria to have an overall view of the task and the steps to follow and then we started implementing
> each criteria in the given order. We immediately opted for the use of textures over drawing simple shapes to achieve a more professional look and design.
> The most challenging part was coming up with the basic structure of nodes and edges (selecting, adding...) since it would then
> impact the whole design structure of the rest of the programme. However, once we had the basis set it was kind of straightforward to build upon that. 
> The simulation step proved to be a bit of a challenge as we had to make objects move without any user input in between.
> 
> At the beginning, in the option menu, we were having issues with the text fields because we did not know how the 
> composite pattern worked and we weren't adding them to a panel. This was a way to learn the hierarchical structure.
> 

## Conclusions

> Overall, the programme succeeds to fulfill all requirements of this assignment as well as some extra functionalities
> such as implementing textures, added extra events and loading from JSON.
