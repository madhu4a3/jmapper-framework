# Explicit conversion #

In this section the conversion is synthesized, each argument is detailed in the following chapters.<br>
Explicit conversion allows you to define your own control logic and / or processing data belonging to the source fields and assign them to the destination fields.<br>
You can define conversions through the use of methods and you can do it in java, using the annotation <b>@JMapConversion</b> and in xml format using the node <b><</b><b>conversion</b><b>></b>.<br>
<br>
<b>IMPORTANT!</b> <i>the XML conversion has greater visibility of the equivalent in java and if no xml conversion was found, will be done a search by annotated conversion</i>

Conversions can be written in the Destination or Source, regardless the configuration's location, follows an example of conversion in annotation:<br>
<pre><code>class Destination{                      class Source{<br>
    @JMap<br>
    private String id;                      private String id;<br>
    @JMap("sourceField")<br>
    private String destinationField;        private String sourceField;<br>
    private String other;                   private String other;<br>
<br>
                                            @JMapConversion<br>
					    public String conversion(String str){<br>
						return str;<br>
					    }<br>
<br>
   // getters and setters...                // getters and setters...<br>
}                                       }<br>
</code></pre>
And an example in XML:<br>
<pre><code>&lt;jmapper&gt;<br>
  &lt;class name="com.application.Destination"&gt;<br>
    &lt;attribute name="id"&gt;<br>
      &lt;value name="id"/&gt;<br>
    &lt;/attribute&gt;<br>
    &lt;attribute name="destinationField"&gt;<br>
      &lt;value name="sourceField"/&gt;<br>
    &lt;/attribute&gt;<br>
  &lt;/class&gt;<br>
  &lt;class name="com.application.Source"&gt;<br>
    &lt;conversion name="conversion"&gt;<br>
		return ${source};<br>
    &lt;/conversion&gt;<br>
  &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
You can also do a combination of the two previous examples: configuration with annotations and conversions in xml format or vice versa.<br>
conversion between the two fields is applied only when there is a relationship between them, expressed with annotations or XML, follows an example of <b>not applied</b> conversion:<br>
<pre><code>class Destination{                  class Source{<br>
    @JMap<br>
    private String id;                  private String id;<br>
    @JMap("sourceField")<br>
    private String destinationField;    private String sourceField;<br>
    private String other;               private String other;<br>
<br>
                                        @JMapConversion(from={"id"}, to={"other"})<br>
				        public String conversion(String str){<br>
					   return str;<br>
					}<br>
    // getters and setters... 	        // getters and setters...<br>
}                                   }<br>
</code></pre>
Assuming to initialize the JMapper class in the following way:<br>
<pre><code>JMapper jmapper = new JMapper(Destination.class, Source.class);<br>
</code></pre>
relations expressed:<br>
<ul><li>Source.id -> Destination.id<br>
</li><li>Source.sourceField -> Destination.destinationField</li></ul>

The conversion instead is defined for the relation:<br>
<ul><li>Source.id -> Destination.other</li></ul>

So in this case the conversion will never be applied, the same happens also for the configuration in XML format.<br>
<br>
<br>
The conversion method may have <b>one or two parameters</b>, depends on the logic that must be applied. A single input parameter indicates the source field, two parameters indicate the destination field and source field, this case comes in handy when you want to act on a destination field already defined.<br>
<br>
java example:<br>
<pre><code>@JMapConversion(from={"sourceField"}, to={"destinationField"})<br>
public String conversion(String destinationField, String sourceField){<br>
   return destinationField +" "+sourceField+" converted";<br>
}<br>
</code></pre>
XML example:<br>
<pre><code>&lt;jmapper&gt;<br>
  &lt;class name="com.application.Source"&gt;<br>
    &lt;conversion name="conversion" from="sourceField" to="destinationField"&gt;<br>
	return ${destination}+" "+${source}+" converted";<br>
    &lt;/conversion&gt;<br>
  &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
<b>IMPORTANT!</b> <i>The conversion, once satisfied the relationship, replaces the basis mapping of the framework</i>

The generated code is surrounded by a Try-Catch inserted with the aim of managing all the errors generated at runtime, such as NullPointerException.<br>
<br>
The conversions can be of two types: <b>static</b> and <b>dynamic</b>.<br>
the method is static when it is used in all the defined relationships, is dynamic when the method's body change according the relationship that must be satisfied.<br>
<br>
<h2>Annotation's general instructions</h2>

The annotation that allows us to perform explicit conversion is the <b>@JMapConversion</b>.<br>
JMapConversion has three parameters: from, to and type.<br>
<br>
<br>
<table><thead><th>Field</th><th>Description</th><th>default value</th></thead><tbody>
<tr><td>String<a href='.md'>.md</a> from</td><td>names of the source fields</td><td>ALL          </td></tr>
<tr><td>String<a href='.md'>.md</a> to</td><td>names of the destination fields</td><td>ALL          </td></tr>
<tr><td>Type type</td><td>conversion type</td><td>STATIC       </td></tr>
<tr><td>Boolean avoidSet (since <b>1.3.1</b>)</td><td>avoid set method usage</td><td>false        </td></tr></tbody></table>

