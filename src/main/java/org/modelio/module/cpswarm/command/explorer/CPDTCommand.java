package org.modelio.module.cpswarm.command.explorer;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.module.cpswarm.utils.CPSWarmResourcesManager;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("da768886-f05b-465b-9237-a1c090d7acc4")
public class CPDTCommand extends DefaultModuleCommandHandler {
    @objid ("00ae53f7-0f81-4fb8-9e41-82cc39257db6")
     String title = "Complete";

    @objid ("99ab8239-3523-450a-9214-66b0e778da59")
     String description = "Generation Complete";

    @objid ("9173180e-60b7-45e2-b669-b44431e955c4")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        MObject owner = selectedElements.get(0);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Template", owner);
        
        String templatePath = CPSWarmResourcesManager.getPattern("Template_1.0.00.umlt");
        
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try(ITransaction transaction = session.createTransaction ("Problem Creation")){
            module.getModuleContext().getModelioServices().getPatternService().applyPattern(Paths.get(templatePath), parameters);
            transaction.commit ();
            completeBox();
            Collection<StaticDiagram> diagrams = module.getModuleContext().getModelingSession().findByClass(StaticDiagram.class);
            IEditionService editionService = module.getModuleContext().getModelioServices().getEditionService();
            for( StaticDiagram diagram : diagrams){
                editionService.openEditor(diagram);
            }
            
        } catch (InvalidParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("c133add4-fc1e-48d3-b796-6b20f6fd7814")
    protected void completeBox() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
            }
        });
    }

    @objid ("88ede7ce-f7b7-4764-a232-d0b59ab3d304")
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
    @objid ("2d9a751d-6151-41cd-b513-3c3995a5013e")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1 )){
            MObject selected = selectedElements.get(0);
            return (selected instanceof org.modelio.metamodel.uml.statik.Package) &&
                    (!(selected instanceof Profile));
        }
        return false;
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @objid ("fdeecf8c-99b2-4eaa-9ee8-ecccbdafa555")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
