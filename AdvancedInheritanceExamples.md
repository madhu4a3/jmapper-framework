# Inheritance #

With JMapper you can apply inheritance to configurations and conversions. The purpose is to share the configurations/conversions that are common between more beans.

## Configuration ##

When you have a complex hierarchy, where there are two or more level of inheritance, it is useful configure the common fields just one time.<br>
Annotation example:<br>
<pre><code>class Item {<br>
<br>
   @JMap String id;<br>
   @JMap String description;<br>
<br>
   // getters and setters..<br>
<br>
}<br>
<br>
class Eggs extends Item {         class Detergents extends Item {<br>
<br>
   @JMap Date expirationDate;        @JMap Integer cl;<br>
 <br>
   // getter and setter              // getter and setter    <br>
<br>
}                                 }<br>
</code></pre>
XML example:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.Item"&gt;<br>
      &lt;attribute name="id"&gt;<br>
         &lt;value name="id"/&gt;<br>
      &lt;/attribute&gt;<br>
      &lt;attribute name ="description"&gt;<br>
         &lt;value name="description"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
   &lt;class name="package.Eggs"&gt;<br>
      &lt;attribute name ="expirationDate"&gt;<br>
         &lt;value name="expirationDate"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
   &lt;class name="package.Detergents"&gt;<br>
      &lt;attribute name ="cl"&gt;<br>
         &lt;value name="cl"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
<h2>Conversion</h2>

The same logic can be applied for the conversions definition.<br>
Annotation example:<br>
<pre><code>class Item {<br>
<br>
   @JMap String id;<br>
   @JMap String description;<br>
<br>
   @JMapConversion(from="description")<br>
   public String conversion(String description){<br>
      return description + " information added";<br>
   }<br>
   // getters and setters..<br>
<br>
}<br>
<br>
class Eggs extends Item {         class Detergents extends Item {<br>
<br>
   @JMap Date expirationDate;        @JMap Integer cl;<br>
 <br>
                                     @JMapConversion(from="cl")<br>
                                     public String conversion(Integer cl){<br>
                                        if(cl &lt;=750) return "small package";<br>
                                        if(cl &gt;=1500) return "large package";<br>
                                        return "normal package";<br>
   }<br>
<br>
   // getter and setter              // getter and setter    <br>
<br>
}                                 }<br>
</code></pre>
XML example:<br>
<pre><code>&lt;jmapper&gt;<br>
   &lt;class name="package.Item"&gt;<br>
      &lt;attribute name="id"&gt;<br>
         &lt;value name="id"/&gt;<br>
      &lt;/attribute&gt;<br>
      &lt;attribute name ="description"&gt;<br>
         &lt;value name="description"/&gt;<br>
      &lt;/attribute&gt;<br>
      &lt;conversion name="fromDescription" from="description"&gt;<br>
         return ${source} + " information added";<br>
      &lt;/conversion&gt;<br>
   &lt;/class&gt;<br>
   &lt;class name="package.Eggs"&gt;<br>
      &lt;attribute name ="expirationDate"&gt;<br>
         &lt;value name="expirationDate"/&gt;<br>
      &lt;/attribute&gt;<br>
   &lt;/class&gt;<br>
   &lt;class name="package.Detergents"&gt;<br>
      &lt;attribute name ="cl"&gt;<br>
         &lt;value name="cl"/&gt;<br>
      &lt;/attribute&gt;<br>
      &lt;conversion name="fromCl" from="cl"&gt;<br>
         if(${source}&lt;=750) return "small package";<br>
         if(${source}&gt;=1500) return "large package";<br>
         return "normal package";<br>
      &lt;/conversion&gt;<br>
   &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>