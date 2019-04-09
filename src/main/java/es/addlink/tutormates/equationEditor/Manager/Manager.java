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
 * File: Manager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import org.eclipse.swt.widgets.TabFolder;

import es.addlink.tutormates.equationEditor.Connections.TutorMatesEditor;
import es.addlink.tutormates.equationEditor.Role.AllowedOperatorsAndFunctions;

/**
 * Clase que engloba toda la configuración del editor.
 * 
 * @author Ignacio Celaya Sesma
 */
public class Manager {

	/**
	 * Tamaño de la fuente de los TextBox cargada finalmente.
	 */
	private static int fontSize;
	
	/**
	 * Tamaño de la fuente de los TextBox para Mac.
	 */
	private final static int fontSizeMac = 14;
	
	/**
	 * Tamaño de la fuente de los TextBox para Windows.
	 */
	private final static int fontSizeWinLin = 12;
	
	public static int getFontSize() {
		return fontSize;
	}
	
	public static boolean isMac()
	{
		return System.getProperty("os.name").toLowerCase().indexOf("mac")>-1;
	}
	
	/**
	 * Guarda la configuración de las acciones (deshacer, rehacer, exportar e importar).
	 */
	private ActionManager action;
	
	/**
	 * Contiene los listados de operadores, variables y funciones disponibles para un rol en concreto.
	 */
	private AllowedOperatorsAndFunctions allowedOperators;
	
	/**
	 * Realiza varias acciones sobre la zona de edición afectando a una expresión al completo.
	 */
	private CentralManager central;
	
	/**
	 * Única clase de visibilidad pública hacia el exterior del editor.
	 */
	private TutorMatesEditor editor;
	
	/**
	 * Guarda el historial para deshacer o rehacer una expresión.
	 */
	private HistoryManager history;
	
	/**
	 * Configuración del lenguaje.
	 */
	private LanguageManager language;
	
	/**
	 * Rol cargado.
	 */
	private RoleManager roleManager;

	/**
	 * Guarda el TextBox que tiene el foco y la posición del cursor. 
	 */
	private StateManager state;
	
	/**
	 * Encargado de construir el ToolBar de los botones de acción.
	 */
	private ActionToolBarDisplayManager toolBar;
	
	private Boolean isTestVersion;
	
	public Boolean isTestVersion() {
		return isTestVersion;
	}

	/**
	 * Constructor.
	 * @param editor Instancia de la única clase de visibilidad pública hacia el exterior del editor.
	 * @param lang Dos primeros caracteres del idioma: "es", "en", etc.
	 */
	public Manager(TutorMatesEditor editor, String lang, Boolean isTestVersion){

		this.editor = editor;
		this.isTestVersion = isTestVersion;
		
		if(isMac()){
			fontSize = fontSizeMac;
		}else{
			fontSize = fontSizeWinLin;
		}
		
		this.language = new LanguageManager(lang);
		this.state = new StateManager();
		this.toolBar = new ActionToolBarDisplayManager(this);
		this.history = new HistoryManager(this);
		this.action = new ActionManager(this);
		this.central = new CentralManager(this);
	}
	
	public void buildMainToolBar(TabFolder tab){
		ToolBarDisplayManager tb = new ToolBarDisplayManager(this,tab,this.getRoleManager().getProfile(),this.getRoleManager().getCourse(),this.getRoleManager().getUnit());
		tb.buildMainToolBar();
	}
	
	/**
	 * Construye el rol a partir de los parámetros pasados.
	 * @param profile Perfil del usuario: profesor o alumno.
	 * @param course Curso: 1ESO, 2ESO, ...
	 * @param unit Tema: ENT_N1, ...
	 */
	public void buildRole(String profile, String course, String unit){
		this.roleManager = new RoleManager(this,profile,course,unit);
		this.allowedOperators = new AllowedOperatorsAndFunctions();
		this.allowedOperators.setOperatorsList(this.roleManager.getRole().getListAllowedOperator());
		this.allowedOperators.setNumbersVariablesList(this.roleManager.getRole().getListAllowedNumbersVariables());
		this.allowedOperators.setFunctionsList(this.roleManager.getRole().getListAllowedFunction());
	}
	
	public ActionManager getActionManager(){
		return this.action;
	}
	
	public ActionToolBarDisplayManager getActionsToolBar(){
		return this.toolBar;
	}
	
	public AllowedOperatorsAndFunctions getAllowedOperatorsAndFunctions() {
		return allowedOperators;
	}

	public CentralManager getCentralManager(){
		return this.central;
	}
	
	public HistoryManager getHistoryManager(){
		return this.history;
	}
	
	public LanguageManager getLanguageManager(){
		return this.language;
	}
	
	public RoleManager getRoleManager() {
		return roleManager;
	}
	
	public StateManager getStateManager(){
		return this.state;
	}
	
	public TutorMatesEditor getTutorMatesEditor(){
		return this.editor;
	}
}
