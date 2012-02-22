package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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

  public static void main(String[] args) throws JAXBException {
    System.out.printf("Hello JAXB!%n%n");

    // Initialize
    final JAXBContext context = JAXBContext.newInstance(EcoSystem.class);
    final Marshaller m = context.createMarshaller();
    final Unmarshaller u = context.createUnmarshaller();

    // Create sample data
    final EcoSystem sample = SampleData.basicEcoSystem();

    // Print on screen
    m.marshal(sample, System.out);

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
