/* ===========================================================
 * TutorMates: Tool for Mathematics Teaching and Learning.
 * ===========================================================
 *
 * (C) Copyright 2011, by Addlink Research.
 *
 * Project Info: 	http://www.tutormates.com
 * Contact: 		info@tutormates.es
 * 
 * TutorMates Equation Editor is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * TutorMates Equation Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with TutorMates Equation Editor.  
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * ---------------
 * File: LoadXML.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.XMLFiles;

import java.io.IOException;
import java.net.URL;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;

import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;

/**
 * Utilidad para cargar ficheros.
 * @author Ignacio Celaya Sesma
 */
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
	@SuppressWarnings("unchecked")
	public static Document getDocument(String fileName,Class clas)throws FileEditorException{
		try {
			SAXBuilder builder=new SAXBuilder();
			URL url = null;
			url = LoadXML.class.getResource(fileName);
			Document doc = builder.build(url);
			return doc;
			
		} catch(JDOMParseException e){
			throw new FileEditorException("Existen errores de sintaxis xml en el fichero '" + fileName +"'.",e);
		}catch (JDOMException e) {
			throw new FileEditorException("Se ha producido un error al cargar el fichero '" + fileName +"'.",e);
		}catch (IOException e) {
			throw new FileEditorException("Se ha producido un error al cargar el fichero '" + fileName +"'.",e);
		}catch (Exception e) {
			throw new FileEditorException("Es posible que el nombre del fichero xml '" + fileName + "' no sea el correcto.",e);
		}
	}
}
