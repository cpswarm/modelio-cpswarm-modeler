package org.modelio.module.cpswarm.generator;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.module.cpswarm.api.CPSWarmTagTypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleNoteTypes;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;

@objid ("1e134616-95e9-4979-8358-433ecea967ac")
public class XMLGeneration extends Generator implements IGenerator {
    @objid ("3dd13a9f-b9ab-40c0-8651-2d9a659e8098")
    private String rosWS = "";

    @objid ("c3291d29-057c-4170-affd-2fd91c4505d7")
    private String rosPath = "";

    @objid ("8f29411c-bba9-47db-98c9-8326f1bcb795")
    private org.modelio.metamodel.uml.statik.Class problem = null;

    @objid ("b8f67ca4-d513-4422-8922-34a3f04b605d")
    private org.modelio.metamodel.uml.statik.Class costFunction = null;

    @objid ("a3d18aeb-1fbb-4eb1-8c6d-64afa1522be4")
    private org.modelio.metamodel.uml.statik.Class swarm = null;

    @objid ("ba3ea70f-882c-4897-beba-15f10287e877")
    private org.modelio.metamodel.uml.statik.Class environment = null;

    @objid ("a5f2575e-aad2-4ddd-97a2-fa9f9a3901a9")
    private org.modelio.metamodel.uml.statik.Class controller = null;

    @objid ("9ba83793-0016-4f2d-b0ca-187e9703774a")
    public XMLGeneration(org.modelio.metamodel.uml.statik.Class problem, org.modelio.metamodel.uml.statik.Class swarm, org.modelio.metamodel.uml.statik.Class environment, org.modelio.metamodel.uml.statik.Class costFunction, org.modelio.metamodel.uml.statik.Class controller, String rosWs, String rosPath) {
        this.problem = problem;
        this.swarm = swarm;
        this.environment = environment;
        this.costFunction = costFunction;
        this.controller = controller;
        this.rosWS = rosWs;
        this.rosPath = rosPath;
    }

    @objid ("678f5491-5100-42e1-b979-ce56f2faf588")
    public StringBuffer generate() {
        headerXML();
        configXML();
        propertyXML();
        requirementXML();
        footerXML();
        return this.content;
    }

    @objid ("601712da-159b-435c-9163-fc33bd5ea2eb")
    private void footerXML() {
        decreaseIndent();
        addLine("</icomponent>");
    }

    @objid ("9df11abf-d830-42fa-8727-df00321008f7")
    private void headerXML() {
        addLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        addLine("<!DOCTYPE zion SYSTEM \"..//IComponent.dtd\">");
        addEmptyLine();
        addLine("<icomponent>");
        increaseIndent();
    }

