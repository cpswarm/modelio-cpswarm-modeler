package es.addlink.tutormates.equationEditor.Manager;

import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import es.addlink.tutormates.equationEditor.Display.ButtonToolBar;
import es.addlink.tutormates.equationEditor.Role.ItemToolBar;
import es.addlink.tutormates.equationEditor.Role.TabToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

@objid ("2148f929-b779-4c22-8429-b993938067ca")
public class ToolBarDisplayManager {
    @objid ("08042da7-27fe-41b2-b9d9-abb2dc2f6bc6")
    private Manager manager;

    @objid ("e912e454-fdf3-4ed6-9601-de649d713772")
    private TabFolder tabFolder;

    @objid ("c2df1d1f-d376-4959-b64f-01ebf86c89d9")
    public ToolBarDisplayManager(Manager manager, TabFolder tabFolder, String profile, String course, String unit) {
        this.manager = manager;
        this.tabFolder = tabFolder;
    }

    @objid ("57b9ad2d-54ee-41ea-814f-ad9d9debea11")
    public void buildMainToolBar() {
        TabItem tabItem = null;            
        
        Iterator<TabToolBar> ite = this.manager.getRoleManager().getRole().getListTabToolBar().iterator();
        while(ite.hasNext()){
            
            TabToolBar tab = (TabToolBar) ite.next();
            if(tab.getShow()){
                tabItem = new TabItem(this.tabFolder,SWT.NONE);
                tabItem.setText(tab.getText());
                tabItem.setImage(tab.getIcon());
                
                Composite cmp = new Composite(this.tabFolder,SWT.NONE);
                tabItem.setControl(cmp);
                GridLayout grid = new GridLayout();
                grid.horizontalSpacing = 0;
                grid.verticalSpacing = 0;
                grid.numColumns = tab.getColumns();
                grid.marginTop = 5;
                grid.marginLeft = 5;
                grid.marginBottom = 5;
                cmp.setLayout(grid);
                
                Iterator<ItemToolBar> it = tab.getListItemToolBar().iterator();
                while(it.hasNext()){
                    ItemToolBar button = (ItemToolBar) it.next();
                    int style = SWT.NONE;
                    if(button.getName().equalsIgnoreCase("separator"))
                        style = SWT.SEPARATOR;
                    
                    Boolean enabled;
                    if(tab.getEnabled() == true)
                        enabled = button.isEnabled();
                    else
                        enabled = false;
                    
                    new ButtonToolBar(this.manager,cmp,button.getIcon(),button.getTooltip(),style,button.isText(),button.getOperator(),enabled);
                    
                }
            }
        }
    }

}
