package org.modelio.module.cpswarm.command.explorer;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.module.cpswarm.impl.CPSWarmPeerModule;
import org.modelio.module.cpswarm.utils.CPSWarmResourcesManager;
import org.modelio.vcore.smkernel.mapi.MObject;


public class CPDTCommand extends DefaultModuleCommandHandler {
    
    protected Shell shell;

    protected String title = "Complete";

    protected ModelTree selectedElt;

    protected String spiderinoPath = "";

    protected IModule module;

    protected String descriptionGeneration = "Generation Complete";

    protected Text nameText;

    protected Text descriptionText;


    protected  List<org.modelio.metamodel.uml.statik.Class> behaviours;

    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        this.selectedElt = (ModelTree) selectedElements.get(0);
        this.module = module;

        //        DSEWizardExport cpdtwizard = new DSEWizardExport(Display.getCurrent().getActiveShell());
        //        cpdtwizard.setSelectedElt(;
        //        cpdtwizard.open();
        createContents2();

    }

    public void createInitiaSpiderinolModel(ModelTree root, IModule module, String name, String description, String behaviourName) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Spiderino", this.selectedElt);
        parameters.put("$(Name)", name);

        for (org.modelio.metamodel.uml.statik.Class behaviour : behaviours){
            if (behaviour.getName().equals(behaviourName))
                parameters.put("Behaviour", behaviour);
        }

        String templatePath = CPSWarmResourcesManager.getPattern("Spiderino_2.0.00.umlt");

        IModelingSession session = module.getModuleContext().getModelingSession();

        try(ITransaction transaction = session.createTransaction ("Spiderino")){
            module.getModuleContext().getModelioServices().getPatternService().applyPattern(Paths.get(templatePath), parameters);

            for(ModelTree owned : this.selectedElt.getOwnedElement()){
                if (owned.isStereotyped(CPSWarmPeerModule.MODULE_NAME, "Swarm_Member")){
                    owned.setName(name);
                    session.getModel().createNote("ModelerModule", "description", owned, description);
                }                  
            }
            transaction.commit();
           

        } catch (InvalidParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createDroneModel(ModelTree root, IModule module, String name, String description, String behaviourName) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("DroneG", this.selectedElt);
        parameters.put("$(Name)", name);

        for (org.modelio.metamodel.uml.statik.Class behaviour : behaviours){
            if (behaviour.getName().equals(behaviourName))
                parameters.put("Behaviour", behaviour);
        }

        String templatePath = CPSWarmResourcesManager.getPattern("DroneG_1.0.00.umlt");

        IModelingSession session = module.getModuleContext().getModelingSession();

        try(ITransaction transaction = session.createTransaction ("Drone")){
            module.getModuleContext().getModelioServices().getPatternService().applyPattern(Paths.get(templatePath), parameters);

            for(ModelTree owned : this.selectedElt.getOwnedElement()){
                if (owned.isStereotyped(CPSWarmPeerModule.MODULE_NAME, "Swarm_Member")){
                    owned.setName(name);
                    session.getModel().createNote("ModelerModule", "description", owned, description);
                }                  
            }
            transaction.commit();
            

        } catch (InvalidParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createContents2(){
        //Shell Creation
        Display display = Display.getCurrent();
        this.shell = new Shell(display);
        this.shell.setText("CPS Template Creation");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        this.shell.setLayout(gridLayout);


        //Name Creation
        new Label(shell, SWT.NONE).setText("Name:");
        this.nameText = new Text(shell, SWT.SINGLE | SWT.BORDER);
        GridData gridDataName = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridDataName.horizontalSpan = 2;
        this.nameText.setLayoutData(gridDataName);

        //Description Creation
        new Label(shell, SWT.NONE).setText("Description:");
        this.descriptionText = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL );
        GridData gridDataDescription = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridDataDescription.horizontalSpan = 2;
        this.descriptionText.setLayoutData(gridDataDescription);


        //Behaviour Creation
        new Label(this.shell, SWT.NONE).setText("Behaviour :");
        Combo cpsBehaviour = new Combo(this.shell, SWT.NONE);

        Collection<org.modelio.metamodel.uml.statik.Class> classes = this.module.getModuleContext().getModelingSession().findByClass(org.modelio.metamodel.uml.statik.Class.class);
        this.behaviours = new ArrayList<org.modelio.metamodel.uml.statik.Class>();
        for (org.modelio.metamodel.uml.statik.Class classe : classes){
            if (classe.isStereotyped(CPSWarmPeerModule.MODULE_NAME, "Controller"))
                this.behaviours.add(classe);
        }

        for (org.modelio.metamodel.uml.statik.Class behaviour: behaviours){
            cpsBehaviour.add(behaviour.getName());
        }

        GridData gridDataBehaviour = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridDataBehaviour.horizontalSpan = 2;
        cpsBehaviour.setLayoutData(gridDataBehaviour);

        //Hardware Creation
        new Label(this.shell, SWT.NONE).setText("Hardware:");
        Combo cpsHardware = new Combo(shell, SWT.NONE);
        cpsHardware.setItems(new String[] { "Spiderino", "Drone"});
        GridData gridDataHardware = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridDataHardware.horizontalSpan = 2;
        cpsHardware.setLayoutData(gridDataHardware);

        //Enter button
        Button enter = new Button(this.shell, SWT.PUSH);
        enter.setText("Create");
        GridData gridDataButton = new GridData(GridData.END, GridData.CENTER, false, false);
        gridDataButton.horizontalSpan = 3;
        enter.setLayoutData(gridDataButton);
        enter.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                if (cpsHardware.getText().equals("SPiderino"))
                    createInitiaSpiderinolModel(selectedElt, module, nameText.getText(), descriptionText.getText(), cpsBehaviour.getText());
                else
                    createDroneModel(selectedElt, module, nameText.getText(), descriptionText.getText(), cpsBehaviour.getText());
                
                completeBox();
            }
        });

        this.shell.pack();
        this.shell.open();

        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }        
    }

    public void old(List<MObject> selectedElements, IModule module){
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
                CPDTCommand.this.shell.dispose();
            }
        });
    }


    void customMessageBox(int icon) {
        MessageBox messageBox = new MessageBox(Display.getCurrent().getActiveShell(), icon);
        messageBox.setMessage(this.descriptionGeneration);
        messageBox.setText(this.title);
        messageBox.open();
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */

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

    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }


}
