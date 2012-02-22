package sample;

import java.util.Arrays;

import data.EcoSystem;
import data.Item;
import data.Property;

public class SampleData {

  public static EcoSystem basicEcoSystem() {
    final Item root = new Item("froot", null, Arrays.asList(new Property("color", true), new Property("joocyness", false)));
    final Item apple = new Item("apple", root, Arrays.asList(new Property("tartness", true)));
    final Item orange = new Item("orange", root, Arrays.asList(new Property("dryness", false)));
    final EcoSystem system = new EcoSystem("basic", Arrays.asList(root, apple, orange));
    return system;
  }

}
