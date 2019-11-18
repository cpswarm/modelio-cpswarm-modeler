package es.addlink.tutormates.equationEditor.Role;

import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

@objid ("a006ecd8-3213-475f-971a-610f0f8bff37")
public class TabToolBar {
    @objid ("92b92494-269a-4428-8019-4757df32faa5")
    private String name;

    @objid ("d42d963d-3eab-4bd8-896c-b2f310fc8163")
    private String text;

    @objid ("6b779ed9-7e96-4925-b122-cc91c7a08b05")
    private int columns;

    @objid ("55e0936c-7b5a-406d-bedf-426a9e519630")
    private Boolean visible;

    @objid ("51b842bc-d4e2-4535-ad18-4e86ae2bacdc")
    private Boolean enabled;

    @objid ("c24060c2-7f16-4b37-b4c9-e1ad0fed9f80")
    private List<ItemToolBar> listItemToolBar;

    @objid ("19b5a94f-f99a-4274-81ca-699c968df9a3")
    private Image icon;

    @objid ("b39b8962-8e8c-4799-b6ab-a577c6233148")
    public TabToolBar(List<ItemToolBar> listItemToolBar, String name, String text, Image icon, int columns, Boolean show, Boolean enabled) {
        super();
        this.listItemToolBar = listItemToolBar;
        this.name = name;
        this.text = text;
        this.icon = icon;
        this.columns = columns;
        this.visible = show;
        this.enabled = enabled;
    }

    @objid ("18a07dee-b242-4aec-8e53-389effe6a8ab")
    public String getText() {
        return text;
    }

    @objid ("8381cdb1-e581-4750-a928-08d2dba8c655")
    public List<ItemToolBar> getListItemToolBar() {
        return listItemToolBar;
    }

    @objid ("0f376d6c-b2b1-4f49-9426-99b95f14ffa5")
    public String getName() {
        return name;
    }

    @objid ("fd4d9c64-8495-425d-b82c-4b0c352c640b")
    public Image getIcon() {
        return icon;
    }

    @objid ("e8171a11-1025-40f3-8890-62d991c44406")
    public int getColumns() {
        return columns;
    }

    @objid ("74e8b849-bb83-4fd5-ab4f-8ba536adaa41")
    public Boolean getShow() {
        return visible;
    }

    @objid ("f2ba34d6-3566-443f-9ef9-22a06f6e154c")
    public Boolean getEnabled() {
        return enabled;
    }

    @objid ("d1cfa856-0d53-40fa-b6e7-94aa49f1688c")
    @Override
    public String toString() {
        String str="";
        
        str += name;
        str += " ~ " + text;
        str += " ~ " + columns;
        str += " ~ " + visible;
        str += " ~ " + enabled;
        str += " ~ " + icon;
        
        if(this.listItemToolBar != null){
            str += " ~ botones: ";
            Iterator<ItemToolBar> ite = this.listItemToolBar.iterator();
            while(ite.hasNext()){
                ItemToolBar item = (ItemToolBar) ite.next();
                str += item + ", ";
            }
        }
        return str;
    }

    /**
     * @param args
     */
    @objid ("e1a8f370-3b96-45d3-882a-7239f1175c84")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

}
