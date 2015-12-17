# Introduction #

Since version <b>1.3.0</b> you can validate and use content assist for the XML configuration.<br />This is the xsd declaration:
```
<?xml version="1.0" encoding="UTF-8"?>
<jmapper
   xmlns="https://jmapper-framework.googlecode.com"
   xmlns:xsi="http://jmapper-framework.googlecode.com/svn"
   xsi:noNamespaceSchemaLocation="http://jmapper-framework.googlecode.com/svn/jmapper-1.3.1.xsd">
</jmapper>
```


# XML Schema #

```
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">

<!-- 
        jmapper:
                (class+)
 -->
        <xs:element name="jmapper">
                <xs:complexType>
                        <xs:sequence>
                                <xs:element name="class" type="jmapperClass" maxOccurs="unbounded"/>
                        </xs:sequence>
                </xs:complexType>
        </xs:element>
        
<!-- 
        class:
                name = string
                (global?,attribute+,conversion*)
 -->
        <xs:complexType name="jmapperClass">
                <xs:sequence>
                        <xs:element name="global" type="jmapperGlobal" minOccurs="0"/>
                        <xs:element name="attribute" type="jmapperAttribute" minOccurs="0" maxOccurs="unbounded"/>
                        <xs:element name="conversion" type="jmapperConversion" minOccurs="0" maxOccurs="unbounded" />
                </xs:sequence>
                <xs:attribute name="name" use="required" type="xs:string"/>
        </xs:complexType>

<!--
        conversion:
                name = string
                from = string
                to = string
                type = STATIC|DYNAMIC 
 -->
        <xs:complexType name="jmapperConversion" mixed="true">
                <xs:attribute name="name" use="required" type="xs:string"/>
                <xs:attribute name="from" use="optional"  type="xs:string" default="all"/>
                <xs:attribute name="to" use="optional" type="xs:string" default="all"/>
                <xs:attribute name="type" use="optional" default="STATIC">
                        <xs:simpleType>
                                <xs:restriction base="xs:string">
                                        <xs:enumeration value="STATIC"/>
                                        <xs:enumeration value="DYNAMIC"/>
                                </xs:restriction>
                        </xs:simpleType>
                </xs:attribute>
        </xs:complexType>
        
<!-- 
        attribute:
                name = string
                get = string
                set = string
                all(value?,attributes?,classes?)
 -->
        <xs:complexType name="jmapperAttribute">
                <xs:all>
                        <xs:element name="value" type="targetAttribute" minOccurs="0"/>
                        <xs:element name="attributes" type="jmapperTargetAttributes" minOccurs="0"/>
                        <xs:element name="classes" type="jmapperClasses" minOccurs="0"/>
                </xs:all>
                <xs:attribute name="name" use="required" type="xs:string"/>
                <xs:attribute name="get" use="optional" type="xs:string" default=""/>
                <xs:attribute name="set" use="optional" type="xs:string" default=""/>
        </xs:complexType>

<!-- 
        global:
                all(value?,attributes?,classes?,excluded?)
 -->
        <xs:complexType name="jmapperGlobal">
                <xs:all>
                        <xs:element name="value" type="targetAttribute"/>
                        <xs:element name="attributes" type="jmapperTargetAttributes" minOccurs="0"/>
                        <xs:element name="classes" type="jmapperClasses" minOccurs="0"/>
                        <xs:element name="excluded" type="jmapperExcluded" minOccurs="0"/>
                </xs:all>
        </xs:complexType>
        
<!-- 
        attributes:
                (attribute+)
 -->    
        <xs:complexType name="jmapperTargetAttributes">
                <xs:sequence>
                        <xs:element name="attribute" type="targetAttribute" maxOccurs="unbounded"/>
                </xs:sequence>
        </xs:complexType>
        
<!-- 
        classes:
                (class+)
-->     
        <xs:complexType name="jmapperClasses">
                <xs:sequence>
                        <xs:element name="class" type="onlyNameAttribute" maxOccurs="unbounded"/>
                </xs:sequence>
        </xs:complexType>

<!-- 
        excluded:
                (attribute+)
-->     
        <xs:complexType name="jmapperExcluded">
                <xs:sequence>
                        <xs:element name="attribute" type="onlyNameAttribute" maxOccurs="unbounded"/>
                </xs:sequence>
        </xs:complexType>
<!--  
    Definition of a target attribute with attributes: "name", "get" and "set"
 -->    
        <xs:complexType name="targetAttribute">
                <xs:attribute name="name" type="xs:string" use="required"/>
                <xs:attribute name="get" use="optional" type="xs:string" default=""/>
                <xs:attribute name="set" use="optional" type="xs:string" default=""/>
        </xs:complexType>
<!--  
    Definition of an element with only an attribute "name"
 -->    
        <xs:complexType name="onlyNameAttribute">
                <xs:attribute name="name" type="xs:string" use="required"/>
        </xs:complexType>
</xs:schema>
```