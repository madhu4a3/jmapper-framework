# Explicit conversion #

The dynamic conversion opens interesting scenarios.
Allows you to going in introspection avoiding the use of Reflection, an advantage not only in performance but also in code readability.
Furthermore, the possibility to outsource the xml, permits to make changes without touch the code, thus greatly increasing the potential of the framework.

## 1° Scenario ##
This example shows the use of static conversions.
We want transform Strings to Dates.
```
@JGlobalMap
class Dates {                      class Strings{

   Date startDate;                   String startDate;
   Date endDate;                     String endDate; 

   // getters and setters..          // getters and setters..
}                                  }
```
XML configuration:
```
<jmapper>
  <class name="it.jmapper.bean.Strings">
    <!-- in this case we use default values -->
    <conversion name="toDate">
    	return new java.text.SimpleDateFormat("dd/MM/yyyy").parse(${source});
    </conversion>
  </class>
</jmapper>
```

_In this example we use annotated configuration and conversion in xml format._
<br>Method created at runtime:<br>
<pre><code>public Date toDate(String source){<br>
   return new java.text.SimpleDateFormat("dd/MM/yyyy").parse(source);<br>
}<br>
</code></pre>
mapping generated:<br>
<pre><code>destination.setStartDate(toDate(source.getStartDate()));<br>
destination.setEndDate(toDate(source.getEndDate()));<br>
</code></pre>
example code:<br>
<pre><code>JMapper&lt;Dates, Strings&gt; mapper = new JMapper&lt;Dates, Strings&gt;(Dates.class, Strings.class);<br>
Dates destination = mapper.getDestination(new Strings("01/01/2012", "01/01/2013"));<br>
System.out.println(destination);<br>
</code></pre>
output:<br>
<pre><code>Dates [startDate=Tue Jan 01 00:00:00 CET 2013, endDate=Wed Jan 01 00:00:00 CET 2014]<br>
</code></pre>
<h2>2° Scenario</h2>

We have a properties file that we want to load in a bean.<br>
properties file:<br>
<pre><code>author = Alessandro Vurro<br>
framework = JMapper<br>
version = 1.1.0<br>
label = dynamic conversion test<br>
</code></pre>
java beans:<br>
<pre><code>@JGlobalMap("properties")<br>
class Destination{                 class Source{<br>
<br>
  private String author;               private Properties properties;<br>
  private String framework;      <br>
  private String version;              @JMapConversion(from={"properties"}, type=Type.DYNAMIC)<br>
  private String label;                public static String conversion(){<br>
                                             return "return (String) ${source}.get(\"${destination.name}\");";<br>
                                       }<br>
<br>
  // getters and setters...            // getter and setter...<br>
       				          					     <br>
}			           }<br>
</code></pre>
<i>@JMap allows you to configure a field with only another and not simultaneously with more fields.</i><br>For this reason we have configured the  Destination with JGlobalMap in direction to the Source field.<br>
<br>
Methods created at runtime:<br>
<pre><code>public String fromPropertiestoAuthor(Properties source){<br>
   return source.get("author");<br>
}<br>
<br>
public String fromPropertiestoFramework(Properties source){<br>
   return source.get("framework");<br>
}<br>
<br>
public String fromPropertiestoVersion(Properties source){<br>
   return source.get("version");<br>
}<br>
<br>
public String fromPropertiestoAuthor(Properties source){<br>
   return source.get("label");<br>
}<br>
</code></pre>
mapping generated:<br>
<pre><code>destination.setAuthor(fromPropertiestoAuthor(source.getProperties()));<br>
destination.setFramework(fromPropertiestoFramework(source.getProperties()));<br>
destination.setVersion(fromPropertiestoVersion(source.getProperties()));<br>
destination.setLabel(fromPropertiestoLabel(source.getProperties()));<br>
</code></pre>
example code:<br>
<pre><code>JMapper&lt;Destination,Source&gt; mapper = new JMapper&lt;Destination,Source&gt;(Destination.class, Source.class);<br>
Destination destination = mapper.getDestination(new Source(properties));<br>
System.out.println(destination);<br>
</code></pre>
output:<br>
<pre><code>Destination:<br>
 author = Alessandro Vurro<br>
 framework = JMapper<br>
 version = 1.1.0<br>
 label = dynamic conversion test<br>
</code></pre>
In the case of addition or removal of properties from the file, just add or remove the field from Destination class.<br>
<br>
<h2>3° Scenario</h2>

We have a HashMap that has the pair <"name field", value field> and a bean with different fields, we want to do the following:<br>
<ul><li>load bean from HashMap<br>
</li><li>load HashMap from bean</li></ul>

