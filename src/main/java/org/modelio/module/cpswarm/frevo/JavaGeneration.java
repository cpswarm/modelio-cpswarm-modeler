package org.modelio.module.cpswarm.frevo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModuleNoteTypes;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;

@objid ("30794e5c-04b1-4605-8c3d-23242490be81")
public class JavaGeneration extends Generator implements IGenerator {
    @objid ("96c0853c-1a21-4233-b519-827c90ccc44a")
    private org.modelio.metamodel.uml.statik.Class agent = null;

    @objid ("7be8fcc8-4569-480e-a0c5-e71c33210ac7")
    private org.modelio.metamodel.uml.statik.Class environment = null;

    @objid ("34e36b24-2bea-4b4b-8a2a-c0204c410845")
    public JavaGeneration(org.modelio.metamodel.uml.statik.Class agent, org.modelio.metamodel.uml.statik.Class environment) {
        this.agent = agent;
        this.environment = environment;
    }

    @objid ("72b43099-2c71-4a51-85fe-7d99db6062bd")
    public StringBuffer generate() {
        headerJava();
        classJava();
        return this.content;
    }

    @objid ("d3ca03b6-1250-422b-86ae-b53b68da1279")
    private void evaluateCandidate() {
        addLine("/**");        
        addLine("*  Simulate without visualization to evaluate the candidate representation.");
        addLine("*  @param AbstractRepresentation candidate: The candidate to evaluate.");
        addLine("* @return double: The fitness of the candidate.");
        addLine("*/");
        
        addLine("@Override");
        addLine("public double evaluateCandidate(AbstractRepresentation candidate) {");
        increaseIndent();
        addLine("// create new simulator interface");     
        addLine("ProblemXMLData data = (ProblemXMLData) getXMLData();");
        addLine("sim = new simROS(candidate, getProperties().get(\"rosPath\").getValue(), getProperties().get(\"rosWs\").getValue(), getProperties().get(\"rosPackage\").getValue(), data.getRequiredNumberOfOutputs());");
        addEmptyLine();
        addLine("// read config for simulation");
        addLine("loadParameters();");
        addEmptyLine();
        addLine("// run simulation");
        addLine("sim.run();");
                    
        addEmptyLine();
              
        addLine("// return fitness");
        addLine("return calcFitness();");
        decreaseIndent();
        addLine("}");
    }

    @objid ("fecee7a7-7335-4281-aa7b-1f191f8d567d")
    private void setupJava() {
        addLine("/**");
        addLine(" * Setup environment by placing agents and neeed object.");
        addLine(" * ");
        addLine(" * @param s: random seed.");
        addLine("*/");
        addLine("void setup(int s) {");
        increaseIndent();
        addEmptyLine();
        addLine("// read config from xml file");
        addLine("numAgents = Integer.parseInt(getProperties().get(\"numAgents\").getValue());");
        
        environmentProperties();
        
        addEmptyLine();
        addLine("// create entities");
        addLine("agents = new agent[numAgents];");
        
        environmentEntities();
        
        addEmptyLine();
        addLine("// reset candidate representation");
        addLine("c.reset();");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

    @objid ("f24ee310-77b2-4808-949b-e3f6aa567794")
    private void environmentProperties() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            AssociationEnd opposite = assocEnd.getOpposite();
            String key = "num" + opposite.getOwner().getName() + "s";
            addLine(key + " = Integer.parseInt(getProperties().get(\"" + key + "\").getValue());");    
        }
            
