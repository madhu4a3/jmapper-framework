# Implicit conversions #

JMapper allows to perform implicitly mapping between primitive and / or wrapper types, structures, mapped and target objects.<br>Below are represented the conventions used to handle all those <b>conversions</b> that are <b>uncommon</b>:<br>
<table><thead><th><b>Conversion</b></th><th><b>Result</b></th></thead><tbody>
<tr><td>Boolean -> Number</td><td>1 if Boolean is true, 0 if false</td></tr>
<tr><td>Boolean -> Character/char</td><td>T if Boolean is true, F if false</td></tr>
<tr><td>String -> Character/char</td><td>is taken the first character</td></tr>
<tr><td>Number -> boolean</td><td>false if Number is equal to 0, true all other cases</td></tr>
<tr><td>Character/char -> Boolean</td><td>true if Char is equal to T, false if Char is equal to F, null all other cases</td></tr>
<tr><td>Character/char -> boolean</td><td>true if Char is equal to T, false all other cases</td></tr></tbody></table>

JMapper converts the arrays, collections and maps including their objects, for example (<code>&lt;--&gt;</code> means bidirectional):<br>
<ul><li>from interface to implementation and vice versa<br>
<blockquote><code>List &lt;--&gt; ArrayList</code><br>
<code>MyInterface &lt;--&gt; MyImplementation </code>
</blockquote></li><li>between different interfaces<br>
<blockquote><code>List &lt;--&gt; Set</code><br>
<code>Map &lt;--&gt; SortedMap</code>
</blockquote></li><li>between different implementations<br>
<blockquote><code>ArrayList &lt;--&gt; HashSet</code><br>
<code>HashMap &lt;--&gt; TreeMap</code>
</blockquote></li><li>a combination of the two previous<br>
<blockquote><code>List &lt;--&gt; HashSet</code><br>
<code>HashMap &lt;--&gt; SortedMap</code>
</blockquote></li><li>conversion of primitive/wrapper items<br>
<blockquote><code>List&lt;String&gt; &lt;--&gt; HashSet&lt;Integer&gt;</code><br>
<code>HashMap&lt;Integer, String&gt; &lt;--&gt; SortedMap&lt;String, Integer&gt;</code>
</blockquote></li><li>conversion of mapped items<br>
<blockquote><code>List&lt;TargetObj&gt; &lt;--&gt; HashSet&lt;ConfigObj&gt;</code><br>
<code>HashMap&lt;Integer, ConfigObj&gt; &lt;--&gt; SortedMap&lt;String, TargetObj&gt;</code>
</blockquote></li><li>conversion of primitive/wrapper arrays<br>
<blockquote><code>String[] &lt;--&gt; Integer[]</code>
</blockquote></li><li>conversion of mapped arrays<br>
<blockquote><code>TargetObj[] &lt;--&gt; ConfigObj[]</code></blockquote></li></ul>

<ul><li>conversion between arrays and collections (since 1.1.1 version):<br>
<ul><li>structural<br>
<blockquote><code>String[] &lt;--&gt; List&lt;String&gt;</code>
</blockquote></li><li>with different primitive/wrapper types<br>
<blockquote><code>String[] &lt;--&gt; List&lt;Integer&gt;</code>
</blockquote></li><li>with configured items<br>
<blockquote><code>TargetObj[] &lt;--&gt; List&lt;ConfigObj&gt;</code></blockquote></li></ul></li></ul>
