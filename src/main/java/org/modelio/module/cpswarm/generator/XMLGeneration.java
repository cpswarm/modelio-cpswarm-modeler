package org.modelio.module.cpswarm.generator;

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


public class XMLGeneration extends Generator implements IGenerator {

    private String rosWS = "";

    private String rosPath = "";


    private org.modelio.metamodel.uml.statik.Class problem = null;


    private org.modelio.metamodel.uml.statik.Class costFunction = null;

    private org.modelio.metamodel.uml.statik.Class swarm = null;


    private org.modelio.metamodel.uml.statik.Class environment = null;


    private org.modelio.metamodel.uml.statik.Class controller = null;


    public XMLGeneration(org.modelio.metamodel.uml.statik.Class problem, org.modelio.metamodel.uml.statik.Class swarm, org.modelio.metamodel.uml.statik.Class environment, org.modelio.metamodel.uml.statik.Class costFunction, org.modelio.metamodel.uml.statik.Class controller, String rosWs, String rosPath) {
        this.problem = problem;
        this.swarm = swarm;
        this.environment = environment;
        this.costFunction = costFunction;
        this.controller = controller;
        this.rosWS = rosWs;
        this.rosPath = rosPath;
    }


    @Override
    public StringBuffer generate() {
        headerXML();
        configXML();
        propertyXML();
        requirementXML();
        footerXML();
        return this.content;
    }


    private void footerXML() {
        decreaseIndent();
        addLine("</icomponent>");
    }

    private void headerXML() {
        addLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        addLine("<!DOCTYPE zion SYSTEM \"..//IComponent.dtd\">");
        addEmptyLine();
        addLine("<icomponent>");
        increaseIndent();
    }


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


    private void agentNumber() {
        String numAgents = "1";
        for (AssociationEnd assocEnd : this.swarm.getOwnedEnd()){
            numAgents = assocEnd.getMultiplicityMax();            
        }
        
        addLine("<propentry key=\"swarmMembers\" type=\"INT\" value=\"" + numAgents + "\" description=\"Number of agents in the swarm.\"/>");
    }

    private void environmentProperties() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            AssociationEnd opposite = assocEnd.getOpposite();
            String key = "num" + opposite.getOwner().getName() + "s";
            String value = opposite.getMultiplicityMax();    
            addLine("<propentry key=\"" + key + "\" type=\"INT\" value=\"" + value + "\"/>");
        }
    }

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

    private void rosProperties() {
        addLine("<propentry key=\"rosPath\" type=\"STRING\" value=\"" + this.rosPath + "\" description=\"Absolute path to the ROS installation folder.\"/>");
        
        addLine("<propentry key=\"rosWs\" type=\"STRING\" value=\"" + this.rosWS + "\" description=\"Absolute path to the ROS workspace, e.g. /home/user/my_ros_ws.\"/>");
        
        String rosPackage = "ros/emergency_exit";
        addLine("<propentry key=\"rosPackage\" type=\"STRING\" value=\"" 
                + rosPackage 
                + "\" description=\"Name of the ROS package, i.e. relative path from the ROS workspace's source folder to the ROS package implementing the simulation, e.g. my_ros_pkg.\"/> ");
    }

}
