# OneToMany method #

The method oneToMany belongs to RelationalJMapper class and it has several signatures that use the `NullPointerControl` and `MappingType` enumerations and always return the destination instance.
These signatures allow both creation and enrichment of the destination instance.

## Creation signatures ##

The methods described here, allow the creation of the destination instance.
It is necessary to declare the target class for the use of these methods.

```
relationalJMapper.oneToMany(Class1.class, source);
```
Default parameters:
|`NullPointerControl`|`SOURCE`|
|:-------------------|:-------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.oneToManyWithoutControl(Class1.class, source);
```
Default parameters:
|`NullPointerControl`|`NOT_ANY`|
|:-------------------|:--------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.oneToMany(Class1.class, source, mtSource);
```
Default parameters:
|`NullPointerControl`|`SOURCE`|
|:-------------------|:-------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`mtSource`|

```
relationalJMapper.oneToMany(Class1.class, source, nullPointerControl, mtSource);
```
Default parameters:
|`NullPointerControl`|`nullPointerControl`|
|:-------------------|:-------------------|
|Destination `MappingType`|`ALL_FIELDS`        |
|Source `MappingType`|`mtSource`          |

## Enrichment signatures ##

The methods described here, allow the enrichment of the destination instance.

**Do not need to declare the destination class**, because it's retrieved from the instance passed in input.

**IMPORTANT!** _the destination instance is directly manipulated, its return is just a convenience_

```
relationalJMapper.oneToMany(destination, source);
```
Default parameters:
|`NullPointerControl`|`ALL`|
|:-------------------|:----|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.oneToManyWithoutControl(destination, source);
```
Default parameters:
|`NullPointerControl`|`NOT_ANY`|
|:-------------------|:--------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.oneToMany(destination, source, mtDestination, mtSource);
```
Default parameters:
|`NullPointerControl`|`ALL`|
|:-------------------|:----|
|Destination `MappingType`|`mtDestination`|
|Source `MappingType`|`mtSource`|

```
relationalJMapper.oneToMany(destination, source, nullPointerControl, mtDestination, mtSource);
```
Default parameters:
|`NullPointerControl`|`nullPointerControl`|
|:-------------------|:-------------------|
|Destination `MappingType`|`mtDestination`     |
|Source `MappingType`|`mtSource`          |