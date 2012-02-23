package sample;

import java.util.Arrays;

import data.EcoSystem;
import data.Item;
import data.Property;
import data.Property.Mood;

public class SampleData {

  public static EcoSystem basicEcoSystem() {
    final Item root = new Item("froot", null, Arrays.asList(new Property("color", true, Mood.HAPPY), new Property("joocyness", false, Mood.SAD)));
    final Item apple = new Item("apple", root, Arrays.asList(new Property("tartness", true, Mood.ANGRY)));
    final Item orange = new Item("orange", root, Arrays.asList(new Property("dryness", false, null)));
    final EcoSystem system = new EcoSystem("basic", Arrays.asList(root, apple, orange));
    return system;
  }

}
