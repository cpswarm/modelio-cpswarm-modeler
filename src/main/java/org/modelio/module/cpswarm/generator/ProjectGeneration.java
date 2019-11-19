package org.modelio.module.cpswarm.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.cpswarm.api.CPSWarmStereotypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;

@objid ("9f04dc80-68df-4ec8-84a9-e5c877227bc6")
public class ProjectGeneration {
    @objid ("93a25939-6897-4e12-bc48-a62f83a1fb3f")
    private String frevopath = "";

    @objid ("83ebc1c3-8815-4df7-8f3e-629663ca6808")
    private org.modelio.metamodel.uml.statik.Class problem = null;

    @objid ("6721bd9e-e3ac-4691-a489-46d4ed02f5a4")
    private org.modelio.metamodel.uml.statik.Class swarm = null;

    @objid ("c8470ea1-2c42-462a-b58e-c5f42af0a3ab")
    private org.modelio.metamodel.uml.statik.Class agent = null;

    @objid ("952244c3-5a66-4422-95cb-a910bed29a65")
    private org.modelio.metamodel.uml.statik.Class environment = null;

    @objid ("ec836d6d-3c76-4532-9111-e50cc4d29571")
    private List<org.modelio.metamodel.uml.statik.Class> environmentRelated = new ArrayList<>();

    @objid ("e2d034a6-bf67-499e-a28c-3f87b71431dd")
    private org.modelio.metamodel.uml.statik.Class costFunction = null;

    @objid ("4fb0c884-a8af-4357-b8e1-aa62349b8fa0")
    private org.modelio.metamodel.uml.statik.Class controller = null;

    @objid ("60a6a8f7-a208-4a41-82a2-5f4dfb56e99c")
    public ProjectGeneration() {
    }

    @objid ("22013a97-2678-4ac9-bf95-3a1d32e85747")
    public ProjectGeneration(org.modelio.metamodel.uml.statik.Class problem) {
        this.problem = problem;
        setRelatedClasses();
    }

    @objid ("beee117e-83dc-4a1b-afce-435cbe351e90")
    private void setRelatedClasses() {
        for (Dependency dependency : this.problem.getDependsOnDependency()){
            
            if (dependency instanceof Usage){
        
                ModelElement target = dependency.getDependsOn();
                
                if ((target instanceof org.modelio.metamodel.uml.statik.Class) 
                        && (target.isStereotyped(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.ENVIRONMENT))){
                    this.environment = (org.modelio.metamodel.uml.statik.Class) target;
        
                }else if ((target instanceof org.modelio.metamodel.uml.statik.Class) 
                        && (target.isStereotyped(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.FITNESSFUNCTION))){
                    this.costFunction = (org.modelio.metamodel.uml.statik.Class) target;
        
                }else if ((target instanceof org.modelio.metamodel.uml.statik.Class) 
                        && (target.isStereotyped(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM))){
        
                    this.swarm = (org.modelio.metamodel.uml.statik.Class) target;
        
                    //looking for Swarm member
                    for (AssociationEnd assocEnd2 : this.swarm.getOwnedEnd()){
                        AssociationEnd oppositeEnd2 = assocEnd2.getOpposite();
                        Classifier oppositeOwner2 = oppositeEnd2.getOwner();
                        if ((oppositeOwner2 instanceof org.modelio.metamodel.uml.statik.Class) 
                                && (oppositeOwner2.isStereotyped(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.SWARM_MEMBER))){                        
                            this.agent = (org.modelio.metamodel.uml.statik.Class) oppositeOwner2;
                        }
                    }
                    
                    for (BindableInstance part : this.agent.getInternalStructure()){
                        NameSpace base = part.getBase();
                        if ((base != null) && (base instanceof org.modelio.metamodel.uml.statik.Class)
                                && (base.isStereotyped(ICPSWarmPeerModule.MODULE_NAME, CPSWarmStereotypes.CONTROLLER))){
                            this.controller = (org.modelio.metamodel.uml.statik.Class) base;
                        }
                    }
                }
            }
        }
    }

    @objid ("b36cadde-e3a7-44f4-b1b8-6cda095ae937")
    public void generate(String rosWs, String rosPath, String frevopath) {
        String xmlPath = frevopath + File.separator + "Components" + File.separator + "Problems" + File.separator + problem.getName() + ".xml";
        File xmlFile = new File(xmlPath);
        if (!xmlFile.exists())
            xmlFile.getParentFile().mkdirs();
            
        XMLGeneration  xmlGen = new XMLGeneration(this.problem, this.swarm, this.environment, this.costFunction, this.controller, rosWs, rosPath);
        StringBuffer content = xmlGen.generate();
        write(xmlPath, content);
        
        
        String javaPath = frevopath + File.separator + "Components" + File.separator + "Problems" + File.separator + problem.getName() + File.separator + problem.getName().toLowerCase() + File.separator + problem.getName() +".java";
        File javaFile = new File(javaPath);
        if (!javaFile.exists())
            javaFile.getParentFile().mkdirs();
        
        JavaGeneration  javaGen = new JavaGeneration(this.agent, this.environment);
        content = javaGen.generate();        
        write(javaPath, content);
    }

    @objid ("c14e4b2f-2f81-457a-b13b-b6d60702047d")
    private void write(String path, StringBuffer sbf) {
        File file = new File(path);
        file.getParentFile().mkdirs();
        
        try {
            file.createNewFile();
            /*
             * To write contents of StringBuffer to a file, use
             * BufferedWriter class.
             */
        
            BufferedWriter bwr;
        
            bwr = new BufferedWriter(new FileWriter(file));
        
        
            //write contents of StringBuffer to a file
            bwr.write(sbf.toString());
        
        
            //flush the stream
            bwr.flush();
        
            //close the stream
            bwr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
