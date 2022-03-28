package minecraft_converter.operations;

import javafx.scene.control.Label;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.operations.function.*;
import minecraft_converter.operations.function.javagui.*;
import minecraft_converter.operations.restructure.*;
import minecraft_converter.tools.FileAccess;

public class OperationManager {

    public static String chooseOperation(Label fileProgress, FileAccess access, String mode, String[] parameters, String source, String target, Settings config) throws ExitNow{
//        Platform.runLater(()->fileProgress.setText(target));
        switch (mode) {
            case "copy":
                return Copy.compute(access, parameters, source, target, config, false);
            case "single":
                return Single.compute(access, parameters, source, target, config);
            case "combine":
                return Combine.compute(access, parameters, source, target, config);
            case "color":
                return combine(
                        Color.compute(access, parameters, source, target, config),
                        Copy.compute(access, parameters, source, target, config, true));
            case "fill":
                return combine(
                        Fill.compute(access, parameters, source, target, config),
                        Copy.compute(access, parameters, source, target, config, true));
            case "restructure":
                if (parameters.length > 1) {
                    String[] files = new String[parameters.length-1];
                    for (int f = 0; f < files.length; f++) {
                        if (parameters[f+1].equals("this"))
                            files[f] = source;
                        else if (!"null".equals(parameters[f+1]))
                            files[f] = parameters[f+1];
                    }
                    return chooseRestructure(access, parameters[0], files, target, config);
                } else {
                    throw new ExitNow("restructure operation needs at least two arguments, a mode and files to work with");
                }
            case "function":
                if (parameters.length > 0)
                    return chooseFunction(fileProgress, access, source, parameters[0], config);
                else
                    throw new ExitNow("function operation needs at needs a function name as parameter to work");
            default:
                System.out.println("unsupported method: '"+mode+"' for '"+source+"'");
        }
        return "";
    }

    private static String chooseRestructure(FileAccess access, String mode, String[] files, String target, Settings config) throws ExitNow{
        switch (mode) {
            case "piston":
                if (files.length == 4) {
                    return RestructurePiston.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure piston operation needs 4 files as arguments");
                }
            case "bed":
                if (files.length == 1) {
                    return RestructureBed.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure bed operation needs 1 file as argument");
                }
            case "chest_single":
                if (files.length == 1) {
                    return RestructureChestSingle.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure chest_single operation needs 1 file as argument");
                }
            case "chest_double":
                if (files.length == 2) {
                    return RestructureChestDouble.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure chest_double operation needs 2 files as argument");
                }
            case "dragon":
                if (files.length == 1) {
                    return RestructureDragon.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure dragon operation needs 1 file as argument");
                }
            case "tropical_a":
                if (files.length == 1) {
                    return RestructureTropicalA.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure tropical_a operation needs 1 file as argument");
                }
            case "tropical_b":
                if (files.length == 1) {
                    return RestructureTropicalB.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure tropical_b operation needs 1 file as argument");
                }
            case "dolphin":
                if (files.length == 1) {
                    return RestructureDolphin.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure dolphin operation needs 1 file as argument");
                }
            case "cod":
                if (files.length == 1) {
                    return RestructureCod.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure cod operation needs 1 file as argument");
                }
            case "fox":
                if (files.length == 2) {
                    return RestructureFox.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure fox operation needs 2 files as argument");
                }
            case "sheep":
                if (files.length == 2) {
                    return RestructureSheep.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure sheep operation needs 2 files as argument");
                }
            case "magmacube":
                if (files.length == 1) {
                    return RestructureMagmaCube.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure magmacube operation needs 1 file as argument");
                }
            case "drowned":
                if (files.length == 2) {
                    return RestructureDrowned.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure drowned operation needs 2 files as argument");
                }
            case "animation":
                if (files.length > 0) {
                    return RestructureAnimation.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure animation operation needs at least 1 file as argument");
                }
            case "banner_base":
                if (files.length == 1) {
                    return RestructureBannerBase.compute(access, files, target, config);
                } else {
                    throw new ExitNow("operations.OperationManager.chooseRestructure\nrestructure banmner_base operation needs 1 file as argument");
                }
            default:
                System.out.println("unsupported restructure type: '" + mode + "' for '"+files[0]+"'");
                return "";
        }
    }

    private static String chooseFunction(Label fileProgress, FileAccess access, String source, String function, Settings config) throws ExitNow {
        //if (!Data.optionDone.containsKey(function)/* || Data.optionDone.get(function)*/)
        //    return "";
        if (config.ui == Settings.UiType.Java) {
            switch (function) {
                case "inventory":
                    return JavaInventory.compute(access, source, config);
                case "horse":
                    return JavaHorse.compute(access, source, config);
                case "furnace":
                    return JavaFurnace.compute(access, source, config);
                case "enchanting":
                    return JavaEnchanting.compute(access, source, config);
            }
        } else if (config.ui == Settings.UiType.Bedrock) {
            switch (function) {
                case "inventory":
                    return FunctionInventory.compute(access, source, config);
                case "horse":
                    return FunctionHorse.compute(access, source, config);
                case "furnace":
                    return FunctionFurnace.compute(access, source, config);
                case "enchanting":
                    return FunctionEnchanting.compute(access, source, config);
            }
        }
        switch (function) {
            case "banner":
                return FunctionBanner.compute(fileProgress, access, config);
            case "banner_illager":
                return FunctionBannerIllager.compute(fileProgress, access, config);
            case "campfire_smoke":
                return FunctionCampfireSmoke.compute(fileProgress, access, config);
            case "painting":
                return FunctionPainting.compute(fileProgress, access, config);
            case "particles":
                return FunctionParticles.compute(fileProgress, access, config);
            case "widgets":
                return FunctionWidgets.compute(access, source, config);
            case "icons":
                return FunctionIcons.compute(access, source, config);
            default:
                System.out.println("unsupported function: '" + function + "' for '"+source+"'");
                return "";
        }
    }

    private static String combine(String one, String two) {
        if (one.isEmpty())
            return two;
        else if (two.isEmpty())
            return one;
        return one+";"+two;
    }
}
