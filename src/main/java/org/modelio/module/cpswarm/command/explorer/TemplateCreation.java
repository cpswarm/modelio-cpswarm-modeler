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

@objid ("5c90520d-34f6-45d6-ad2b-02df5e39426d")
public class TemplateCreation extends DefaultModuleCommandHandler {
    @objid ("a920b048-582b-4d0e-8309-ef3f54f13e26")
     String title = "Complete";

    @objid ("af022d3f-e0f1-447c-8309-08387e9b9ef2")
     String description = "Generation Complete";

    @objid ("f4ab2900-8e19-4776-8963-5ed931b32a59")
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

    @objid ("8385997b-615d-4f56-aba0-8d31333bc481")
    protected void completeBox() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
            }
        });
    }

    @objid ("2670d761-c913-4e9e-8ac8-1751ad2970b8")
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
    @objid ("7645b31c-37dc-4787-a473-736d316a4d93")
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
    @objid ("f24b85ff-3f48-49a3-8af4-2601deb73da2")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
