package org.modelio.module.cpswarm.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.module.cpswarm.api.CPSWarmNoteTypes;
import org.modelio.module.cpswarm.api.ICPSWarmPeerModule;

@objid ("6ef2520d-29f4-455e-acb6-b1ea90a86b4a")
public class FitnessFunctionGeneration extends Generator implements IGenerator {
    @objid ("5612587d-a879-477a-917d-127be05cfc50")
    private Class ff = null;

    @objid ("79fd3062-dc2e-445f-9318-84575eb0a4fc")
    private List<BindableInstance> fcts = new ArrayList<>();

    @objid ("364291e1-cb5c-4e32-8e25-e77a18d42140")
    private Set<Classifier> FCT = new HashSet<>();

    @objid ("166feae3-a210-4535-be3c-160b11aa44e2")
    public FitnessFunctionGeneration(Class ff) {
        this.ff = ff;
    }

    @objid ("98d0c72a-91d1-4f6d-af73-9d9f12326b2d")
    @Override
    public StringBuffer generate() {
        header();
        attribut();
        main();
        logs();
        function();
        fcts();
        footer();
        return this.content;
    }

    @objid ("02e54304-27ef-4e2f-a988-e4a432012fb3")
    private void main() {
        addLine("public static void main(String[] args) {");
        increaseIndent();
        addLine("calcFitness();");
        decreaseIndent();
        addLine("}");
    }

    @objid ("fcf8f913-c720-4fd0-82f2-79ca90827fb4")
    private void logs() {
        addEmptyLine();
        addLine("/**");
        addLine("* Read the log files produced by ROS.");
        addLine("* It assumes log files with two columns, separated by tabulator.");
        addLine("* The first column must be an integer, the second a double value.");
        addLine("* @return ArrayList<NavigableMap<Integer,Double>>: An array with one map entry for each log file.");
        addLine("*/");
        addLine("private static boolean readLogs() {");
        increaseIndent();
        addLine("// container for data of all log files");
        addLine("logs = new ArrayList<NavigableMap<Integer,Double>>();");
        addEmptyLine();
        addLine("// path to log directory");
        addLine("File logPath = new File(path);");
        addEmptyLine();
        addLine("// iterate through all log files");
        addLine("String[] logFiles = logPath.list(new FileLogFilter());");
        addLine("for ( int i=0; i<logFiles.length; i++ ) {");
        addEmptyLine();
        addLine("// container for data of one log file");
        addLine("NavigableMap<Integer,Double> log = new TreeMap<Integer, Double>();");
        addEmptyLine();
        addLine("// read log file");
        addLine("Path logFile = Paths.get(logPath + \"/\" + logFiles[i]);");
        addLine("try {");
        increaseIndent();
        addLine("BufferedReader logReader = Files.newBufferedReader(logFile);");
        addEmptyLine();
        addLine("// store every line");
        addLine("String line;");
        addLine("while ((line = logReader.readLine()) != null) {");
        increaseIndent();
        addLine("if ( line.length() <= 1 || line.startsWith(\"#\") )");
        increaseIndent();
        addLine("continue;");
        addEmptyLine();
        decreaseIndent();
        addLine("log.put(Integer.parseInt(line.split(\"\t\")[0]), Double.parseDouble(line.split(\"\t\")[1]));");
        decreaseIndent();
        addLine("}");
        decreaseIndent();
        addLine("}");
        decreaseIndent();
        addLine("catch (IOException e) {");
        increaseIndent();
        addLine("e.printStackTrace();");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
        addLine("// store contents of log file");
        addLine("logs.add(log);");
        addLine("}");
        addLine("return true;");
        addLine("}");
        addEmptyLine();
    }

    @objid ("2491a8b2-d73f-4383-863a-3b263e4d778c")
    private void attribut() {
        for (BindableInstance attr : this.ff.getInternalStructure()) {
            if (!(attr instanceof Port)) {
                if (attr.getBase() instanceof DataType) {
                    String type = attr.getBase().getName().toLowerCase();
                    if (type.equals("integer"))
                        type ="int";
        
                    addLine("private static final " + type + " " + attr.getName() + " = " + attr.getValue() + ";");
                }else {
                    this.fcts.add(attr);
                    if (attr.getBase() instanceof Classifier)
                        this.FCT.add((Classifier)attr.getBase());
                }
            }
        }
        
        addEmptyLine();
    }

    @objid ("ab37f107-e9d9-41a9-bf94-73009baed85d")
    private void footer() {
        decreaseIndent();
        addLine("}");
    }

