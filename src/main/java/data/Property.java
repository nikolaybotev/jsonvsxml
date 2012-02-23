package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnumValue;

public class Property {

  public enum Mood {
    @XmlEnumValue(":-)") HAPPY,
    ANGRY,
    SAD;
  }

  @XmlAttribute
  private final String name;

  @XmlAttribute
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
