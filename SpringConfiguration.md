# Spring configuration #

Example of JMapper configuration:
```
<bean id="jmapper" class="com.googlecode.jmapper.JMapper">
   <constructor-arg><value>bean.Destination</value></constructor-arg>
   <constructor-arg><value>bean.Source</value></constructor-arg>
   <constructor-arg><value>ChooseConfig.SOURCE</value></constructor-arg>
   <constructor-arg><value>com/myapplication/xml/jmapper.xml</value></constructor-arg>
</bean>
```
Example of RelationalJMapper configuration:
```
<bean id="relationalJmapper" class="com.googlecode.jmapper.RelationalJMapper">
   <constructor-arg><value>bean.ConfiguredBean</value></constructor-arg>
   <constructor-arg><value>com/myapplication/xml/jmapper.xml</value></constructor-arg>
</bean>
```