# Capture4J

[![Build Status](https://travis-ci.org/firaja/capture4j.svg?branch=master)](https://travis-ci.org/firaja/capture4j)
[![codecov](https://codecov.io/gh/firaja/capture4j/branch/master/graph/badge.svg)](https://codecov.io/gh/firaja/capture4j)
![License](https://img.shields.io/github/license/firaja/capture4j)

This library aims to remove from your code bulky try-catch blocks and make your code easier to read and maintain.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
Make sure you have installed all of the following prerequisites on your development machine:
 * **Java JDK 1.8+** - You can use a JDK from any vendor, but it is recommended to use 
 [Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html) or 
 [OpenJDK](https://openjdk.java.net/install/)
 * **Maven 3+** - [Download and install](https://maven.apache.org/install.html)
 
### Installing

#### Git
```shell script
git clone git@github.com:firaja/capture4j.git
cd capture4j
mvn clean install
```

#### Maven
Insert the following dependency in your `pom.xml`
```xml
<dependency>
  <groupId>dev.firaja.utils</groupId>
  <artifactId>capture4j</artifactId>
  <version>0.1.1-beta</version>
</dependency>
```
Then run
```shell script
mvn install
```

## Profit

Here a classic example where a method returns the same value in case of *error*
```java
public int doSomething(MyObject myObject)
    {
        if(myObject == null || myObject.getId() == null)
        {
            return -1;
        }

        String name;
        try
        {
            name = readNameFromSomewhere(myObject.getId());
        } 
        catch (EntryNotFoundException e)
        {
            return -1;
        }

        if(name != null && name.indexOf(45) == 'a')
        {
            return -1;
        }

        return 1;
    }
```
while the same method can be rewritten with **capture4j** like this
```java
@EasyCapture(returns = "-1")
    public int doSomething(MyObject myObject)
    {
        String name = readNameFromMyDB(myObject.getId());
        return name.indexOf(45) == 'a' ? 1 : -1;
    }
```

You can specify the type of exception you want to treat
```java
@EasyCapture(what = IllegalStateException.class, returns = "illegal")
@EasyCapture(what = NullPointerException.class, returns = "null pointer :(")
public String doSomething()
{
    ...
}
```

Or even use a custom handler for standard of custom exceptions
```java
@Capture(what = MyException.class, with = MyHandler.class)
public long doSomething()
{
    ...
}
```
```java
class MyHandler implements Handler<Long>
{
    public Long handle(Throwable theException) // <- MyException
    {
        long fallback = doSomeCalculation();
        logger.warn("Something went wrong :( but I'returning {}.", fallback, theException);
        return fallback;
    }    
}
```
## Versioning
![GitHub release (latest by date)](https://img.shields.io/github/v/release/firaja/capture4j) ![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/firaja/capture4j?include_prereleases)

We use [SemVer](http://semver.org/) for versioning. 

For the versions available, see the [tags on this repository](https://github.com/firaja/capture4j/tags).

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **David Bertoldi** - *Initial work* - [firaja](https://github.com/firaja)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
