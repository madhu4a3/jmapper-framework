# Global Mapping #

The global mapping permits to reduce the configuration in cases that  the fields have the same name or all fields are mapped in direction to one.

## 1° Scenario ##

We have two beans with some variables that have the same names.

#### In Annotation ####
```
@JGlobalMap(excluded={"other"})
class Destination {            class Source {

    String[] authors;              List<String> authors;  
    Date releaseDate;              Date releaseDate;
    String releaseVersion;         String releaseVersion;
    String other;                  String other;

    // getters and setters..       // getters and setters..
}                              }
```
#### In XML ####
```
<jmapper>
   <class name="package.Destination">
      <global>
          <excluded>
             <attribute name="other"/>
          </excluded>
      </global>
   </class>
</jmapper>
```
#### example code ####
```
List<String> authors = new ArrayList<String>();
authors.add("Alessandro Vurro");
authors.add("Federico De Felici");
Source source = new Source(authors, new GregorianCalendar(2013, 1, 16).getTime(), "1.2.0", "other");

JMapper<Destination, Source> mapper = new JMapper<Destination, Source>(Destination.class, Source.class);
Destination destination = mapper.getDestination(source);
```
output:
```
Destination [authors=[Alessandro Vurro, Federico De Felici], releaseDate=Sat Feb 16 00:00:00 CET 2013, releaseVersion=1.2.0, other=null]
```

## 2° Scenario ##

We have one bean with some variables that point to a single field.

#### In Annotation ####
```
class Destination {

    Double overallCost;

    public Destination(){
        overallCost = 0D;
    }

    public void setOverallCost(Double singleCost){
        this.overallCost += singleCost;
    }

    // getter..                                   
}

@JGlobalMap("overallCost")
class Source {
  
    Double costEmployees;
    Double costStructure;
    Double costAdvertising;

    // getters and setters..                                   
}
```
#### In XML ####
```
<jmapper>
   <class name="package.Source">
      <global>
          <value name="overallCost"/>
      </global>
   </class>
</jmapper>
```
#### example code ####
```
JMapper<Destination, Source> mapper = new JMapper<Destination, Source>(Destination.class, Source.class);
		Destination destination = mapper.getDestination(new Source(154080.80D,24000D,12570.20D));
```
output:
```
Destination [overallCost=190651.0]
```