The parameters from and to indicate which fields will be part of the conversion.<br>
The <b>from</b> indicates the source, the <b>to</b> indicates the recipient, the default value for each is ALL.<br>
ALL indicates that the conversion is to be applied to all related fields.<br>
The <b>Type</b> is an enumeration of @JMapConversion that allows you to define the conversion modes and has only two values: STATIC and DYNAMIC, the default value of type is STATIC. <b>avoidSet</b> parameter set to true avoids the call to the set method.<br>
Conversion modes are detailed in the following paragraphs.<br>
<br>
<h2>XML's general instructions</h2>

In the xml definition the conversion is defined using the <b><</b><b>conversion</b><b>></b> node, whose attributes are: name, from, to and type.<br>
<br>
<br>
<table><thead><th>Attribute</th><th>Description</th><th>default value</th></thead><tbody>
<tr><td>name     </td><td>method name</td><td>undefined    </td></tr>
<tr><td>from     </td><td>names of the source fields (separated by commas:"field","field2")</td><td>ALL          </td></tr>
<tr><td>to       </td><td>names of the destination fields(separated by commas:"field","field2")</td><td>ALL          </td></tr>
<tr><td>type     </td><td>conversion type</td><td>STATIC       </td></tr>
<tr><td>avoidSet (since <b>1.3.1</b>)</td><td>avoid set method usage</td><td>false        </td></tr></tbody></table>

The <b>name</b> attribute permits to define a name to the current conversion method, this attribute is <b>mandatory</b>.<br>
<br>
<b>IMPORTANT!</b> <i>make sure the name is unique for proper operation.</i>

The parameters from and to indicate which fields will be part of the conversion, the <b>from</b> indicates the source, the <b>to</b> indicates the recipient, the default value for each is ALL, ALL indicates that the conversion is to be applied to all related fields.<br>
the <b>Type</b> is an attribute that allows you to define the conversion modes, and has only two values: STATIC and DYNAMIC, the default value of type is STATIC.<br>
Conversion modes are detailed in the following paragraphs. <b>avoidSet</b> attribute set to true avoids the call to the set method.<br>
<br>
<b>IMPORTANT!</b> <i>The code must be written according the <a href='http://www.csg.ci.i.u-tokyo.ac.jp/~chiba/javassist/tutorial/tutorial2.html#limit'>javassist specifications</a>.</i>

<h2>Static conversion</h2>

A conversion is called static when its body does not change with the defined relations.<br>
Suppose we have two classes: Destination and Source, both with 3 fields of type String: d1, d2 and d3 for Destination and s1, s2, and s3 for Source and that you have configured s1 with d1, d2 with s2 and d3 with s3.<br>
We want to define a conversion to be applied to all relationships, then the method will be defined (eg in annotation):<br>
<pre><code>// you can obtain the same result writing @JMapConversion without parameters<br>
@JMapConversion(from={"s1,s2,s3"}, to={"d1,d2,d3"}, type=Type.STATIC)<br>
public String conversion(String source){<br>
   return source + " converted";<br>
}<br>
</code></pre>
Assuming that the method is defined in the Destination, the mapping generated at runtime will be as follows:<br>
<pre><code>destination.setD1(destination.conversion(source.getS1()));<br>
destination.setD2(destination.conversion(source.getS2()));<br>
destination.setD3(destination.conversion(source.getS3()));<br>
</code></pre>
This conversion is the most common and also the most useful, allows to pool a manipulation that can affect more data.<br>
In case there are any static methods with the same name, will be considered only the first one that meets the requirements.<br>
<br>
<h3>In annotation</h3>

Consists in the definition of the conversion method using the following conventions:<br>
<ul><li>the input field to the method must be the same type of the source fields<br>
</li><li>the output field to the method must be the same type of destination fields</li></ul>

Example:<br>
<pre><code>@JMapConversion(from={"anInteger"}, to={"aString"}, type=Type.STATIC)<br>
public String conversion(Integer anInteger){<br>
   return anInteger.toString();<br>
}<br>
</code></pre>
Assuming that the method is defined in the Destination, JMapper generate the following mapping:<br>
<pre><code>destination.setAString(destination.conversion(source.getAnInteger()));<br>
</code></pre>
If you want act even on the present value of the destination field, just add an input parameter, keeping in mind that the first field on the left is the destination and the second is the source:<br>
<pre><code>@JMapConversion(from={"anInteger"}, to={"aString"}, type=Type.STATIC)<br>
public String conversion(String aString, Integer anInteger){<br>
   if(aString == null) aString = "initialized";<br>
   return aString + anInteger;<br>
}<br>
</code></pre>
The generated mapping will be as follows:<br>
<pre><code>destination.setAString(destination.conversion(destination.getAString(),source.getAnInteger()));<br>
</code></pre>

<h3>In XML</h3>

Consists in the definition of the conversion node, whose value is the body of the method to create.<br>To refer to the source field and destination field you must use the following placeholders:<br>
<ul><li>${source} to refer to the source field<br>
</li><li>${destination} to refer to the destination field</li></ul>

