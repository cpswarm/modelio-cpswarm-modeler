package es.addlink.tutormates.equationEditor.Display;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Manager.Manager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * Clase encargada de construir un ToolBar con un sólo ToolItem para representar un sólo botón.
 * 
 * @author Ignacio Celaya Sesma
 */
@objid ("b098069d-9f63-4607-8cf1-c3b4569b2e77")
public class ButtonToolBar {
    /**
     * Constructor.
     * @param parent Composite donde está situado el ToolBar.
     * @param image Imagen de fondo del ToolItem.
     * @param toolTipText ToolTipText que se muestra al establecer el ratón sobre el ToolItem.
     * @param style Estilo.
     * @param isString Si se trata de insertar una cadena (la del parámetro loadParameter) entonces es true, false en caso contrario.
     * @param loadParameter Cadena o nombre del componente que se va a insertar.
     */
    @objid ("b8ff053f-8d47-48cc-8485-b5bd7e7baf0a")
    public ButtonToolBar(final Manager manager, Composite parent, Image image, String toolTipText, int style, final Boolean isString, final String loadParameter, Boolean enabled) {
        ToolBar toolbar = new ToolBar(parent, SWT.NONE);
        ToolItem item = new ToolItem(toolbar, style);
        if(image != null)
            item.setImage(image);
        
        item.setToolTipText(toolTipText);
        
        //if(!isString)
            //System.out.println("-------\nImagen: " + image + "\nToolTip: " + toolTipText + "\nEstilo: " + style + "\nEs cadena: " + isString + "\nParametro: " + loadParameter);
        
        item.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                try{
                    if (isString)
                        manager.getCentralManager().insertarCadena(loadParameter);
                    else{
                        manager.getCentralManager().insertarComponenteNoTexto(loadParameter);
                    }
                }catch(Exception ex){}
            }
        });
        item.setEnabled(enabled);
    }

}
