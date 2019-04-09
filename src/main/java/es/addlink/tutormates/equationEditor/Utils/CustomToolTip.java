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
 * File: CustomToolTip.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Utils;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;

/**
 * @author Ignacio Celaya Sesma
 *
 */
public class CustomToolTip extends ToolTip {

	private Shell sh;
	private int milliseconds;
	
	/**
	 * @param parent
	 * @param style
	 */
	public CustomToolTip(Shell parent, int style, int milliseconds) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		this.milliseconds = milliseconds;
		this.sh = parent;
		setAutoHide(false);
	}

	public void activeAutoHide(){
		
		
		TimerTask CmpHide = new TimerTask() 
		{
			public void run() {
				sh.getDisplay().asyncExec(new Runnable(){
					public void run() {
						setVisible(false);
					}
				});
			}
		};
		Timer timer = new Timer("time");
		timer.schedule(CmpHide, this.milliseconds);
	}
	
	/**
	 * Método necesario para poder heredar una clase de Text.
	 */
	@Override
	protected void checkSubclass() {}

}
