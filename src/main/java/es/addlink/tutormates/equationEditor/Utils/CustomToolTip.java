package es.addlink.tutormates.equationEditor.Utils;

import java.util.Timer;
import java.util.TimerTask;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;

/**
 * @author Ignacio Celaya Sesma
 */
@objid ("71855d85-26f4-4703-a18f-47627986e419")
public class CustomToolTip extends ToolTip {
    @objid ("f57709f3-d2d5-4faf-abc1-53cad17fd0b9")
    private int milliseconds;

    @objid ("5567b440-c69f-409b-b317-d760a3b6cbe6")
    private Shell sh;

    /**
     * @param parent
     * @param style
     */
    @objid ("401c8c77-72d2-4349-a29b-9a6727befd87")
    public CustomToolTip(Shell parent, int style, int milliseconds) {
        super(parent, style);
        // TODO Auto-generated constructor stub
        this.milliseconds = milliseconds;
        this.sh = parent;
        setAutoHide(false);
    }

    @objid ("981a2a4f-9f72-4780-8e6e-e3702b21b7c3")
    public void activeAutoHide() {
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
    @objid ("417fa3e3-850a-4476-b8b0-991dbcbd7019")
    @Override
    protected void checkSubclass() {
    }

}
