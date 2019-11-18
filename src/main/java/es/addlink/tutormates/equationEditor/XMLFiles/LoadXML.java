package es.addlink.tutormates.equationEditor.XMLFiles;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Utilidad para cargar ficheros.
 * @author Ignacio Celaya Sesma
 */
@objid ("07e211bc-df73-4a5d-b027-9d60ac53ff39")
public class LoadXML {

/**
     * Carga el fichero según los parámetros de entrada.
     * @param comunPath Ejemplo: "es.addlink.tutormates.editor"
     * @param confFileDirectory Ejemplo: "/es/addlink/tutormates/editor/Translation/MathML.xml"
     * @param otherPath Ejemplo: "../Translation/MathML.xml"
     * @return Devuelve la URL del fichero
     */
/*public static URL getFile(String comunPath, String confFileDirectory, String otherPath) {
        URL u = null;
        Bundle b = Platform.getBundle(comunPath);

        try {
            u = FileLocator.find(b, new Path(confFileDirectory), null);
        } catch (java.lang.ExceptionInInitializerError e) {
        } catch (java.lang.NoClassDefFoundError e) {
        }
        if (u == null) {
            u = MathEditor2MathML.class.getResource(otherPath);
        }
        return u;
    }*/
/**
     * Construye un Document obtenido a partir de las rutas del fichero XML.
     * @param commonPath Ejemplo: "es.addlink.tutormates.editor"
     * @param confFileDirectory Ejemplo: "/es/addlink/tutormates/editor/Translation/MathML.xml"
     * @param fileName Ejemplo: "../Translation/MathML.xml"
     * @return Devuelve el Document del XML.
     */
//    @SuppressWarnings("unchecked")
//    public static Document getDocument(String fileName,Class clas)throws FileEditorException{
//        try {
//            SAXBuilder builder = new SAXBuilder();
//            URL url = null;
//            String path = "/es/addlink/tutormates/equationEditor/";
//            url = LoadXML.class.getClass().getResource(fileName);
//            url = LoadXML.class.getClassLoader().getSystemResource(fileName);
//            url = LoadXML.class.getClassLoader().getResource(fileName);
//            Document doc = builder.build(url);
//            return doc;
//
//        } catch(JDOMParseException e){
//            throw new FileEditorException("Existen errores de sintaxis xml en el fichero '" + fileName +"'.",e);
//        }catch (JDOMException e) {
//            throw new FileEditorException("Se ha producido un error al cargar el fichero '" + fileName +"'.",e);
//        }catch (IOException e) {
//            throw new FileEditorException("Se ha producido un error al cargar el fichero '" + fileName +"'.",e);
//        }catch (Exception e) {
//            throw new FileEditorException("Es posible que el nombre del fichero xml '" + fileName + "' no sea el correcto.",e);
//        }
//    }
}
