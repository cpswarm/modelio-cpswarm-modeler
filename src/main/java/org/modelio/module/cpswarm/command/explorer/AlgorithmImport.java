package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.module.cpswarm.ui.window.AlgorithmImportWindow;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of the IModuleContextualCommand interface.
 * <br>The module contextual commands are displayed in the contextual menu and in the specific toolbar of each module property page.
 * <br>The developer may inherit the DefaultModuleContextualCommand class which contains a default standard contextual command implementation.
 */
@objid ("33d706ee-fe05-4e87-a6bc-b30f7950ebae")
public class AlgorithmImport extends DefaultModuleCommandHandler {
    /**
     * @see org.modelio.api.module.commands.DefaultModuleContextualCommand#actionPerformed(java.util.List,
     * org.modelio.api.module.IModule)
     */
    @objid ("8984a6b7-7048-4ed1-9902-dcd3b0b631b1")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        AlgorithmImportWindow importW = new AlgorithmImportWindow(Display.getCurrent().getActiveShell());
        importW.setSelectedElt((ModelElement)selectedElements.get(0));
        importW.open();
    }

    /**
     * @see org.modelio.api.module.commands.DefaultModuleContextualCommand#accept(java.util.List,
     * org.modelio.api.module.IModule)
     */
    @objid ("d06b8bbd-0b39-4090-b54b-9e35ec035cd0")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        // Check that there is only one selected element
        return ((selectedElements.size() == 1)
                && (selectedElements.get(0) instanceof Component));
    }

    /**
     * Constructor.
     */
    @objid ("afb84df7-6ea8-4840-b0fb-7c7399e05913")
    public AlgorithmImport() {
        super();
    }

}
