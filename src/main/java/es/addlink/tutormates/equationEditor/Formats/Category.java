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
 * File: Category.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Formats;

/**
 * Categorías de los operadores.
 * 
 * @author Ignacio Celaya Sesma
 */
public class Category {
	public final static String SimpleOperatorsGroup = "simpleOperators";
	public final static String ComplexOperatorsGroup = "complexOperators";
	public final static String TextOperatorsGroup = "textOperators";
	public final static String VariablesGroup = "variables";
	
	public final static String WithoutEntriesType = "numWithoutEntries";
	public final static String TextType = "texto";
	public final static String UnaryType = "unary";
	public final static String UnaryComplexType = "unaryComplex";
	public final static String BinaryType = "binary";
	public final static String TernaryType = "ternary";
}
