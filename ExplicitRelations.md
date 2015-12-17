# Explicit Relations #

In this first part we have seen how JMapper will define relations one-to-many and many-to-one with a single configuration, this approach is useful when you are not aware of classes that may use them, but this way the developer is bound to instantiate whenever JMapper passing in input both classes.

To overcome this, the framework exposes the **RelationalJMapper** class.
To take advantage of it, you must define, for each mapped field, the target classes of the mapping, as shown below.
```
class AnnotatedClass{                      
    
    @JMap(attributes = {"field1Class1", "field1Class2", "field1Class3"}
          classes    = {Class1.class, Class2.class, Class3.class})
    private String field1;                      
    @JMap(attributes = {"field2Class1", "field2Class2", "field2Class3"}
          classes    = {Class1.class, Class2.class, Class3.class})
    private String field2;       
    @JMap(attributes = {"field3Class1", "field3Class2", "field3Class3"}
          classes    = {Class1.class, Class2.class, Class3.class})
    private String field3;                  

    // getters and setters...                
}                                     
```
RelationalJMapper takes as input only the configured class:
```
new RelationalJMapper<AnnotatedClass>(AnnotatedClass.class);
```
and the path of xml configuration file if it's defined:
```
new RelationalJMapper<AnnotatedClass>(AnnotatedClass.class,"xml/jmapper.xml");
```
## Many to One ##
Using the manyToOne method will indicate to the framework that the configured class is the destination class, while target classes become the sources.
In `AnnotatedClass` eg the source classes are Class1, Class2 and Class3, then passing an instance belonging to one of them, the method returns an instance of `AnnotatedClass`.
```
AnnotatedClass manyToOne = null;

Class1 class1 = new Class1("field1Class1","field2Class1","field3Class1");
Class2 class2 = new Class2("field1Class2","field2Class2","field3Class2");
Class3 class3 = new Class3("field1Class3","field2Class3","field3Class3");

RelationalJMapper<AnnotatedClass> rm = new RelationalJMapper<AnnotatedClass>(AnnotatedClass.class);


manyToOne = rm.manyToOne(class1);
System.out.println(manyToOne);

manyToOne = rm.manyToOne(class2);
System.out.println(manyToOne);

manyToOne = rm.manyToOne(class3);
System.out.println(manyToOne);
```
Output:
```
AnnotatedClass [field1="field1Class1", field2="field2Class1", field3="field3Class1"]
AnnotatedClass [field1="field1Class2", field2="field2Class2", field3="field3Class2"]
AnnotatedClass [field1="field1Class3", field2="field2Class3", field3="field3Class3"]
```
For more informations read [ManyToOne](manyToOne.md) section.
## One to Many ##
The method oneToMany instead allows us to define the configured class as a source class and the target classes as destination classes.
`AnnotatedClass` in this example is the Source class and classes Class1, Class2 and Class3 are Destinations, it is still required, to explain the instance to be created, as follows:
```
relationalJMapper.oneToMany(Class1.class, annotatedClass);
```
Sample code:
```
RelationalJMapper<AnnotatedClass> rm = new RelationalJMapper<AnnotatedClass>(AnnotatedClass.class);

AnnotatedClass annotatedClass = new AnnotatedClass("field1", "field2", "field3");

Class1 class1 = rm.oneToMany(Class1.class, annotatedClass);
System.out.println(class1);

Class2 class2 = rm.oneToMany(Class2.class, annotatedClass);
System.out.println(class2);

Class3 class3 = rm.oneToMany(Class3.class, annotatedClass);
System.out.println(class3);
```
Output:
```
Class1[field1Class1="field1", field2Class1="field2", field3Class1="field3"]
Class2[field1Class2="field1", field2Class2="field2", field3Class2="field3"]
Class3[field1Class3="field1", field2Class3="field2", field3Class3="field3"]
```
For more informations read [OneToMany](oneToMany.md) section.

# Examples #

To better understand its use see the [relational mapping usage](AdvancedExamples.md) page.