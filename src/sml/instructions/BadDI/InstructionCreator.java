package sml.instructions.BadDI;



import sml.Instruction;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class InstructionCreator {

    private static final InstructionCreator instance = new InstructionCreator();

    private static InstructionProviderOld imple;
    private InstructionCreator() {

    };

    public static InstructionCreator getInstance(){
        return instance;
    }
    public static Instruction serveInstructionSet(String InstructionSet , List<String> InstructionArguments){
        Properties props = new Properties();
        //String filePath = Paths.get("../../bean.properties").toString();
        String filePath = "C:\\Users\\alexv\\IdeaProjects\\coursework-one-sml-AlexeiVartoumian\\beans.properties";
        try{
            try(var resource = new FileInputStream(filePath)){
                props.load(resource);
            }
            String providerClass = props.getProperty("provider.class");

           imple = (InstructionProviderOld) Class.forName(providerClass)
                   .getDeclaredConstructor(String.class)
                   .newInstance(InstructionSet);

        } catch(Exception e){
            e.printStackTrace();
        }
        return imple.deliverInstruction(InstructionArguments);
    }


}
