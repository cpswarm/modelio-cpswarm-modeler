package es.addlink.tutormates.equationEditor.Utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolItem;

@objid ("17c32852-7357-4130-8df2-d5f1ef06a022")
public class DropdownSelectionListener extends SelectionAdapter {
    @objid ("2e3e1bd2-556c-4774-9199-787bba615906")
    private ToolItem dropdown;

    @objid ("f57c3dc0-a6f8-4f7f-a307-6f3019c57013")
    private Menu menu;

    @objid ("8456ad00-fa4e-4147-a2d2-b2b9407899d2")
    public DropdownSelectionListener(ToolItem dropdown) {
        this.dropdown = dropdown;
        menu = new Menu(dropdown.getParent().getShell());
    }

    @objid ("40d9aab7-36fe-4120-8f47-c0cbd50b278e")
    public void add(ToolItem item) {
        MenuItem menuItem = new MenuItem(menu, SWT.NONE);
        menuItem.setImage(item.getImage());
        menuItem.setText("");
        menuItem.addSelectionListener(new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            MenuItem selected = (MenuItem) event.widget;
            dropdown.setText(selected.getText());
          }
        });
    }

    @objid ("d7859453-a88b-4169-b072-69de4dcff33e")
    public void widgetSelected(SelectionEvent event) {
        if (event.detail == SWT.ARROW) {
          ToolItem item = (ToolItem) event.widget;
          Rectangle rect = item.getBounds();
          Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
          menu.setLocation(pt.x, pt.y + rect.height);
          menu.setVisible(true);
        } else {
          //System.out.println(dropdown.getText() + " Pressed");
        }
    }

}
