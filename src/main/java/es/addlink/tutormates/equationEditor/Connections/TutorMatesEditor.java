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
 * File: TutorMatesEditor.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Connections;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.ManagerEditorException;
import es.addlink.tutormates.equationEditor.Manager.Manager;

/**
 * Connects the equation editor with another java application.
 * 
 * @author Ignacio Celaya Sesma
 */
public class TutorMatesEditor {
	
	/**
	 * GUI's equation editor.
	 */
	private GUIEditor gui;
	
	/**
	 * Captures enter key.
	 */
	private KeyAdapter kAdapter;
	
	/**
	 * Contains equation editor's global configuration.
	 */
	private Manager manager;
	
	/**
	 * Requires a correct expression for exporting.
	 */
	private Boolean correctExpresion;

	/**
	 * Constructor.
	 * @param lang Two first chars of the language. Example: "es" -> Spanish, "en" -> English, ...
	 */
	public TutorMatesEditor(String lang){
		this.correctExpresion = true;
		this.manager = new Manager(this,lang,false);
	}
	
	public TutorMatesEditor(String lang, Boolean correctExpression, Boolean isTestVersion){
		this.correctExpresion = correctExpression;
		this.manager = new Manager(this,lang,isTestVersion);
	}

	public TutorMatesEditor(String lang, Boolean correctExpression, String profile, String course, String unit){
		this.correctExpresion = correctExpression;
		this.manager = new Manager(this,lang,false);
		this.manager.buildRole(profile, course, unit);
	}
	
	/**
	 * Construye la parte gráfica.
	 * @param parent Composite donde se quiere alojar el editor.
	 * @param lang Lenguaje con el que se quiere inicializar el editor (en,es,ca,eu,ga).
	 */
	public void buildGUI(Composite parent, KeyAdapter kAdapter){
		this.manager.buildRole("all","","");
		this.kAdapter = kAdapter;
		this.gui = new GUIEditor(this.manager,parent);
		this.gui.createGUI(false);
	}
	
	/**
	 * Insert the first text box at the beginning.
	 */
	public void insertFirstText(){
		this.gui.insertFirstText();
	}
	
	/**
	 * Inserts the equation editor into the Composite. It will load the role configuration.
	 * @param parent
	 * @param kAdapter
	 * @param profile
	 * @param course
	 * @param unit
	 */
	public void buildGUI(Composite parent, KeyAdapter kAdapter,String profile, String course, String unit){
		this.manager.buildRole(profile,course,unit);
		this.kAdapter = kAdapter;
		this.gui = new GUIEditor(this.manager,parent);
		this.gui.createGUI(false);
	}
	
	/**
	 * Construye la parte gráfica, añadiendo una parte para obtener y comprobar el mathml de una expresión.
	 * @param parent Composite donde se quiere alojar el editor.
	 * @param lang Lenguaje con el que se quiere inicializar el editor (en,es,ca,eu,ga).
	 */
	public void buildGUIWithMathMLTesting(Composite parent, KeyAdapter kAdapter){
		this.manager.buildRole("all","","");
		this.kAdapter = kAdapter;
		this.gui = new GUIEditor(this.manager,parent);
		this.gui.createGUI(true);
	}
	
	/**
	 * Clases utilizada para las pruebas de código mathml.
	 */
	public void exportMathMLInText(){
		try {
			String mathml = this.manager.getActionManager().exportMathML();
			if(mathml != null){
				this.gui.getGUIMathMLTest().setTextOut(mathml);
				this.gui.getGUIMathMLTest().setTextIn(mathml);
			} 
		}
		catch (ManagerEditorException e) {
			//e.showExtendedError();
		}
	}
	
	/**
	 * Devuelve el ToolItem del botón exportar para que desde el exterior se capture el clic.
	 * @return ToolItem del botón exportar para que desde el exterior se capture el clic.
	 */
	public ToolItem getExportToolItem(){
		return this.manager.getActionsToolBar().getExportToolItem();
	}
	
	public Button getImportButton_Test(){
		return this.gui.getGUIMathMLTest().getBtnImport();
	}
	
	/**
	 * Devuelve el ToolItem del botón importar para que desde el exterior se capture el clic.
	 * @return ToolItem del botón importar para que desde el exterior se capture el clic.
	 */
	public ToolItem getImportToolItem(){
		return this.manager.getActionsToolBar().getImportToolItem();
	}
	
	/**
	 * Devuelve el KeyAdapter definido fuera del editor, cuya misión es capturar las pulsaciones de "enter".
	 * @return
	 */
	public KeyAdapter getKeyAdapter(){
		return this.kAdapter;
	}
	
	/**
	 * Devuelve el manager del editor.
	 * @return Manager del editor.
	 */
	public Manager getManager(){
		return this.manager;
	}

	/**
	 * Devuelve el MathML de la expresión actual que hay en el editor.
	 * @return MathML de la expresión actual que hay en el editor.
	 */
	public String getMathML(){
		try {
			return this.manager.getActionManager().exportMathML();
		}
		catch (ManagerEditorException e) {
			e.showExtendedError();
			return null;
		}
	}
	
	/**
	 * Devuelve true si el editor solo exporta expresiones correctas, false en caso contrario.
	 * @return True si el editor solo exporta expresiones correctas, false en caso contrario.
	 */
	public Boolean getCorrectExpresion() {
		return correctExpresion;
	}
	
	/**
	 * Devuelve el título para la ventana donde se aloje el editor.
	 * @return
	 */
	public String getTitle(){
		return this.manager.getLanguageManager().getTitle();
	}
	
	public void importMathMLInText(){
		try {
			manager.getActionManager().actionImportMathML(this.gui.getGUIMathMLTest().getTextImput());
		}
		catch (ManagerEditorException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public boolean isMathMLCorrect(String mathml){
		return this.manager.getActionManager().isMathMLCorrect(mathml);
	}
	
	/**
	 * Establece el foco en el editor. Se suele llamar a este método al inicio.
	 */
	public void setFocus(){
		this.gui.setFocus();
	}
	
	/**
	 * Carga la expresión pasada por parámetro en el editor.
	 * @param mathml MathML que se desea cargar en el editor.
	 */
	public void setMathML(String mathml){
		try {
			this.manager.getActionManager().actionImportMathML(mathml);
		}
		catch (ManagerEditorException e) {
			e.showExtendedError();
		}
	}
}
