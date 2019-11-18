package es.addlink.tutormates.equationEditor.Display;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import es.addlink.tutormates.equationEditor.Utils.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * Crea el GUI para la versión de generar y cargar MathML, ejecutada desde Equation_Editor_SWT_MathMLTest.java.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("97c53fd5-9396-48cc-a4a8-97247563b14c")
public class GUIMathMLTest {
    @objid ("2122f02c-47e2-4e41-a5fb-62f08ae6a960")
    private Manager manager;

    @objid ("5431079e-1622-4993-b663-103a4fa707bf")
    private Composite parent;

    @objid ("164eff15-0e9f-4895-b666-fa71a9152ed9")
    private Text txtIn;

    @objid ("e99b4336-c470-49a0-bd41-860a895e4060")
    private Text txtOut;

    @objid ("2c1e268e-59a8-4f0d-ba05-0388b99cc13e")
    private Button btnImport;

    @objid ("87411469-fe33-4d9f-8be9-b08bc21eba98")
    protected GUIMathMLTest(Composite parent, Manager manager) {
        this.parent = parent;
        this.manager = manager;
    }

    @objid ("b002bd0e-cd15-4ff1-bc27-c5c173032ed8")
    protected void createGUI() {
        this.manager.getActionsToolBar().getImportToolItem().setEnabled(false);
        
        Composite c = new Composite(this.parent,SWT.NONE);
        
        GridLayout grid = new GridLayout(3,false);
        grid.marginLeft = 0;
        grid.marginBottom = 0;
        grid.marginTop = 5;
        grid.marginRight = 0;
        
        
        c.setLayout(grid);
        c.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
        
        //OUT - verde
        Label lblOut = new Label(c,SWT.NONE);
        lblOut.setText("MathML de\nsalida:");
        
        this.txtOut = new Text(c,SWT.BORDER);
        this.txtOut.setFont(new Font(this.parent.getDisplay(), "Courier New", 11, SWT.BOLD));
        this.txtOut.setForeground(new Color(this.parent.getDisplay(),4,108,2));
        this.txtOut.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        this.txtOut.setEditable(false);
        
        Button btnOut = new Button(c,SWT.NONE);
        Image imgCopy = SWTResourceManager.getImage(GUIEditor.class, "images/copy-to-clipboard.png");
        btnOut.setImage(imgCopy);
        btnOut.setToolTipText("Copiar MathML");
        
        btnOut.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                try{
                org.eclipse.swt.dnd.Clipboard clipboard = new org.eclipse.swt.dnd.Clipboard(parent.getDisplay());
                String plainText = txtOut.getText();
                
                TextTransfer textTransfer = TextTransfer.getInstance();
                
                if(!plainText.trim().equalsIgnoreCase(""))
                    clipboard.setContents(new String[]{plainText}, new Transfer[]{textTransfer});
                else
                    clipboard.setContents(new String[]{" "}, new Transfer[]{textTransfer});
                
                clipboard.dispose();
                }catch(Exception ex){ex.printStackTrace();}
            }
        });
        
        
        //IN azul
        Label lblIn = new Label(c,SWT.NONE);
        lblIn.setText("Comprobar\nMathML:");
        
        this.txtIn = new Text(c,SWT.BORDER);
        txtIn.setFont(new Font(this.parent.getDisplay(), "Courier New", 11, SWT.BOLD));
        txtIn.setForeground(new Color(this.parent.getDisplay(),16,37,169));
        txtIn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        btnImport = new Button(c,SWT.NONE);
        Image imgIn = SWTResourceManager.getImage(GUIEditor.class, "images/ok.png");
        btnImport.setImage(imgIn);
        btnImport.setToolTipText("Importar expresión");
        
        //Limpiar
        new Label(c,SWT.NONE);
        new Label(c,SWT.NONE);
        /*Label lblAviso = new Label(c,SWT.NONE);
        lblAviso.setFont(new Font(this.parent.getDisplay(), "Arial", 8, SWT.NONE));
        lblAviso.setForeground(new Color(this.parent.getDisplay(),200,0,0));
        lblAviso.setText("No se podrá COMPROBAR expresiones que contengan algún\nelemento de estos:\n\n* Subíndices: sub, <msub>");*/
        Button btnLimpiar = new Button(c,SWT.NONE);
        
        Image imgClean = SWTResourceManager.getImage(GUIEditor.class, "images/clean.png");
        btnLimpiar.setImage(imgClean);
        
        btnLimpiar.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                try{
                    txtOut.setText("");
                    txtIn.setText("");
                }catch(Exception ex){ex.printStackTrace();}
            }
        });
        c.pack();
    }

    @objid ("60be5d55-c4a3-4e1b-b0c0-07223a9e2cc2")
    public Button getBtnImport() {
        return btnImport;
    }

    @objid ("eb229ab9-7172-46b1-ae38-fb66d15702e0")
    public void setTextOut(String str) {
        this.txtOut.setText(str);
    }

    @objid ("c19498e8-557b-4e66-9382-4d9bb796f299")
    public void setTextIn(String str) {
        this.txtIn.setText(str);
    }

    @objid ("71743475-315e-4050-b985-bab9e01a6f67")
    public String getTextImput() {
        return this.txtIn.getText();
    }

}
