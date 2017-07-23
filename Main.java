import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
 
public class Main
{
 
    private static boolean bcompany,btitle,bname,bemail,bphone;
 
    public static void main(String[] args) throws FileNotFoundException,
                                                  XMLStreamException
    {
        // Create a File object with appropriate xml file name
        File file = new File("data.xml");
 
        
        parser(file);
    }
 
    public static void parser(File file) throws FileNotFoundException,
                                                   XMLStreamException
    {
        
        bcompany = btitle = bname = bemail = bphone = false;
 
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
 
        
        XMLEventReader eventReader =
                 factory.createXMLEventReader(new FileReader(file));
 
        while (eventReader.hasNext())
        {
            
            XMLEvent event = eventReader.nextEvent();
 
           
            if (event.isStartElement())
            {
                StartElement element = (StartElement)event;
 
                
                Iterator<Attribute> iterator = element.getAttributes();
                while (iterator.hasNext())
                {
                    Attribute attribute = iterator.next();
                    QName name = attribute.getName();
                    String value = attribute.getValue();
                    System.out.println(name+" = " + value);
                }
 
                
                if (element.getName().toString().equalsIgnoreCase("comapany"))
                {
                    bcompany = true;
                }
                if (element.getName().toString().equalsIgnoreCase("title"))
                {
                    btitle = true;
                }
                if (element.getName().toString().equalsIgnoreCase("name"))
                {
                    bname = true;
                }
                if (element.getName().toString().equalsIgnoreCase("email"))
                {
                    bemail = true;
                }
                if (element.getName().toString().equalsIgnoreCase("phone"))
                {
                    bphone = true;
                }
            }
 
            
            if (event.isEndElement())
            {
                EndElement element = (EndElement) event;
 
                
                if (element.getName().toString().equalsIgnoreCase("comapany"))
                {
                    bcompany = false;
                }
                if (element.getName().toString().equalsIgnoreCase("title"))
                {
                    btitle = false;
                }
                if (element.getName().toString().equalsIgnoreCase("name"))
                {
                    bname = false;
                }
                if (element.getName().toString().equalsIgnoreCase("email"))
                {
                    bemail = false;
                }
                if (element.getName().toString().equalsIgnoreCase("phone"))
                {
                    bphone = false;
                }
            }
 
            
            if (event.isCharacters())
            {
                
                Characters element = (Characters) event;
                if (bcompany)
                { 
                    System.out.println("Company Name: "+element.getData());
                }
                if (btitle)
                {
                   System.out.println("Title: "+element.getData());
                }
                if (bname)
                {
                    System.out.println("Name: "+element.getData());
                }
                if (bemail)
                {
                    System.out.println("Email Address: "+element.getData());
                }
                if (bphone)
                {
                    System.out.println("Phone No.: "+element.getData());
                    System.out.println();
                }
            }
        }
    }
}