The classes are:<br>
<pre><code>class Destination{             class Source{<br>
   <br>
    private String  id;            private HashMap&lt;String, Object&gt; map;<br>
    private Integer quantity;    <br>
    private Date    purchase;   <br>
                                  <br>
    // getters and setters...      // getter and setter...<br>
       				          					     <br>
}			       }   <br>
</code></pre>
XML configuration:<br>
<pre><code>&lt;jmapper&gt;<br>
  &lt;class name="com.application.Destination"&gt;<br>
    &lt;global&gt;<br>
      &lt;value name="map"/&gt;<br>
      &lt;classes&gt;<br>
         &lt;class name="com.application.Source"/&gt;<br>
      &lt;/classes&gt;     <br>
    &lt;/global&gt;<br>
    &lt;conversion name="fromMapToAll" from="map" type="DYNAMIC"&gt;<br>
	    return (${destination.type}) ${source}.get("${destination.name}");<br>
    &lt;/conversion&gt;<br>
    &lt;conversion name="fromAllToMap" to="map" type="DYNAMIC"&gt;<br>
	    ${destination}.put("${source.name}",${source});<br>
            return ${destination};<br>
    &lt;/conversion&gt;<br>
  &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
Methods generated at runtime:<br>
<pre><code>public String fromMaptoId(HashMap source){<br>
   return (String) source.get("id");<br>
}<br>
<br>
public Integer fromMaptoQuantity(HashMap source){<br>
   return (Integer) source.get("quantity");<br>
}<br>
<br>
public Date fromMaptoPurchase(HashMap source){<br>
   return (Date) source.get("purchase");<br>
}<br>
<br>
public String fromPropertiestoAuthor(Properties source){<br>
   return source.get("label");<br>
}<br>
<br>
public HashMap fromIdtoMap(HashMap destination, String source){<br>
   destination.put("id",source);<br>
   return destination;<br>
}<br>
<br>
public HashMap fromQuantitytoMap(HashMap destination, Integer source){<br>
   destination.put("quantity",source);<br>
   return destination;<br>
}<br>
public HashMap fromPurchasetoMap(HashMap destination, Date source){<br>
   destination.put("purchase",source);<br>
   return destination;<br>
}<br>
</code></pre>
So in case we want to fill the bean starting from HashMap the mapping used will be:<br>
<pre><code>destination.setId(fromMaptoId(source.getMap()));<br>
destination.setQuantity(fromMaptoQuantity(source.getMap()));<br>
destination.setPurchase(fromMaptoPurchase(source.getMap()));<br>
</code></pre>
Instead, the mapping used to populate the HashMap from bean:<br>
<pre><code>destination.setMap(fromIdtoMap(destination.getMap(),source.getId()));<br>
destination.setMap(fromQuantitytoMap(destination.getMap(),source.getQuantity()));<br>
destination.setMap(fromPurchasetoMap(destination.getMap(),source.getPurchase()));<br>
</code></pre>
example code:<br>
<pre><code>HashMap&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();<br>
map.put("id", "JMapper Framework v.1.1.0");<br>
map.put("quantity", 10);<br>
map.put("purchase", new Date());<br>
<br>
RelationalJMapper&lt;Destination&gt; mapper;<br>
mapper = new RelationalJMapper&lt;Destination&gt;(Destination.class,"xml/dynamicConversion.xml");<br>
<br>
Source source = new Source(map);<br>
Destination manyToOne = mapper.manyToOne(source);<br>
System.out.println(manyToOne);<br>
<br>
Source empty = new Source(new HashMap&lt;String, Object&gt;());<br>
Source oneToMany = mapper.oneToMany(empty,manyToOne);<br>
System.out.println(oneToMany);	<br>
</code></pre>
output:<br>
<pre><code>Destination:<br>
 id = JMapper Framework v.1.1.0<br>
 quantity = 10<br>
 purchase = Tue Dec 11 15:41:06 CET 2012<br>
<br>
Source:<br>
 map = {purchase=Tue Dec 11 15:41:06 CET 2012, quantity=10, id=JMapper Framework v.1.1.0}<br>
</code></pre>
In this example we use RelationalJMapper, as you can see in the oneToMany example we pass as input an empty destination instance (Source class in this case become a destination), because the conversion method uses a reference to destination.<br>
<h2>Overall</h2>

Despite the potential of dynamic conversion are so high, the performance  isn't affected, since the code continues to be generated at runtime and therefore equivalent to the static code, also increasing advantages such as:<br>
<ul><li>reducing the amount of code ->  increase the readability<br>
</li><li>development simplified -> easier maintenance