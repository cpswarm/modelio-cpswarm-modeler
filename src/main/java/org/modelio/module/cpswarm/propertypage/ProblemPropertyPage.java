package org.modelio.module.cpswarm.propertypage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.api.CPSWarmTagTypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.sysml.utils.ModelUtils;

@objid ("3891a4b0-4d09-40f2-a225-ea86410af5a7")
public class ProblemPropertyPage implements IPropertyContent {
    @objid ("a14d346e-50cc-4c0c-9c09-d3704a77ef93")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_NAME, value, element);
        }else if(row == 2){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_IMAGE, value, element);
        }else if(row == 3){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_MAXSTEPS, value, element);
        }else if(row == 4){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_SEED, value, element);
        }else if(row == 5){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.PROBLEM_NUMEVALUATIONS, value, element);
        }
        return 5;
    }

    @objid ("4431fc90-c68c-470d-b14e-6a9f52cc04e5")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_NAME, element);    
        table.addProperty("Name", value);
        
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_IMAGE, element);
        table.addProperty("Image", value);
           
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_MAXSTEPS, element);
        table.addProperty("MaxSteps", value);
        
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_SEED, element);
        table.addProperty("Seed", value);
        
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.PROBLEM_NUMEVALUATIONS, element);
        table.addProperty("NumEvaluations", value);
    }

}
