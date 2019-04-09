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
 * File: PathManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import es.addlink.tutormates.equationEditor.Role.Role;
import es.addlink.tutormates.equationEditor.XMLFiles.LoadXML;

/**
 * Clase que agrupa todas las rutas de ficheros y paquetes.
 * 
 * @author Ignacio Celaya Sesma
 */
public class PathManager {
	
	private final static String commonPath = "es.addlink.tutormates.equationEditor";
	private final static String commonPathWithSlash = "/es/addlink/tutormates/equationEditor";
	private final static String MathEditorClasses = commonPath + ".MathEditor";
	
	private final static String AllowedOperators_fileName = "allowedOperators.xml";
	private final static Class<LoadXML> AllowedOperators_class = LoadXML.class;
	
	private final static String ToolBar_fileName = "toolBarBase.xml";
	private final static Class<Role> ToolBar_class = Role.class;
	
	private final static String Language_fileName = "";
	//private final static Class<LanguagePackage> Language_class = LanguagePackage.class;
	
	
	public static Class<LoadXML> getAllowedoperatorsClass() {
		return AllowedOperators_class;
	}
	public static Class<Role> getToolbarClass() {
		return ToolBar_class;
	}
	/*public static Class<LanguagePackage> getLanguageClass() {
		return Language_class;
	}*/
	public static String getCommonPath() {
		return commonPath;
	}
	public static String getAllowedOperatorsFileName() {
		return AllowedOperators_fileName;
	}
	public static String getMathEditorClasses() {
		return MathEditorClasses;
	}
	public static String getCommonPathWithSlash() {
		return commonPathWithSlash;
	}
	public static String getToolBarFileName() {
		return ToolBar_fileName;
	}
	public static String getLanguageFileName() {
		return Language_fileName;
	}
	
}
