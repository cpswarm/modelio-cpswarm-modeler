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
 * File: LanguageManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;

/**
 * Guarda y carga los ficheros necesarios para el idioma.
 * 
 * @author Ignacio Celaya Sesma
 */
public class LanguageManager {
	
	private String lang;
	private String fileName;
	private Document doc;
	
	public LanguageManager(String lang){
		this.lang = lang;
	}
	
	public String getLanguage(){
		return this.lang;
	}
	
	public void setLanguage(String lang)throws FileEditorException{
		this.lang = lang;
		setFileName();
		setDocument();
	}
	
	public Boolean isLanguageConfigured(){
		return (lang != null);
	}
	
	public Document getDocument()throws FileEditorException{
		if(this.doc == null)
			buildDefaultLanguage();
		return this.doc;
	}
	
	private void setFileName(){
		this.fileName = "language_" + this.lang + ".xml";
	}
	
	private void setDocument()throws FileEditorException{
		this.doc = ResourcesManager.getInstance().getXMLDocument(this.fileName);
		
	}
	
	private void buildDefaultLanguage()throws FileEditorException{
		if(this.lang != null){
			setFileName();
			setDocument();
		}
	}
	
	public String getFileName(){
		return "language_" + this.lang + ".xml";
	}
	
	public String getTitle(){
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
