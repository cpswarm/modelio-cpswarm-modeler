package es.addlink.tutormates.equationEditor.Manager;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;

/**
 * Guarda y carga los ficheros necesarios para el idioma.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("37b481c8-a393-4cca-a22a-bc2a63395e2b")
public class LanguageManager {
    @objid ("452c9e12-3dee-4809-83b8-9e28570fb933")
    private String lang;

    @objid ("a62a8c1e-39b3-4df3-a0a1-ecfa57210697")
    private String fileName;

    @objid ("4975e3fe-b65d-41b4-85d9-ec8626660ac5")
    private Document doc;

    @objid ("62955b1a-c25a-4f53-8548-c54ba8765b3b")
    public LanguageManager(String lang) {
        this.lang = lang;
    }

    @objid ("6d7014a2-d866-4fbe-91f2-f1fff68aba78")
    public String getLanguage() {
        return this.lang;
    }

    @objid ("a94cfc7a-f06c-4788-a967-990bdc5d7ecd")
    public void setLanguage(String lang) throws FileEditorException {
        this.lang = lang;
        setFileName();
        setDocument();
    }

    @objid ("75ccfe05-b9aa-442f-9801-315ca3ecbff0")
    public Boolean isLanguageConfigured() {
        return (lang != null);
    }

    @objid ("2bc8c374-885f-439f-bce0-31ddd5e7e32e")
    public Document getDocument() throws FileEditorException {
        if(this.doc == null)
            buildDefaultLanguage();
        return this.doc;
    }

    @objid ("4940ddac-1408-4e92-9578-9a4c5bd46af3")
    private void setFileName() {
        this.fileName = "language_" + this.lang + ".xml";
    }

    @objid ("925196c3-c4c8-4bd3-84ce-32dea5da4fd6")
    private void setDocument() throws FileEditorException {
        this.doc = ResourcesManager.getInstance().getXMLDocument(this.fileName);
    }

    @objid ("02f3b18f-a527-4c71-ab75-56d20ecf39d5")
    private void buildDefaultLanguage() throws FileEditorException {
        if(this.lang != null){
            setFileName();
            setDocument();
        }
    }

    @objid ("82ff74bf-a503-4be4-ae66-b3ef2bef9cfd")
    public String getFileName() {
        return "language_" + this.lang + ".xml";
    }

    @objid ("629bc011-262c-4e33-a682-39bc42419c6d")
    public String getTitle() {
        try{
        if(this.doc == null)
            buildDefaultLanguage();
        
        String title = null;
        
        Element el = this.doc.getRootElement();
        title = el.getAttributeValue("title");
        
        return title;
        
        }catch(Exception e){
            return "Equation Editor - Error";
        }
    }

}
