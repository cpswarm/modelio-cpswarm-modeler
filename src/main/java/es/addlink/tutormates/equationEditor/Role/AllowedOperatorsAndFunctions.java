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
 * File: AllowedOperatorsAndFunctions.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Role;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import es.addlink.tutormates.equationEditor.Formats.MathML.MathML;
import es.addlink.tutormates.equationEditor.Role.BaseFunction;
import es.addlink.tutormates.equationEditor.Role.BaseOperator;

public class AllowedOperatorsAndFunctions {

	private List<BaseFunction> functionsList;
	private List<BaseOperator> numbersVariablesList;
	private List<BaseOperator> operatorsList;
	
	public AllowedOperatorsAndFunctions() {
		//TODO Auto-generated constructor stub
	}
	
	public Boolean existsFunction(String nameFunction){
		Iterator<BaseFunction> iterator = functionsList.iterator();
		while(iterator.hasNext()){
			BaseFunction af = iterator.next();
			if(af.getName().equalsIgnoreCase(nameFunction))
				return true;
		}
		return false;
	}

	public int getPriority(String value){
		int priority = -1;
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getSymbolEditor().equalsIgnoreCase(value)){
				exists = true;
				priority = ope.getPriority();
			}
		}

		return priority;
	}
	
	public Boolean existsOperator(String value){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getSymbolEditor().equalsIgnoreCase(value))
				exists = true;
		}
		if(!exists && value.equalsIgnoreCase("neg")) exists=true;
		return exists;
	}

	public Boolean existsOperatorById(int id){
		Boolean exists = false;
		
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getId() == id)
				exists = true;
		}
		
		return exists;
	}

	public Boolean existsOperatorByMathML(String mathML){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			MathML mathml = ope.getMathML();
			if(mathml.getMathMLLabel().equalsIgnoreCase(mathML))
				exists = true;
		}
		return exists;
	}

	public Boolean existsOperatorByName(String name){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getName().equalsIgnoreCase(name))
				exists = true;
		}
		return exists;
	}

	public Boolean existsOperatorByPriority(int priority, String operator){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getPriority() == priority && ope.getSymbolEditor().equals(operator)){
				exists = true;
				
			}
		}
		return exists;
	}
	
	/**
	 * Obtiene los operadores del grupo y tipo especificados.
	 * Ejemplo de grupos:
	 *  > simpleOperators
	 *  > complexOperators
	 *  > textOperators
	 *  > variables
	 * 
	 * Ejemplo de tipos:
	 * 	> unary
	 * 	> binary
	 *  > variable
	 *  
	 * @param group
	 * @param type
	 * @return
	 */
	public Boolean existsOperatorByType(String type, String operator){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equals(operator)){
				exists = true;
			}
		}
		return exists;
	}
	
	public Boolean existsOperatorByTypeAndId(String type, int id){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getId() == id)
				exists = true;
		}
		return exists;
	}
	
	/*public static String[] getArrayFunctionNames(){
		try{
		arrayFunctionNames = new String[listFunctions.size()];
		int i=0;
		if(listFunctions != null){
			Iterator<AllowedFunction> iterator = listFunctions.iterator();
			while(iterator.hasNext()){
				AllowedFunction af = iterator.next();
				arrayFunctionNames[i] = af.getName();
				i++;
			}
			return arrayFunctionNames;
		}
		return null;
		}catch(Exception e){e.printStackTrace(); return null;}
	}*/
	
	public Boolean existsOperatorByTypeAndName(String type, String name){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getName().equalsIgnoreCase(name))
				exists = true;
		}
		return exists;
	}
	
	public Boolean existsOperatorByTypeAndPriority(String type, int priority, String operator){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equals(operator) && ope.getPriority() == priority && !ope.getName().equals("parenthesis")){
				exists = true;
			}
		}
		return exists;
	}
	
	public Boolean existsOperatorByTypeAndSymbol(String type, String symbol){
		Boolean exists = false;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext() && !exists){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equalsIgnoreCase(symbol))
				exists = true;
		}
		
		return exists;
	}
	
	public Boolean existsVariable(String variable){
		try{
			Boolean exists = false;
			
			Iterator<BaseOperator> ite = numbersVariablesList.iterator();
			
			BaseOperator ope = null;
			while(ite.hasNext() && !exists){
				ope = ite.next();				
				if(ope.getSymbolMathML().equals(variable) && ope.getType().equals("variable"))
					exists = true;
			}
			return exists;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public BaseOperator getVariable(String variable){
		try{
			Boolean exists = false;
			
			
			Iterator<BaseOperator> ite = numbersVariablesList.iterator();
			
			BaseOperator ope = null;
			while(ite.hasNext() && !exists){
				ope = ite.next();				
				if(ope.getSymbolMathML().equals(variable) && ope.getType().equals("variable"))
					exists = true;
				
					
			}
			return ope;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public int getEntriesFunction(String nameFunction){
		Iterator<BaseFunction> iterator = functionsList.iterator();
		while(iterator.hasNext()){
			BaseFunction af = iterator.next();
			if(af.getName().equalsIgnoreCase(nameFunction))
				return af.getEntries();
		}
		return -1;
	}
	
	public BaseFunction getFunction(String name){
		
		Iterator<BaseFunction> ite = functionsList.iterator();
		BaseFunction f = null;
		while(ite.hasNext()){
			f = ite.next();
			if(f.getName().equalsIgnoreCase(name))
				return f;
		}
		return null;
	}
	
	public List<BaseFunction> getFunctionsList() {
		return functionsList;
	}
	
	public List<BaseOperator> getNumbersVariablesByName(String name){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		Iterator<BaseOperator> ite = numbersVariablesList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getName().equalsIgnoreCase(name))
				outList.add(ope);
		}
		return outList;
	}
	
	public List<BaseOperator> getNumbersVariablesList() {
		return numbersVariablesList;
	}
	
	/**
	 * Obtiene los operadores del valor especificado.<br>
	 * Ejemplo de valores:<br>
	 *  > msqrt<br>
	 *  > a<br>
	 *  > +<br>
	 *  > *<br>
	 *  
	 * @param value
	 * @return
	 */
	public List<BaseOperator> getOperator(String value){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getSymbolEditor().equalsIgnoreCase(value))
				outList.add(ope);
		}
		return outList;
	}
	
	/**
	 * Obtiene los operadores del id especificado.
	 * @param id
	 * @return
	 */
	public BaseOperator getOperatorById(int id){
		BaseOperator outAllowedOperator = null;
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getId() == id)
				outAllowedOperator = ope;
		}
		return outAllowedOperator;
	}
	
	public BaseOperator getOperatorByName(String name){
		
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getName().equalsIgnoreCase(name)){
				return ope;
			}
		}
		return null;
	}
	
	public BaseOperator getOperatorByName(List<BaseOperator> l,String name){
		
		Iterator<BaseOperator> ite = l.iterator();
		BaseOperator ope = null;
		
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getName().equalsIgnoreCase(name)){
				return ope;
			}
		}
		return null;
	}
	
	/**
	 * Obtiene los operadores del nombre especificado.
	 * Ejemplo de mathML's:
	 * 	> mo
	 * 	> mi
	 *  > mfrac
	 *  
	 * @param mathML
	 * @return
	 */
	public List<BaseOperator> getOperatorsByMathML(String mathML){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getMathML().getMathMLLabel().equalsIgnoreCase(mathML)){
				outList.add(ope);
			}
		}
		return outList;
	}
	
	/**
	 * Obtiene los operadores del nombre especificado.
	 * Ejemplo de nombres:
	 * 	> subtraction
	 * 	> multiplication
	 *  > squareRoot
	 *  > var_a
	 *  
	 * @param name
	 * @return
	 */
	public List<BaseOperator> getOperatorsByName(String name){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getName().equalsIgnoreCase(name))
				outList.add(ope);
		}
		return outList;
	}
	
	public List<BaseOperator> getOperatorsBySymbolEditor(String symbol){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getSymbolEditor().equalsIgnoreCase(symbol)){
				outList.add(ope);
			}
		}
		return outList;
	}
	
	/**
	 * Obtiene los operadores del tipo especificado.
	 * Ejemplo de tipos:
	 * 	> unary
	 * 	> binary
	 *  > variable
	 * @param type
	 * @return
	 */
	public List<BaseOperator> getOperatorsByType(String type){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type))
				outList.add(ope);
		}
		return outList;
	}
	
	/**
	 * Obtiene los operadores del grupo y tipo especificados.
	 * Ejemplo de grupos:
	 *  > simpleOperators
	 *  > complexOperators
	 *  > textOperators
	 *  > variables
	 * 
	 * Ejemplo de tipos:
	 * 	> unary
	 * 	> binary
	 *  > variable
	 *  
	 * @param group
	 * @param type
	 * @return
	 */
	public List<BaseOperator> getOperatorsByTypeAndName(String type, String name){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getName().equalsIgnoreCase(name))
				outList.add(ope);
		}
		return outList;
	}
	
	public List<BaseOperator> getOperatorsByTypeAndSymbol(String type,String symbol){
		List<BaseOperator> outList = new Vector<BaseOperator>();
		Iterator<BaseOperator> ite = operatorsList.iterator();
		BaseOperator ope = null;
		while(ite.hasNext()){
			ope = ite.next();
			if(ope.getType().equalsIgnoreCase(type) && ope.getSymbolEditor().equalsIgnoreCase(symbol))
				outList.add(ope);
		}
		return outList;
	}
	
	public List<BaseOperator> getOperatorsList() {
		return operatorsList;
	}
	
	public void setFunctionsList(List<BaseFunction> functionsList) {
		this.functionsList = functionsList;
	}
	
	public void setNumbersVariablesList(List<BaseOperator> numbersVariablesList) {
		this.numbersVariablesList = numbersVariablesList;
	}
	
	public void setOperatorsList(List<BaseOperator> operatorsList) {
		this.operatorsList = operatorsList;
	}
}
