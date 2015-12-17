# Xml handling #

As it may be compact, the configuration in XML format, there is always the possibility of error-prone writing it, to eliminate these risks and speed up its completion, the framework exposes the class **`XmlHandler`**, with whom you can create or modify the configuration file.

**IMPORTANT!** _XmlHandler handles the configuration but not the conversions_

## Constructors ##

The empty constructor defines a jmapper.xml file positioned at the application root: `new XmlHandler()`.<br>If exists an xml file that  you want to handle, pass it to the constructor, as follows: <code>new XmlHandler("jmapper.xml")</code>.<br>Each method invoked on its instance will modify the file.<br>
<br>
<b>IMPORTANT!</b> <i>In this case, you do not need to declare the full path but only the file name, JMapper finds it recursively.</i>

<h2>Methods</h2>

<code>XmlHandler</code> has a number of utility methods.<br>
Most of its methods use two javaBean: <b><code>Attribute</code></b> and <b><code>Global</code></b>:<br>
<pre><code>         class Attribute {                  class Global {<br>
<br>
               String name;                       String value;<br>
               String value;                      String[] attributes;<br>
               String[] attributes;               Class&lt;?&gt;[] classes;<br>
               Class&lt;?&gt;[] classes;                String[] excluded;<br>
    <br>
               // getters and setters...          // getters and setters...<br>
         }                                  }<br>
</code></pre>
The Attribute class reflects the parameters of the @JMap annotation plus a string: name, the name of the current attribute.<br>
The Global class reflects the parameters of the @JGlobalMap.<br>
<br>
<br>
<h3>addAttributes</h3>

addAttributes lets you to add one or more attributes in a class that is present in the xml configuration file.<br>
<pre><code>         String[] attributes = {"field2Class1","field2Class2","field2Class3"};<br>
         Class&lt;?&gt;[]  classes = {Class1.class,Class2.class,Class3.class};<br>
         Attribute attribute = new Attribute("field2", attributes, classes);<br>
<br>
         xmlHandler.addAttributes(MyClass.class, attribute);<br>
</code></pre>

<h3>deleteAttributes</h3>

deleteAttributes eliminates attributes related to an existing class specifying their names.<br>
<pre><code>         xmlHandler.deleteAttributes(MyClass.class, "field2");<br>
</code></pre>

<h3>addGlobal</h3>

addGlobal permits to add a global mapping in a class that is present in the xml configuration file.<br>
<pre><code>         String[] excluded = {"field1Class1","field1Class2","field1Class3"};<br>
	 Class&lt;?&gt;[]  globalClasses = {Class1.class,Class2.class,Class3.class};<br>
	 Global global = new Global("globalMapping", globalClasses, excluded);<br>
	<br>
         xmlHandler.addGlobal(MyClass.class, global);<br>
</code></pre>

<h3>deleteGlobal</h3>

deleteGlobal eliminates the global mapping related to an existing class specifying class name.<br>
<pre><code>         xmlHandler.deleteGlobal(MyClass.class);<br>
</code></pre>

<h3>overrideGlobal</h3>

overrideGlobal allows you to override an existing global mapping specifying the class name.<br>
<pre><code>         String[] excluded = {"field1Class1","field1Class2","field1Class3"};<br>
	 Class&lt;?&gt;[]  globalClasses = {Class1.class,Class2.class,Class3.class};<br>
	 Global global = new Global("globalMapping", globalClasses, excluded);<br>
	<br>
         xmlHandler.overrideGlobal(MyClass.class, global);<br>
</code></pre>


<h3>addClass</h3>

addClass allows you to add, into the file configuration, the class passed in input.<br>
There are many signatures of this method:<br>
<pre><code>         String[] attributes = {"field2Class1","field2Class2","field2Class3"};<br>
         Class&lt;?&gt;[]  classes = {Class1.class,Class2.class,Class3.class};<br>
         Attribute attribute = new Attribute("field2", attributes, classes);<br>
