package org.modelio.module.cpswarm.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;

/**
 * @see com.modeliosoft.modelio.api.module.IPeerModule
 */
@objid ("d1c0cb6e-38b2-4397-8603-019f780b16d7")
public interface ICPSWarmPeerModule extends IPeerModule {
    @objid ("7b21190a-b942-4670-9cff-635576f44f75")
    public static final String MODULE_NAME = "CPSWarm";

    @objid ("ec82135a-4224-40b4-b136-d55d2048ea1e")
    void generateFrevoProject(org.modelio.metamodel.uml.statik.Class selectedElt);

}
