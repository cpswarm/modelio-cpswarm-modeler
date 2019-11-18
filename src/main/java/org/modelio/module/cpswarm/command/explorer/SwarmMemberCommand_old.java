package org.modelio.module.cpswarm.command.explorer;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.module.cpswarm.utils.CPSWarmResourcesManager;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ffe1b6bb-5781-46e4-9730-ef888730cb31")
public class SwarmMemberCommand_old extends DefaultModuleCommandHandler {
    @objid ("77017ae6-59a5-4dae-8ed4-d1553db28dbc")
     String title = "Complete";

    @objid ("50e8abc2-4323-418f-a0d2-6079b8fa6965")
     String description = "Generation Complete";

    @objid ("431891a4-f6a9-4944-8342-c83f31bae8c1")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        MObject owner = selectedElements.get(0);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("SwarmMember", owner);
        
        String templatePath = CPSWarmResourcesManager.getPattern("SwarmMember_1.0.00.umlt");
        
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try(ITransaction transaction = session.createTransaction ("SwarmMember Creation")){
            module.getModuleContext().getModelioServices().getPatternService().applyPattern(Paths.get(templatePath), parameters);
            transaction.commit ();
            completeBox();
            
        } catch (InvalidParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @objid ("7cc194bc-18fe-462d-b0d9-c69b12a50f8f")
    protected void completeBox() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
            }
        });
    }

    @objid ("d79a1c4e-ac59-4fff-ab65-29fccf9fc039")
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
    @objid ("19a8cbd1-e40d-48c1-a2a5-83fd52b13b68")
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
    @objid ("23bf9d96-affb-4eb3-91d7-640321cfe3fa")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
