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
 * File: IncorrectExpression.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.MathEditor;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class IncorrectExpression extends MathEditor {

	private List<MathEditor> list;
	
	public IncorrectExpression(MathEditor parent) {
		super("incorrectExpression", "incorrectExpression", -1, parent);
		// TODO Auto-generated constructor stub
		this.list = new Vector<MathEditor>();
	}

	public void addMathEditor(MathEditor math){
		this.list.add(math);
	}
	
	public List<MathEditor> getList(){
		return list;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str="*** incorrect expression ***\n";
		
		Iterator<MathEditor> ite = this.list.iterator();
		while(ite.hasNext()){
			MathEditor m = ite.next();
			str += "   > " + m + "\n";
		}
		
		str+="****************************";
		
		return str;
	}

}
