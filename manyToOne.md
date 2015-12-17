# ManyToOne method #

The method manyToOne belongs to RelationalJMapper class and it has several signatures that use the `NullPointerControl` and `MappingType` enumerations and always return the destination instance.
These signatures allow both creation and enrichment of the destination instance.

## Creation signatures ##

The methods described here, allow the creation of the destination instance.

```
relationalJMapper.manyToOne(source);
```
Default parameters:
|`NullPointerControl`|`SOURCE`|
|:-------------------|:-------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.manyToOneWithoutControl(source);
```
Default parameters:
|`NullPointerControl`|`NOT_ANY`|
|:-------------------|:--------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.manyToOne(source, mtSource);
```
Default parameters:
|`NullPointerControl`|`SOURCE`|
|:-------------------|:-------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`mtSource`|

```
relationalJMapper.manyToOne(source, nullPointerControl, mtSource);
```
Default parameters:
|`NullPointerControl`|`nullPointerControl`|
|:-------------------|:-------------------|
|Destination `MappingType`|`ALL_FIELDS`        |
|Source `MappingType`|`mtSource`          |

## Enrichment signatures ##

The methods described here, allow the enrichment of the destination instance.

**IMPORTANT!** _the destination instance is directly manipulated, its return is just a convenience_

```
relationalJMapper.manyToOne(destination, source);
```
Default parameters:
|`NullPointerControl`|`ALL`|
|:-------------------|:----|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.manyToOneWithoutControl(destination, source);
```
Default parameters:
|`NullPointerControl`|`NOT_ANY`|
|:-------------------|:--------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
relationalJMapper.manyToOne(destination, source, mtDestination, mtSource);
```
Default parameters:
|`NullPointerControl`|`ALL`|
|:-------------------|:----|
|Destination `MappingType`|`mtDestination`|
|Source `MappingType`|`mtSource`|

```
relationalJMapper.manyToOne(destination, source, nullPointerControl, mtDestination, mtSource);
```
Default parameters:
|`NullPointerControl`|`nullPointerControl`|
|:-------------------|:-------------------|
|Destination `MappingType`|`mtDestination`     |
|Source `MappingType`|`mtSource`          |