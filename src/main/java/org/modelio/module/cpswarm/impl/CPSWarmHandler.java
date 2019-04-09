package org.modelio.module.cpswarm.impl;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * @author ebrosse
 */
@objid ("bae1c0cb-4cec-4db7-8d0e-1f8f7228fdaa")
public class CPSWarmHandler implements IModelChangeHandler {
    @objid ("9ebe4ffd-820f-4e0a-b695-4856f3798275")
    @Override
    public void handleModelChange(IModelingSession session, IModelChangeEvent event) {
        //created elements
        Set<MObject> createdElements =  event.getCreationEvents();
           
               
        //updated elements
        Set<MObject> updatedElements =  event.getUpdateEvents();
    }

}
