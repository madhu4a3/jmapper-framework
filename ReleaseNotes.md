# Release Notes #
## 1.3.3.1 - 21 July 2015 ##
some bug fixed:
  * NPE when XML path is wrong
  * recursive mapping Object/List/List doesn't work
  * bug in getGenericString method of ClassesManager class

For more info go to the [issues](https://github.com/jmapper-framework/jmapper-core/issues?q=milestone%3A1.3.3.1+is%3Aclosed) page.
## 1.3.3 - 31 May 2015 ##
**regex function added**

Now it's possible use regex to identify fields.
## 1.3.2 - 13 May 2015 ##
**added the get and set placeholder**

With this relase it's possible to use, in dynamic methods, the get and set placeholder for destination: `${destination.get`}, `${destination.set`} and for source: `${source.get`}, `${source.set`}.

This placeholder permits to implement the flatten and projection functions easily.
## 1.3.1 - 17 April 2015 ##
**class parameter in JMapAccessor**

Added class parameter in JMapAccessor to avoid cases where target and mapped fields have the same name for example:
```
public class Destination{
	
   @JMapAccessors({
     @JMapAccessor(name="field",get="get",set="set", classes={Source.class}),
     @JMapAccessor(name="field",get="get",set="set", classes={Destination.class})
   })
   private String field;
}
```

**avoid set method in explicit conversions**

Now you can avoid the use of set method for the destination instance.
In Annotation:
```
@JMapConversion(avoidSet=true)
```
In XML:
```
<conversion name="conversion" avoidSet="true">
   conversion body
</conversion>
```
This is usefull when destination fields are obtainable only by the get method

**JMapAccessor handled in XMlHandler**

The methods from and to XML are updated to work with JMapAccessor too.

## 1.3.0 - 27 March 2015 ##

**XSD for XML configuration**

Added the XML Schema relative to xml configuration.

```
<?xml version="1.0" encoding="UTF-8"?>
<jmapper xmlns="https://jmapper-framework.googlecode.com"
	xmlns:xsi="https://jmapper-framework.googlecode.com/svn"
	xsi:noNamespaceSchemaLocation="https://jmapper-framework.googlecode.com/svn/jmapper.xsd">
</jmapper>
```

**JMapAccessor**

Now you can define get/set custom methods! With @JMapAccessor and get and set attribute.<br />For more information see the [wiki](CustomAccessors.md) page.

**XmlHandler**

Added xsd declaration in generated xml.

**Exception rethrown**

The internal exceptions are wrapped as RuntimeException and relaunched.

## 1.2.2 - 15 December 2014 ##

**merge optimized of two arrays**.

the actual operation is:
```
String[] newArray = new String[firstArray.length + secondArray.length];
int index = 0;
for(int i = 0; i < firstArray.length: i++){
   newArray[index++] = firstArray[i];
}
 
for(int i = 0; i < secondArray.length: i++){
   newArray[index++] = secondArray[i];
}
```
This code is simple but slow, the best way to do this is to use system methods, as follow:
```
String[] newArray = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
System.arraycopy(secondArray, 0, newArray, firstArray.length, secondArray.length);  
```
This algorithm is three times faster and short.

**Errors management of Dynamic Methods**.

errors managed: methods poorly written and null variables as input.

**Added management of primitive types in complex mappings**.

the dynamic mappings between primitive types and complex types as Map,Collection etc.. now are permitted.

**Dependencies update**.

XStream, slf4j and javassist and added JMapper API.

**Added printStackTrace**.

To avoid log management a printStackTrace will be added.

**Mapping between collections**.

The order of the destination will be the same of source.

**get method for boolean**.

For boolean types: will be permitted have a "get" method in addition to "is" method.

**OSGI compatible**.

Will be published an artifact with only interfaces to be compliant to OSGI specification.

## 1.2.0 - 16 February 2013 ##

**[Global Mapping](globalMapping.md)** added.<br />This feature permits to apply a global mapping avoiding configuration redundancy.

**Packaging renamed**.<br />
The package names will be changed from it.avutils.`*` to com.googlecode.`*`

**Maven pom adjustment**.<br />
Some dependencies will be deleted as redundant.

## 1.1.1 - 9 January 2013 ##

With this released the features added are:

**[Inherited configuration](AdvancedInheritanceExamples.md)**<br />
Now we can define a common configuration/conversions in the superclass, less code, less redundance and less maintenance.

**[Implicit conversion between arrays and collections](ImplicitConversions.md)**<br />
With this features we can configure arrays with collections and vice versa, with any type of items (primitive, wrapper and mapped).

**[OneToMany method changed](oneToMany.md)**<br />
To prevent unexpected behavior in case of singleton pattern usage in multi-threaded environment, the definition of the target class will be done through the passage of the class as input to the method.

## 1.1.0 - 8 December 2012 ##

**[Explicit conversions](ExplicitConversions.md)** added.<br />This feature permits to apply a specific logic as a simple conversion or to insert a condition, both in XML that in java.

## 1.0.0 - 12 September 2012 ##

First release of JMapper Framework.<br />
This release contains basic features as:
  * create and enrich target objects
  * apply a specific logic to the mapping (only valued fields, only null fields, etc..)
  * automated management of the XML file
  * possibility of implementing the 1 to N and N to 1 relationships