# GetDestination method #

The method getDestination belongs to JMapper class and it has several signatures that use the `NullPointerControl` and `MappingType` enumerations and always return the destination instance.
These signatures allow both creation and enrichment of the destination instance.

## Creation signatures ##

The methods described here, allow the creation of the destination instance.
```
jmapper.getDestination(source);
```
Default parameters:
|`NullPointerControl`|`SOURCE`|
|:-------------------|:-------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
jmapper.getDestinationWithoutControl(source);
```
Default parameters:
|`NullPointerControl`|`NOT_ANY`|
|:-------------------|:--------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
jmapper.getDestination(source, mtSource);
```
Default parameters:
|`NullPointerControl`|`SOURCE`|
|:-------------------|:-------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`mtSource`|

```
jmapper.getDestination(source, nullPointerControl, mtSource);
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
jmapper.getDestination(destination, source);
```
Default parameters:
|`NullPointerControl`|`ALL`|
|:-------------------|:----|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
jmapper.getDestinationWithoutControl(destination, source);
```
Default parameters:
|`NullPointerControl`|`NOT_ANY`|
|:-------------------|:--------|
|Destination `MappingType`|`ALL_FIELDS`|
|Source `MappingType`|`ALL_FIELDS`|

```
jmapper.getDestination(destination, source, mtDestination, mtSource);
```
Default parameters:
|`NullPointerControl`|`ALL`|
|:-------------------|:----|
|Destination `MappingType`|`mtDestination`|
|Source `MappingType`|`mtSource`|

```
jmapper.getDestination(destination, source, nullPointerControl, mtDestination, mtSource);
```
Default parameters:
|`NullPointerControl`|`nullPointerControl`|
|:-------------------|:-------------------|
|Destination `MappingType`|`mtDestination`     |
|Source `MappingType`|`mtSource`          |