# Relational mapping #

We will see now some very interesting examples of use, which makes the code more concise and elegant.

## Layered application ##

A classic example is a web application that consists of three layers: facade, business and data access.
Each layer has two bean, one of input and one of output.
```
             ____________       ____________       ____________
      PivotI |          | BoI   |          | DtoI  |          |
      ------>|  Facade  |------>| Business |------>|  Data    |
             |          |       |          |       |  Access  |
      PivotO |  Layer   | BoO   | Layer    | DtoO  |  Layer   |
      <------|          |<------|          |<------|          |
             |__________|       |__________|       |__________|
```
In an architecture of this type, for each service you need to implement four mapping: `PivotI -> BoI -> DtoI` and `DtoO -> BoO -> PivotO`.

With JMapper you need to configure only the two business objects with the surrounding objects.
<table><tr><td width='50%'>
<pre><code>   class PivotI{<br>
       String pivotIfield;<br>
   }<br>
<br>
   class BoI{              <br>
       @JMap(attributes={"pivotIfield", "dtoIfield"}<br>
             classes   ={PivotI.class,  DtoI.class}) <br>
       Integer boIfield; <br>
   }<br>
<br>
   class DtoI{<br>
       Integer dtoIfield;<br>
   }<br>
</code></pre>
</td><td width='50%'>
<pre><code>   class PivotO{<br>
       String pivotOfield;<br>
   }<br>
<br>
   class BoO{              <br>
       @JMap(attributes={"pivotOfield", "dtoOfield"}<br>
             classes   ={PivotO.class,  DtoO.class}) <br>
       Integer boOfield; <br>
   }<br>
<br>
   class DtoO{<br>
       Integer dtoOfield;<br>
   }<br>
</code></pre>
</td></tr></table>
below the sample code:
```
   class ExampleFacade{
       
       RelationalJMapper<BoI> rmI;
       RelationalJMapper<BoO> rmO;
       ExampleBusiness exampleBusiness;

       public PivotO executes(PivotI pivotI){
           
           // mapping
           BoI boI = rmI.manyToOne(pivotI);

           // logic
           BoO boO = exampleBusiness.executes(boI);

           // mapping
           PivotO pivotO = rmO.oneToMany(PivotO.class, boO);
           
           return pivotO;
       }      
   }
   
   class ExampleBusiness{

       RelationalJMapper<BoI> rmI;
       RelationalJMapper<BoO> rmO;
       ExampleDAO exampleDao;

       public BoO executes(BoI boI){
           
           // mapping
           DtoI dtoI = rmI.oneToMany(DtoI.class, boI);

           // logic
           DtoO dtoO = exampleDao.executes(dtoI);
           
           // mapping
           BoO boO = rmO.manyToOne(dtoO);

           return boO;
       }      
   }

   class ExampleDao{

       public DtoO executes(DtoI dtoI){
           // some operations
           return dtoO;
       }

   }
```
**Two Configurations and two instances of RelationalJMapper is all you need**.

## common logic for different objects ##

You may have the need to apply a common logic in most parts of the web application, using different objects as input.
```
              ____________    ___________    ___________
             |            |  |           |  |           |
             |  Service   |  | Service2  |  | Service3  |
             |____________|  |___________|  |___________|  
                       \           |          /
                ServiceI\      Service2I     / Service3I
                         \         |        /
                          V________V_______V
                          |                |  
                          |  CommonLogic   |         
                          |________________|
```
Standard solutions can be:
  * use **`instanceof`** to identify class type
  * pass, as input to the method, the values ​to handle

Any solution you can design there will be always the need to change the code for every change request. With JMapper becomes:

Input objects:
<table width='100%'><tr><td width='33%'>
<pre><code>class Service1I{<br>
<br>
   List&lt;String&gt; customersIds;<br>
<br>
   // getter and setter...<br>
}<br>
</code></pre>
</td><td width='33%'>
<pre><code>class Service2I{<br>
<br>
   Set&lt;Integer&gt; companyIds;<br>
<br>
   // getter and setter...<br>
}<br>
</code></pre>
</td><td width='33%'>
<pre><code>class Service3I{<br>
<br>
   TreeSet&lt;String&gt; supplierIds;<br>
<br>
   // getter and setter...<br>
}<br>
</code></pre>
</td></tr></table>
As you can see the fields have different structures, JMapper handles all implicitly.
<table width='100%'><tr><td width='33%'>
<pre><code><br>
<br>
class CommonLogicI{<br>
<br>
   List&lt;Integer&gt; ids;<br>
<br>
   // getter and setter...<br>
}<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</code></pre>
</td><td width='66%'>
<pre><code>Xml configuration:<br>
&lt;jmapper&gt;<br>
  &lt;class name="com.myapplication.CommonLogicI"&gt;<br>
    &lt;attribute name="ids"&gt;<br>
      &lt;attributes&gt;<br>
        &lt;attribute name="customersIds" /&gt;<br>
        &lt;attribute name="companyIds" /&gt;<br>
        &lt;attribute name="supplierIds" /&gt;<br>
      &lt;/attributes&gt;<br>
      &lt;classes&gt;<br>
        &lt;class name="com.myapplication.Service1I" /&gt;<br>
        &lt;class name="com.myapplication.Service2I" /&gt;<br>
        &lt;class name="com.myapplication.Service3I" /&gt;<br>
      &lt;/classes&gt;<br>
    &lt;/attribute&gt;<br>
  &lt;/class&gt;<br>
&lt;/jmapper&gt;<br>
</code></pre>
</td></tr></table>
service implementations:
<table width='100%'><tr><td width='33%'>
<pre><code>class Service1{<br>
<br>
   CommonLogic cl;<br>
    <br>
   public void executes(Service1I service1I){<br>
      cl.idControl(service1I);<br>
   }<br>
}<br>
</code></pre>
</td><td width='33%'>
<pre><code>class Service2{<br>
<br>
   CommonLogic cl;<br>
    <br>
   public void executes(Service2I service2I){<br>
      cl.idControl(service2I);<br>
   }<br>
}<br>
</code></pre>
</td><td width='33%'>
<pre><code>class Service3{<br>
<br>
   CommonLogic cl;<br>
    <br>
   public void executes(Service3I service3I){<br>
      cl.idControl(service3I);<br>
   }<br>
}<br>
</code></pre>
</td></tr></table>
CommonLogic implementation:
<table width='100%'><tr><td width='33%'>
<pre><code>class CommonLogic{<br>
    <br>
   RelationalJMapper&lt;CommonLogicI&gt; rm;<br>
<br>
   public void idControl(Object source){<br>
<br>
      // mapping<br>
      CommonLogicI clI=rm.manyToOne(source);<br>
<br>
      // logic<br>
      someOperations(clI);<br>
<br>
    }<br>
}<br>
</code></pre>
</td><td width='66%' align='center'>
<h4>The conversions between different structures are performed implicitly,<br>
<br>
including the conversion of their elements.</h4>
</td></tr></table>