        for (Attribute attribute : this.environment.getOwnedAttribute()){
            String key = attribute.getName();
            addLine(key + " = Integer.parseInt(getProperties().get(\"" + key + "\").getValue());");    
        }
    }

    @objid ("e43328b8-dcab-4a7c-854f-d62edf6385f4")
    private void environmentEntities() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            AssociationEnd opposite = assocEnd.getOpposite();
            Classifier oppositeOwner = opposite.getOwner(); 
            String key = "num" + oppositeOwner.getName() + "s";
            addLine(oppositeOwner.getName().toLowerCase() + "s = new " + oppositeOwner.getName().toLowerCase() + "[" + key +"];");    
        }
    }

    @objid ("90f5ec02-84ee-49ae-8d94-3779eb817d37")
    private void simulateJava() {
        addLine("/**");
        addLine("* Execute the emergency exit simulation for a limited number of steps.");
        addLine("* ");
        addLine("* @return double: number of simulation steps plus negative sum of the minimum distance between each agent and the nearest emergency exit.");
        addLine("*/");        
        addLine("double simulate() {");
        increaseIndent();
        addLine("ArrayList<Float> output = a.representation.getOutput(input);");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

    @objid ("a0896449-00a7-40d6-b226-b52efdc249e4")
    private void agentJava() {
        addLine("/**");
        addLine(" * Class representing one agent in the problem.");
        addLine(" */");    
        addLine("public class " + this.agent.getName() + " {");
        increaseIndent();
        addLine("public AbstractRepresentation r;");
        
        classAttribute(this.agent);
        
        addEmptyLine();
        addLine("/**");
        addLine(" * Constructor.");
        addLine(" * @param r: representation of this agent.");
        addLine(" */");
        addLine("public " + this.agent.getName() + "(AbstractRepresentation r, boolean reachedExit ");
        parameter(this.agent);
        addLine(") {");
        increaseIndent();
        addLine("this.r = r;");
        
        constAttribute(this.agent);
        
        decreaseIndent();
        addLine("}");
        addEmptyLine();
        
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            AssociationEnd opposite = assocEnd.getOpposite();
            generateClass(opposite.getOwner());
        }
        
        
        addEmptyLine();
    }

    @objid ("27090a59-a256-4f67-89c0-8a5c88850c6e")
    private void generateClass(org.modelio.metamodel.uml.statik.Classifier generateClass) {
        addLine("/**");
        addLine(" * " + generateClass.getNoteContent(IModelerModulePeerModule.MODULE_NAME, IModelerModuleNoteTypes.MODELELEMENT_DESCRIPTION) );
        addLine(" */");
        addLine("public class " + generateClass.getName() + " {");
        increaseIndent();
        classAttribute(generateClass);
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

    @objid ("8e1e3315-7630-46ee-a463-869e36007819")
    private void classAttribute(org.modelio.metamodel.uml.statik.Classifier generateClass) {
        for (Attribute attribute : generateClass.getOwnedAttribute()){
            String line = "";
            line += attribute.getVisibility().getLiteral() + " ";
            if (attribute.getType().getName().equals("integer")){
                line += "int" + " ";
            }else{
                line += attribute.getType().getName() + " ";
            }
            line += attribute.getName() + ";";
            addLine(line);    
        }
    }

    @objid ("5a9b80d9-2614-4e06-9fa9-105fb9dda5ff")
    private void parameter(org.modelio.metamodel.uml.statik.Class generateClass) {
        for (Attribute attribute : generateClass.getOwnedAttribute()){
            String name = attribute.getName().toLowerCase();
            String line = " ," + name;
            addLine(line);    
        }
    }

    @objid ("bdfc9f6f-9e78-4e7c-b26d-25bf35b11c23")
    private void constAttribute(org.modelio.metamodel.uml.statik.Class generateClass) {
        for (Attribute attribute : generateClass.getOwnedAttribute()){
            String name = attribute.getName().toLowerCase();
            String line = "this." + name + " = " + name + ";";
            addLine(line);    
        }
    }

    @objid ("5a9aac04-a6b7-459b-a03b-8da963d18428")
    private void maximumFitnessJava() {
        addLine("/**");
        addLine("* Get the maximum possible fitness.");
        addLine("* @return double: maximum fitness.");
        addLine("*/");
        addLine("@Override");
        addLine("public double getMaximumFitness() {");
        addLine("// TODO Auto-generated method stub");
        increaseIndent();
        addLine("return Double.MAX_VALUE;");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

    @objid ("363ab422-891b-4a6b-ab6f-e14fa4b78fa9")
    private void classJava() {
        addLine("/**");
        addLine(" */");
        
        addLine("public class EmergencyExitROS extends AbstractSingleProblem {");
        addEmptyLine();
        increaseIndent();
        
               
        addLine("/**");
        addLine("* Interface to ROS simulator.");
        addLine("*/");
        addLine("private simROS sim;");
        addEmptyLine();
              
        
        loadParameters();
        calcFitness();
        
        
        //        agentJava();
        //        simulateJava();
        //        setupJava();
        evaluateCandidate();
        replayWithVisualization();
        maximumFitnessJava();
        
        decreaseIndent();
        addLine("}");
    }

    @objid ("00480931-23dc-49c1-801a-8928d4700f6f")
    private void envArray() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            String name = assocEnd.getOpposite().getOwner().getName().toLowerCase();
            String line =  name + "[] " + name + "s;";
            addLine(line);
        }
    }

    @objid ("07347e32-1e6e-4c20-bfe5-b2f2ec2eaab5")
    private void envProperties() {
        for (AssociationEnd assocEnd : this.environment.getOwnedEnd()){
            
            String line = "int num" + assocEnd.getOpposite().getOwner().getName() + "s;";
            addLine(line);
        }
        
        
        for (Attribute attribute : this.environment.getOwnedAttribute()){
            String line = "";
            if (attribute.getType().getName().equals("integer")){
                line += "int" ;
            }else {
                line += attribute.getType().getName() ; 
            }
            line += " " + attribute.getName() + ";";
            addLine(line);
        }
    }

    @objid ("23350b62-7804-4fb9-a109-11fbf6a744c8")
    private void headerJava() {
        addLine("package emergencyexitros;");
        addEmptyLine();
        addLine("import java.util.ArrayList;");
        addLine("import java.util.Hashtable;");
        addLine("import java.util.NavigableMap;");
        addEmptyLine();
        addLine("import core.AbstractRepresentation;");
        addLine("import core.AbstractSingleProblem;");
        addLine("import core.ProblemXMLData;");
        addEmptyLine();     
        addLine("import helper.simROS;");
        addLine("import helper.Environment;");
        addEmptyLine();
    }

    @objid ("c74ef61a-7326-4002-8980-c46a67265141")
    private void replayWithVisualization() {
        addLine("/**");        
        addLine("*  Simulate with visualization to introspect a certain candidate representation.");
        addLine("* @param AbstractRepresentation candidate: The candidate to introspect.");
        addLine("*/");
        
        addLine("@Override");
        addLine("public void replayWithVisualization(AbstractRepresentation candidate) {");
        increaseIndent();
        addLine("// create new simulator interface");     
        addLine("ProblemXMLData data = (ProblemXMLData) getXMLData();");
        addLine("sim = new simROS(candidate, getProperties().get(\"rosPath\").getValue(), getProperties().get(\"rosWs\").getValue(), getProperties().get(\"rosPackage\").getValue(), data.getRequiredNumberOfOutputs());");
        addEmptyLine();
        addLine("// read config for simulation");
        addLine("loadParameters();");
        addEmptyLine();
        addLine("// run simulation");
        addLine("sim.runVisual();");
        decreaseIndent();
        addLine("}");
    }

    @objid ("a4fcb7f0-80cb-4f51-a2b4-b21ea593c1b8")
    private void loadParameters() {
        addLine("/**");
        addLine("* Load configuration parameters and store in simulator interface.");
        addLine("*/");
        addLine("private void loadParameters() {");
        increaseIndent();
        addLine("// general simulation parameters for ROS");
        addLine("Hashtable<String,String> params = new Hashtable<String, String>();");
        addLine("params.put(\"steps\", getProperties().get(\"steps\").getValue());");
        addLine("params.put(\"agents\", getProperties().get(\"swarmMembers\").getValue());");
        addLine("sim.setParameters(params);");
        addEmptyLine();
        addLine("// name of the environment to simulate in");
        addLine("sim.setEnvironment(Environment.fromString(getProperties().get(\"environment\").getValue()));");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

    @objid ("74383ad8-53eb-4909-8d8b-9a7c1ca63a0c")
    private void calcFitness() {
        addLine("/**");
        addLine("* Calculate the fitness score of the last simulation run.");
        addLine("* @return double: The fitness score.");
        addLine("*/");
        addLine("private double calcFitness() {");
        increaseIndent();
        addLine("// get content from log files");
        addLine("ArrayList<NavigableMap<Integer,Double>> logs = sim.getLogs();");
        addEmptyLine();
        
        addLine("// fitness score is negative sum of all distances");
        addLine("double dist = 0;");
        addEmptyLine();
        
        addLine("// iterate all log files");
        addLine("for (NavigableMap<Integer,Double> log : logs) {");
        increaseIndent();
        addLine("if (log.size() > 0)");
        addLine("// take last line of log file");
        addLine("dist = dist + log.lastEntry().getValue();");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
        
        addLine("// return negative distance as fitness;");
        addLine("return -dist;");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

}
