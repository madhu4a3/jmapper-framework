# Enumerations #

There are two enumerations that help the developer to simplify the work and they are: `NullPointerControl` and `MappingType`.<br>
This enumerations allow you to control instances in input and apply a logical mapping specification.<br>They are passed as input to <a href='getDestination.md'>getDestination</a>, <a href='manyToOne.md'>manyToOne</a> and <a href='oneToMany.md'>oneToMany</a> methods.<br>
<br>
<h2>NullPointerControl</h2>
This enumeration verifies that the objects passed in input are valued, if not, a null will be returned.<br>The values of <code>NullPointerControl</code> are:<br>
<table><thead><th><code>NullPointerControl.DESTINATION</code></th><th>only destination will be controlled</th></thead><tbody>
<tr><td><code>NullPointerControl.SOURCE</code>     </td><td>only source will be controlled     </td></tr>
<tr><td><code>NullPointerControl.ALL</code>        </td><td>both instances will be checked     </td></tr>
<tr><td><code>NullPointerControl.NOT_ANY</code>    </td><td>neither                            </td></tr></tbody></table>

<h2>MappingType</h2>
This enumeration allows you to define which mode of mapping to apply.<br>This feature is useful when you want to enrich destination.<br>MappingType can be used for both instances.<br>
<br>
<b>IMPORTANT!</b> <i>JMapper not apply these mapping types to primitive variables, because they can not be null</i>

The values of <code>MappingType </code> are:<br>
<br>
<table><thead><th><code>MappingType.ALL_FIELDS</code></th><th>all fields will be included in the mapping</th></thead><tbody>
<tr><td><code>MappingType.ONLY_VALUED_FIELDS</code></td><td>only valued fields will be included in the mapping</td></tr>
<tr><td><code>MappingType.ONLY_NULL_FIELDS</code></td><td>only null fields will be included in the mapping</td></tr></tbody></table>

the possible combinations are summarized in the following table:<br>
<br>
<pre><code>destinationField = "DESTINATION"<br>
sourceField = "SOURCE"<br>
</code></pre>

<table><thead><th>Destination MappingType</th><th>Source MappingType</th><th>Destination field</th></thead><tbody>
<tr><td><code>ALL_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ALL_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ALL_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr></tbody></table>

<pre><code>destinationField = null<br>
sourceField = "SOURCE"<br>
</code></pre>

<table><thead><th>Destination MappingType</th><th>Source MappingType</th><th>Destination field</th></thead><tbody>
<tr><td><code>ALL_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ALL_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"SOURCE"</code></td></tr>
<tr><td><code>ALL_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>null</code></td></tr></tbody></table>

<pre><code>destinationField = "DESTINATION"<br>
sourceField = null<br>
</code></pre>

<table><thead><th>Destination MappingType</th><th>Source MappingType</th><th>Destination field</th></thead><tbody>
<tr><td><code>ALL_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ALL_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ALL_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ONLY_VALUED_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr>
<tr><td><code>ALL_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_VALUED_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>null</code></td></tr>
<tr><td><code>ONLY_NULL_FIELDS</code></td><td><code>ONLY_NULL_FIELDS</code></td><td><code>"DESTINATION"</code></td></tr></tbody></table>

<h1>Examples</h1>

To better understand its use see the <a href='AdvancedEnumerationExamples.md'>enumeration usage</a> page.