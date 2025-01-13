[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/RyiBKJgD)

# Meal Planning Application

By Johannes Nupen Theigen

## Project description

The meal planning application was my very first graded project at NTNU.
The application aims to make consumers more concious about their food waste.
It provides a list of groceries in their household and how 
they can use them to make food before its expiration date. 


## Project structure

The unit tests are located in the test folder, in  packages matching the unit classes.
The source code is packaged in the main folder.

There are five packages in the main folder:

- **`edu.ntnu.idi.bidata.items`**: Contains the unit class `Ingredient`.
- **`edu.ntnu.idi.bidata.recipe`**: Contains the `Recipe` class.
- **`edu.ntnu.idi.bidata.app`**: Contains the `UserInterface` class and the `Main` class.
- **`edu.ntnu.idi.bidata.utilou`**: Contains utility classes for input parsing, input validation, and output handling.

## Link to repository

[Meal Planning Repository](https://github.com/NTNU-BIDATA-IDATG1003-2024/meal-planning-johannestheigen)

## How to run the project

To run the project you first have to download the project from the repository. 
Before running the project, make sure you have Java installed on your computer version
21 or newer.  Open the project and locate the path to the main class. Once you have 
located the main class, copy the path and open a terminal. Use the command `cd` to navigate 
to the project folder.  Use the command `javac filename.java` to compile the project.
Use the command `java filename` to run the project.

## How to run the tests

The unit tests are managed with Apache Maven.
JUnit5 dependencies are specified in the pom.xml file.

To run the tests:

In IntelliJ, use the Maven plugin and select the test phase in the Build Lifecycle.
Alternatively, `run mvn` test in the terminal