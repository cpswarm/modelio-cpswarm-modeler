package org.modelio.module.cpswarm.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Stereotype list defined in CPSwarm module
 * @author ebrosse
 */
@objid ("7c502392-a25a-4bd0-9c8a-6287709ccbff")
public interface CPSWarmStereotypes {
    
    public static final String BEHAVIOR_DEFINITION = "Behavior_Definition"; 
    
    @objid ("1365507d-38b6-4c22-836a-87c4e0b58b72")
    public static final String ENVIRONMENT = "Environment";

    @objid ("c465c423-a824-4af4-9897-25d562f9be48")
    public static final String ENVIRONMENT_DEFINITION = "Environment_Definition";

    @objid ("57ca7a81-bae0-46ec-ae0e-52d57a482f25")
    public static final String FITNESSFUNCTION = "FitnessFunction";

    @objid ("fca1d48f-8cbd-4412-86e8-b1a9c3521aad")
    public static final String FITNESS_DEFINITION = "Fitness_Definition";

    @objid ("55ac09bd-d8d9-4ae2-aae4-d8afbbe5d200")
    public static final String PROBLEM = "Problem";

    @objid ("3c952632-ceaa-4959-a448-614713665674")
    public static final String PROBLEM_STATEMENT = "Problem_Statement";

    @objid ("385bab0a-421c-4c29-b509-066bbd028a78")
    public static final String SWARM = "Swarm";

    @objid ("bef3f1b9-e32d-4a6a-9fe3-51a5a0dea5e2")
    public static final String SWARMMEMBER_ARCHITECTURE = "SwarmMember_Architecture";

    @objid ("e7f8d9a7-b8db-41ba-98b7-b8074e85a637")
    public static final String SWARM_DEFINITION = "Swarm_Definition";

    @objid ("d5bfc91b-4fea-4434-bb2e-d47ff00407b4")
    public static final String SWARM_MEMBER = "Swarm_Member";

    @objid ("219b7047-2644-470a-9ff9-cca48b6f2c4c")
    public static final String CONTROLLER = "Controller";
    

}
