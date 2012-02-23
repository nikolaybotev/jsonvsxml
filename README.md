# JSON vs JAXB

A simple example of JSON and JAXB usage. The following areas of interest are covered.

## Object Identity

Object identity matters. The sample object graph contains an object with more than one incoming reference (although no *directed* cycles).

JAXB can handle this using XmlID and XmlIDREF.

Jackson 1.x does not support object identity beyond the specific case of containment and instead serializes a new copy for each new reference to an existing object.

Jackson 2.0 adds support for object identity handling ([doc](http://wiki.fasterxml.com/JacksonFeatureObjectIdentity)) via the [@JsonIdentityInfo](http://wiki.fasterxml.com/JacksonAnnotations) annotation, which can use either an internal object identifier or an explicit user-defined field.

## Final Fields

Both JAXB and Jackson can deserialize final fields.

However, JAXB requires a no-args constructor and Jackson appears to require explicit configuration to be able to use custom constructors. This makes the use of final fields less desirable, because the no-args constructor must assign values to every final field either explicitly or by calling another constructor. When a class contains many fields this can be quite inconvenient. Private fields with getter only are OK though.  The no-args constructor can be private.

## Maps

JAXB does not support direct mapping of Java Collection Library maps (e.g. HashMap) to XML. 
See the JavaDoc for [XmlAdapter](http://docs.oracle.com/javase/6/docs/api/javax/xml/bind/annotation/adapters/XmlAdapter.html).
One way to map maps to XML with JAXB is to write a custom adapter class that converts a map to and from a collection of name/value tuples. 
See an example of this approach [here](http://www.mail-archive.com/cxf-user@incubator.apache.org/msg04723.html).

The first thing to consider when confronted with a map is if you can just do with a list of tuples directly in your class, eliminating the need for an adapter. KISS.

Jackson supports mapping of maps to JSON. See an example [here](http://www.mail-archive.com/cxf-user@incubator.apache.org/msg04723.html).

## Enums

Both JAXB and Jackson support automatic mapping of java enums and can customize the serialized values of enums via the
[XmlEnumValue](http://docs.oracle.com/javase/6/docs/api/index.html?javax/xml/bind/annotation/XmlEnumValue.html) annotation.

## Pretty-printing

Both JAXB and Jackson produce one-liner documents by default, and both support pretty-printing in a simple indented format. 
Jackson pretty-printing is very simple to configure; JAXB pretty-printing configuration is somewhat more involved 
(relying on poorly documented features of the default back-end implementation) but possible.

## Interoperability

Jackson can recognize and use many of the JAXB [annotations](http://wiki.fasterxml.com/JacksonJAXBAnnotations). See json.StartJSONwithJAXB for an example.
