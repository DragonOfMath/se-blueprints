package Data;

public class Item {
    public String type    = "Ingot";
    public String subtype = "Stone";
    public float  amount  = 1.0f;
    
    public Item() {}
    public Item(String subtype) {
        this.subtype = subtype;
    }
    public Item(String subtype, float amount) {
        this.subtype = subtype;
        this.amount  = amount;
    }
    public Item(String type, String subtype, float amount) {
        this.type    = type;
        this.subtype = subtype;
        this.amount  = amount;
    }
    @Override
    public String toString() {
        return String.format("%.2fx %s (%s)", this.amount, this.subtype, this.type);
    }
}
