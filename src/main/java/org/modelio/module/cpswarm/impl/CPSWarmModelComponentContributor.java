package org.modelio.module.cpswarm.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.modelio.api.modelio.mc.AbstractModelComponentContributor;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.cpswarm.api.CPSWarmStereotypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;


public class CPSWarmModelComponentContributor extends AbstractModelComponentContributor {
    
    private IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();
    
    private MMetamodel metamodel = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel();
  
    public CPSWarmModelComponentContributor(IModule module) {
        super(module);
    }

    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        Set<Stereotype> stereotypes = new HashSet<>();
        
        stereotypes.add(getStereotype(org.modelio.metamodel.uml.statik.Class.class, CPSWarmStereotypes.CONTROLLER));
        return stereotypes;
    }

    @Override
    public Set<MObject> getElements() {
        return Collections.emptySet();
    }

    @Override
    public Set<ExportedFileEntry> getFiles() {
        return Collections.emptySet();
    }

    @Override
    public Set<NoteType> getNoteTypes() {
        Set<NoteType> noteTypes = new HashSet<>();
        return noteTypes;
    }

    @Override
    public Set<TagType> getTagTypes() {
        Set<TagType> tagTypes = new HashSet<>();       
        return tagTypes;
    }
    
    
    /**
     * Get a Steretotype from the metamodel extensions.
     */
    private Stereotype getStereotype(java.lang.Class<? extends MObject> metaclass, String stereotypeName) {
        IMetamodelExtensions metamodelExt = this.session.getMetamodelExtensions();     
        return metamodelExt.getStereotype(ICPSWarmPeerModule.MODULE_NAME, stereotypeName, this.metamodel.getMClass(metaclass));
    }

}
