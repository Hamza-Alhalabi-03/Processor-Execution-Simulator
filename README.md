# Processor Execution Simulator
A multithreaded Java application that simulates how tasks are scheduled and executed across multiple processors simultaneously, demonstrating advanced concurrent programming and system architecture design.

## 🎯 Project Goal
Build a realistic processor execution simulator that models task scheduling, resource allocation, and concurrent execution using Java multithreading, design patterns, and object-oriented principles.

## ▶️ Demo
Watch the simulator in action:  
[🎬 Processor Execution Simulator Demo](https://youtu.be/w5GaUCdccC8)

## 🔧 Key Features
- **Multithreaded Processing**: Concurrent task execution across multiple processor threads
- **Priority-Based Scheduling**: High-priority tasks with longest-execution-time-first algorithm
- **Real-Time Simulation**: Clock-driven execution with observer pattern synchronization
- **Extensible Architecture**: Abstract classes for custom scheduling and processing algorithms
- **Thread-Safe Operations**: Proper synchronization using ExecutorService and concurrent collections
- **Visual Reports**: Color-coded console output showing real-time task and processor status

## 🧱 Architecture Overview
```
┌─────────────┐    ┌──────────────┐    ┌─────────────┐
│   Simulator │────│   Scheduler  │────│  Processor  │
│  (Abstract) │    │  (Abstract)  │    │ (Abstract)  │
└─────────────┘    └──────────────┘    └─────────────┘
       │                   │                   │
       ▼                   ▼                   ▼
┌─────────────┐    ┌──────────────┐    ┌─────────────┐
│BasicSimulator│   │BasicScheduler│    │BasicProcessor│
└─────────────┘    └──────────────┘    └─────────────┘
       │
       ▼
┌─────────────┐    ┌──────────────┐
│    Clock    │────│ClockObserver │
│ (Singleton) │    │ (Interface)  │
└─────────────┘    └──────────────┘
```

## 🚀 Technologies Used
- **Java 21**: Core programming language with advanced threading features
- **Maven**: Project management and dependency handling
- **JUnit**: Unit testing framework
- **Concurrent Collections**: Thread-safe data structures (PriorityQueue, ExecutorService)
- **Design Patterns**: Singleton, Observer, Factory Method, Strategy

## 🔧 Extensibility
Create custom implementations by extending:
- `Simulator` - New simulation algorithms
- `Scheduler` - Custom task assignment strategies  
- `Processor` - Different execution behaviors

## 📄 License
This project is provided for educational and demonstration purposes only.  
Created by Hamza Alhalabi as part of concurrent programming and system design learning.
