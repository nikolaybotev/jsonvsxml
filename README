A simple example of JSON and JAXB usage.

Object identity matters. The sample object graph contains object with multiple references to them (although no loops).

JAXB can handle this using XmlID and XmlIDREF.
Jackson JSON cannot (as of 1.9.4) and instead serializes a new copy for each reference to the same object.

Both JAXB and Jackson can deserialize final fields.

However, JAXB requires a no-args constructor and Jackson appears to require explicit configuration to be able to use custom construtors. This makes the use of final fields less desirable. Private fields with getter only are OK though. 