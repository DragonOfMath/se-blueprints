package Data;

import java.util.ArrayList;

public class Blueprint {
    public final String type = "BlueprintDefinition";
    public String subtype = "OreToIngot";
    public String displayName = "DisplayName_Item_Ingot";
    public String icon = "Textures\\GUI\\Icons\\Ingot\\ingot.dds";
    public ArrayList<Item> prerequisites = new ArrayList<>();
    public ArrayList<Item> results = new ArrayList<>();
    public float productionTime = 0.0f;
    
    public Blueprint() {}
    public Blueprint(String subtype, String displayName, String icon) {
        this.subtype     = subtype;
        this.displayName = displayName;
        this.icon        = icon;
    }
    public Blueprint(String subtype, String displayName, String icon, ArrayList<Item> prerequisites, ArrayList<Item> results, float productionTime) {
        this.subtype        = subtype;
        this.displayName    = displayName;
        this.icon           = icon;
        this.prerequisites  = prerequisites;
        this.results        = results;
        this.productionTime = productionTime;
    }
    public void addPrerequisite(Item item) {
        prerequisites.add(item);
    }
    public void addPrerequisite(String type, String subtype, float amount) {
        Item item = new Item(type, subtype, amount);
        prerequisites.add(item);
    }
    public void removePrerequisite(Item item) {
        prerequisites.remove(item);
    }
    public void removePrerequisite(int index) {
        prerequisites.remove(index);
    }
    public void addResult(Item item) {
        results.add(item);
    }
    public void addResult(String type, String subtype, float amount) {
        Item item = new Item(type, subtype, amount);
        results.add(item);
    }
    public void removeResult(Item item) {
        results.remove(item);
    }
    public void removeResult(int index) {
        results.remove(index);
    }
}
