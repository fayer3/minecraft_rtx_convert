package minecraft_converter;

import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.operations.OperationManager;
import minecraft_converter.tools.FileAccess;

import java.util.concurrent.Callable;

public class Convert implements Callable<String> {

    private final String target;
    private String parameter;
    private final String filepath;
    private final Settings config;
    private final FileAccess access;

    public Convert(String target, String parameter, String filepath, Settings config, FileAccess access) {
        this.target = target;
        this.parameter = parameter;
        this.filepath = filepath;
        this.config = config;
        this.access = access;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     */
    @Override
    public String call() throws ExitNow {
        String mode = parameter;
        String[] parameters = null;
        if (parameter.contains("[")) {
            mode = parameter.substring(0, mode.indexOf("["));
            String par = parameter.substring(mode.length() + 1, parameter.length() - 1);
            parameters = par.split(":");
        }
        return OperationManager.chooseOperation(null, access, mode, parameters, filepath, target, config);
    }
}
