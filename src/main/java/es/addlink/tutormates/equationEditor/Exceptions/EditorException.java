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
 * File: EditorException.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Exceptions;

/**
 * Clase padre que engloba a todas las excepciones propias del editor.
 * 
 * @author Ignacio Celaya Sesma
 */
public class EditorException extends Exception {
	
	/**
	 * 
	 */
	static final long serialVersionUID = 0;
	
	private Throwable ex;

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public EditorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		
		this.ex = arg1;
	}

	/**
	 * 
	 * @param arg0
	 */
	public EditorException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Muestra la excepción por consola de forma resumida.
	 */
	public void showError(){
		System.err.println("# Equation Editor [Handled Exception]: Excepción capturada en el método '" + this.getStackTrace()[1].getMethodName() + "' de la clase '" + this.getStackTrace()[1].getClassName() + "'. Para más información, ejecutar 'showExtendedError()'.");
	}
	
	/**
	 * Muestra la excepción por consola de forma extendida.
	 */
	public void showExtendedError(){		
		System.err.println(getExtendedError());
	}
	
	public String getExtendedError(){
		String str="";
		
		str += "# Equation Editor [Handled Exception] *******************************************************\n";
		str += "    ·Message 1:  " + this.ex.getMessage() +"\n";
		str += "    ·Message 2:  " + this.getMessage() +"\n";
		str += "    ·Exception:  " + this.getClass().getSimpleName() +"\n";
		str += "    ·Cause:      " + this.ex.getCause() +"\n";
		str += "    ·Class:      " + this.getStackTrace()[0].toString() +"\n";
		str += "    ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
		str += "    Nota: Si ve este error notifíquelo a <ignacio.celaya@tutormates.es> por favor. Explique detallademente (si lo sabe) como ha saltado.\n";
		str += "  *******************************************************************************************";
		return str;
	}
	
}
