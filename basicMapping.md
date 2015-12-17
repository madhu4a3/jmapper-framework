# Basic Mapping #

We can define the configuration both in xml and annotations, both modes have the same structure.<br>
<b>IMPORTANT!</b> <i>With a basic mapping you can only define a 1 to 1 relation between fields.</i><br>
the class used as an example is the following:<br>
<pre><code>class MyClass {<br>
<br>
    private String field;<br>
<br>
    // getter and setter..<br>
<br>
}<br>
</code></pre>

The following will be exhibited various combinations allowed in both modes.<br>
<br>
<h2>Value</h2>

the parameter value permits to define the target field name.<br>
<br>
<h4>in Annotation</h4>

Value is the default parameter for the annotation that you may omit to write, if used alone.<br>
<pre><code>class MyClass {<br>
<br>
   @JMap("targetFieldName") // or @JMap(value = "targetFieldName")<br>
   private String field;<br>
   <br>
}<br>
</code></pre>

<h4>in Xml</h4>

The equivalent configuration in xml is:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.MyClass"&gt;<br>
      &lt;attribute name="field"&gt;<br>
         &lt;value name="targetFieldName"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>

<h2>Attributes</h2>

With the attributes parameter it's possible define more target fields.<br>
This is useful when you want associate a field with many other that belong to different classes.<br>
This configuration may lead to undesirable results in the case there are multiple matches between the identifiers declared in attributes and the fields of the target class, in this case JMapper considers valid the first field that respects the correspondence.<br>
This use is recommended only if you are certain of the uniqueness of the identifiers defined.<br>

<h4>in Annotation</h4>

<pre><code>class MyClass {<br>
<br>
   @JMap(attributes = {"firstField","secondField"})<br>
   private String field;<br>
   <br>
}<br>
</code></pre>
To better understand how attributes parameter works see the following example:<br>
<pre><code>class Destination {          class Source {<br>
<br>
   String dField3;              @JMap(attributes={"dField1","dField2","dField3"})<br>
   String dField2;              String sField1; <br>
   String dField1;<br>
<br>
   //getters and setters..      //getter and setter..<br>
}                            }<br>
</code></pre>
Code:<br>
<pre><code>Source source = new Source("sField1");<br>
<br>
JMapper mapper = new JMapper(Destination.class, Source.class);<br>
<br>
Destination destination = mapper.getDestination(source);<br>
<br>
System.out.println(destination);<br>
</code></pre>
Output:<br>
<pre><code>Destination[dField3=null, dField2=null, dField1="sField1"]<br>
</code></pre>
The first field of Destination that respects the correspondence is dField1.<br>
<br>
<h4>in Xml</h4>

The equivalent configuration in xml is:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.MyClass"&gt;<br>
      &lt;attribute name="field"&gt;<br>
         &lt;attributes&gt;<br>
             &lt;attribute name="firstField"/&gt;<br>
             &lt;attribute name="secondField"/&gt;<br>
         &lt;/attributes&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>

<h2>Classes</h2>

Classes is used when you want to limit the association to a defined set of classes, without value and attributes, you indicates to JMapper that the identifier of target field is the same of mapped field.<br>
<br>
<h4>in Annotation</h4>

<pre><code>class MyClass {<br>
<br>
   @JMap(classes = {Source1.class, Source2.class, Source3.class})<br>
   private String field;<br>
<br>
}<br>
</code></pre>
For example, we have the following classes:<br>
<pre><code>   class Target4 {             class Source{<br>
<br>
                                   @JMap(classes={Target1.class, Target2.class, Target3.class})<br>
       String field;               String field;<br>
<br>
       // getter and setter..      // getter and setter.. <br>
   }                           }<br>
</code></pre>
Code:<br>
<pre><code>new JMapper&lt;Target4, Source&gt;(Target4.class, Source.class);<br>
</code></pre>
Output:<br>
<pre><code>"JMapper AbsentRelationshipException: there isn't relationship between Target4 Class and Source Class"<br>
</code></pre>

<h4>in Xml</h4>

The equivalent configuration in xml is:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.MyClass"&gt;<br>
      &lt;attribute name="field"&gt;<br>
         &lt;classes&gt;<br>
             &lt;class name="package.Source1"/&gt;<br>
             &lt;class name="package.Source2"/&gt;<br>
             &lt;class name="package.Source3"/&gt;<br>
         &lt;/classes&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>

<h2>Value and Classes</h2>

This combination allows you to limit the association between the mapped field and the target field to a defined set of classes, declaring the target field name.<br>
<br>
<h4>in Annotation</h4>

<pre><code>class MyClass {<br>
<br>
   @JMap(value = "targetField", classes = {Source1.class, Source2.class, Source3.class})<br>
   private String field;<br>
<br>
}<br>
</code></pre>

<h4>in Xml</h4>

The equivalent configuration in xml is:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.MyClass"&gt;<br>
      &lt;attribute name="field"&gt;<br>
         &lt;value name="targetField"/&gt;<br>
         &lt;classes&gt;<br>
             &lt;class name="package.Source1"/&gt;<br>
             &lt;class name="package.Source2"/&gt;<br>
             &lt;class name="package.Source3"/&gt;<br>
         &lt;/classes&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>

<h2>Attributes and Classes</h2>

To clarify the association between the mapped field and other fields belonging to different classes, you can simply make a joint use of attributes and classes.<br>
<br>
<h4>in Annotation</h4>

<pre><code>class MyClass {<br>
<br>
   @JMap(attributes = {"field1","field2","field3"}, classes = {Source1.class, Source2.class, Source3.class})<br>
   private String field;<br>
<br>
}<br>
</code></pre>
In this specific example JMapper recognizes the association that exists between the mapped field with the field "field1" of the Source class, with the field "field2" of the Source2 class and with the field "field3" of the Source3 class.<br>
The equality of the mapped field and the target field can be specified with an empty string "". For example:<br>
<pre><code>@JMap(attributes = {"field1", "", "field3"}<br>
         classes = {Source1.class, Source2.class, Source3.class})<br>
</code></pre>

<h4>in Xml</h4>

The equivalent configuration in xml is:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.MyClass"&gt;<br>
      &lt;attribute name="field"&gt;<br>
         &lt;attributes&gt;<br>
             &lt;attribute name="field1"/&gt;<br>
             &lt;attribute name="field2"/&gt;<br>
	     &lt;attribute name="field3"/&gt;<br>
         &lt;/attributes&gt;<br>
         &lt;classes&gt;<br>
             &lt;class name="package.Source1"/&gt;<br>
             &lt;class name="package.Source2"/&gt;<br>
             &lt;class name="package.Source3"/&gt;<br>
         &lt;/classes&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>