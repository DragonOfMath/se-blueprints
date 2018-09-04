package IO;

import javax.xml.stream.*;
import java.io.*;
import java.nio.file.*;

public class BlueprintWriter {
    public static void save(String filename, Data.Blueprint blueprint) throws IOException, XMLStreamException {
        Path path = Paths.get(filename);
        save(path, blueprint);
    }
    public static void save(Path path, Data.Blueprint blueprint) throws IOException, XMLStreamException {
        File file = path.toFile();
        save(file, blueprint);
    }
    public static void save(File file, Data.Blueprint blueprint) throws IOException, XMLStreamException {
        FileWriter writer = new FileWriter(file);
        save(writer, blueprint);
    }
    public static void save(FileWriter writer, Data.Blueprint blueprint) throws IOException, XMLStreamException {
        XMLStreamWriter out = XMLOutputFactory.newFactory().createXMLStreamWriter(writer);
        save(out, blueprint);
    }
    public static void save(XMLStreamWriter out, Data.Blueprint blueprint) throws XMLStreamException {
        System.out.println("Writing document header...");
        out.writeStartDocument("1.0");
        //out.writeDTD("<!DOCTYPE Definitions SYSTEM \"OreBlueprint.dtd\">");
        out.writeStartElement("Definitions");
            out.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            out.writeAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
            out.writeStartElement("Blueprints");
                writeBlueprint(out, blueprint);
            out.writeEndElement();
        out.writeEndElement();
        
        System.out.println("Finished writing document.");
        
        out.flush();
        out.close();
    }
    private static void writeBlueprint(XMLStreamWriter out, Data.Blueprint blueprint) throws XMLStreamException {
        System.out.println("Writing blueprint...");
        out.writeStartElement("Blueprint");
            out.writeStartElement("Id");
                out.writeStartElement("TypeId");
                    out.writeCharacters(blueprint.type);
                out.writeEndElement();
                out.writeStartElement("SubtypeId");
                    out.writeCharacters(blueprint.subtype);
                out.writeEndElement();
            out.writeEndElement();
            out.writeStartElement("DisplayName");
                out.writeCharacters(blueprint.displayName);
            out.writeEndElement();
            out.writeStartElement("Icon");
                out.writeCharacters(blueprint.icon);
            out.writeEndElement();
            out.writeStartElement("Prerequisites");
            for (Data.Item item : blueprint.prerequisites) {
                writeItem(out, item);
            }
            out.writeEndElement();
            out.writeStartElement("Results");
            for (Data.Item item : blueprint.results) {
                writeItem(out, item);
            }
            out.writeEndElement();
            out.writeStartElement("BaseProductionTimeInSeconds");
                out.writeCharacters(Float.toString(blueprint.productionTime));
            out.writeEndElement();
        out.writeEndElement();
        System.out.println("Finished writing blueprint.");
    }
    private static void writeItem(XMLStreamWriter out, Data.Item item) throws XMLStreamException {
        System.out.println("Writing item " + item.toString() + "...");
        out.writeEmptyElement("Item");
            out.writeAttribute("Amount", Float.toString(item.amount));
            out.writeAttribute("TypeId", item.type);
            out.writeAttribute("SubtypeId", item.subtype);
        //out.writeEndElement();
    }
}
