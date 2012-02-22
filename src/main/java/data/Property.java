package data;

import javax.xml.bind.annotation.XmlAttribute;

public class Property {

  @XmlAttribute
  private final String name;

  @XmlAttribute
  private final boolean required;

  protected Property() {
    this(null, false);
  }

  public Property(String name, boolean required) {
    super();
    this.name = name;
    this.required = required;
  }

  public String getName() {
    return name;
  }

  public boolean isRequired() {
    return required;
  }

}
