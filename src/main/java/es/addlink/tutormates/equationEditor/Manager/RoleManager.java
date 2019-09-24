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
 * File: RoleManager.java
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
import org.jdom.Document;
import org.jdom.Element;
import org.modelio.module.cpswarm.utils.ResourcesManager;
import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import es.addlink.tutormates.equationEditor.Role.ItemToolBar;
import es.addlink.tutormates.equationEditor.Role.Role;
import es.addlink.tutormates.equationEditor.Role.TabToolBar;

/**
 * Obtiene y guarda todos los botones de la toolbar que pueden ser cargados en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
public class RoleManager {
	
	private Role role;
	private String profile;
	private String course;
	private String unit;
	private String fileName;
	private Document docXMLRole;
	private MainToolBarBaseManager toolBarManager;
	private OperatorsBaseManager operatorsBaseManager;
	private Manager manager;
	private Element toolBarLanguage;
	
	public RoleManager(Manager manager, String profile, String course, String unit) {
		super();
		try {
			this.manager = manager;
			this.operatorsBaseManager = new OperatorsBaseManager();
			this.toolBarManager = new MainToolBarBaseManager();
			this.profile = profile;
			this.course = course;
			this.unit = unit;
			this.fileName = getFileName();
			
			Document docLanguage = this.manager.getLanguageManager().getDocument();
			Element rootLanguage = docLanguage.getRootElement();
			this.toolBarLanguage = rootLanguage.getChild("toolBars");
			loadToolBarXML();
			
			this.role = new Role(this.profile,this.course,this.unit,getOperatorsList(),getFunctionsList(),getVariablesList(),getTabsToolBar());
		}
		catch (FileEditorException e) {
			e.printStackTrace();
		}
	}
	
	private List<BaseOperator> getOperatorsList(){
		try {
			List<BaseOperator> result = new Vector<>();
			
			Document doc = ResourcesManager.getInstance().getXMLDocument(this.fileName);
			Element root = doc.getRootElement();
			Element operators = root.getChild("operators");
			
			@SuppressWarnings("unchecked")
			List<Element> nodeList = operators.getChildren();
			
			Iterator<Element> ite = nodeList.iterator();
			while(ite.hasNext()){
				Element el = (Element)ite.next();
				int id = Integer.valueOf(el.getValue());
				BaseOperator ope = this.operatorsBaseManager.getOperator(id);
				if(ope != null)
					result.add(ope);
			}
			
			return result;
		}
		catch (FileEditorException e) {
			// TODO Auto-gnerated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private List<BaseFunction> getFunctionsList(){
		try {
			List<BaseFunction> result = new Vector<>();
			
			Document doc = ResourcesManager.getInstance().getXMLDocument(this.fileName);
			Element root = doc.getRootElement();
			Element operators = root.getChild("functions");
			
			@SuppressWarnings("unchecked")
			List<Element> nodeList = operators.getChildren();
			
			Iterator<Element> ite = nodeList.iterator();
			while(ite.hasNext()){
				Element el = (Element)ite.next();
				String f = el.getValue();
				BaseFunction function = this.operatorsBaseManager.getFunction(f);
				if(function != null)
					result.add(function);
			}
			
			return result;
		}
		catch (FileEditorException e) {
			// TODO Auto-gnerated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private List<BaseOperator> getVariablesList(){
		try {
			List<BaseOperator> result = new Vector<BaseOperator>();
			
			Document doc = ResourcesManager.getInstance().getXMLDocument("role_all.xml");
			Element root = doc.getRootElement();
			Element operators = root.getChild("variables");
			
			@SuppressWarnings("unchecked")
			List<Element> nodeList = operators.getChildren();
			
			Iterator<Element> ite = nodeList.iterator();
			while(ite.hasNext()){
				Element el = (Element)ite.next();
				String name = el.getValue();
				BaseOperator ope = this.operatorsBaseManager.getVariableOrNumber(name);
				if(ope != null)
					result.add(ope);
			}
			
			Element numbers = root.getChild("numbers");
			@SuppressWarnings("unchecked")
			List<Element> node = numbers.getChildren();
			
			Iterator<Element> i = node.iterator();
			while(i.hasNext()){
				Element el = (Element)i.next();
				String name = el.getValue();
				BaseOperator ope = this.operatorsBaseManager.getVariableOrNumber(name);
				if(ope != null)
					result.add(ope);
			}
			
			return result;
		}
		catch (FileEditorException e) {
			// TODO Auto-gnerated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void loadToolBarXML(){
		try {
			
			this.docXMLRole = ResourcesManager.getInstance().getXMLDocument(this.fileName);
			
			if(this.profile.equalsIgnoreCase(""))
				System.out.print("# Editor: Loading profile. ");
			else{
				if(!this.course.equalsIgnoreCase(""))
					System.out.print("# Editor: Loading profile ["+ profile + "|" + course);
				else
					System.out.print("# Editor: Loading profile ["+ profile);
					
				if(!this.unit.equalsIgnoreCase(""))
					System.out.print("|" + unit + "]. ");
				else
					System.out.print("]. ");
			}
			System.out.println("Language '" + this.manager.getLanguageManager().getLanguage() + "'. Profile file '" + this.fileName + "'.");
			
		}catch (FileEditorException e) {
			findNewProfile();
		}
	}
	
	private void findNewProfile(){
		
		if(this.profile.equalsIgnoreCase("")){
			this.profile = "";
			this.course = "";
			this.unit = "";
			this.fileName = getFileName();
			loadToolBarXML();
		}
		else{
			if(!this.course.equalsIgnoreCase("")){
				this.fileName = "role_" + this.profile + "_" + this.course + ".xml";
				this.unit = "";
				this.fileName = getFileName();
				loadToolBarXML();
			}
			else{
				this.profile = "";
				this.course = "";
				this.unit = "";
				this.fileName = getFileName();
				loadToolBarXML();
			}
		}
	}
	
	public Role getRole(){
		return this.role;
	}
	
	private List<TabToolBar> getTabsToolBar(){
		
		List<TabToolBar> listTabs = new Vector<TabToolBar>();
		List<ItemToolBar> listItems;
		
		Element root = this.docXMLRole.getRootElement();
		Element toolbar = root.getChild("toolBar");
		
		@SuppressWarnings("unchecked")
		List<Element> nodeList = toolbar.getChildren();
		
		Iterator<Element> iterator = nodeList.iterator();
		while(iterator.hasNext()){
			Element el = (Element)iterator.next();
			
			// Tabs  toolbar
			String name = el.getAttributeValue("name");
			
			TabToolBar tabBase = this.toolBarManager.getTabToolBar(name);
			
			if(tabBase != null){
				int columns = Integer.valueOf(el.getAttributeValue("columns"));
				Boolean visible = Boolean.valueOf(el.getAttributeValue("visible"));
				Boolean enabled = Boolean.valueOf(el.getAttributeValue("enabled"));
				
				// Items toolbar
				@SuppressWarnings("unchecked")
				List<Element> nodeButton = el.getChildren();
				Iterator<Element> ite = nodeButton.iterator();
				listItems = new Vector<ItemToolBar>();
				while(ite.hasNext()){
					Element e = (Element)ite.next();
					String name2 = e.getValue();
					
					ItemToolBar itemBase = this.toolBarManager.getItemToolBar(name2);
					
					if(itemBase != null){
						Boolean visible2 = Boolean.valueOf(e.getAttributeValue("visible"));
						Boolean enabled2 = Boolean.valueOf(e.getAttributeValue("enabled"));
						if(visible2 == true)
							listItems.add(new ItemToolBar(itemBase.getIcon(),itemBase.isText(),itemBase.getOperator(),name2,itemBase.getShortcut(),getButtonTraduction(name2),visible2,enabled2));
					}
				}
				
				listTabs.add(new TabToolBar(listItems,name,getTabTraduction(name),tabBase.getIcon(),columns,visible,enabled));
			}
		}
		
		return listTabs;
	}
	
	private String getTabTraduction(String name){
		Element tabsLanguage = toolBarLanguage.getChild("tabs");
		Element el = tabsLanguage.getChild(name);
		
		if(el != null) return el.getValue();
		else return "s/n";
	}
	
	private String getButtonTraduction(String name){
		Element buttonsLanguage = toolBarLanguage.getChild("buttons");
		Element el = buttonsLanguage.getChild(name);
		
		if(el != null) return el.getValue();
		else return "";
	}
	
	public String getProfile() {
		return profile;
	}

	public String getCourse() {
		return course;
	}

	public String getUnit() {
		return unit;
	}

	private String getFileName(){
		String fileName = "role";
		
		if(!profile.equalsIgnoreCase(""))
			fileName += "_" + profile;
		if(!course.equalsIgnoreCase(""))
			fileName += "_" + course;
		if(!unit.equalsIgnoreCase(""))
			fileName += "_" + unit;
		
		fileName += ".xml";
		
		if(profile.equalsIgnoreCase("profesor") || profile.equalsIgnoreCase(""))
			fileName = "role_Profesor.xml";
		
		if(profile.equalsIgnoreCase("") && course.equalsIgnoreCase("") && unit.equalsIgnoreCase(""))
			fileName = "role_Profesor.xml";
		
		if(profile.equalsIgnoreCase("all"))
			fileName = "role_all.xml";
			
		return fileName;
	}
}
