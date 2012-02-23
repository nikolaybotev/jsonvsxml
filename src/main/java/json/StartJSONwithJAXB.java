package json;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import sample.SampleData;
import data.EcoSystem;

public class StartJSONwithJAXB {

  /**
   * Jackson does not handle object identity (as of 1.9.4). See
   * <a href="http://stackoverflow.com/questions/8351986/jackson-serialization-should-ignore-already-included-objects">this</a>
   * and
   * <a href="http://wiki.fasterxml.com/JacksonJAXBAnnotations">this</a>.
   * <p>
   * Jackson has a SAX-like streaming API core, a DOM-like Tree Model and a
   * JAXB-like annotation-based data (POJO) binding API. Look
   * <a href="http://wiki.fasterxml.com/JacksonInFiveMinutes">here</a>
   * for details.
   *
   * @param args
   * @throws IOException
   * @throws JsonMappingException
   * @throws JsonGenerationException
   */
  public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
    System.out.printf("Hello JSON!%n%n");

    // Initialize
    final ObjectMapper mapper = new ObjectMapper();

    // Register annotation introspector (JAXB only)
    final AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
    // Alternative introspector that supports both Jackson and JAXB annotations
    //final AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
    //final AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
    //final AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(primary, secondary);

    // make deserializer use JAXB annotations (only)
    mapper.setDeserializationConfig(mapper.getDeserializationConfig().withAnnotationIntrospector(introspector));
    // make serializer use JAXB annotations (only)
    mapper.setSerializationConfig(mapper.getSerializationConfig().withAnnotationIntrospector(introspector));

    final ObjectWriter w = mapper.writerWithDefaultPrettyPrinter();
    final ObjectReader r = mapper.reader(EcoSystem.class);

    // Create sample data
    final EcoSystem sample = SampleData.basicEcoSystem();

    // Print on screen
    System.out.println(mapper.writeValueAsString(sample));
    System.out.printf("%n%n");

    // Pretty-print on screen
    System.out.println(w.writeValueAsString(sample));

    // Serialize
    final File output1 = new File("sample-jaxb.json");
    w.writeValue(output1, sample);

    // Deserialize
    final EcoSystem unsample = r.readValue(output1);

    // Serialize again
    w.writeValue(new File("sample-jaxb2.json"), unsample);

    System.out.printf("%nDone%n");
}

}