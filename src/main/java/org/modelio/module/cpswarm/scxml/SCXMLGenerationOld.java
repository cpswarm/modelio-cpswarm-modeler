package org.modelio.module.cpswarm.scxml;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.module.cpswarm.generator.Generator;
import org.modelio.module.cpswarm.generator.IGenerator;

@objid ("b7f7f7b7-e458-40a8-8014-b201a14e7242")
public class SCXMLGenerationOld extends Generator implements IGenerator {
    @objid ("3a2bc062-f79b-46c8-a14b-a0344ac8ee45")
    private String filePath = "";

    @objid ("7dfb2a32-3be9-4cba-a38d-00c54beb6c45")
    private StateMachine statemachine = null;

    @objid ("7d9317bf-37f9-447d-b97e-30e910915483")
    public SCXMLGenerationOld(StateMachine statemachine) {
        this.statemachine = statemachine;
    }

    @objid ("15675942-318a-4d38-9c88-32e9e8d83106")
    public StringBuffer generate() {
        headerXML();
        stateXML();
        footerXML();
        return this.content;
    }

    @objid ("285617ef-4208-40c9-81d2-efd9a5cc7729")
    private void footerXML() {
        decreaseIndent();
        addLine("</scxml>");
    }

    @objid ("08ec095b-6415-4be9-9090-2e65d951b951")
    private void headerXML() {
        String initialname = "";
        for (StateVertex currentVertex : this.statemachine.getTop().getSub()) {
            if (currentVertex instanceof InitialPseudoState) {
                initialname = currentVertex.getOutGoing().get(0).getTarget().getName();
            }
        }
        
        addLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        addLine("<scxml xmlns=\"http://www.w3.org/2005/07/scxml\" version=\"1.0\" initial=\"" + initialname + "\">");
        addEmptyLine();
        increaseIndent();
    }

    @objid ("664ecf1c-a519-4e01-ad64-c722a4abc2d6")
    private void stateXML() {
        for (StateVertex currentVertex : this.statemachine.getTop().getSub()) {
            
            if (currentVertex instanceof State) {
                State currentState = (State) currentVertex;
                String stateName = currentState.getName();
        
                addLine("<state id=\"" + stateName + "\">");
        
                increaseIndent();
                transitionXML(currentState);
                decreaseIndent();
        
                addLine("</state>");
            }
            
            addEmptyLine();
        }
    }

    @objid ("e1ffa9b5-4c7b-46cb-9564-3d0882fc3e46")
    private void transitionXML(State currentState) {
        for (Transition currentTrans: currentState.getOutGoing()) {
           
        }
    }

}
