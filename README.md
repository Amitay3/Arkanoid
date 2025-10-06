# 🧱 Arkanoid – Java OOP Game Project

Welcome to **Arkanoid**, a classic brick-breaker game — built entirely in **Java** using **Object-Oriented Programming (OOP)** principles and a graphical library.  

This project was developed as part of an academic assignment focused on **design patterns**, **code modularity**, and **interactive graphics programming**.

---

## 🎮 Gameplay Overview

Your goal is simple:  
Control the paddle, keep the ball in play, and break all the blocks to win!  

But beware — once the ball falls below the paddle, it’s game over 😬  

### 🕹️ Controls
- **← / →** — Move the paddle  
- **SPACE** — Launch the ball / Start game  
- **H** — View “How to Play” instructions  
- **ESC** — Exit the game  

---

## 🧩 Features

✅ **Interactive GUI** built with the `biuoop` library (for drawing, animation, keyboard input, etc.)  
✅ **Modular OOP Architecture** with clear class separation and encapsulation  
✅ **Dynamic Menu System** (Play / How to Play / Exit)  
✅ **Collision Detection** between the ball, blocks, and paddle  
✅ **Scoring System** that tracks player progress  
✅ **Smooth Animation Loop** at ~60 FPS  
✅ **Loss Screen and Restart Option**  

---

## Object-Oriented Design Highlights

This project was built from the ground up with **clean OOP structure** in mind:

| Concept | Implementation Example |
|----------|-------------------------|
| **Encapsulation** | Each game element (Ball, Paddle, Block, GameEnvironment, etc.) maintains its own state and behavior. |
| **Inheritance & Interfaces** | Shared behaviors implemented via interfaces such as `Sprite`, `Collidable`, and `HitListener`. |
| **Polymorphism** | Different game entities respond uniquely to hits, drawing, and updates while sharing a common interface. |
| **Abstraction** | The `GameLevel` and `AnimationRunner` classes abstract the game loop and rendering logic. |
| **Composition** | The game aggregates multiple objects (e.g., blocks, balls, score indicators) into cohesive systems. |

This architecture promotes **reusability**, **readability**, and **easy scalability** — enabling new features or mechanics to be added with minimal refactoring.

---

## 🧰 Technologies Used

| Tool / Library | Purpose |
|----------------|----------|
| **Java (JDK 17+)** | Core language used for logic and OOP structure |
| **biuoop-1.4.jar** | Lightweight educational GUI library (drawing, keyboard, animation) |
| **OOP Principles** | Encapsulation, Inheritance, Polymorphism, Abstraction |
| **Gradual Design Approach** | Iterative development from simple collision detection to full-featured game loop |

---

## 🚀 Running the Game

You have two options: run from source or run the pre-built JAR file.

---

### 🧩 Option 1: Build & Run from Source

Make sure you have **Java (JDK 11 or later)** installed.  
Then compile and run the project manually:

```bash
# Compile all source files into the 'out' folder
javac -cp ".;biuoop-1.4.jar" -d out @sources.txt

# Run the game
java -cp ".;biuoop-1.4.jar;out" game.Menu
```

---

### 🎮 Option 2: Run the Pre-Built JAR

In a terminal, in the root folder of this project, simply run:

```bash
java -jar Arkanoid.jar
```

Make sure that `biuoop-1.4.jar` is in the **same directory** as the JAR file (unless it’s already bundled inside).

---

## 🧠 Lessons Learned

Through this project, I practiced:
- Structuring large-scale Java programs using **Object-Oriented Design**  
- Managing **real-time rendering and input handling** via an external GUI library  
- Building a complete, interactive game loop from scratch  
- Applying **design patterns** to maintain code clarity and modularity  

---

## ✨ Credits

- **Developed by:** Amitay Raviv  
- **Course:** Object-Oriented Programming in Java  
- **Instructor Library:** [biuoop](https://www.cs.bgu.ac.il/~OOPCourse/biuoop/)  
