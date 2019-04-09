package org.modelio.module.cpswarm.scxml;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
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

    @objid ("678f5491-5100-42e1-b979-ce56f2faf588")
    public StringBuffer generate() {
        headerXML();
        stateXML();
        footerXML();

        return this.content;
    }

    @objid ("601712da-159b-435c-9163-fc33bd5ea2eb")
    private void footerXML() {
        decreaseIndent();
        addLine("</scxml>");
    }

    @objid ("9df11abf-d830-42fa-8727-df00321;008f7")
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
