# Electronic Catalogue

Homework Assignment during the OOP course.

## Description

A Java project that serves as an electronic catalogue, using Design Patterns and UI interfaces for a login page, which
redirects you to your personalized page based on your status. (student, parent, teacher, assistant)

![poza1](https://user-images.githubusercontent.com/95059633/215353216-dcd1bfbf-05d9-45c5-bd67-8dc72a0195cc.PNG)
![teachassist](https://user-images.githubusercontent.com/95059633/215353261-4dfecdb2-0a20-4ed7-bc34-98acb1db5dd2.PNG)
![student](https://user-images.githubusercontent.com/95059633/215353256-e9141b79-b0b3-44ba-9743-753c06d2f415.PNG)

## Everything you need to know about the implementation

### Catalog class
* using Singleton Design Pattern
* User, Student, Parent, Assistant, Teacher classes
*  Factory Design Pattern, UserFactory class returns specific User
*  added toString() method

### Grade class
*  fields: partialScore, examScore, student, course
*  implements Comparable, Cloneable, wrote clone and compareTo
*  setter, getter methods
*  getTotal: we sum up partialScore and examScore

### Group class
*  inherits a Student Arraylist
*  i added a Comparator field which gets updated in the constructor;
we later could use it if we wanted to sort the students by their name;
implemented NameSorter class as Comparator for this;

### Course class
*  abstract class, implemented all methods
*  added a String strategy variable to use it for getBestStudent

### Partial course/Full course classes
*  inherits Course, getGraduatedStudents differs.
* For the courses, we had to implement CourseBuilder and then
FullCourseBuilder and PartialCourseBuilder. (Builder Design Pattern)

### Observer Design Pattern
*  the Notification class stores a grade and has a specific message for each parent,
depending on the student, course, type of exam
*  Catalog implements Subject;
i created a list of observers in Catalog and the specified methods;
*  Parent implements Observer - a notification is sent to the parent and added into an arraylist of notifications
for later usage in the parent UI;

### Strategy Design Pattern
*  created a BestScoreStrategy interface and 3 separate classes who select the best
student depending on their grades;

### Visitor Design Pattern
*  addExamScore & addPartialScore methods adds a Tuple to the map
*  visit: get each entry of the map, iterate through courses and store the value for
each entry; for each course, check if it contains the Assistant/Teacher & if the course
name is the same as the tuple one; create a grade with the tuple given, set its Score
and add the grade in the Course (modifying the existing grade with no Score set)
and notifyObservers;
*  in NotifyObservers iterate through the observers ArrayList, create a Notification
and if the observer is the student's parent, call update;
*  in update print the Notification and add it to the Notification ArrayList

### Memento Design Pattern
*  created Snapshot class which stores an arraylist of grades
*  implemented makeBackup() which clones every grade in the snapshot grades
*  implemented undo() which changes the value of the course grades with the
backup ones from Snapshot

### Student Page
*  constructor receives a Student;
*  used BorderLayout, at the top placing the name of the student
*  in the center, there is a message and a list of courses
*  if you select a course a new frame opens up with details about the course

### Teacher/Assistant Page
*  constructor receives an User, its type, the visitor
*  same layout as Student Page, except in the center we list the courses of the
Teacher/Assistant and a list of tuples
*  if you press the validate grades button they are added in the catalog and
notifications are sent

### Parent Page
*  constructor receives a Parent
*  if you press Check Notifications a new frame opens with a list of the notifications
sent to the parent

### Login Page
*  used GUI Designer in Intelij who helped me build a frame that pleases me aesthetically
*  added an image to be cuter
*  the password is not seen
*  For the User section i used User.toString() and i chose the passwords this way:
    * for Teachers/Assistants i added a "password" field in the json file
      and gave them original ones
    * for students and parents their passwords are User.toString()+"123"
      (for example: Student - Gigel Frone | User: "Gigel Frone" | Pass: "Gigel Frone123")
* after login, the specific user's page is opened.

### Testing and allocating values for variables
* I tested with the values from a JSON file
* In the Test class the methods and interfaces are tested
### Executing program

* How to run the program
```
Run Test class ->
Enter a User & password - you can find them in the JSON file ->
Have fun!
```
