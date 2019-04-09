package org.modelio.module.cpswarm.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;

public class FitnessFunctionGeneration extends Generator implements IGenerator {

    private List<BindableInstance> fcts = new ArrayList<>();

    private Set<Classifier> FCT = new HashSet<>();

    private Class ff = null;

    public FitnessFunctionGeneration(Class ff) {    
        this.ff = ff;
    }

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


    private void main() {
        addLine("public static void main(String[] args) {");
        increaseIndent();
        addLine("calcFitness();");
        decreaseIndent();
        addLine("}");
    }

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
        addLine("File logPath = null;");
        addLine("try {");
        increaseIndent();
        addLine("logPath = new File(FitnessFunctionCalculator.class.getResource(\"/logs\").toURI());");
        decreaseIndent();
        addLine(" } catch (URISyntaxException e1) {");
        increaseIndent();
        addLine("// TODO Auto-generated catch block");
        addLine("e1.printStackTrace();");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
        increaseIndent();
        addLine("// iterate through all log files");
        addLine("String[] logFiles = logPath.list(new FileLogFilter());");
        addLine("for ( int i=0; i<logFiles.length; i++ ) {");
        addEmptyLine();
        increaseIndent();
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
        decreaseIndent();
        addEmptyLine();
        addLine("// store contents of log file");
        addLine("logs.add(log);");
        addLine("}");
        addLine("return true;");
        decreaseIndent();
        addLine("}");
        addEmptyLine();
    }

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
    }

    private void footer() {
        decreaseIndent();
        addLine("}");
    }

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
            addLine(base.getConstraintDefinition().get(0).getBody());
            decreaseIndent();
            addLine("}");
        }
    }

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

    private void header() {
        addLine("package manager;");
        addEmptyLine();
        addLine("import java.io.BufferedReader;");
        addLine("import java.io.File;");
        addLine("import java.io.IOException;");
        addLine("import java.net.URISyntaxException;");
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
