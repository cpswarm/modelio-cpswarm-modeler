package org.modelio.module.cpswarm.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("0bb6ed66-1522-425f-bc65-51422aace3a7")
public class CPSWarmPropertyManager {
    @objid ("d4f82d96-d7d4-44a1-8e8b-fc9ee20469ef")
    public int changeProperty(ModelElement element, int row, String value) {
        return row;
    }

    @objid ("e1cd8c18-df4a-44cd-852e-7dbddaac0f3e")
    public void update(ModelElement element, IModulePropertyTable table) {
    }

}
