# Migrated to [GitHub](https://github.com/jmapper-framework/jmapper-core) #
<br>Please use it for all new <a href='https://github.com/jmapper-framework/jmapper-core/issues'>issue</a> reports, consultation of <a href='https://github.com/jmapper-framework/jmapper-core/wiki'>guidelines</a>, etc..<br>
<hr />
JMapper Framework is a java bean to java bean mapper, allows you to perform the passage of data dinamically with annotations and / or XML.<br>With JMapper you can:<br>
<br>
<ul><li>create and enrich target objects<br>
</li><li>apply a specific logic to the mapping<br>
</li><li>automatically manage the XML file<br>
</li><li>implement the 1 to N and N to 1 relationships<br>
</li><li>implement explicit conversions<br>
</li><li>apply inherited configurations</li></ul>

<h3>the most important feature is the ease of use</h3>

<b>Configuration</b>
<pre><code>class Destination{                      class Source{<br>
    @JMap<br>
    private String id;                      private String id;<br>
    @JMap("sourceField")                    <br>
    private String destinationField;        private String sourceField;<br>
    private String other;                   private String other;<br>
<br>
    // getters and setters...               // getters and setters...<br>
}                                       }<br>
</code></pre>
<b>Usage</b>
<pre><code>Source source = new Source("id", "sourceField", "other");<br>
<br>
JMapper&lt;Destination, Source&gt; mapper = new JMapper&lt;&gt;(Destination.class, Source.class);<br>
<br>
Destination destination = mapper.getDestination(source);<br>
</code></pre>
<b>Result</b>
<pre><code>destination ["id", "sourceField", null]<br>
</code></pre>
<b>With JMapper we have all the advantages of dynamic mapping with the performance of static code, with 0 memory consumption</b>
<br><b>Required java 1.5+</b>

<a href='http://code.google.com/p/jmapper-framework/'><img src='http://dozer.sourceforge.net/images/logos/maven-feather.png' /></a>