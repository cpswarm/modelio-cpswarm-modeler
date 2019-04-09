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
 * File: MathEditor.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * árbol genérico, el único con el que es capaz de trabajar DIRECTAMENTE el editor.
 * 
 * @author Nuria García
 * @author Ignacio Celaya Sesma
 */
public abstract class MathEditor {

	/**
	 * Identificador del componente.
	 */
	private int id;

	/**
	 * Nombre del componente.
	 */
	private String name;

	/**
	 * Padre del objeto MathEditor.
	 */
	private MathEditor parent = null;

	/**
	 * Grupo al que pertenece el componente. Grupos: unario, binario, especial, numero.
	 */
	private String grupo; // posibles valores: binario, unario, especial, operacion, numero

	/**
	 * Constructor
	 * 
	 * @param grupo Grupo al que pertenece el componente. Grupos: unario, binario, especial, numero.
	 * @param name Nombre del componente.
	 * @param id Identificador del componente.
	 * @param parent Padre del objeto MathEditor.
	 */
	public MathEditor(String grupo, String name, int id, MathEditor parent) {
		this.grupo = grupo;
		this.name = name;
		this.id = id;
		this.parent = parent;
	}

	/**
	 * Devuelve el nombre del grupo al que pertenece el componente.
	 * 
	 * @return Nombre del grupo al que pertenece el componente.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Devuelve el nombre del componente.
	 * 
	 * @return Nombre del componente.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve el identificador del componente.
	 * 
	 * @return Identificador del componente.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Devuelve el padre del objeto MathEditor.
	 * 
	 * @return Padre del objeto MathEditor.
	 */
	public MathEditor getParent() {
		return parent;
	}

	/**
	 * Establece el padre del objeto MathEditor.
	 * 
	 * @param newParent Nuevo padre para el objeto MathEditor.
	 */
	public void setParent(MathEditor newParent) {
		parent = newParent;
	}

	/**
	 * Devuelve el MathEditor en formato String.
	 * 
	 * @return MathEditor en formato String.
	 */
	public abstract String toString();

