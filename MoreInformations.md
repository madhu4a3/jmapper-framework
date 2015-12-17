# Design by contract #

In order to make the code independent from the concrete classes (JMapper and  RelationalJMapper), the framework exposes the interfaces: **IJMapper** and **IRelationalJMapper**, which respectively define the contracts for JMapper and  RelationalJMapper classes.

# About raw types #

Also you can perform mapping between raw types, operations allowed are implicit structural conversions:<br />
`ArrayList <--> Set`<br />
`SortedMap <--> HashMap`<br />
`List <--> Set`<br />For the framework  is impossible to know the type of the internal objects, for this reason they must be assignables.

# Utility classes #

There are two classes that can help you in everyday situations and they are: **GeneralUtility** and **ClassesManager**, by a look.