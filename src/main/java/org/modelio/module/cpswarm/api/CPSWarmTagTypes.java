package org.modelio.module.cpswarm.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * TagType list defined in CPSwarm module
 * @author ebrosse
 */
@objid ("b646be95-7df7-47ad-9685-32c6abfcd674")
public interface CPSWarmTagTypes {
    @objid ("29f0b057-3a2d-4d41-9402-f8e2ff04c7e3")
    public static final String PROBLEM_IMAGE = "image";

    @objid ("6f7f5d0b-0821-4635-a07c-7cfb00967f0c")
    public static final String PROBLEM_NAME = "name";

    @objid ("64451faf-87a4-4bb5-80bc-3d538a55bc9e")
    public static final String PROBLEM_SEED = "seed";

    @objid ("be28203c-3c96-47c9-9f16-e88e437e318d")
    public static final String PROBLEM_NUMEVALUATIONS = "numEvaluations";

    @objid ("2e909587-d731-4e1e-805a-c6146a195381")
    public static final String FITNESSFUNCTION_MINIMUMCANDIDATES = "minimumCandidates";

    @objid ("dbe99de5-dd5a-46db-b13d-11f4372f468a")
    public static final String FITNESSFUNCTION_MAXIMUMCANDIDATES = "maximumCandidates";

    @objid ("56655dcb-8d98-42d7-abc6-0dc9051acdcf")
    public static final String PROBLEM_MAXSTEPS = "maxSteps";

}
