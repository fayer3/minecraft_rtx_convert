package minecraft_converter;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.datatypes.TextureConverter;
import minecraft_converter.datatypes.TextureConverterLAB;
import minecraft_converter.datatypes.packMeta;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.tools.FileAccess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.ZipEntry;

import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.Messages.showMessage;

public class MainController {

    @FXML
    private ProgressIndicator progressbar;
    @FXML
    private Label currentFile;
    @FXML
    private BorderPane progress;
    @FXML
    private AnchorPane main;
    @FXML
    private TextField source;
    @FXML
    private CheckBox raytracing;
    @FXML
    private ChoiceBox<TextureConverter> textureFormat;
    @FXML
    private Button customizeFormat;
    @FXML
    private CheckBox onlyTransparentBlocks;
    @FXML
    private CheckBox useTga;

    private Stage customizeWindow = null;
    private Stage settingsWindow = null;

    private ExecutorService threadpool = null;
    private Queue<Future<String>> filesWritten = null;

    private Settings config = new Settings();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @FXML
    public void initialize() {
        main.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles() || event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });
        main.setOnDragDropped(event -> {
            if (event.getDragboard().hasString()) {
                source.setText(event.getDragboard().getString());
            } else if (event.getDragboard().hasFiles()) {
                List<File> files = event.getDragboard().getFiles();
                if (files.size() > 0) {
                    source.setText(files.get(0).getAbsolutePath());
                }
            }
            event.consume();
        });

        textureFormat.setDisable(!raytracing.isSelected());
        customizeFormat.setDisable(!raytracing.isSelected());
        useTga.setDisable(!raytracing.isSelected());
        onlyTransparentBlocks.setDisable(!raytracing.isSelected() || !useTga.isSelected());
        raytracing.selectedProperty().addListener((observable, oldValue, newValue) -> {
            textureFormat.setDisable(!newValue);
            customizeFormat.setDisable(!newValue);
            useTga.setDisable(!newValue);
            onlyTransparentBlocks.setDisable(!newValue || !useTga.isSelected());
        });
        useTga.selectedProperty().addListener((observable, oldValue, newValue) -> {
            onlyTransparentBlocks.setDisable(!raytracing.isSelected() || !newValue);
        });
        textureFormat.getItems().add(new TextureConverterLAB());
        textureFormat.getSelectionModel().selectFirst();

        // load defaults from settings object
        useTga.setSelected(config.useTga);
        raytracing.setSelected(config.raytraced);
        onlyTransparentBlocks.setSelected(config.limitTransparencyToBlocks);
    }

    public void close() {
        if (customizeWindow != null) {
            customizeWindow.close();
        }
    }

    @FXML
    private void customize() {
        if (customizeWindow == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customizeFormat.fxml"));
                Parent root = loader.load();
                CustomizeFormatController c = loader.getController();
                customizeWindow = new Stage();
                customizeWindow.initModality(Modality.WINDOW_MODAL);
                c.setStage(customizeWindow);
                c.setConverters(textureFormat.getItems());
                c.setConverter(textureFormat.getValue());

                customizeWindow.setTitle("Customize texture Format");
                customizeWindow.setScene(new Scene(root));
                customizeWindow.showAndWait();
                TextureConverter conv = c.getResult();
                if (conv != null) {
                    if (!textureFormat.getItems().contains(conv))
                        textureFormat.getItems().add(conv);
                    textureFormat.setValue(conv);
                }
                customizeWindow = null;
            } catch (IOException e) {
                showException(e, "Error opening second window");
            }
        }
    }

    @FXML
    private void settings() {
        if (settingsWindow == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
                Parent root = loader.load();
                SettingsController c = loader.getController();
                settingsWindow = new Stage();
                settingsWindow.initModality(Modality.WINDOW_MODAL);
                c.setStage(settingsWindow);
                c.setSettings(config);

                settingsWindow.setTitle("Customize texture Format");
                settingsWindow.setScene(new Scene(root));
                settingsWindow.showAndWait();
                config = c.getResult();
                settingsWindow = null;
            } catch (IOException e) {
                showException(e, "Error opening second window");
            }
        }
    }

    @FXML
    private void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About Information");
        alert.setTitle("About Information");

        // source https://code.makery.ch/blog/javafx-dialogs-official/
        TextArea textArea = new TextArea(Copyright.text);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        alert.getDialogPane().setPrefWidth(550);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(textArea);
        alert.getDialogPane().expandedProperty().setValue(true);

        alert.showAndWait();
    }

    public void locate(){
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Resourcepack Folder");
        if (!source.getText().isEmpty() && (source.getText().lastIndexOf('\\') != -1) || (source.getText().lastIndexOf('/') != -1)) {
            File folder;
            try {
                folder = new File(source.getText().substring(0, source.getText().lastIndexOf('\\')));
            } catch (IndexOutOfBoundsException e) {
                folder = new File(source.getText().substring(0, source.getText().lastIndexOf('/')));
            }
            if (folder.isDirectory())
                fileChooser.setInitialDirectory(folder);
        }
        File target =fileChooser.showDialog(source.getScene().getWindow());

        if (target != null) {
            source.setText(target.getAbsolutePath());
        }
    }

    public void locateZip(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Resourcepack Zip");
        if (!source.getText().isEmpty()) {
            File folder = new File(source.getText());
            if (folder.isDirectory())
                fileChooser.setInitialDirectory(folder);
            else {
                fileChooser.setInitialDirectory(folder.getParentFile());
            }
        }
        File target =fileChooser.showOpenDialog(source.getScene().getWindow());

        if (target != null) {
            source.setText(target.getAbsolutePath());
        }
    }

    public void convert(){
        if (!source.getText().trim().isEmpty()) {
            progress.setVisible(true);
            progress.setDisable(false);
            progressbar.setProgress(0);
            main.setDisable(true);
            Thread thread = new Thread(this::generateThread);
            thread.setDaemon(true);
            thread.start();
        }
    }

    private FileAccess access = null;
    //private int packFormat = 5; // 4 for 1.12/1.14, 5 for 1.15/1.16

    private void generateThread() {
        try {
            access = new FileAccess();

            if (source.getText().trim().startsWith("\"") && source.getText().trim().endsWith("\"")) {
                source.setText(source.getText().trim().substring(1, source.getText().trim().length()-1));
            }

            access.setSourceFile(new File(source.getText().trim()));

            if (!access.getSourceFile().isDirectory()) {
                if (access.getSourceFile().getName().endsWith(".zip")) {
                    access.setTarget(new File(access.getSourceFile().getParentFile(), access.getSourceFile().getName().substring(0, access.getSourceFile().getName().lastIndexOf(".")) + ".mcpack"));
                    access.setZip(true);
                    access.loadZip();
                    access.findZipOffset();
                } else {
                    // only zip supported
                    showMessage("Only .zip Files or extracted RPs are supported");
                    exit();
                    return;
                }
            } else {
                access.setTarget(new File(access.getSourceFile().getParentFile(), access.getSourceFile().getName() + ".mcpack"));
            }

            Platform.runLater(()-> currentFile.setText("reading pack data"));

            config.raytraced = raytracing.isSelected();
            config.converter = textureFormat.getValue();
            config.createTextureMap();
            config.useTga = useTga.isSelected();
            config.limitTransparencyToBlocks = onlyTransparentBlocks.isSelected();
            Data.resetOptionDone();

            // use all threads
            // use daemon threads
            threadpool = Executors.newFixedThreadPool(config.numberOfThreads, r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            });
            //threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            filesWritten = new LinkedList<>();

            // copy pack icon
            //File icon = new File(sourceFile, "pack.png");

            access.zipAddFilePath("pack.png", "pack_icon.png");

            // create needed json objects
            JsonArray contents_array = new JsonArray();
            JsonArray textures_list = new JsonArray();
            Set<String> textures = new HashSet<>();
            String description;

            try {
                JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(access.getFileBytes("pack.mcmeta", false)), StandardCharsets.UTF_8));
                packMeta meta = gson.fromJson(reader, packMeta.class);
                reader.close();
                description = meta.pack.description;
                config.packFormat = meta.pack.pack_format;
            } catch (IOException e) {
                showException(e, "error opening 'pack.meta'");
                throw new ExitNow("generateThread, opening 'pack.meta'");
            }
            JsonObject manifest = new JsonObject();
            manifest.add("format_version", new JsonPrimitive(2));

            JsonObject header = new JsonObject();
            header.add("description", new JsonPrimitive(description));
            String name =access.getSourceFile().getName().endsWith(".zip") ?
                    access.getSourceFile().getName().substring(0,access.getSourceFile().getName().length()-4) :
                    access.getSourceFile().getName();
            header.add("name", new JsonPrimitive(name));
            header.add("uuid", new JsonPrimitive(UUID.randomUUID().toString()));
            JsonArray version = new JsonArray(3);
            version.add(0);
            version.add(0);
            version.add(1);
            header.add("version", version);
            JsonArray min_engine_version = new JsonArray(3);
            min_engine_version.add(1);
            min_engine_version.add(17);
            min_engine_version.add(0);
            header.add("min_engine_version", min_engine_version);
            manifest.add("header", header);

            JsonObject module = new JsonObject();
            module.add("description", new JsonPrimitive(name + "resources"));
            module.add("type", new JsonPrimitive("resources"));
            module.add("uuid", new JsonPrimitive(UUID.randomUUID().toString()));
            module.add("version", version);
            JsonArray modules = new JsonArray();
            modules.add(module);
            manifest.add("modules", modules);

            if (raytracing.isSelected()) {
                JsonArray capabilities = new JsonArray();
                capabilities.add("raytraced");
                manifest.add("capabilities", capabilities);
            }

            // save manifest json
            String manifest_json = gson.toJson(manifest);
            access.zipAddFileByte("manifest.json", manifest_json.getBytes());

            if (config.mipmaps >= 0) {
                JsonObject terrain_texture = new JsonObject();
                terrain_texture.add("resource_pack_name", new JsonPrimitive("vanilla"));
                terrain_texture.add("texture_name", new JsonPrimitive("atlas.terrain"));
                terrain_texture.add("padding", new JsonPrimitive(8));
                terrain_texture.add("num_mip_levels", new JsonPrimitive(config.mipmaps));
                String terrain_texture_json = gson.toJson(terrain_texture);
                access.zipAddFileByte("textures/terrain_texture.json", terrain_texture_json.getBytes());
            }

            float progressAdd = 1f / access.countFiles();

            if (access.isZip())
                processZip(access, progressAdd, config);
            else
                processFiles(access, access.getSourceFile(), progressAdd, config);

            //convertFile(sourceFile, progressAdd);
            float progressAddFinal = 1f / filesWritten.size();
            try {
                Platform.runLater(()-> currentFile.setText("converting files"));
                while (!filesWritten.isEmpty()) {
                    String images = filesWritten.poll().get();
                    if (images != null && !images.isEmpty()) {
                        for (String image : images.split(";")) {
                            JsonObject pathJson = new JsonObject();
                            //String format = filepath.substring(filepath.lastIndexOf("."));
                            //String file = targets[i].replace(".tga", format).replace(".png", format);
                            pathJson.add("path", new JsonPrimitive(image));
                            contents_array.add(pathJson);
                            if (!".json".equals(image.substring(0, image.lastIndexOf("."))))
                                textures.add(image.substring(0, image.lastIndexOf(".")));
                        }
                    }
                    Platform.runLater(()-> progressbar.setProgress(progressbar.getProgress()+progressAddFinal));
                }
                for(String tex : textures)
                    textures_list.add(tex);
            } catch (InterruptedException|ExecutionException e) {
                showException(e, "");
                throw new ExitNow(e);
            }

            // write contents and textures_list
            JsonObject path = new JsonObject();
            path.add("path", new JsonPrimitive("textures/textures_list.json"));
            contents_array.add(path);

            path = new JsonObject();
            path.add("path", new JsonPrimitive("manifest.json"));
            contents_array.add(path);

            path = new JsonObject();
            path.add("path", new JsonPrimitive("pack_icon.png"));
            contents_array.add(path);

            JsonObject contents = new JsonObject();
            contents.add("content", contents_array);
            String contents_json = gson.toJson(contents);
            access.zipAddFileByte("contents.json", contents_json.getBytes());

            String textures_list_json = gson.toJson(textures_list);
            access.zipAddFileByte("textures/textures_list.json", textures_list_json.getBytes());

            // write to zip
            Platform.runLater(()-> currentFile.setText("writing final package"));
            access.writeZip();
        } catch (IOException e) {
            showException(e, "error on reading zip file");
        } catch (ExitNow e) {
            System.out.println("Exit requested by: '" + e.getMessage() + "'");
        } finally {
            exit();
        }
    }

    private void exit() {
        if (filesWritten != null) {
            while (!filesWritten.isEmpty()) {
                filesWritten.poll().cancel(true);
            }
        }
        if (threadpool != null) {
            threadpool.shutdownNow();
            threadpool = null;
        }
        Data.resetOptionDone();
        if (access != null)
            access.close();
        access = null;
        Platform.runLater(()-> {
            progressbar.setProgress(1);
            progress.setVisible(false);
            progress.setDisable(true);
            main.setDisable(false);
        });
    }

    private void processFiles(FileAccess access, File current, float progressAdd, Settings config) throws ExitNow {
        if (current.isDirectory()) {
            File[] childs = current.listFiles();
            if (childs != null) {
                for (File f : childs)
                    processFiles(access, f, progressAdd, config);
            }
        } else {
            String path = access.getSourceFile().toPath().relativize(current.toPath()).toString().replace('\\', '/');
            convertFile(access, path, progressAdd, config);
        }
    }

    private void processZip(FileAccess access, float progressAdd, Settings config) throws ExitNow {
        Enumeration<? extends ZipEntry> entries = access.getZipSource().entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (!entry.isDirectory()) {
                convertFile(access, entry.getName(), progressAdd, config);
            }
        }
    }

    private void convertFile(FileAccess access, String path, float progressAdd, Settings config) throws ExitNow{
        //try {
            String filepath = path.replace(access.getZipOffsetPath(), "");
            if (!config.excluded(filepath) && Data.textures.containsKey(filepath) && (!Data.textures.get(filepath).isEmpty() || Data.texturesMode.containsKey(filepath))) {
                String target = Data.textures.get(filepath);
                if (target.equals("option.this")){
                    target = config.options.get(filepath);
                    if (target.isEmpty())
                        return;
                }
                String[] targets = target.split(";");
                String[] modes = new String[targets.length];
                Arrays.fill(modes, "copy");
                if (Data.texturesMode.containsKey(filepath)) {
                    String temp = Data.texturesMode.get(filepath);
                    if (temp.equals("versionSpecific"))
                        temp = Data.getModeForVersion(filepath, config.packFormat);
                    modes = temp.split(";");
                }
                for (int i = 0; i < targets.length; i++) {
                    if (Data.optionDone.containsKey(targets[i])) {
                        if (Data.optionDone.get(targets[i]))
                            continue;
                        Data.optionDone.put(targets[i], true);
                    }
                    // submit functions only once
                    if (modes[i].startsWith("function")){
                        String mode = modes[i];
                        String[] parameters = null;
                        if (mode.contains("[")) {
                            mode = mode.substring(0, mode.indexOf("["));
                            String par = modes[i].substring(mode.length() + 1, modes[i].length() - 1);
                            parameters = par.split(":");
                            if (Data.optionDone.containsKey(parameters[0])) {
                                if (Data.optionDone.get(parameters[0]))
                                    continue;
                                else
                                    Data.optionDone.put(parameters[0], true);
                            }
                        }
                    }
                    Convert conv = new Convert(targets[i], modes[i], filepath, config, access);
                    filesWritten.add(threadpool.submit(conv));
                    /*String mode = modes[i];
                    String[] parameters = null;
                    if (mode.contains("[")) {
                        mode = mode.substring(0, mode.indexOf("["));
                        String par = modes[i].substring(mode.length() + 1, modes[i].length() - 1);
                        parameters = par.split(":");
                    }
                    String images = OperationManager.chooseOperation(currentFile, access, mode, parameters, filepath, targets[i], config);

                    if (images != null && !images.isEmpty()) {
                        for (String image : images.split(";")) {
                            JsonObject pathJson = new JsonObject();
                            //String format = filepath.substring(filepath.lastIndexOf("."));
                            //String file = targets[i].replace(".tga", format).replace(".png", format);
                            pathJson.add("path", new JsonPrimitive(image));
                            contents_array.add(pathJson);
                            textures_list.add(image);
                        }
                    }*/
                }
            } else if (!Data.textures.containsKey(filepath) && !filepath.contains("_n.png") && !filepath.contains("_s.png") && !filepath.contains(".mcmeta")){
                // print out files which are not handled
                System.out.println("Skipping file: " + filepath);
            }
        /*} catch(ExitNow e) {
            showException(e, "error converting File: '" + path + "'");
            throw new ExitNow("convertFile");
        }

        Platform.runLater(()-> progressbar.setProgress(progressbar.getProgress()+progressAdd));
        */
    }
}
