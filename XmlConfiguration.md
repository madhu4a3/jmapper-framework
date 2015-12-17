# XML format #

The annotations are easy to use, minimize setup time, but you must necessarily put hands to manipulate the code, for this reason, the framework also handles the configuration in XML format.

The xml follows the @JMap criteria: the combinations between value, attributes and classes are the same.
The xml configuration follows the pattern below:
```
                                                      1..*
                                          attributes ------ attribute
                                           /
                                     0..1 /
                                         /
           1..*         1..*            /  0..1            1..* 
  jmapper ------ class ------ attribute --------- classes ------ class
                      \                 \
                       \                 \
                   0..* \            0..1 \
                         \                 \
                      conversion          value
```
There's an example:
```
<jmapper>
  <class name='com.myapplication.bean.ExampleClass'>
    <attribute name='field1'>
      <value name='field1Class1'/>
      <attributes>
        <attribute name='field1Class1' />
        <attribute name='field1Class2' />
        <attribute name='field1Class3' />
      </attributes>
      <classes>
        <class name='com.myapplication.bean.Class1' />
        <class name='com.myapplication.bean.Class2' />
        <class name='com.myapplication.bean.Class3' />
      </classes>
    </attribute>
    <conversion name="firstConversion" from="field1" to="field1Class1" type="STATIC">
        return ${source};
    </conversion>
  </class>
</jmapper>
```
Each class can have many attributes, each of them reflects the annotation @JMap, infact they have a node with value name, a list of attributes and a list of classes, the name of the configured attribute is defined in the name.

If you want create this mapping file with utility methods, see the [XML handling](XmlHandling.md) section.<br>For more informations about conversion node see <a href='ExplicitConversions.md'>explicit conversions</a> section.<br>See also <a href='Introduction#JMapper_constructors.md'>JMapper constructors</a> paragraph.