# Project Overview

Resourcium Optima is a tool dedicated to optimizing talent and equipment management within businesses. It provides HR managers and supervisors with an intuitive interface to orchestrate tasks related to employees and equipment.

# Resourcium Optima Project Setup Guide

This guide is intended for team members who will be working on the "Resourcium Optima" project. It provides an overview of the project's dependencies and what's needed to run the project locally.

## Dependencies

To work on and run the Resourcium Optima project, you'll need the following dependencies:

1. **Java Development Kit (JDK):**
   - Install the latest version of the Java Development Kit (JDK). The project is built using Java, so having the JDK is essential.

2. **Integrated Development Environment (IDE):**
   - We recommend using an IDE for Java development. Some popular choices include:
     - [Eclipse](https://www.eclipse.org/downloads/)
     - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
   
3. **Database:**
   - Make sure you have a MySQL database set up. You'll need the database URL, username, and password to configure the project.

4. **Git:**
   - You should have Git installed on your machine to clone the project repository and collaborate with the team.

5. **Build Tool (Maven):**
   - The project is managed with Maven. Ensure you have Maven installed to build and manage project dependencies.

6. **Web Server (e.g., Apache Tomcat):**
   - For deploying and testing the project, you'll need a web server like Apache Tomcat. Download and set it up in your development environment.

## Getting Started

To start working on the project, follow these steps:

1. **Clone the Repository:**
   - Use Git to clone the project repository to your local machine.

   ```bash
   git clone <repository_url>

2. **Import Project into IDE:**

Import the project into your chosen IDE (Eclipse or IntelliJ IDEA) as a Maven project.

3. **Configure the Database:**

Update the project's database configuration. You'll find this in a configuration file (persistence.xml).

4. **Build the Project:**

Use Maven to build the project and resolve its dependencies.
```bash
mvn clean install
```
5. **Run the Project:**

Deploy the project on your web server (e.g., Apache Tomcat) to run and test it locally.
