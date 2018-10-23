package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.module.cpswarm.api.CPSWarmParameters;
import org.modelio.module.cpswarm.frevo.ProjectGeneration;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("c0e3b8a9-ee46-42e7-bfd1-bd79e0ee3a17")
public class FrevoGeneration extends DefaultModuleCommandHandler {
    @objid ("026c557d-18f1-48c0-a6c5-4d24b98a2efa")
     String title = "Complete";

    @objid ("b78d34f4-4d44-44e9-be81-0c04e30de8e8")
     String description = "Generation Complete";

    @objid ("98a38252-89f8-4961-b222-15ee401ddcdb")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        org.modelio.metamodel.uml.statik.Class selectedElt = (org.modelio.metamodel.uml.statik.Class) selectedElements.get(0);
              
        IModuleUserConfiguration configuration = module.getModuleContext().getConfiguration();
        String rosWs = configuration.getParameterValue(CPSWarmParameters.WORKSPACEPATH);       
        String rosPath = configuration.getParameterValue(CPSWarmParameters.ROSPATH);        
        String frevoPath = configuration.getParameterValue(CPSWarmParameters.FREVOPATH);
        
        ProjectGeneration generation = new ProjectGeneration(selectedElt);
        generation.generate(rosWs, rosPath, frevoPath);
        completeBox();
    }

    @objid ("140e3607-cad8-4bfb-9075-81fe86d41c37")
    protected void completeBox() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
            }
        });
    }

    @objid ("d883a8e6-1985-4d98-ac81-b37bab8d9b4c")
    void customMessageBox(int icon) {
        MessageBox messageBox = new MessageBox(Display.getCurrent().getActiveShell(), icon);
        messageBox.setMessage(this.description);
        messageBox.setText(this.title);
        messageBox.open();
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("720bcaee-c600-4fa7-9479-5442c2a79aa7")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1 )){
            MObject selected = selectedElements.get(0);
            return (selected instanceof org.modelio.metamodel.uml.statik.Class);
        }
        return false;
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("27a9a39d-5910-40f1-a672-3d048b85b044")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
