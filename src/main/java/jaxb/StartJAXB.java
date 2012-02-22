package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import sample.SampleData;
import data.EcoSystem;

/**
 * An excellent JAXB tutorial is available at
 * http://www.vogella.de/articles/JAXB/article.html
 *
 * @author Nikolay
 *
 */
public class StartJAXB {

  public static void main(String[] args) throws JAXBException, TransformerFactoryConfigurationError, TransformerException {
    System.out.printf("Hello JAXB!%n%n");

    // Initialize
    final JAXBContext context = JAXBContext.newInstance(EcoSystem.class);
    final Marshaller m = context.createMarshaller();
    final Unmarshaller u = context.createUnmarshaller();

    // Create sample data
    final EcoSystem sample = SampleData.basicEcoSystem();

    // Print on screen
    m.marshal(sample, System.out);
    System.out.printf("%n%n");

    // Pretty-print on screen
    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    final Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    final JAXBSource source = new JAXBSource(m, sample);
    final StreamResult result = new StreamResult(System.out);
    transformer.transform(source, result);

    // Serialize
    final File output1 = new File("sample.xml");
    m.marshal(sample, output1);

    // Deserialize
    final EcoSystem unsample = (EcoSystem) u.unmarshal(output1);

    // Serialize again
    m.marshal(unsample, new File("sample2.xml"));

    System.out.printf("%nDone%n");
  }

}
