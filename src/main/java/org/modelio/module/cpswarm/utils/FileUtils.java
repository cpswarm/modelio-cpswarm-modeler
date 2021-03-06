package org.modelio.module.cpswarm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.cpswarm.impl.CPSWarmModule;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a6d0dea7-b1b7-4f01-a170-caa125495be2")
public class FileUtils {
    /**
     * Unzip given file to the outpur folder
     * @param output zip file output folder
     * @param zipFile input zip file
     */
    @objid ("953efc59-ae7c-43be-a0e2-6f07aa351f9a")
    public static void unZipIt(File zipFile, File folder) {
        byte[] buffer = new byte[1024];
        
        try{
        
            //create output directory is not exists
            if(!folder.exists()){
                folder.mkdir();
            }
        
            //get the zip file content
            try(ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))){
                //get the zipped file list entry
                ZipEntry ze = zis.getNextEntry();
        
                while(ze != null){
        
                    String fileName = ze.getName();
                    File newFile = new File(folder.getAbsolutePath() + File.separator + fileName);
        
                    //create all non exists folders
                    //else you will hit FileNotFoundException for compressed folder
                    new File(newFile.getParent()).mkdirs();
        
                    if (fileName.endsWith("/")){
                        newFile.mkdir();
        
                    }else{
        
                        try(FileOutputStream fos = new FileOutputStream(newFile)){
        
                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }
        
                            fos.close();
                        }
                    }
                    ze = zis.getNextEntry();
                }
        
                zis.closeEntry();
                zis.close();
            }
        
        
        }catch(IOException e){
            CPSWarmModule.logService.error(e);
        }
    }

    /**
     * Write the stringBuffer to the file specified by the filePath
     * @param filePath  output File
     * @param sbf input StringBuffer
     */
    @objid ("7692cdc1-d527-41a4-a674-559012d4ab9d")
    public static void write(final File file, StringBuffer sbf) {
        BufferedWriter bwr = null;
        
        try {
            
            file.getParentFile().mkdirs();
            file.createNewFile();
            
            try {
                /*
                 * To write contents of StringBuffer to a file
                 */
                bwr = new BufferedWriter(new FileWriter(file));
        
                //write contents of StringBuffer to a file
                bwr.write(sbf.toString());
        
                //flush the stream
                bwr.flush();
                
            
        
            } finally {
                
                //close the stream
                bwr.close();
                
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            CPSWarmModule.logService.error(e);
        }
    }

    @objid ("1b895671-0f04-47e5-b1f2-d949a7744714")
    public static void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                delete(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file: " + f);
    }

    @objid ("87126127-3576-4465-91f1-2be7f1c061d2")
    public static void saveRelatedDiag(ModelElement element, String filePath, String moduleName, String diagramType) {
        for (Dependency depends : element.getDependsOnDependency()){
            if (depends.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.RELATED_DIAGRAM)){
                ModelElement target = depends.getDependsOn();
        
                if (target.isStereotyped(moduleName, diagramType)){
                    try(IDiagramHandle diagramHandle = CPSWarmModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle((AbstractDiagram) target)){
        
                        File image = new File(filePath);
                        if (!image.exists()){
                            image.getParentFile().mkdirs();
                            try {
                                image.createNewFile();
                            } catch (IOException e) {
                                CPSWarmModule.logService.error(e);
                            }
                        }
                        diagramHandle.saveInFile("PNG", filePath , 2);
                    }
                }
            }
        }
    }

    @objid ("8931a844-7622-42a3-a268-85932c928ae7")
    public static String findFilePath(String rootFilePath, final MObject object) {
        File rootFile = new File(rootFilePath);
        String filename = object.getUuid().toString() + ".exml";
        
        String absolutePath = FileUtils.findFilePath(rootFile, filename);
        
        MObject current = object;
        
        while (absolutePath.equals("")){
            current = current.getCompositionOwner();
            filename = current.getUuid().toString() + ".exml";
            absolutePath = findFilePath(rootFile, filename);
        }
        return absolutePath;
    }

    @objid ("3f83fefb-0eb7-4a2e-bd09-3f21c9d57413")
    public static boolean fileExist(String rootFilePath, MObject object) {
        File rootFile = new File(rootFilePath);
        String filename = object.getUuid().toString() + ".exml";
        
        String absolutePath = FileUtils.findFilePath(rootFile, filename);
        return (!absolutePath.equals(""));
    }

    @objid ("f1bdc140-4db6-4e5c-86f1-3656d2b5b28d")
    private static String findFilePath(File rootFile, String filename) {
        String path = "";
        
        if(rootFile.isDirectory()){
            List<File> files = Arrays.asList(rootFile.listFiles());
            Iterator<File> fileIterator = files.iterator();
            while(fileIterator.hasNext() &&  path.equals("")){
                path = findFilePath(fileIterator.next(), filename);
            }
        } else {
        
            if (rootFile.getName().equals(filename)) {
                path = rootFile.getAbsolutePath();
            }
        
        }
        return path;
    }

    @objid ("479b6893-0e37-4aaf-b82c-3fda7d755786")
    public static File findFile(File file, String filename) {
        File path = null;
        
        if(file.isDirectory()){
            List<File> files = Arrays.asList(file.listFiles());
            Iterator<File> fileIterator = files.iterator();
            while(fileIterator.hasNext() &&  (path == null)){
                path = findFile(fileIterator.next(), filename);
            }
        } else {
        
            if (file.getName().equals(filename)) {
                path = file;
            }
        
        }
        return path;
    }

    @objid ("9ba716c3-82b6-47ec-b15d-1cf817f9af20")
    public static String findDir(File parent, String dirname) {
        String path = "";
        
        if(parent.isDirectory()){
            if (parent.getName().equals(dirname)) {
                path = parent.getAbsolutePath();
            }
        
            List<File> files = Arrays.asList(parent.listFiles());
            Iterator<File> fileIterator = files.iterator();
            while(fileIterator.hasNext() &&  path.equals("")){
                path = findDir(fileIterator.next(), dirname);
            }
        }
        return path;
    }

}
