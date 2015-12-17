# How to flatten and encapsulate an infinite number of fields with few lines of code #

With **1.3.2** release it's possible to use get and set placeholders for destination and source, but what's the advantages of this new feature?<br>
The following is a quick example, i have two beans:<br>
<pre><code>public class Encapsulate {<br>
<br>
	private User user;<br>
	<br>
        public Encapsulate(){<br>
           user = new User();<br>
        }<br>
<br>
        // getter and setter..<br>
<br>
	public static class User {<br>
		<br>
		private String name;<br>
		private String surname;<br>
		private String address;<br>
		private String number;<br>
                <br>
                // getter and setter..<br>
        }<br>
}<br>
</code></pre>
And<br>
<pre><code>public class Flatten {<br>
<br>
	private String name;<br>
	private String surname;<br>
	private String address;<br>
	private String number;<br>
<br>
        //getter and setter..<br>
}<br>
</code></pre>
My purpose is to map Flatten in Encapsulate and viceversa.<br>
With any mapper you are obliged to define every relationship, in this case:<br>
<br>
user.name = name<br>
user.surname = surname<br>
etc..<br>
<br>
With JMapper all you need are two methods that define a mapping for each direction:<br>
<pre><code>@JGlobalMap("user")<br>
public class Flatten {<br>
<br>
	private String name;<br>
	private String surname;<br>
	private String address;<br>
	private String number;<br>
<br>
	@JMapConversion(from={"name","surname","address","number"},type=Type.DYNAMIC,avoidSet=true)<br>
	public static String fromDestConversion(){<br>
		return "${destination}.${source.set}(${source});";<br>
	}<br>
	<br>
	@JMapConversion(from={"user"},type=Type.DYNAMIC)<br>
	public static String fromSourceConversion(){<br>
		return "return ${source}.${destination.get}();";<br>
	}<br>
<br>
        //getter and setter..<br>
}<br>
</code></pre>
or in XML:<br>
<pre><code>&lt;?xml version="1.0" encoding="UTF-8"?&gt;<br>
&lt;jmapper xmlns="https://jmapper-framework.googlecode.com"<br>
	xmlns:xsi="https://jmapper-framework.googlecode.com/svn"<br>
	xsi:noNamespaceSchemaLocation="https://jmapper-framework.googlecode.com/svn/jmapper-1.3.1.xsd"&gt;<br>
<br>
	&lt;class name="package.Encapsulate"&gt;<br>
		&lt;global&gt;<br>
			&lt;value name="user"/&gt;<br>
		&lt;/global&gt;<br>
		&lt;attribute name="name" get="gName" set="sName"/&gt;<br>
		&lt;conversion name="fromFlatten" from="name,surname,address,number" type="DYNAMIC" avoidSet="true"&gt;<br>
		   ${destination}.${source.set}(${source});<br>
		&lt;/conversion&gt;<br>
		&lt;conversion name="fromEncapsulate" from="user" type="DYNAMIC"&gt;<br>
		   return ${source}.${destination.get}();<br>
		&lt;/conversion&gt;<br>
	&lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
This mapping suppose that:<br>
<blockquote>- from Flatten to Encapsulate the set method name is the same for both<br>
- from Encapsulate to Flatten the get method name is the same for both</blockquote>

<b>with these precautions you can add new fields without having to put hands to the configuration</b>.