<br>
	 String[] excluded = {"field1Class1","field1Class2","field1Class3"};<br>
	 Class&lt;?&gt;[]  globalClasses = {Class1.class,Class2.class,Class3.class};<br>
	 Global global = new Global("globalMapping", globalClasses, excluded);<br>
		<br>
         xmlHandler.addClass(MyClass.class, attribute);<br>
	 // or<br>
         xmlHandler.addClass(MyClass.class, global);<br>
         // or<br>
         xmlHandler.addClass(MyClass.class, global, attribute);<br>
<br>
</code></pre>

<h3>deleteClass</h3>

Deletes a class present in the Xml file.<br>
<pre><code>         xmlHandler.deleteClass(MyClass.class);<br>
</code></pre>

<h3>overrideClass</h3>

Override the class replacing the actual configuration with a new one given as input.<br>
<pre><code>         String[] attributes = {"field2Class1","field2Class2","field2Class3"};<br>
         Class&lt;?&gt;[]  classes = {Class1.class,Class2.class,Class3.class};<br>
         Attribute attribute = new Attribute("field2", attributes, classes);<br>
<br>
	 String[] excluded = {"field1Class1","field1Class2","field1Class3"};<br>
	 Class&lt;?&gt;[]  globalClasses = {Class1.class,Class2.class,Class3.class};<br>
	 Global global = new Global("globalMapping", globalClasses, excluded);<br>
		<br>
         xmlHandler.overrideClass(MyClass.class, attribute);<br>
	 // or<br>
         xmlHandler.overrideClass(MyClass.class, global);<br>
         // or<br>
         xmlHandler.overrideClass(MyClass.class, global, attribute);<br>
</code></pre>

<h3>addAnnotatedClass</h3>

addAnnotatedClass allows you to add in the xml configuration file to one or more classes annotated passed as input to the method, not specifying any class the same will be done for all classes configured with annotation (<code>xmlHandler.addAnnotatedClass()</code>).<br>
<pre><code>         xmlHandler.addAnnotatedClass(AnnotatedClass.class);<br>
</code></pre>
<h3>addAnnotatedClassAll</h3>

Same logic as the previous method with the difference that the function will be effective even for the innerClass present.<br>
<br>
<h3>deleteAnnotatedClasses</h3>

deleteAnnotatedClasses eliminates from xml file all the annotated classes.<br>
<pre><code>         xmlHandler.deleteAnnotatedClasses();<br>
</code></pre>
<h3>overrideAnnotatedClass</h3>

overrideAnnotatedClass allows you to update the xml configuration starting from the annotation of the classes passed as input, not specifying any parameters the same will be done for all annotated classes (<code>xmlHandler.overrideAnnotatedClass()</code>).<br>
<pre><code>         xmlHandler.overrideAnnotatedClass(AnnotatedClass.class);<br>
</code></pre>

<h3>overrideAnnotatedClassAll</h3>

Same logic as the previous method with the difference that the function will be effective even for the inner classes present.<br>
<br>
<h3>fromXmlToAnnotation</h3>

This method allows to annotate a class starting from its xml configuration.<br>
Not defining any class will be executed the same for all the classes present in the xml configuration file (<code>xmlHandler.fromXmlToAnnotation()</code>).<br>
<pre><code>         xmlHandler.fromXmlToAnnotation(MyClass.class);<br>
</code></pre>
<h3>fromXmlToAnnotationAll</h3>
Same logic as the previous method with the difference that the function will be effective even for the innerClass present.<br>
<h3>cleanAnnotatedClass</h3>
This method eliminates the annotations from classes, without parameters will do the same for all annotated classes (<code>xmlHandler.cleanAnnotatedClass()</code>).<br>
<pre><code>         xmlHandler.cleanAnnotatedClass(AnnotatedClass.class);<br>
</code></pre>
<h3>cleanAnnotatedClassAll</h3>
Same logic as the previous method with the difference that the function will be effective even for the innerClass present.