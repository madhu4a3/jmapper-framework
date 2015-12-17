# Enumerations usage #

The following examples show the usage of the enumerations: NullPointerControl and MappingType.<br>
The classes used are:<br>
<pre><code>class Destination {              class Source {<br>
<br>
   @JMap String name;               String name;<br>
   @JMap Integer age;               Integer age;<br>
   @JMap String company;            String company;<br>
<br>
   // getters and setters..         // getters and setters..<br>
<br>
}                                }<br>
</code></pre>
And the mapper used is created as follows:<br>
<pre><code>JMapper&lt;Destination, Source&gt; mapper = new JMapper&lt;Destination, Source&gt;(Destination.class, Source.class);<br>
</code></pre>

<h2>MappingType</h2>

In this example you will see a case where you need to take only the valued fields of Source:<br>
<pre><code>Destination destination = new Destination("empty", null, "Google");<br>
Source source = new Source("Alessandro", 27, null);<br>
<br>
Destination result = mapper.getDestination(destination, source, MappingType.ALL_FIELDS, MappingType.ONLY_VALUED_FIELDS);<br>
</code></pre>
result:<br>
<pre><code>Destination[ name="Alessandro", age=27, company="Google"]<br>
</code></pre>
If you want just fill null fields of Destination:<br>
<pre><code>Destination destination = new Destination("empty", null, "Google");<br>
Source source = new Source("Alessandro", 27, null);<br>
<br>
Destination result = mapper.getDestination(destination, source, MappingType.ONLY_NULL_FIELDS, MappingType.ALL_FIELDS);<br>
</code></pre>
result:<br>
<pre><code>Destination[ name="empty", age=27, company="Google"]<br>
</code></pre>
the possible combinations of mapping are many, try yourself to explore the other combinations.<br>
For more information see the <a href='Enumerations.md'>enumerations</a> section.<br>
<br>
<h2>NullPointerControl</h2>

You can check the source using NullPointerControl enumeration.<br>
This enumeration avoids an explicit control, as shows by the following example:<br>
<pre><code>Source source = null;<br>
<br>
Destination destination = mapper.getDestination(source, NullPointerControl.SOURCE, MappingType.ALL_FIELDS);<br>
<br>
assertNull(destination);<br>
</code></pre>
No error is launched, the mapping isn't executed, a null is returned instead.<br>
For more information see the <a href='Enumerations.md'>enumerations</a> section.