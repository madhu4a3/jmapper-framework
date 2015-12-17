# Overview #

Currently, there are many frameworks that significantly reduce the workload, leading mainly two benefits: **robustness** and **productivity**.<br>Among the most important we can mention Hibernate, Spring, Struts, and other more recent as Vaadin, Spring Roo, Grails ..., thanks to them the mechanical work is reduced to a minimum.<br><br>However, none of these automates a common feature present in all J2EE applications, the <b>mapping of data</b>.<br><br>The mapping is a mechanical operation almost 100%, in medium-large applications, between development, testing and bug fixing, it reaches a weight that is certainly not negligible.<br>
As the rule may seem elementary, enclose in a single framework a potentially unlimited number of combinations, recursive mapping, addition of logic and other features, makes its implementation very complex with questionable compromises, such as poor performance, covering a limited features, etc... .<br>
<br>
JMapper aims not only to coverage all requirements but also to make it as easy as possible to use, that's all with performance equivalent or higher to static mapping.<br>
<br>
JMapper allows:<br>
<ul><li>to create and enrich target objects<br>
</li><li>to apply a <a href='Enumerations.md'>specific logic</a> to the mapping<br>
</li><li>to perform complex mapping (Collection, Map, Array, inner classes, etc...)<br>
</li><li>to perform recursive mapping (mapped objects into other objects)<br>
</li><li>to manage <a href='ImplicitRelations.md'>implicit</a> and <a href='ExplicitRelations.md'>explicit</a> relationships of various types:<br>
<ul><li>one to one<br>
</li><li>one to many<br>
</li><li>many to one<br>
</li></ul></li><li>to implement <a href='ExplicitConversions.md'>explicit conversions</a>
</li><li>to configure the mapping using the annotation and / or xml<br>
</li><li>to apply <a href='AdvancedInheritanceExamples.md'>inherited configurations</a>
</li><li>to write quick and simple xml configuration file, through a series of <a href='XmlHandling.md'>utility methods</a></li></ul>

<h1>Preface</h1>

Before describing the features of the framework is good to do a little introduction: the <b>Destination</b> is the instance that captures the data, the <b>Source</b> is the instance that contains the data, the goal is to get the Destination starting from the Source.<br>
The configurations are associated with the instance variables and may be present in the Destination and Source, then we will call <b>target field</b> the target field of the configuration and <b>mapped field</b> the field that contains the configuration.<br>
As a prerequisite classes used for mapping must comply with the <a href='http://en.wikipedia.org/wiki/JavaBean'>Javabean conventions</a>.<br>
<br>
<blockquote>Since <b>1.3.0</b> version it's possible define get and set custom methods.<br> For more info go to <a href='CustomAccessors.md'>wiki</a> page.</blockquote>

Below shows the classes that will be used in the following examples:<br>
<pre><code>class Destination{                      class Source{<br>
<br>
    private String id;                      private String id;<br>
    private String destinationField;        private String sourceField;<br>
    private String other;                   private String other;<br>
<br>
    // getters and setters...               // getters and setters...<br>
}                                       }<br>
</code></pre>
As mentioned in the introduction, JMapper allows you to configure the mapping using both annotations and XML. In the next chapter we will learn to configure classes with annotation and especially with <code>@JMap</code>.<br>
<br>
<b>IMPORTANT!</b> <i>the XML configuration has greater visibility of the equivalent annotation</i>

<h1>Annotated configuration</h1>

The annotation used to configure the single field is <code>@JMap</code>, it has several parameters, including: value.<br>
Value is the default parameter of the annotations and therefore isn't necessary to explain it if used individually.<br>
<pre><code>class Destination{                      <br>
    <br>
    /* implicit mapping when mapped field name and target field name match */<br>
    @JMap <br>
    private String id;                      <br>
    /* explicit mapping when mapped field name and target field name don't match */<br>
    @JMap("sourceField") <br>
    private String destinationField;       <br>
    private String other;                  <br>
<br>
    // getters and setters...                <br>
}                                     <br>
</code></pre>

<h1>XML configuration</h1>

this is the xml configuration equivalent to the previous one written with annotation:<br>
<pre><code>&lt;jmapper&gt;<br>
  &lt;class name="it.jmapper.bean.Destination"&gt;<br>
    &lt;attribute name="id"&gt;<br>
      &lt;value name="id"/&gt;<br>
    &lt;/attribute&gt;<br>
    &lt;attribute name="destinationField"&gt;<br>
      &lt;value name="sourceField"/&gt;<br>
    &lt;/attribute&gt;<br>
  &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
For writing automated XML, read the <a href='XmlHandling.md'>XML handling</a> section.<br>
<br>
<h1>Example</h1>

Once set up, we go immediately to a practical example:<br>
<pre><code>Source source = new Source("id", "sourceField", "other");<br>
<br>
JMapper &lt;Destination, Souce&gt; jmapper = new JMapper &lt;Destination, Source&gt;(Destination.class, Source.class);<br>
<br>
Destination destination = jmapper.getDestination(source);<br>
<br>
System.out.println(destination);<br>
</code></pre>
The class to use is JMapper, takes as input two classes: the first from the left, belonging to the Destination and the second to Source, then just invoke the method <a href='getDestination.md'>getDestination</a> passing an instance of Source to create an instance of Destination.<br>
The output below shows the fields of the destination bean.<br>
<pre><code>Destination [id="id", destinationField="source field", other=null]<br>
</code></pre>

<b>IMPORTANT!</b> <i>the configuration can be written in both classes, the result is the same</i>

<h1>JMapper constructors</h1>

If both classes are configured, the framework will consider the configuration of the Destination Class. If you want to define which configuration to be analyze, simply pass to the constructor the <code>ChooseConfig</code> enumeration.<br>
<pre><code>new JMapper &lt;Destination, Source&gt;(Destination.class, Source.class, ChooseConfig.SOURCE);<br>
</code></pre>
<table><thead><th><code>ChooseConfig.SOURCE</code></th><th>it evaluates the configuration of Source</th></thead><tbody>
<tr><td><code>ChooseConfig.DESTINATION</code></td><td>it evaluates the configuration of Destination</td></tr></tbody></table>

if you wrote the xml, you have to pass the path to the constructor, as below:<br>
<pre><code>new JMapper &lt;Destination, Source&gt;(Destination.class, Source.class, "xml/jmapper.xml");<br>
</code></pre>
<b>Remember</b> <i>the xml file must be accessible from your classpath at runtime. If you want define an external path, you need to add this prefix:</i> <code>"file:"</code> <i>for example:</i><br><b>Windows</b> <code>"file:/C:/path/jmapper.xml"</code><br><b>Unix</b> <code>"file:/Users/path/jmapper.xml"</code>

Below the constructor complete with all parameters:<br>
<pre><code>new JMapper &lt;Destination, Source&gt;(Destination.class, Source.class, ChooseConfig.SOURCE, "xml/jmapper.xml");<br>
</code></pre>