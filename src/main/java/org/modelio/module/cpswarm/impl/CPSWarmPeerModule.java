package org.modelio.module.cpswarm.impl;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.cpswarm.generator.SCXMLGeneration;
import org.modelio.module.cpswarm.utils.FileUtils;
import org.modelio.module.cpswarm.utils.ResourcesManager;
import org.modelio.vbasic.version.Version;

/**
 * Implementation of Module services
 * <br>When a module is built using the MDA Modeler tool, a public interface is generated and accessible for the other module developments.
 * <br>The main class that allows developpers to get specific module services has to implement the current interface.
 * <br>Each mda component brings a specific interface that inherit from this one and gives all the desired module services.
 */
@objid ("342db563-838b-41bd-9e1e-5d1b758cae24")
public class CPSWarmPeerModule implements ICPSWarmPeerModule {
    @objid ("63ad6efd-88c9-4504-9f63-7e6bc26039eb")
    private IModuleAPIConfiguration peerConfiguration;

    @objid ("6422c550-69bc-4013-ae5c-2abd05e4d064")
    private CPSWarmModule module;

    @objid ("749c8004-b304-43a4-ada8-7ba8f4c1fdc4")
    public CPSWarmPeerModule(CPSWarmModule statModuleModule, IModuleAPIConfiguration peerConfiguration) {
        super();
        this.module = statModuleModule;
        this.peerConfiguration = peerConfiguration;
    }

    @objid ("0ce66f02-176e-4308-bdad-667ff8de2a1d")
    public void init() {
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getConfiguration()
     */
    @objid ("f860ebe3-1242-469c-932d-c3d8e10a1ac3")
    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.peerConfiguration;
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getDescription()
     */
    @objid ("2d32a507-14cd-44a8-99e9-b4e19633bd3b")
    @Override
    public String getDescription() {
        return this.module.getDescription();
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getName()
     */
    @objid ("3492a187-c4b4-466c-ad11-93ea39c576cf")
    @Override
    public String getName() {
        return this.module.getName();
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getVersion()
     */
    @objid ("b16e7082-213b-4c6a-b529-60c9536ff896")
    @Override
    public Version getVersion() {
        return this.module.getVersion();
    }

    @objid ("2f11a190-bf31-4681-ae7d-214b512277f2")
    @Override
    public void generateSCXML(StateMachine stateMachine) {
        String pathDest = ResourcesManager.getInstance().getGeneratedPath() + File.separator + stateMachine.getName() + ".xml";
        File file = new File(pathDest);
        
        SCXMLGeneration scxmlGen = new SCXMLGeneration(stateMachine);
        StringBuffer content = scxmlGen.generate();        
        FileUtils.write(file, content);
    }

    @objid ("4d4ca74c-6282-4dbb-9a75-c951d8a5b689")
    @Override
    public void generateCommunicationConfigurartion(Classifier CPS) {
        // TODO Auto-generated method stub
    }

    @objid ("2eb833fb-8385-4319-919b-da8afd56091e")
    @Override
    public void generateSwarmComposition(Classifier swarm) {
        // TODO Auto-generated method stub
    }


//    @objid ("781e6890-9282-462f-b3ea-830c0a2dbbbf")
//    @Override
//    public void generateFrevoProject(org.modelio.metamodel.uml.statik.Class selectedElt) {
//        IModuleUserConfiguration configuration = this.module.getModuleContext().getConfiguration();
//
//        String rosWs = configuration.getParameterValue(CPSWarmParameters.WORKSPACEPATH);
//
//        String rosPath = configuration.getParameterValue(CPSWarmParameters.ROSPATH);
//
//        String frevoPath = configuration.getParameterValue(CPSWarmParameters.FREVOPATH);
//
//        ProjectGeneration generation = new ProjectGeneration(selectedElt);
//        generation.generate(rosWs, rosPath, frevoPath);
//    }
}
