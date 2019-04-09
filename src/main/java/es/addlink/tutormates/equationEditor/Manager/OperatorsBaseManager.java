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
 * File: OperatorsBaseManager.java
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

import es.addlink.tutormates.equationEditor.Exceptions.FileEditorException;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;
import es.addlink.tutormates.equationEditor.Formats.MathML.MathMLAttribute;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;
import es.addlink.tutormates.equationEditor.XMLFiles.LoadXML;

/**
 * Obtiene y guarda todos los operadores que pueden ser cargados en el editor.
 * 
 * @author Ignacio Celaya Sesma
 */
public class OperatorsBaseManager {
	
	private List<BaseOperator> numbersVariablesList;
	private List<BaseOperator> operatorsList;
	private List<BaseFunction> functionsList;
	private Document doc;
	
	public OperatorsBaseManager(){
		try {
			this.operatorsList = new Vector<BaseOperator>();
			this.functionsList = new Vector<BaseFunction>();
			this.numbersVariablesList = new Vector<BaseOperator>();
			
			this.doc = LoadXML.getDocument("allowedOperators.xml",LoadXML.class);
			buildOperators();
			buildFunctions();
			buildNumbersVariables("numbers");
			buildNumbersVariables("variables");			
		}
		catch (FileEditorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildOperators(){
		Element root = this.doc.getRootElement();
		Element unaryOperators = root.getChild("operators");
		@SuppressWarnings("unchecked")
		List<Element> nodeList = unaryOperators.getChildren();
		
		/* Carga todos los operadores con sus MathML incluídos *********************************/
		
			Iterator<Element> iterator = nodeList.iterator();
			while(iterator.hasNext()){
				Element el = iterator.next();
				@SuppressWarnings("unchecked")
				List<Element> lis = el.getChildren();
				
				MathML mathML = null;
				
				if(lis.get(0).getChildren().size() > 0){
					List<MathMLAttribute> lisAt = new Vector<MathMLAttribute>();
					
					/* Carga los atributos de cada MathML de cada operador *********************/
						@SuppressWarnings("unchecked")
						Iterator<Element> ite = lis.get(0).getChildren().iterator();
						while(ite.hasNext()){
							Element elAt = ite.next();
							
							@SuppressWarnings("unchecked")
							Iterator<Element> itera = elAt.getChildren().iterator();
							while(itera.hasNext()){
								Element element = itera.next();
								lisAt.add(new MathMLAttribute(element.getName(),element.getValue()));
							}
							
						}
						mathML = new MathML(lisAt,lis.get(0).getAttributeValue("label"),false,false);
					/* *************************************************************************/
						
				}else
					mathML = new MathML(null,lis.get(0).getAttributeValue("label"),false,false);
				
				BaseOperator ope = new BaseOperator(Integer.valueOf(el.getAttributeValue("id")),Boolean.valueOf(el.getAttributeValue("isText")),el.getAttributeValue("name"),mathML,el.getAttributeValue("symbolEditor"),el.getAttributeValue("symbolMathML"),el.getAttributeValue("type"),el.getAttributeValue("pos"),Integer.valueOf(el.getAttributeValue("priority")));
				operatorsList.add(ope);				
			}
	}
	
	private void buildFunctions(){
		try{
			Element root = this.doc.getRootElement();
			Element functions = root.getChild("functions");
			
			@SuppressWarnings("unchecked")
			List<Element> nodeList = functions.getChildren();
			
			/* Carga todos los operadores con sus MathML incluídos *********************************/
			
				Iterator<Element> iterator = nodeList.iterator();
				while(iterator.hasNext()){
					Element el = iterator.next();
					
					@SuppressWarnings("unchecked")
					List<Element> lis = el.getChildren();
					
					String name = el.getAttributeValue("name");
					int entries = Integer.valueOf(el.getAttributeValue("entries"));
					/*int idOperator = -1;
					if(!el.getAttributeValue("idOperator").equals(""))
						idOperator = Integer.valueOf(el.getAttributeValue("idOperator"));
					*/
					
					MathML mathML = new MathML(null,lis.get(0).getAttributeValue("label"),Boolean.valueOf(lis.get(0).getAttributeValue("functionType")),Boolean.valueOf(lis.get(0).getAttributeValue("withinLabel")));
					
					BaseFunction af = new BaseFunction(name,entries,null,mathML);
					this.functionsList.add(af);
				}
			/* ************************************************************************************/
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
	
	private void buildNumbersVariables(String group){
		try{
			Element root = this.doc.getRootElement();
			Element rootNumbersVariables = root.getChild("others");
			Element unaryOperators = rootNumbersVariables.getChild(group);
			
			@SuppressWarnings("unchecked")
			List<Element> nodeList = unaryOperators.getChildren();
			
			/* Carga todos los operadores con sus MathML incluídos *********************************/
				
				Iterator<Element> iterator = nodeList.iterator();
				while(iterator.hasNext()){
					Element el = iterator.next();
					
					@SuppressWarnings("unchecked")
					List<Element> lis = el.getChildren();
					
					MathML mathML = null;
					
					if(lis.get(0).getChildren().size() > 0){
						List<MathMLAttribute> lisAt = new Vector<MathMLAttribute>();
						
						/* Carga los atributos de cada MathML de cada operador *********************/
							@SuppressWarnings("unchecked")
							Iterator<Element> ite = lis.get(0).getChildren().iterator();
							while(ite.hasNext()){
								Element elAt = ite.next();
								lisAt.add(new MathMLAttribute(elAt.getName(),elAt.getValue()));
							}
							mathML = new MathML(lisAt,lis.get(0).getAttributeValue("label"),false,false);
						/* *************************************************************************/
							
					}else
						mathML = new MathML(null,lis.get(0).getAttributeValue("label"),false,false);
	
					BaseOperator ope = new BaseOperator(Integer.valueOf(el.getAttributeValue("id")),Boolean.valueOf(el.getAttributeValue("isText")),el.getAttributeValue("name"),mathML,el.getAttributeValue("symbolEditor"),el.getAttributeValue("symbolMathML"),el.getAttributeValue("type"),el.getAttributeValue("pos"),Integer.valueOf(el.getAttributeValue("priority")));
					this.numbersVariablesList.add(ope);				
				}
				
			/* ************************************************************************************/
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public BaseOperator getOperator(int id){
		BaseOperator result = null;
		
		Iterator<BaseOperator> ite = this.operatorsList.iterator();
		while(ite.hasNext() && result == null){
			BaseOperator ao = (BaseOperator)ite.next();
			if(ao.getId() == id)
				result = ao;
		}
		
		return result;
	}
	
	public BaseOperator getVariableOrNumber(String name){
		
		BaseOperator result = null;
		
		Iterator<BaseOperator> ite = this.numbersVariablesList.iterator();
		while(ite.hasNext() && result == null){
			BaseOperator ao = (BaseOperator)ite.next();
			if(ao.getName().equalsIgnoreCase(name))
				result = ao;
		}
		
		return result;
	}
	
	public BaseFunction getFunction(String function){
		BaseFunction result = null;
		
		Iterator<BaseFunction> ite = this.functionsList.iterator();
		while(ite.hasNext() && result == null){
			BaseFunction af = (BaseFunction)ite.next();
			if(af.getName().equalsIgnoreCase(function))
				result = af;
		}
		
		return result;
	}
	
	public List<BaseOperator> getNumbersVariablesList() {
		return numbersVariablesList;
	}

	public List<BaseOperator> getOperatorsList() {
		return operatorsList;
	}

	public List<BaseFunction> getFunctionsList() {
		return functionsList;
	}
	
	public static void main(String[] args){
		OperatorsBaseManager o = new OperatorsBaseManager();
		
		Iterator<BaseFunction> ite = o.getFunctionsList().iterator();
		while(ite.hasNext()){
			BaseFunction ao = (BaseFunction)ite.next();
			System.out.println(ao);
		}
	}
	
}
