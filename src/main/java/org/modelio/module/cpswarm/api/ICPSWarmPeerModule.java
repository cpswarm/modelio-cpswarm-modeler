package org.modelio.module.cpswarm.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.statik.Classifier;

/**
 * @see com.modeliosoft.modelio.api.module.IPeerModule
 */
@objid ("d1c0cb6e-38b2-4397-8603-019f780b16d7")
public interface ICPSWarmPeerModule extends IPeerModule {
    @objid ("7b21190a-b942-4670-9cff-635576f44f75")
    public static final String MODULE_NAME = "CPSWarm";


    @objid ("d78a2bda-c812-4ac8-ad01-c5857a401aa3")
    void generateSCXML(StateMachine stateMachine);
    
    
    void generateCommunicationConfigurartion(Classifier CPS);
    
    
    void generateSwarmComposition(Classifier swarm);
    
    
    

}
