# Global Mapping #

In some cases we have the need to map two or more fields toward the same field.<br>Currently the only mode to do this is configure each field creating much redundance.<br>To avoid it, will be introduced the global mapping.<br>
<br>
<h2>Annotation</h2>

The annotation to use is <code>@JGlobalMap</code> and must be applied to the class, it has the parameters:<br>
<br>
<ul><li><code>value</code>, it permits to define the target name<br>
</li><li><code>classes</code>, are the classes to which the field must belong<br>
</li><li><code>attributes</code>,are the fields of the current class that will be part of the mapping<br>
</li><li><code>excluded</code>, are the fields of the current class that will be excluded from mapping</li></ul>

<h3>1° example</h3>

With @JMap:<br>
<pre><code>class Bean {<br>
           <br>
   @JMap("properties") <br>
   String field1;<br>
   @JMap("properties") <br>
   String field2;<br>
               <br>
   String field3;<br>
               <br>
  //getters and setters..<br>
}<br>
</code></pre>
With @JGlobalMap:<br>
<pre><code>@JGlobalMap(value="properties",excluded={"field3"})<br>
// or @JGlobalMap(value="properties",attributes={"field1","field2"})<br>
class Bean {<br>
           <br>
   String field1;<br>
   String field2;<br>
   String field3;<br>
             <br>
   //getters and setters..<br>
}<br>
</code></pre>

<h3>2° example</h3>

With @JMap:<br>
<pre><code>class Bean {<br>
           <br>
   @JMap <br>
   String field1;<br>
   @JMap <br>
   String field2;<br>
   <br>
   String field3;<br>
               <br>
   //getters and setters..<br>
}<br>
</code></pre>
With @JGlobalMap:<br>
<pre><code>@JGlobalMap(excluded={"field3"})<br>
// or @JGlobalMap(attributes={"field1""field2"})<br>
class Bean {<br>
           <br>
    String field1;<br>
    String field2;<br>
    String field3;<br>
<br>
    //getters and setters..<br>
}<br>
</code></pre>

@JGlobalMap has greater visibility of @JMap, if a field is not configured with @JGlobalMap JMapper checks if it is configured with @JMap.<br>
<br>
For example:<br>
<pre><code>@JGlobalMap(excluded={"field3"})<br>
class Bean {<br>
           <br>
    String field1;<br>
    String field2;<br>
    @JMap("other")<br>
    String field3;<br>
               <br>
    //getters and setters..<br>
} <br>
</code></pre>

<h2>XML format</h2>

The tag to use is:<code>&lt;global&gt;</code>.<br>It has the same structure of <code>&lt;attribute&gt;</code> node, but without the attribute name and with a more node: <code>&lt;excluded&gt;</code>.<br>Considering the class written in advance, see the following examples.<br>
<br>
<h3>1° example</h3>

The local fields have the same target field names.<br>
<br>
With <code>&lt;attribute&gt;</code>:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name = "Bean"&gt;<br>
      &lt;attribute name = "field1"&gt;<br>
         &lt;value name = "field1"/&gt;<br>
      &lt;/attribute&gt;<br>
      &lt;attribute name = "field2"&gt;<br>
         &lt;value name = "field2"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
With <code>&lt;global&gt;</code>:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name = "Bean"&gt;<br>
      &lt;global&gt;<br>
         &lt;excluded&gt;<br>
            &lt;attribute name = "field3"/&gt;<br>
         &lt;/excluded&gt;<br>
      &lt;!-- or <br>
         &lt;attributes&gt;<br>
            &lt;attribute name = "field1"/&gt;<br>
            &lt;attribute name = "field2"/&gt;<br>
         &lt;/attributes&gt;<br>
      --&gt;<br>
      &lt;/global&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>

<h3>2° example</h3>

Explicit target field.<br>
<br>
With <code>&lt;attribute&gt;</code>:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name = "Bean"&gt;<br>
      &lt;attribute name = "field1"&gt;<br>
         &lt;value name = "properties"/&gt;<br>
      &lt;/attribute&gt;<br>
      &lt;attribute name = "field2"&gt;<br>
         &lt;value name = "properties"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
With <code>&lt;global&gt;</code>:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name = "Bean"&gt;<br>
      &lt;global&gt;<br>
         &lt;value name = "properties"/&gt;<br>
         &lt;excluded&gt;<br>
            &lt;attribute name = "field3"/&gt;<br>
         &lt;/excluded&gt;<br>
      &lt;!-- or <br>
         &lt;attributes&gt;<br>
            &lt;attribute name = "field1"/&gt;<br>
            &lt;attribute name = "field2"/&gt;<br>
         &lt;/attributes&gt;<br>
      --&gt;<br>
      &lt;/global&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
<code>&lt;global&gt;</code> has greater visibility of <code>&lt;attribute&gt;</code>, if a field is not configured with <code>&lt;global&gt;</code> JMapper checks if it is configured with <code>&lt;attribute&gt;</code>.<br>
<br>
For example:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name = "Bean"&gt;<br>
      &lt;global&gt;<br>
         &lt;value name = "properties"/&gt;<br>
            &lt;excluded&gt;<br>
               &lt;attribute name = "field3" /&gt;<br>
  	    &lt;/excluded&gt;<br>
      &lt;/global&gt;<br>
      &lt;attribute name = "field3"&gt;<br>
         &lt;value name = "other"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>