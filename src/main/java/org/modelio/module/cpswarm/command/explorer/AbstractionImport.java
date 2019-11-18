package org.modelio.module.cpswarm.command.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.cpswarm.ui.window.AbstractionImportWindow;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of the IModuleContextualCommand interface.
 * <br>The module contextual commands are displayed in the contextual menu and in the specific toolbar of each module property page.
 * <br>The developer may inherit the DefaultModuleContextualCommand class which contains a default standard contextual command implementation.
 */
@objid ("fa204117-7c40-41a9-b40e-d0cdad6e8887")
public class AbstractionImport extends DefaultModuleCommandHandler {
    /**
     * @see org.modelio.api.module.commands.DefaultModuleContextualCommand#actionPerformed(java.util.List,
     * org.modelio.api.module.IModule)
     */
    @objid ("bda18da8-7614-447b-ad27-ab8641da4e12")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        AbstractionImportWindow importW = new AbstractionImportWindow(Display.getCurrent().getActiveShell());
        importW.setSelectedElt((ModelElement)selectedElements.get(0));
        importW.open();
    }

    /**
     * @see org.modelio.api.module.commands.DefaultModuleContextualCommand#accept(java.util.List,
     * org.modelio.api.module.IModule)
     */
    @objid ("7f3ac120-3eff-44f4-b29a-b47b813cca8c")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        // Check that there is only one selected element
        return ((selectedElements.size() == 1)
                && (selectedElements.get(0) instanceof NameSpace));
    }

    /**
     * Constructor.
     */
    @objid ("ef5f6f35-19fa-4478-aa56-eac4d258a094")
    public AbstractionImport() {
        super();
    }

}
