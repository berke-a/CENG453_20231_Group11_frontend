# CENG453 20231 Group11 Frontend

This is the Group11 frontend application of the CENG453 Term Project. In this frontend application, Java 17 is used with JavaFX 21.

## How to run the application

After cloning the project to your local machine, in the project directory, use `mvn clean install` command on your terminal to build the frontend application. Then you can run the CatanApplication.java from your IDE (preferably Intellij IDEA). Backend application is currently hosted at `https://ceng453-20231-group11-backend.onrender.com` hence there is no need to run the backend application to play the game. 

## Existing Users

There are 6 different users which can be used to login and play the game.

| Username | Password |
| -------- | -------- |
| admin   | admin   |
| user1   | password1   |
| user2   | password2  |
| user3   | password3   |
| user4   | password4   |
| user5   | password5   |

## About the game

Hexalands is a Catan board game adaptation with some differences. After starting the project, players first need to register with their emails and login with their new user or login with one of the available users above. After login, Play button will be available and it will start a game with 3 CPU players. Since there is no trading unlike Catan, players are given some initial resources to prevent starvation. Game rules can be accessed from inside the game or home page of the application. When a game is won by any of the players, current score of the player will be added to leaderboard which is also available in the home page and visible with no authentication.