    @objid ("e28e8764-047b-4ba8-ab4e-fd5b52c70e41")
    private void fcts() {
        for (Classifier base : this.FCT) {
            addEmptyLine();
            String name = "private static ";
            for (BindableInstance part : base.getInternalStructure()) {
                if ((part instanceof Port) & ((Port) part).getDirection().equals(PortOrientation.OUT)) {
                    String basename = part.getBase().getName().toLowerCase();
                    if (basename.equals("integer")){
                        basename = "int";
                    }
                    name += basename + " ";
                }
            }
            
            name += base.getName().toLowerCase();
            name += getPortList(base);
            name += "{"; 
            addLine(name);
            increaseIndent();
            
            String code = base.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.JAVA);  
            if ((code == null) || (code.equals("")))
                code = "return " + base.getNoteContent(ICPSWarmPeerModule.MODULE_NAME, CPSWarmNoteTypes.MATH);  
            
            addLine(code);
            
            
            decreaseIndent();
            addLine("}");
        }
    }

    @objid ("48f07233-83cb-45d0-8391-ff53810c662c")
    private void function() {
        addLine("/**");
        addLine("* Calculate the fitness score of the last simulation run.");
        addLine("* @return boolean: result of the method.");
        addLine("*/");
        addLine("private static boolean calcFitness() {");
        addEmptyLine();increaseIndent();             
        addLine(" if(!readLogs()) {");
        increaseIndent();
        addLine("return false;");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
        addLine("double fitnessSum = 0;");
        addLine("double totalTimeTaken = 0;");
        addLine("double totalDistance = 0;");
        addEmptyLine();
        addLine("for (NavigableMap<Integer,Double> log : logs) {");
        increaseIndent();
        addLine("double agentTimeTaken = TIMEOUT;");
        addLine("double agentDistance = MAX_DISTANCE;");
        addLine("if (log.size() > 0) {");
        increaseIndent();
        addLine("agentTimeTaken = Math.min(log.size(), TIMEOUT);");    
        addLine("agentDistance = Math.min(log.lastEntry().getValue(), MAX_DISTANCE);");
        decreaseIndent();
        addLine("}");
        for (BindableInstance fct : this.fcts) {
            addEmptyLine();
            String msg =  "double "+ fct.getName() + " = " + fct.getBase().getName().toLowerCase() ;
            msg += getPortList(fct);
            addLine(msg);
        }
        addEmptyLine();
        addLine("fitnessSum += fitness;");
        decreaseIndent();
        addLine("}");
        addLine("// overall fitness is average fitness of agents");
        addLine(" System.out.println(\"fitness score:\" + fitnessSum / logs.size());");
        addLine("return true;");
        decreaseIndent();
        addLine("}");
    }

    @objid ("e972aaac-6817-4208-8069-e2b5461d933c")
    private String getPortList(BindableInstance fct) {
        String result = "(";
        
        for (BindableInstance part : fct.getPart()) {
            if (part instanceof Port) {
                Port port = (Port) part;
                if (port.getDirection().equals(PortOrientation.IN)){
                    result += port.getTargetingEnd().get(0).getSource().getName() + ",";
                }
            }
        }
        
        result = result.substring(0, result.length() -1);
        return result += ")";
    }

    @objid ("11403192-203b-4d4f-9702-24f82166155a")
    private String getPortList(NameSpace fct) {
        String result = "(";
        
        if (fct instanceof Classifier) {
            for (BindableInstance part : ((Classifier)fct).getInternalStructure()) {
                if (part instanceof Port) {
                    Port port = (Port) part;
                    if (port.getDirection().equals(PortOrientation.IN)){
                        result += port.getBase().getName().toLowerCase() + " " + port.getName() + "," ;
                    }
                }
            }
        
            result = result.substring(0, result.length() -1);
        }
        return result += ")";
    }

    @objid ("52c4be50-6899-4a34-a9c9-3be361c54e48")
    private void header() {
        addLine("package manager;");
        addEmptyLine();
        addLine("import java.io.BufferedReader;");
        addLine("import java.io.File;");
        addLine("import java.io.IOException;");
        addLine("import java.nio.file.Files;");
        addLine("import java.nio.file.Path;");
        addLine("import java.nio.file.Paths;");
        addLine("import java.util.ArrayList;");
        addLine("import java.util.NavigableMap;");
        addLine("import java.util.TreeMap;");
        addEmptyLine();
        addLine("public final class FitnessFunctionCalculator {");
        increaseIndent();
        addLine("private static ArrayList<NavigableMap<Integer,Double>> logs;");
    }

}
