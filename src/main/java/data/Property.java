package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnumValue;

public class Property {

  public enum Mood {
    @XmlEnumValue(":-)") HAPPY,
    ANGRY,
    SAD;
  }

  @XmlAttribute
  private final String name;

  /**
   * XmlAttribute does not allow a default value to be specified.
   * <p>
   * XmlElement can have a default value, but this does not prevent
   * an XML element from being generated when serializing, even if 
   * the object's property contains the default value. 
   */
  @XmlElement(defaultValue = "false")
  private final boolean required;

  @XmlAttribute
  private final Mood mood;

  @SuppressWarnings("unused")
  private Property() {
    this(null, false, null);
  }

  public Property(String name, boolean required, Mood mood) {
    super();
    this.name = name;
    this.required = required;
    this.mood = mood;
  }

  public String getName() {
    return name;
  }

  public boolean isRequired() {
    return required;
  }

  public Mood getMood() {
    return mood;
  }

}
