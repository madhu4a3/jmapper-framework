_since 1.3.0 version_
# Custom accessor methods #

It is not always possible to comply with the get and set prefixes, for this reason we have introduced this new important feature.<br>We have two possibility: with annotation @JMapAccessor and in XML with attributes get and set.<br>
<br>
<h2>Annotation</h2>

@JMapAccessor is possibile to use on field and on class.<br>It containts three parameters:<br>
<br>
<ul><li><b>name</b>: (Optional) the name of the field that has custom accessor methods, if you not define it, JMapper assumes that the custom methods refer to the field itself<br>
</li><li><b>get</b>: (Optional) Permits to define a custom get method<br>
</li><li><b>set</b>: (Optional) Permits to define a custom set method<br>
</li><li><b>classes</b> (since 1.3.1 version): (Optional) allows to limit the configuration to a range of classes<br>
<pre><code>Class Bean {<br>
<br>
   @JMapAccessor(get="SpecialGetField",set="SpecialSetField")<br>
   @JMap("targetField")<br>
   String field;<br>
<br>
   public String SpecialGetField(){<br>
      return field;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.field = field;<br>
   }<br>
<br>
}<br>
</code></pre></li></ul>

As you can see custom accessor methods are easy to use, in this case we want to define get/set methods for that field for this reason the name is optional, by default Jmapper associates custom methods to the field that declares it.<br>You can use @JMapAccessor to define custom methods for the opposite field, follow an example:<br>
<br>
<pre><code>Class DestBean {<br>
<br>
   @JMapAccessor(name="srcField",get="SpecialGetField",set="SpecialSetField")<br>
   @JMap("srcField")<br>
   String destField;<br>
<br>
}<br>
<br>
Class SrcBean {<br>
<br>
   String srcField;<br>
<br>
   public String SpecialGetField(){<br>
      return srcField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.srcField= field;<br>
   }<br>
}<br>
</code></pre>

You can do the two operations together, with @JMapAccessors:<br>
<br>
<pre><code>Class DestBean {<br>
<br>
   @JMapAccessors({<br>
      @JMapAccessor(name="srcField",get="SpecialGetField",set="SpecialSetField")<br>
      @JMapAccessor(get="SpecialGetField",set="SpecialSetField")<br>
   })<br>
   @JMap("srcField")<br>
   String destField;<br>
   <br>
   public String SpecialGetField(){<br>
      return destField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.destField= field;<br>
   }<br>
}<br>
<br>
Class SrcBean {<br>
<br>
   String srcField;<br>
<br>
   public String SpecialGetField(){<br>
      return srcField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.srcField= field;<br>
   }<br>
}<br>
</code></pre>

<blockquote><b>IMPORTANT!</b>   <i>The definition of custom accessor methods for the opposite field has less visibility of the same definition on the field itself.</i></blockquote>

Example:<br>
<pre><code>Class DestBean {<br>
<br>
   @JMapAccessors({<br>
      @JMapAccessor(name="srcField",get="UNREAD",set="SpecialSetField")<br>
      @JMapAccessor(get="SpecialGetField",set="SpecialSetField")<br>
   })<br>
   @JMap("srcField")<br>
   String destField;<br>
   <br>
   public String SpecialGetField(){<br>
      return destField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.destField= field;<br>
   }<br>
}<br>
<br>
Class SrcBean {<br>
<br>
   @JMapAccessor(get="SpecialGetField")<br>
   String srcField;<br>
<br>
   public String SpecialGetField(){<br>
      return srcField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.srcField= field;<br>
   }<br>
}<br>
</code></pre>

You can do the same things on class:<br>
<br>
<pre><code>   @JMapAccessors({<br>
      @JMapAccessor(name="srcField",get="UNREAD",set="SpecialSetField")<br>
      @JMapAccessor(name="destField",get="SpecialGetField",set="SpecialSetField")<br>
   })<br>
   Class DestBean {<br>
<br>
   @JMap("srcField")<br>
   String destField;<br>
   <br>
   public String SpecialGetField(){<br>
      return destField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.destField= field;<br>
   }<br>
}<br>
<br>
Class SrcBean {<br>
<br>
   @JMapAccessor(get="SpecialGetField")<br>
   String srcField;<br>
<br>
   public String SpecialGetField(){<br>
      return srcField;<br>
   }<br>
<br>
   public void SpecialSetField(String field){<br>
      this.srcField= field;<br>
   }<br>
}<br>
</code></pre>

<b>classes</b> parameter permits to avoid cases where target and mapped fields have the same name for example:<br>
<pre><code>public class Destination{<br>
        <br>
   @JMapAccessors({<br>
     @JMapAccessor(name="field",get="get",set="set", classes={Source.class}),<br>
     @JMapAccessor(name="field",get="get",set="set", classes={Destination.class})<br>
   })<br>
   private String field;<br>
}<br>
</code></pre>
<blockquote><b>IMPORTANT!</b>   <i>@JMapAccessor on class has major visibility of @JMapAccessor on field.</i></blockquote>

<h2>XML</h2>

In xml configuration you can add the get/set custom methods in the attribute node, both in the current attribute and in the target attribute:<br>
<br>
<pre><code>...<br>
&lt;class  name="destBean"&gt;<br>
   &lt;attribute name="dField" get="getDestField" set="setDestField"&gt;<br>
      &lt;value name="sField" get="UNREAD" set="setSrcField"/&gt;<br>
   &lt;/attribute&gt;<br>
&lt;/class&gt;<br>
&lt;class  name="srcBean"&gt;<br>
   &lt;attribute name="sField" get="getsField" /&gt;<br>
&lt;/class&gt;<br>
...<br>
</code></pre>

<blockquote><b>IMPORTANT!</b>   <i>The definition of custom accessor methods for the opposite field has less visibility of the same definition on the field itself.</i></blockquote>

You can do the same work with global node:<br>
<pre><code>...<br>
&lt;class  name="DestBean"&gt;<br>
   &lt;global&gt;<br>
      &lt;value name="sField" get="getSrcField" /&gt;<br>
      &lt;attributes&gt;<br>
         &lt;attribute name="dField" get="getDestField" set="setDestField"/&gt;<br>
      &lt;/attributes&gt;<br>
   &lt;/global&gt;<br>
   &lt;attribute name="dField" get="UNREAD" set="UNREAD"/&gt;<br>
&lt;/class&gt;<br>
...<br>
</code></pre>

<blockquote><b>IMPORTANT!</b>   <i>The custom methods definition on global node has major visibility of it on attribute node.</i>