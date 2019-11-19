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

@objid ("8b943db7-b6e2-4b6c-bea8-dc2cfc4dee86")
public class XMLGeneration extends Generator implements IGenerator {
    @objid ("9c62fce2-f3db-4fcd-a12a-7d252c4bd884")
    private String rosWS = "";

    @objid ("fef15994-8bec-483a-a3ae-7e7ab3fd7336")
    private String rosPath = "";

    @objid ("8fc6b762-3efd-4770-89dd-c9799037553c")
    private org.modelio.metamodel.uml.statik.Class problem = null;

    @objid ("1bacfbbc-88f1-4e53-a140-1487816e4706")
    private org.modelio.metamodel.uml.statik.Class costFunction = null;

    @objid ("9e29c19f-a735-47d0-ba5e-c2daf2167979")
    private org.modelio.metamodel.uml.statik.Class swarm = null;

    @objid ("d39cd338-552b-443e-b8e5-4021df4c45fd")
    private org.modelio.metamodel.uml.statik.Class environment = null;

    @objid ("a38f95c0-434f-4198-bbf2-dc3f628dc440")
    private org.modelio.metamodel.uml.statik.Class controller = null;

    @objid ("c217f848-600b-461c-9b7f-c1d43407a303")
    public XMLGeneration(org.modelio.metamodel.uml.statik.Class problem, org.modelio.metamodel.uml.statik.Class swarm, org.modelio.metamodel.uml.statik.Class environment, org.modelio.metamodel.uml.statik.Class costFunction, org.modelio.metamodel.uml.statik.Class controller, String rosWs, String rosPath) {
        this.problem = problem;
        this.swarm = swarm;
        this.environment = environment;
        this.costFunction = costFunction;
        this.controller = controller;
        this.rosWS = rosWs;
        this.rosPath = rosPath;
    }

    @objid ("87c573d8-4d57-4b1e-bf9e-0ed36ec258cf")
    @Override
    public StringBuffer generate() {
        headerXML();
        configXML();
        propertyXML();
        requirementXML();
        footerXML();
        return this.content;
    }

    @objid ("9d8d6987-3502-43d4-8399-ae36066d3b52")
    private void footerXML() {
        decreaseIndent();
        addLine("</icomponent>");
    }

    @objid ("0c0c2a0d-500d-49c7-8eca-ce1c5bedd9de")
    private void headerXML() {
        addLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        addLine("<!DOCTYPE zion SYSTEM \"..//IComponent.dtd\">");
        addEmptyLine();
        addLine("<icomponent>");
        increaseIndent();
    }

    @objid ("f71e61bc-0f92-419e-a743-3f4a9e8628a0")
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

    @objid ("1b7c5d16-99ec-420a-b4c6-38be868e89c6")
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

    @objid ("0b46423e-1489-4411-bb60-84dfdc21b0cb")
    private void propertyXML() {
        addLine("<properties>");
        increaseIndent();
        
        String maxsteps = this.problem.getTagValue(ICPSWarmPeerModule.MODULE_NAME, CPSWarmTagTypes.PROBLEM_MAXSTEPS);
        addLine("<propentry key=\"steps\" type=\"INT\" value=\"" + maxsteps + "\" description=\"Maximum number of steps the agents are allowed to make.\"/>");    
        agentNumber();
              
        rosProperties();
        
        addLine("<propentry key=\"environment\" type=\"ENUM\" value=\"small_maze\" enumName=\"helper.Environment\" description=\"Choose one of the existing environments.\"/>");
        decreaseIndent();
        addLine("</properties>");
    }

    @objid ("1406e25c-03b0-4feb-859c-7f70223fc2fa")
    private void agentNumber() {
        String numAgents = "1";
        for (AssociationEnd assocEnd : this.swarm.getOwnedEnd()){
            numAgents = assocEnd.getMultiplicityMax();            
        }
        
        addLine("<propentry key=\"swarmMembers\" type=\"INT\" value=\"" + numAgents + "\" description=\"Number of agents in the swarm.\"/>");
    }

    @objid ("509cf1a6-6255-42c0-8c99-8c14472f144e")
    private void environmentProperties() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            AssociationEnd opposite = assocEnd.getOpposite();
            String key = "num" + opposite.getOwner().getName() + "s";
            String value = opposite.getMultiplicityMax();    
            addLine("<propentry key=\"" + key + "\" type=\"INT\" value=\"" + value + "\"/>");
        }
    }

    @objid ("4fcebc4e-c2f4-4351-811f-60c9dd38afe3")
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

    @objid ("0482bf4a-965a-49ad-88da-e0912d9e4b71")
    private void rosProperties() {
        addLine("<propentry key=\"rosPath\" type=\"STRING\" value=\"" + this.rosPath + "\" description=\"Absolute path to the ROS installation folder.\"/>");
        
        addLine("<propentry key=\"rosWs\" type=\"STRING\" value=\"" + this.rosWS + "\" description=\"Absolute path to the ROS workspace, e.g. /home/user/my_ros_ws.\"/>");
        
        String rosPackage = "ros/emergency_exit";
        addLine("<propentry key=\"rosPackage\" type=\"STRING\" value=\"" 
                + rosPackage 
                + "\" description=\"Name of the ROS package, i.e. relative path from the ROS workspace's source folder to the ROS package implementing the simulation, e.g. my_ros_pkg.\"/> ");
    }

}
