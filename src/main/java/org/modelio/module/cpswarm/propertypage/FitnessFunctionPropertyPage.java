package org.modelio.module.cpswarm.propertypage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.api.CPSWarmTagTypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.sysml.utils.ModelUtils;

@objid ("5add035c-b0ca-41b1-8401-51d5f7dc8603")
public class FitnessFunctionPropertyPage implements IPropertyContent {
    @objid ("72d92552-da8c-4c5f-a4c6-f0e590520620")
    @Override
    public int changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.FITNESSFUNCTION_MINIMUMCANDIDATES, value, element);
        }else if(row == 2){
            ModelUtils.addValue(ICPSWarmPeerModule.MODULE_NAME,CPSWarmTagTypes.FITNESSFUNCTION_MAXIMUMCANDIDATES, value, element);
        }
        return 2;
    }

    @objid ("54f37b82-f2d6-4a51-806f-215e69c21043")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value = ModelUtils.getTaggedValue(CPSWarmTagTypes.FITNESSFUNCTION_MINIMUMCANDIDATES, element);    
        table.addProperty("MinCandidates", value);
        
        value = ModelUtils.getTaggedValue(CPSWarmTagTypes.FITNESSFUNCTION_MAXIMUMCANDIDATES, element);
        table.addProperty("MaxCandidates", value);
    }

}
