This is a simple REST API. Using this api you can manage your trello board. You can easly add a new element to three cards: "To do" card, "In progress" card and "Done" card. 

This application uses the following technologies:
- REST API,
- Spring WEB,
- Hibernate,
- Java,
- Gradle,
- MVC architecture,
- MySQL database,
- design patterns.

The following API commands:
- GET:
    - /getTasks - get all tasks,
    - /getTask?taskId={id} - get task with id = {id},
- POST:
    - /createTask - create new task (with @RequestBody - title and content),
- PUT:
    - /updateTask - update existed task (with @RequestBody - id, title, content).
    
