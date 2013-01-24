package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

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
  public static void main(String[] args) throws JsonMappingException, IOException {
    System.out.printf("Hello JSON!%n%n");

    // Initialize
    final ObjectMapper mapper = new ObjectMapper();

    // Register annotation introspector (JAXB only)
    final AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
    // Alternative introspector that supports both Jackson and JAXB annotations
    //final AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
    //final AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
    //final AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(primary, secondary);

    // make deserializer and serializer use JAXB annotations (only)
    mapper.setAnnotationIntrospector(introspector);

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
