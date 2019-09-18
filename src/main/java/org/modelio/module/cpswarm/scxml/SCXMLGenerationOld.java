package org.modelio.module.cpswarm.scxml;

import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.module.cpswarm.generator.Generator;
import org.modelio.module.cpswarm.generator.IGenerator;


public class SCXMLGenerationOld extends Generator implements IGenerator {


    private String filePath = "";


    private StateMachine statemachine = null;


    public SCXMLGenerationOld(StateMachine statemachine) {
        this.statemachine = statemachine;
    }

    public StringBuffer generate() {
        headerXML();
        stateXML();
        footerXML();

        return this.content;
    }

    private void footerXML() {
        decreaseIndent();
        addLine("</scxml>");
    }

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

    private void transitionXML(State currentState) {

        for (Transition currentTrans: currentState.getOutGoing()) {
           
        }
    }


}
