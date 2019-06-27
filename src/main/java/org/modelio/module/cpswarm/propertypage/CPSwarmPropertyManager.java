package org.modelio.module.cpswarm.propertypage;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.cpswarm.api.CPSWarmStereotypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

@objid ("5174ac7d-23d0-423c-9e73-fd50c9877e90")
public class CPSwarmPropertyManager {
    @objid ("e5a89292-1b28-42e4-bfc2-49464917ef38")
    public int changeProperty(ModelElement element, int row, String value) {
        IPropertyContent propertypage = null;
        IModuleContext moduleContext = CPSWarmModule.getInstance().getModuleContext();
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();

        int currentRow = row;

        if ((element instanceof Association) || (element instanceof AssociationEnd)) {
            propertypage = new AssociationPropertyPage();
            propertypage.changeProperty(element, currentRow, value);
            propertypage = null;
        }
        
        if (element instanceof Transition) {
            propertypage = new TransitionPropertyPage();
            propertypage.changeProperty(element, currentRow, value);
            propertypage = null;
        }

        if (element instanceof State) {
            State state = (State) element;
            if (state.getInternal().size() >0) {
                propertypage = new ActionPropertyPage();
                propertypage.changeProperty(element, currentRow, value);
                propertypage = null;
            }else {
                propertypage = new SubMachinePropertyPage();
                propertypage.changeProperty(element, currentRow, value);
                propertypage = null;
            }
        }

        List<Stereotype> sterList = element.getExtension();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();

        for (Stereotype ster : sterList) {

            // Problem property page
            if (ster.equals(extensions.getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.PROBLEM,
                    metamodel.getMClass(Class.class)))) {
                propertypage = new ProblemPropertyPage();
            }

            // FitnessFunction property page
            if (ster.equals(extensions.getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESSFUNCTION,
                    metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new FitnessFunctionPropertyPage();
            }

            if (propertypage != null) {          
                currentRow = currentRow - propertypage.changeProperty(element, currentRow, value);
                propertypage = null;
            }

        }
        return currentRow;
    }

    @objid ("9f489a96-0219-4e6a-95f5-116f11420c8a")
    public void update(ModelElement element, IModulePropertyTable table) {
        IPropertyContent propertypage = null;
        IModuleContext moduleContext = CPSWarmModule.getInstance().getModuleContext();
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();


        if ((element instanceof Association) || (element instanceof AssociationEnd)) {
            propertypage = new AssociationPropertyPage();
            propertypage.update(element, table);
            propertypage = null;
        }
        
        if (element instanceof Transition) {
            propertypage = new TransitionPropertyPage();
            propertypage.update(element, table);
            propertypage = null;
        }
        
        if (element instanceof State) {
            State state = (State) element;
            if (state.getInternal().size() >0) {
                propertypage = new ActionPropertyPage();
                propertypage.update(element, table);
                propertypage = null;
            }else {
                propertypage = new SubMachinePropertyPage();
                propertypage.update(element, table);
                propertypage = null;
            }
        }

        List<Stereotype> sterList = element.getExtension();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();

        for (Stereotype ster : sterList) {

            // Problem property page
            if (ster.equals(extensions.getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.PROBLEM,
                    metamodel.getMClass(Class.class)))) {
                propertypage = new ProblemPropertyPage();
            }

            // FitnessFunction property page
            if (ster.equals(extensions.getStereotype(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESSFUNCTION,
                    metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new FitnessFunctionPropertyPage();
            }


            if (propertypage != null) {
                propertypage.update(element, table);
                propertypage = null;
            }
        }
    }

}
