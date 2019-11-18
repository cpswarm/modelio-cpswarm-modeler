package es.addlink.tutormates.equationEditor.Display;

import javax.swing.JFrame;
import javax.swing.UIManager;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Connections.TutorMatesEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

@objid ("470c9434-303c-4ae4-94aa-c6fd1e50d879")
public class Swing_launcher {
    /**
     * @param args
     */
    @objid ("b1c436f9-daee-42da-9f84-64aa0b285258")
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
        
        //        EmbeddedShell sh = SWT_Swing.newEmbeddedShell();
        
        
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
        //        jf.add(myComp);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        
        shell.setText(editor.getTitle());
        shell.setSize(500, 300);
        //        jf.add(myComp);
        //        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
