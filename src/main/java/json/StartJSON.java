package json;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import sample.SampleData;
import data.EcoSystem;

public class StartJSON {

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

    // Example mapper configuration options
    mapper.setSerializationConfig(mapper.getSerializationConfig()
        // Sort properties for consistent order in the JSON file
        .with(Feature.SORT_PROPERTIES_ALPHABETICALLY)
        // Include private fields when scanning for properties
        .withVisibility(JsonMethod.FIELD, Visibility.ANY));

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
    final File output1 = new File("sample.json");
    w.writeValue(output1, sample);

    // Deserialize
    final EcoSystem unsample = r.readValue(output1);

    // Serialize again
    w.writeValue(new File("sample2.json"), unsample);

    System.out.printf("%nDone%n");
}

}
