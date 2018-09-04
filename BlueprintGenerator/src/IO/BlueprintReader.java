package IO;

import javax.xml.stream.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class BlueprintReader {
    public static Data.Blueprint load(String filename) throws IOException, XMLStreamException {
        Path path = Paths.get(filename);
        return load(path);
    }
    public static Data.Blueprint load(Path path) throws IOException, XMLStreamException {
        if (!Files.exists(path)) {
            throw new IOException("File not found");
        }
        File file = path.toFile();
        return load(file);
    }
    public static Data.Blueprint load(File file) throws IOException, XMLStreamException {
        FileReader reader = new FileReader(file);
        return load(reader);
    }
    public static Data.Blueprint load(FileReader reader) throws IOException, XMLStreamException {
        XMLStreamReader in = XMLInputFactory.newFactory().createXMLStreamReader(reader);
        return load(in);
    }
    public static Data.Blueprint load(XMLStreamReader in) throws XMLStreamException {
        do {
            in.next();
            if (in.getEventType() == XMLStreamConstants.START_ELEMENT && in.getLocalName().equals("Blueprint")) {
                System.out.println("Reading Blueprint...");
                return readBlueprint(in);
            }
        } while (in.hasNext());
        throw new XMLStreamException("Blueprint data not found");
    }
    private static Data.Blueprint readBlueprint(XMLStreamReader in) throws XMLStreamException {
        Data.Blueprint blueprint = new Data.Blueprint();
        boolean exit = false;
        do {
            in.next();
            switch (in.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    System.out.println("Reading " + in.getLocalName());
                    switch (in.getLocalName()) {
                        case "TypeId":
                            //blueprint.type = in.getElementText();
                            break;
                        case "SubtypeId":
                            blueprint.subtype = in.getElementText();
                            break;
                        case "DisplayName":
                            blueprint.displayName = in.getElementText();
                            break;
                        case "Icon":
                            blueprint.icon = in.getElementText();
                            break;
                        case "Prerequisites":
                            blueprint.prerequisites = readItems(in, "Prerequisites");
                            break;
                        case "Results":
                            blueprint.results = readItems(in, "Results");
                            break;
                        case "BaseProductionTimeInSeconds":
                            blueprint.productionTime = Float.parseFloat(in.getElementText());
                            break;
                    }
                case XMLStreamConstants.END_ELEMENT:
                    if (in.getLocalName().equals("Blueprint")) {
                        System.out.println("Exiting Blueprint...");
                        exit = true;
                    }
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("Exiting document...");
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);
        return blueprint;
    }
    private static ArrayList<Data.Item> readItems(XMLStreamReader in, String tag) throws XMLStreamException {
        ArrayList<Data.Item> items = new ArrayList<>();
        boolean exit = false;
        do {
            in.next();
            switch (in.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if (in.getLocalName().equals("Item")) {
                        System.out.println("Reading Item...");
                        items.add(readItem(in));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (in.getLocalName().equals(tag)) {
                        System.out.println("Exiting Items...");
                        exit = true;
                    }
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("Exiting document...");
                    exit = true;
                    break;
            }
        } while (!exit);
        return items;
    }
    private static Data.Item readItem(XMLStreamReader in) throws XMLStreamException {
        Data.Item item = new Data.Item();
        int attrCount = in.getAttributeCount();
        for (int i = 0; i < attrCount; i++) {
            System.out.println("Reading attribute " + in.getAttributeLocalName(i));
            switch (in.getAttributeLocalName(i)) {
                case "Amount":
                    item.amount = Float.parseFloat(in.getAttributeValue(i));
                    break;
                case "TypeId":
                    item.type = in.getAttributeValue(i);
                    break;
                case "SubtypeId":
                    item.subtype = in.getAttributeValue(i);
                    break;
            }
        }
        //while (in.getEventType() != XMLStreamConstants.END_ELEMENT) in.next();
        return item;
    }
}
