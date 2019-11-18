package es.addlink.tutormates.equationEditor.Utils;

import java.io.IOException;
import java.io.StringReader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

@objid ("f1d44f51-406f-4cb2-ad48-0bdd51d9d922")
public class JDomUtils {
    /**
     * Converts String to org.jdom.Document
     * @param str @return
     */
    @objid ("8a329e1d-7613-4cd8-a47b-d6a839c154c2")
    public static Document String2Document(String str) {
        try {
            SAXBuilder builder=new SAXBuilder();
            Document doc = builder.build(new StringReader(str));
            return doc;
        }
        catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts org.jdom.Document to String.
     * @param doc @return
     */
    @objid ("6bd91288-3ead-4d9c-b391-8df38f8f6ae2")
    public static String Document2String(Document doc) {
        XMLOutputter out = new XMLOutputter();
        Format format = Format.getRawFormat();
        format.setOmitEncoding(true) ;
        format.setOmitDeclaration(true);
        format.setEncoding("US-ASCII");
        out.setFormat(format);
        String xml = (out.outputString(doc));
        return (xml);
    }

}