    @objid ("5596dff4-fb9b-4194-833a-7481e5eadfd7")
    private void requirementXML() {
        addLine("<requirements>");
        increaseIndent();
        
        inputOuputNumber();
        
        String min = this.costFunction.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.FITNESSFUNCTION_MINIMUMCANDIDATES);
        addLine("<reqentry key=\"minimumCandidates\" type=\"INT\" value=\"" + min + "\"/>");
        
        
        String max = this.costFunction.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.FITNESSFUNCTION_MAXIMUMCANDIDATES);
        addLine("<reqentry key=\"maximumCandidates\" type=\"INT\" value=\""+ max +"\"/>");
        
        decreaseIndent();
        addLine("</requirements>");
    }

    @objid ("b3f7cffd-2ab5-455f-9be9-ff44c4406dc0")
    private void inputOuputNumber() {
        int inputnumber = 0;
        int outputnumber = 0;
        for(BindableInstance bi : this.controller.getInternalStructure()){
            if (bi instanceof Port){
                Port port = (Port) bi;
                NameSpace base = port.getBase();
                if ((base != null) && (base instanceof Classifier)) {
                    int attnumber = ((Classifier) base).getOwnedAttribute().size();
                    if (port.getDirection().equals(PortOrientation.IN)){
                        inputnumber += attnumber;
                    }else{
                        outputnumber += attnumber;
                    }
                }else{
                    if (port.getDirection().equals(PortOrientation.IN)){
                        inputnumber += 1;
                    }else{
                        outputnumber += 1;
                    }
                }
            }
        }
        
        addLine("<reqentry key=\"inputnumber\" type=\"INT\" value=\"" + String.valueOf(inputnumber) +"\"/>");
        addLine("<reqentry key=\"outputnumber\" type=\"INT\" value=\"" + String.valueOf(outputnumber) +"\"/>");
    }

    @objid ("b6b2f1c2-3260-4e00-ad43-8fd5edb5f07d")
    private void propertyXML() {
        addLine("<properties>");
        increaseIndent();
        
        String maxsteps = this.problem.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.PROBLEM_MAXSTEPS);
        addLine("<propentry key=\"steps\" type=\"INT\" value=\"" + maxsteps + "\" description=\"Maximum number of steps the agents are allowed to make.\"/>");    
        
        //        for (Attribute attribut : this.environment.getOwnedAttribute()){
        //            addLine("<propentry key=\"" + attribut.getName() + "\" type=\"INT\" value=\"" + attribut.getValue() + "\"/>");        
        //        }
        
        //        String seed = this.problem.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.PROBLEM_SEED);
        //        addLine("<propentry key=\"seed\" type=\"INT\" value=\"" + seed + "\"/>");
        //        
        agentNumber();
        
        //        environmentProperties();
        
        rosProperties();
        
        //        String numEva = this.problem.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.PROBLEM_NUMEVALUATIONS);
        //        addLine("<propentry key=\"numEvaluations\" type=\"INT\" value=\"" + numEva + "\"/>");
        
        addLine("<propentry key=\"environment\" type=\"ENUM\" value=\"small_maze\" enumName=\"helper.Environment\" description=\"Choose one of the existing environments.\"/>");
        decreaseIndent();
        addLine("</properties>");
    }

    @objid ("73a16044-10ea-4162-b0f7-d81c86466622")
    private void agentNumber() {
        String numAgents = "1";
        for (AssociationEnd assocEnd : this.swarm.getOwnedEnd()){
            //            AssociationEnd opposite = assocEnd.getOpposite();
            numAgents = assocEnd.getMultiplicityMax();            
        }
        
        addLine("<propentry key=\"swarmMembers\" type=\"INT\" value=\"" + numAgents + "\" description=\"Number of agents in the swarm.\"/>");
    }

    @objid ("ea66fe45-0d5a-44f5-bf46-505e1aa053b6")
    private void environmentProperties() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            AssociationEnd opposite = assocEnd.getOpposite();
            String key = "num" + opposite.getOwner().getName() + "s";
            String value = opposite.getMultiplicityMax();    
            addLine("<propentry key=\"" + key + "\" type=\"INT\" value=\"" + value + "\"/>");
        }
    }

    @objid ("8fdfbd4e-5521-4dea-a046-ff0a9ee2729a")
    private void configXML() {
        addLine("<config>");
        increaseIndent();
        String pbName = this.problem.getName();
        
        addLine("<entry key=\"classdir\" type=\"STRING\" value=\"" + pbName + "/" + pbName.toLowerCase() + "\"/>");
        addLine("<entry key=\"classname\" type=\"STRING\" value=\"" + pbName.toLowerCase() + "." + pbName +  "\"/>");
        
        String name = this.problem.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.PROBLEM_NAME);
        addLine("<entry key=\"name\" type=\"STRING\" value=\"" + name + "\"/>");
        
        String description = this.problem.getNoteContent(IModelerModulePeerModule.MODULE_NAME, IModelerModuleNoteTypes.MODELELEMENT_DESCRIPTION);
        description = description.replace("<p>", "");
        description = description.replace("</p>", "");
        description = description.replace("\n", "");
        addLine("<entry key=\"description\" type=\"STRING\" value=\"" + description + "\"/>");
        
        String image = this.problem.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.PROBLEM_IMAGE);
        addLine("<entry key=\"image\" type=\"STRING\" value=\"" + image + "\"/>");
        
        decreaseIndent();
        addLine("</config>");
    }

    @objid ("6242fa10-1ad8-49a5-95a1-9e1fd0bb7629")
    private void rosProperties() {
        addLine("<propentry key=\"rosPath\" type=\"STRING\" value=\"" + this.rosPath + "\" description=\"Absolute path to the ROS installation folder.\"/>");
        
        addLine("<propentry key=\"rosWs\" type=\"STRING\" value=\"" + this.rosWS + "\" description=\"Absolute path to the ROS workspace, e.g. /home/user/my_ros_ws.\"/>");
        
        String rosPackage = "ros/emergency_exit";
        addLine("<propentry key=\"rosPackage\" type=\"STRING\" value=\"" 
                + rosPackage 
                + "\" description=\"Name of the ROS package, i.e. relative path from the ROS workspace's source folder to the ROS package implementing the simulation, e.g. my_ros_pkg.\"/> ");
    }

}
