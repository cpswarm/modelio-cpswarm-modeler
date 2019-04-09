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
 * File: Swing_launcher.java
 * ---------------
 * (C) Copyright 2011, by Addlink Research.
 *
 * Original Author:  Ignacio Celaya - ignacio.celaya@gmail.com (Addlink Research and University of La Rioja)
 * Contributor(s):
 */

package es.addlink.tutormates.equationEditor.Display;

import javax.swing.JFrame;
import javax.swing.UIManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import es.addlink.tutormates.equationEditor.Connections.TutorMatesEditor;

public class Swing_launcher {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		
		final TutorMatesEditor editor = new TutorMatesEditor("en",true,false);
		
		final Display display = new Display();
        final Shell shell = new Shell(display);
        
//		EmbeddedShell sh = SWT_Swing.newEmbeddedShell();
		
		
		try {
			
			//Capture 'Enter' key press - at the moment this feature doesn't work.
			KeyAdapter kAdapter = new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent event) {
					/*if ((event.keyCode == SWT.CR) || (event.keyCode == SWT.KEYPAD_CR)){					
						editor.getMathML();
					}*/
				}
			};
			
			//Build the Equation Editor's GUI
			editor.buildGUI(shell, kAdapter);
			
			//Get Mathml code when export button is pressed.
			editor.getExportToolItem().addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event arg0) {
					//String mathmlOutput = editor.getMathML();
					editor.getMathML();
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
       
        Composite myComp = new Composite(shell, SWT.EMBEDDED  | SWT.NO_BACKGROUND); 
		
		JFrame jf = new JFrame();
		jf.setTitle(editor.getTitle());
		jf.setSize(500, 300);
//		jf.add(myComp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
		shell.setText(editor.getTitle());
		shell.setSize(500, 300);
//		jf.add(myComp);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shell.setVisible(true);
		editor.insertFirstText();
		shell.pack();
		
        shell.open();
        while (!shell.isDisposed()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
        shell.dispose();
        display.dispose();
        
        System.out.print("test");
	}

}
