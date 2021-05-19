Another project from hyperskill.  
It's called Contacts.   
The description from hyperskill says that I will learn the basic fundamentals of object-oriented programming: encapsulation, inheritance and polymorphism.  
_________________________
Stage 1-2:  
  
created the following classes:  
Person (represents a person, i.e. name, surname, phone number)  
Storage (stores contacts)  

created two enums:  
Property (represents person's properties)    
Operations (actions that available for users)

____________________________
Stage 3:  
  
project consists of the following things:  
  
app package:  
App.java - class that contains main logic  
ContactMaker.java - class helps make contacts
editor package:  
contains two .java files that helps modify contacts
  
contactTypes package:  
  it contains a base class contact and its subclasses,   
organization class and person class  
  
exceptions package:  
it holds two exception classes(contact not found and no such contact type)
  
  storage package:
  it contains list with contacts, it has methods for contacts managing
  
  typeOfOperation package:
  it contains enum with actions available for user

____________________________
Stage 4:
  
Added serialization, deserialization and search by contact fields.  
The structure of packages has been changed.    
  
app package contains all the business logic.
In this package, almost every option for dealing with a contact has its own package and a class that implements its logic.
  
contactFactory package creates contacts. One class from there is used as simple factory idiom 
  
A class was added to storage package. The class is SerializationUtils.java. The name speaks for itself

