# Organising the OnUpdateReceived Method

Every Bot written with this library does have a method called OnUpdateReceived. This method will be called for every Update the bot receives. Less experienced programmers will then continue to write the entire code for the bot into that same method, causing it to grow to easily a few hundred to sometimes a thousand lines of code, making it completely unmaintainable. In this Guide we'll cover some ways how to organise that method better and modularise the code in it

## The Basics: Classes and Objects
We will have to start with some groundwork here. If you are already familiar with Java, Classes and Methods you can skip this part and read further (Or you will probably already know everything i am going to explain here)

In Java everything is a class. (Exceptions prove the rule). Your Bot is a Class, The Update is a class, Messages, The BotApiInitializer, Strings, Files, Images and so on and they all function on the same basic level.

A class consists of a number of fields and a collection of methods. You can understand a class like a blueprint. They can't be called directly unless, explicitly created that way, and are mostly used to create Instances of itself or objects.

Each Object has a reference to itself using Object-variables. If an object is not referenced anymore, the garbage collector deletes it.

Whenever you write ```MyBot bot = new MyBot()``` the variable _bot_ will be now a reference to the Object.

## The Basics: References and Methods
Whenever you want to call a method on an object, you have to give a reference to the object.
Given this class:
```java
public class MyClass {
    public void someMethod() {
        //do important stuff...
    }
}
```

We can call the method by first creating a reference and then calling it:

```java
MyClass object = new MyClass();
object.someMethod();
```

We don't have to create the Reference ourselves though. Since references are just variables, they can be passed into a method

```java
public void myFunction(MyClass object) {
    object.someMethod();
}
```

But what if we want to call a method of the class we are actually in? Look at this examle:

```java
public class MyClass {
    public void someMethod() {
        //do important stuff...
    }
    
    public void callSomeMethod() {
        someMethod();
    }
}
```

In this situation the compiler actually knows that when we call someMethod(), we reference the object we are currently in. There's another way of referencing the object we are in. Using the _this_ keyword. This, inside an Object method, will always reference the current object.

```java
public class MyClass {
    public void someMethod() {
        //do important stuff...
    }
    
    public void callSomeMethod() {
        this.someMethod();
    }
}
```
This will do exactly the same as the example above, except that your IDE will probably give you a warning that you are using an unnecessary _this_

Going further, we can pass _this_ like a variable into a method

```java
public class MyClass {
    public void someMethod(MyClass object) {
        //do important stuff with the object...
    }
    
    public void callSomeMethod() {
        someMethod(this);
    }
}
```

## The Basics: Bringing it together with the Library
So, what does all of this has to do with making bots? Easy.

Whenever you want to send something to telegram, or execute a BotMethod you use the execute method of the bot. One of the most frequently asked questions is "How do i call execute outside of the bot class"

Pretty simple: You need to pass a reference to the bot into the class you want to call it from. And that's what basically all further explanations base upon. Getting the right objects to the right method to execute some Code.

But lets get more hands on:

## Hands on: Organising your code

In the following sections i will talk about a few different ways on how to organise your methods and classes and how to be able to call all important functions. These examples will not be the perfect solutions nor be all of them. They will only include some basic functions as well as 2 Frameworks already build and integrated into the Library repository.

I will not cover any third party libraries or frameworks, since i don't know all of them and it would be unfair to just feature a few. If you are interested in these, ask in the telegram chat 

### 