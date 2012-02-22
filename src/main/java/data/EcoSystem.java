package data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EcoSystem {

  @XmlAttribute
  private final String name;

  @XmlElementWrapper
  @XmlElement(name = "item")
  private final List<Item> items;

  protected EcoSystem() {
    this(null, null);
  }

  public EcoSystem(String name, List<Item> items) {
    super();
    this.name = name;
    this.items = items;
  }

  public String getName() {
    return name;
  }

  public List<Item> getItems() {
    return items;
  }

}
