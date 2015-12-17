# Implicit relations #

Mapping a class with another in this way is simple and intuitive, also observing the configuration used in the introduction, show an important feature: there aren't declared classes, this allows various features such as one to many and many to one implicit relationships.
These features allow to greatly reduce the amount of configurations in case you want to map an object with many others.

## Many to One ##

The Many to One relationship is obtained when Destination is configured, giving the possibility to different Source classes, to instantiate it.

The following example will clarify any doubt:
```
                                        class Source1{
    
                                            private String id;
                                            private String sourceField;
                                            private String other;
                     
                                            // getters and setters...  
                                        }    
 
class Destination{                      class Source2{
    @JMap
    private String id;                      private String id;
    @JMap("sourceField")                    private String sourceField;
    private String destinationField;        private String other;
    private String other;                   
                                            // getters and setters...  
    // getters and setters...           }    
}   

                                        class Source3{
     
                                            private String id;
                                            private String sourceField;
                                            private String other;
                     
                                            // getters and setters...  
                                        }                                
```
Sample code:
```
JMapper<Destination, Source1> jmapper1 = new JMapper<Destination, Source1>(Destination.class, Source1.class);

Source1 source1 = new Source1("id1", "sourceField1", "other1");
Destination destination = jmapper1.getDestination(source1);

JMapper<Destination, Source2> jmapper2 = new JMapper<Destination, Source2>(Destination.class, Source2.class);

Source2 source2 = new Source2("id2", "sourceField2", "other2");
destination = jmapper2.getDestination(source2);

JMapper<Destination, Source3> jmapper3 = new JMapper<Destination, Source3>(Destination.class, Source3.class);

Source3 source3 = new Source3("id3", "sourceField3", "other3");
destination = jmapper3.getDestination(source3);
```
In this way, with a single configuration, you can interface a Destination class with several Source classes.

## One to Many ##

The One to Many relationship is obtained when Source is configured, giving the possibility to instantiate different Destination classes.

For example:
```
class Destination1{                      
                                            
    private String id;                     
    private String destinationField;       
    private String other;                   
                                                             
    // getters and setters...                                        
}                                            
                                           
class Destination2{                     class Source{
                                            @JMap
    private String id;                      private String id;
    private String destinationField;        @JMap("destinationField")
    private String other;                   private String sourceField;
                                            private String other;                   
    // getters and setters...                                        
}                                           // getters and setters...  
                                        }    
class Destination3{                      
                                         
    private String id;                  
    private String destinationField;    
    private String other;               
                                                       
    // getters and setters...                                        
}                                                                  
```
Sample code:
```
Source source = new Source("id", "sourceField", "other");

JMapper<Destination1, Source> jmapper1 = new JMapper<Destination1, Source>(Destination1.class, Source.class);
Destination1 destination1 = jmapper1.getDestination(source);

JMapper<Destination2, Source> jmapper2 = new JMapper<Destination2, Source>(Destination2.class, Source.class);
Destination2 destination2 = jmapper2.getDestination(source);

JMapper<Destination3, Source> jmapper3 = new JMapper<Destination3, Source>(Destination3.class, Source.class);
Destination3 destination3 = jmapper3.getDestination(source);
```
then, as in the many to one, with a single configuration, you can interface a Source class with several Destination classes.