Example:<br>
<pre><code>&lt;conversion name="conversion" from="anInteger" to="aString" type="STATIC"&gt;<br>
   return ${source}.toString();<br>
&lt;/conversion&gt;<br>
</code></pre>
Method created at runtime:<br>
<pre><code>public String conversion(Integer source){<br>
   return source.toString();<br>
}<br>
</code></pre>
Mapping generated:<br>
<pre><code>destination.setAString(conversion(source.getAnInteger()));<br>
</code></pre>
If you want to act even on the destination, just use the placeholder: ${destination}.<br>
<pre><code>&lt;conversion name="conversion" from="sourceField" to="destinationField"&gt;<br>
   return ${destination}+" "+${source}+" converted";<br>
&lt;/conversion&gt;<br>
</code></pre>
Method created at runtime:<br>
<pre><code>public String conversion(String destination, Integer source){<br>
   return destination +" "+ source +" converted";<br>
}<br>
</code></pre>
Mapping generated:<br>
<pre><code>destination.setAString(conversion(destination.getAString(),source.getAnInteger()));<br>
</code></pre>

<h2>Dynamic conversion</h2>

For dynamic conversion we means a method whose body is adapted to every relationship.<br>
The conversion method defined, both in java that in xml, is always one, but at runtime will be created a number of conversions equal to the number of relations.<br>
The dynamic conversion allows to pool the basic logic varying only some values.<br>The placeholders that can be used both in java that in xml are as follows:<br>
<br>
<table><thead><th>Placeholder</th><th>Description</th></thead><tbody>
<tr><td>${source}  </td><td>source reference</td></tr>
<tr><td>${destination}</td><td>destination reference</td></tr>
<tr><td>${source.type}</td><td>source type</td></tr>
<tr><td>${destination.type}</td><td>destination type</td></tr>
<tr><td>${source.name}</td><td>source name</td></tr>
<tr><td>${destination.name}</td><td>destination name</td></tr></tbody></table>

<b>These placeholders were introduced with 1.3.2 version</b>

<table><thead><th>${destination.get}</th><th>destination get method name</th></thead><tbody>
<tr><td>${destination.set}</td><td>destination set method name</td></tr>
<tr><td>${source.get}     </td><td>source get method name     </td></tr>
<tr><td>${source.set}     </td><td>source set method name     </td></tr></tbody></table>

annotation example:<br>
<pre><code>@JMapConversion(from={"s1,s2,s3"}, to={"d1,d2,d3"}, type=Type.DYNAMIC)<br>
public static String conversion(){<br>
   return "return \"${destination.name} ${source.name}\";";<br>
}<br>
</code></pre>
Method created at runtime:<br>
<pre><code>public String FROMs1TOd1(){<br>
   return "d1 s1";<br>
}<br>
<br>
public String FROMs2TOd2(){<br>
   return "d2 s2";<br>
}<br>
<br>
public String FROMs3TOd3(){<br>
   return "d3 s3";<br>
}<br>
</code></pre>
As you can see, the methods do not take input data because there is no reference to variables using the placeholder ${source} and ${destination}.<br>
JMapper generate the following mapping:<br>
<pre><code>destination.setD1(fromS1toD1());<br>
destination.setD2(fromS2toD2());<br>
destination.setD3(fromS3toD3());<br>
</code></pre>

<h3>In annotation</h3>

Consists in the definition of the conversion method using the following conventions:<br>
<ul><li>the method must be: public static<br>
</li><li>should not receive input parameters<br>
</li><li>must return a string containing the dynamic mapping</li></ul>

Example:<br>
<pre><code>@JMapConversion(from={"anInteger"}, to={"aString"}, type=Type.DYNAMIC)<br>
Public static String conversion(){<br>
 return "${destination.type} result = ${source} == 2?\"${source.name}\":\"${destination.name}\";"<br>
     +  "return result + ${destination};";<br>
}<br>
</code></pre>
Method created at runtime:<br>
<pre><code>public String FROManIntegerTOaString(String destination,Integer source){<br>
   String result = source == 2? "anInteger":"aString";<br>
   return result + destination;<br>
}<br>
</code></pre>
JMapper generate the following mapping:<br>
<pre><code>dest.setAString(fromAnIntegertoAString(dest.getAString(),source.getAnInteger()));<br>
</code></pre>

<h3>In XML</h3>

The placeholders can also be used in xml, the above example is repeated in xml format:<br>
<pre><code>&lt;conversion name="conversion" from="anInteger" to="aString" type="DYNAMIC"&gt;<br>
   ${destination.type} result = ${source} == 2?"${source.name}":"${destination.name}";<br>
   return result + ${destination};<br>
&lt;/conversion&gt;<br>
</code></pre>
Method created at runtime:<br>
<pre><code>public String FROManIntegerTOaString(String destination,Integer source){<br>
   String result = source == 2? "anInteger":"aString";<br>
   return result + destination;<br>
}<br>
</code></pre>
Mapping generated:<br>
<pre><code>dest.setAString(fromAnIntegertoAString(dest.getAString(),source.getAnInteger()));<br>
</code></pre>