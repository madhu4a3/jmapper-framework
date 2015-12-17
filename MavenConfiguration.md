# Maven configuration #

JMapper uses slf4j for logging, for the dependences resolution see [slf4j.org](http://www.slf4j.org/), fast configuration:
```
<dependency>
   <groupId>com.googlecode.jmapper-framework</groupId>
   <artifactId>jmapper-core</artifactId>
   <version>1.2.2</version>
</dependency>
<dependency> 
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.6.6</version>
</dependency>
```

# Libraries #

These are the libraries to add if you do not use Maven:

```
<dependencies>
    <dependency>
	<groupId>commons-collections</groupId>
	<artifactId>commons-collections</artifactId>
	<version>3.2.1</version>
   </dependency>
	
   <dependency>
 	<groupId>javassist</groupId>
 	<artifactId>javassist</artifactId>
  	<version>3.18.2-GA</version>
   </dependency>
	
   <dependency>
        <groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
	<version>1.7.7</version>
   </dependency>

   <dependency>
	<groupId>com.thoughtworks.xstream</groupId>
	<artifactId>xstream</artifactId>
	<version>1.4.7</version>
   </dependency>
</dependencies>
```