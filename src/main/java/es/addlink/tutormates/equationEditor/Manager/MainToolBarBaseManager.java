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
 * File: MainToolBarBaseManager.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.eclipse.swt.graphics.Image;
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;
import es.addlink.tutormates.equationEditor.Display.GUIEditor;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import es.addlink.tutormates.equationEditor.Role.ItemToolBar;
import es.addlink.tutormates.equationEditor.Role.TabToolBar;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;

/**
 * Obtiene y guarda todos los botones de la toolbar que pueden ser cargados en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
public class MainToolBarBaseManager {
	
	
	private List<TabToolBar> tabToolBarList;
	private List<ItemToolBar> itemToolBarList;
	private Document docToolBarBase;
	
	public MainToolBarBaseManager(){
		this.tabToolBarList = new Vector<>();
		this.itemToolBarList = new Vector<>();
		
		buildXMLFiles();
		buildToolBarBase();
		
	}
	
	
	private void buildXMLFiles(){
		try {
			this.docToolBarBase = ResourcesManager.getInstance().getXMLDocument(PathManager.getToolBarFileName());
		}
		catch (FileEditorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void buildToolBarBase(){
		try {
			
			Element root = docToolBarBase.getRootElement();
			
			Element mainPalette = root.getChild("mainPalette");
			Element tabs = mainPalette.getChild("tabs");
			Element buttons = mainPalette.getChild("buttons");
			
			@SuppressWarnings("unchecked")
			List<Element> tabsList = tabs.getChildren();
			@SuppressWarnings("unchecked")
			List<Element> buttonsList = buttons.getChildren();
			
			Iterator<Element> iteTabs = tabsList.iterator();
			while(iteTabs.hasNext()){
				Element el = (Element)iteTabs.next();
				String name = el.getValue();
				Image icon = SWTResourceManager.getImage(GUIEditor.class, el.getAttributeValue("icon"));
				this.tabToolBarList.add(new TabToolBar(null,name,"",icon,-1,true,true));
			}
			
			Iterator<Element> iteButtons = buttonsList.iterator();
			while(iteButtons.hasNext()){
				Element el = (Element)iteButtons.next();
				
				String name = el.getValue();
				Image icon = SWTResourceManager.getImage(GUIEditor.class, el.getAttributeValue("icon"));
				Boolean isText = Boolean.valueOf(el.getAttributeValue("isText"));
				String operator = el.getAttributeValue("operator");
				String shortcut = el.getAttributeValue("shortcut");
				this.itemToolBarList.add(new ItemToolBar(icon, isText, operator, name, shortcut,"", true, true));
			}
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public TabToolBar getTabToolBar(String name){
		
		Iterator<TabToolBar> ite = this.tabToolBarList.iterator();
		while(ite.hasNext()){
			TabToolBar t = (TabToolBar)ite.next();
			if(t.getName().equalsIgnoreCase(name))
				return t;
		}
		
		return null;
	}

	public ItemToolBar getItemToolBar(String name){
		Iterator<ItemToolBar> ite = this.itemToolBarList.iterator();
		while(ite.hasNext()){
			ItemToolBar i = (ItemToolBar)ite.next();
			if(i.getName().equalsIgnoreCase(name))
				return i;
		}
		
		return null;
	}
	
	
	/**
	 * La paleta lateral de acciones no se carga mediante xml. Ya que es una barra fija, se carga de forma manual en Display/ActionsToolBar.java.
	 */
}
