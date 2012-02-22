package data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

public class Item {

  @XmlAttribute @XmlID
  private final String name;

  @XmlAttribute @XmlIDREF
  private final Item parent;

  @XmlElementWrapper
  @XmlElement(name = "property")
  private final List<Property> properties;

  protected Item() {
    this(null, null, null);
  }

  public Item(String name, Item parent, List<Property> properties) {
    super();
    this.name = name;
    this.parent = parent;
    this.properties = properties;
  }

  public String getName() {
    return name;
  }

  public Item getParent() {
    return parent;
  }

  public List<Property> getProperties() {
    return properties;
  }

}