	/**
	 * Devuelve true si el MathEditor pasado por parámetro está bien formado, false en caso contrario.
	 * 
	 * @param object Objeto de tipo MathEditor para determinar si está bien formado.
	 * @return True si el MathEditor pasado por parámetro está bien formado, false en caso contrario.
	 */
	public static boolean isCorrect(MathEditor object) {
		try {
			if (object == null) {
				return false;
			}
			else {
				
				if (object.getGrupo() == "binario") {
					Binary bin = (Binary) object;

					boolean res;
					if ((bin == null) || (bin.getLeftChild() == null) || (bin.getRightChild() == null))
						return false;
					else {
						res = isCorrect(bin.getLeftChild());
						if (res)
							res = isCorrect(bin.getRightChild());
						if (res)
							return true;
						else
							return false;
					}
				}
				else if (object.getGrupo() == "unario") {
					Unary una = (Unary) object;
					if ((una == null) || (una.getChild() == null))
						return false;
					else
						return isCorrect(una.getChild());
				}
				else if(object.getName().equalsIgnoreCase("repeatingDecimal")){
					RepeatingDecimal rd = (RepeatingDecimal)object;
					if(rd.getFirstChild() == null  || rd.getThirdChild() == null)
						return false;
					
					try{
						((IntNumber)rd.getFirstChild()).getNumber();
					}catch(Exception ex){
						return false;
					}

					if(rd.getSecondChild() != null && ((StringNumber)rd.getSecondChild()).getText().trim().equalsIgnoreCase(""))
						return false;
					
					String third = ((StringNumber)rd.getThirdChild()).getText().trim();
					if(third.equalsIgnoreCase(""))
						return false;
					else{
						try{
							Integer.parseInt(third);
						}catch(Exception ex){
							return false;
						}
					}
					
				}

				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public static MathEditor getValue(String value){
		MathEditor mathEditor = null;

		if(esEntero(value)){
			BigInteger num = new BigInteger(value);
			mathEditor = new IntNumber(num,null);
		}
		else if(esReal(value)){
			BigDecimal num = new BigDecimal(value);
			mathEditor = new RealNumber(num,null);
		}
		
		return mathEditor;
	}
	
	/**
	 * Devuelve true si el valor es entero, false en caso contrario.
	 * 
	 * @param num Valor que se quiere estudiar.
	 * @return True si el valor es entero, false en caso contrario.
	 */
	private static boolean esEntero(String num) {
		try{
			
			//Decide que un número del tipo 8.0 no es entero
			String[] str = num.split("\\.");			
			if(str.length > 1){
				if(str[str.length-1].equalsIgnoreCase("0"))
					return false;
			}
			
			
			double d = Double.parseDouble(num);
			if ((d % 1) == 0)	return true;
			else return false;
		}catch(NumberFormatException ex){
			return false;
		}
	}
	
	/**
	 * Devuelve true si el valor es un número real, false en caso contrario.
	 * 
	 * @param num Valor que se quiere estudiar.
	 * @return True si el valor es un número real, false en caso contrario.
	 */
	private static boolean esReal(String num) {
		boolean esReal;
		if (num.length() == 0) esReal = false;
		else esReal = true;

		try { Double.parseDouble(num);}
		catch (NumberFormatException e) { esReal = false;}

		return esReal;
	}
	
	/**
	 * Devuelve true si los dos MathEditor pasados por parámetros son iguales, false en caso contrario.
	 * 
	 * @param obj1 Primer objeto de tipo MathEditor para comparar.
	 * @param obj2 Segundo objeto de tipo MathEditor para comparar.
	 * @return True si los dos MathEditor pasados por parámetros son iguales, false en caso contrario.
	 */
	public static boolean equals(MathEditor obj1, MathEditor obj2) {
		try {
			if (obj1 == null || obj2 == null) {
				return false;
			}
			else {
				if (obj1.getName().equals(obj2.getName())) {
					if (obj1.getGrupo().equals("binario")) {
						Binary bin1 = (Binary) obj1;
						Binary bin2 = (Binary) obj2;
						return (equals(bin1.getLeftChild(), bin2.getLeftChild()) && equals(bin1.getRightChild(), bin2.getRightChild()));
					}
					if (obj1.getGrupo().equals("unario")) {
						Unary una1 = (Unary) obj1;
						Unary una2 = (Unary) obj2;
						return (equals(una1.getChild(), una2.getChild()));
					}
					if (obj1.getGrupo().equals("especial")) {
						RepeatingDecimal per1 = (RepeatingDecimal) obj1;
						RepeatingDecimal per2 = (RepeatingDecimal) obj2;
						return (equals(per1.getFirstChild(), per2.getFirstChild()) && equals(per1.getSecondChild(), per2.getSecondChild()) && equals(per1.getThirdChild(), per2.getThirdChild()));
					}
					if (obj1.getName().equals("entero")) {
						IntNumber int1 = (IntNumber) obj1;
						IntNumber int2 = (IntNumber) obj2;
						return (int1.getNumber() == int2.getNumber());
					}
					if (obj1.getName().equals("real")) {
						RealNumber real1 = (RealNumber) obj1;
						RealNumber real2 = (RealNumber) obj2;
						return (real1.getNumber() == real2.getNumber());
					}
					if (obj1.getName().equals("variable")) {
						Variable var1 = (Variable) obj1;
						Variable var2 = (Variable) obj2;
						return (var1.getVar() == var2.getVar());
					}
					if (obj1.getGrupo().equals("")) { // Se trata de un TextoObject
						StringNumber txt1 = (StringNumber) obj1;
						StringNumber txt2 = (StringNumber) obj2;
						return (txt1.getText().equals(txt2.getText()));
					}
					return false;
				}
				else {
					return false;
				}
			}
		} catch (Exception ex) {
			return false;
		}
	}
}
