## Synopsis

A simple command line application that scrapes a list of available products from the groceries page and retrieves information about each of those products to be returned in a structured JSON array.

## Installation

This project requires:

Java (1.7 or greater)
Maven

To install the application run the following (from the project location)

mvn clean install assembly:assembly

## Running the application

Once installed run the following (from the project location)

java -jar target/sainsburystest-1.0-SNAPSHOT-jar-with-dependencies.jar

## Tests

There is a small suite of unit tests which are run during installation but can be run at any time by running the following (from the project location)

mvn test

## Future improvements

Implementation of DI framework

Better testing coverage of services

Functional tests of services involving real data

