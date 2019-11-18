package org.modelio.module.cpswarm.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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

@objid ("0329d19c-033e-4cbf-9529-3c43c1f67a4d")
public class CPSWarmModelComponentContributor extends AbstractModelComponentContributor {
    @objid ("b6e1a8d9-bac2-4874-b6bd-a8f455bebfed")
    private IModelingSession session = CPSWarmModule.getInstance().getModuleContext().getModelingSession();

    @objid ("19d33bcb-5722-40ff-8b63-3a2655efcc9b")
    private MMetamodel metamodel = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel();

    @objid ("a8550462-a4c1-4f8e-8415-56a5e5375d11")
    public CPSWarmModelComponentContributor(IModule module) {
        super(module);
    }

    @objid ("d2c471d2-9751-4a11-bbe0-cdc9206e23ff")
    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        Set<Stereotype> stereotypes = new HashSet<>();
        
        stereotypes.add(getStereotype(org.modelio.metamodel.uml.statik.Class.class, CPSWarmStereotypes.CONTROLLER));
        return stereotypes;
    }

    @objid ("4357592c-0204-45d3-87d6-f503bfe01b4e")
    @Override
    public Set<MObject> getElements() {
        return Collections.emptySet();
    }

    @objid ("b2de2b08-1eb1-4dcf-add0-24ec64b89f14")
    @Override
    public Set<ExportedFileEntry> getFiles() {
        return Collections.emptySet();
    }

    @objid ("b4a3ec8f-d4d8-457c-bbd5-6b16b9fa32bb")
    @Override
    public Set<NoteType> getNoteTypes() {
        Set<NoteType> noteTypes = new HashSet<>();
        return noteTypes;
    }

    @objid ("cccaaa3d-2b37-449e-9d9b-596ceaa912e1")
    @Override
    public Set<TagType> getTagTypes() {
        Set<TagType> tagTypes = new HashSet<>();
        return tagTypes;
    }

    /**
     * Get a Steretotype from the metamodel extensions.
     */
    @objid ("c9b5b708-2309-4c8f-b209-4536e8701b2b")
    private Stereotype getStereotype(java.lang.Class<? extends MObject> metaclass, String stereotypeName) {
        IMetamodelExtensions metamodelExt = this.session.getMetamodelExtensions();
        return metamodelExt.getStereotype(ICPSWarmPeerModule.MODULE_NAME, stereotypeName, this.metamodel.getMClass(metaclass));
    }

}
