JSON vs JAXB
===

A simple example of JSON and JAXB usage. The following areas of interest are covered.

Object Identity
---

Object identity matters. The sample object graph contains object with multiple references to them (although no loops).

JAXB can handle this using XmlID and XmlIDREF.

Jackson JSON cannot (as of 1.9.4) and instead serializes a new copy for each reference to the same object.

Final Fields
---

Both JAXB and Jackson can deserialize final fields.

However, JAXB requires a no-args constructor and Jackson appears to require explicit configuration to be able to use custom constructors. This makes the use of final fields less desirable, because the no-args constructor must assign values to every final field either explicitly or by calling another constructor. When a class contains many fields this can be quite inconvenient. Private fields with getter only are OK though.  The no-args constructor can be private.

Maps
---

JAXB does not support direct mapping of Java Collection Library maps (e.g. HashMap) to XML. See http://docs.oracle.com/javase/6/docs/api/javax/xml/bind/annotation/adapters/XmlAdapter.html
One way to map maps to XML with JAXB is to write a custom adapter class that converts a map to and from a collection of name/value tuples. See an example of this approach here: http://www.mail-archive.com/cxf-user@incubator.apache.org/msg04723.html
The first thing to consider when confronted with a map is if you can just do with a list of tuples directly in your class, eliminating the need for an adapter. KISS.

Jackson supports mapping of maps to JSON. See an example here: http://www.mail-archive.com/cxf-user@incubator.apache.org/msg04723.html

Enums
---

TODO