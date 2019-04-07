# web-crawler
Java based Web Crawler
=================

This is Java Spring HATEOAS based web crawler.

## Table of contents
  * [Requirements](#requirements)
  * [Project Setup](#project-setup)
    * [IDE Setup](#ide-setup)
  * [Commands](#commands)
    * [Build Project](#build-project)
    * [Run the Application](#run-the-application)
    * [Run Integration Tests](#run-tests)

## Requirements
* Java 1.8

## Project Setup
### IDE Setup
IntelliJ is suggested: File -> New -> Module/Project from Existing Source -> (Select root `build.gradle`)

## Commands
### Build Project
Compiles the source, runs the unit tests, and also installs into your local Maven repository.
In the project directory run:
```
./gradlew clean build
```

### Run the Application
```
./gradlew bootRun
```

### Run Tests
```
./gradlew test
```
