package minecraft_converter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import minecraft_converter.datatypes.TextureConverter;
import minecraft_converter.datatypes.TextureConverterCustom;

import java.util.Optional;

public class CustomizeFormatController {

    private TextureConverter converter;

    private Stage stage;

    @FXML
    private ChoiceBox<TextureConverter> choicePreset;

    @FXML
    private TextField normalSuffix;
    @FXML
    private ChoiceBox<TextureConverter.NormalFormat> normalFormat;
    @FXML
    private ChoiceBox<TextureConverter.NormalChannels> normalChannels;

    @FXML
    private TextField metallicSuffix;
    @FXML
    private ChoiceBox<TextureConverter.Channel> metallicChannel;
    @FXML
    private ChoiceBox<TextureConverter.Operation> metallicOperation;
    @FXML
    private TextField metallicOperationExtra;

    @FXML
    private TextField emissiveSuffix;
    @FXML
    public ChoiceBox<TextureConverter.Channel> emissiveChannel;
    @FXML
    private ChoiceBox<TextureConverter.Operation> emissiveOperation;
    @FXML
    private TextField emissiveOperationExtra;

    @FXML
    private TextField roughnessSuffix;
    @FXML
    public ChoiceBox<TextureConverter.Channel> roughnessChannel;
    @FXML
    private ChoiceBox<TextureConverter.Operation> roughnessOperation;
    @FXML
    private TextField roughnessOperationExtra;

    private TextureConverter custom;

    @FXML
    public void initialize() {
        normalFormat.getItems().add(TextureConverter.NormalFormat.DIRECTX);
        normalFormat.getItems().add(TextureConverter.NormalFormat.OPENGL);
        normalFormat.setValue(TextureConverter.NormalFormat.DIRECTX);

        normalChannels.getItems().add(TextureConverter.NormalChannels.RGB);
        normalChannels.getItems().add(TextureConverter.NormalChannels.RG);
        normalChannels.setValue(TextureConverter.NormalChannels.RG);

        metallicChannel.getItems().add(TextureConverter.Channel.Red);
        metallicChannel.getItems().add(TextureConverter.Channel.Green);
        metallicChannel.getItems().add(TextureConverter.Channel.Blue);
        metallicChannel.getItems().add(TextureConverter.Channel.Alpha);
        emissiveChannel.getSelectionModel().selectFirst();

        emissiveChannel.getItems().addAll(metallicChannel.getItems());
        emissiveChannel.getSelectionModel().selectFirst();
        roughnessChannel.getItems().addAll(metallicChannel.getItems());
        emissiveChannel.getSelectionModel().selectFirst();

        metallicOperation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            metallicOperation.setTooltip(new Tooltip(newValue.explanation));
        });
        metallicOperation.getItems().add(TextureConverter.Operation.COPY);
        metallicOperation.getItems().add(TextureConverter.Operation.COPY_RANGE);
        metallicOperation.getItems().add(TextureConverter.Operation.INVERSE);
        metallicOperation.getItems().add(TextureConverter.Operation.INVERSE_SQUARE);
        metallicOperation.getItems().add(TextureConverter.Operation.THRESHOLD_UP);
        metallicOperation.getItems().add(TextureConverter.Operation.THRESHOLD_DOWN);
        metallicOperation.getSelectionModel().selectFirst();

        emissiveOperation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            emissiveOperation.setTooltip(new Tooltip(newValue.explanation));
        });
        emissiveOperation.getItems().addAll(metallicOperation.getItems());
        emissiveOperation.getSelectionModel().selectFirst();
        roughnessOperation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            roughnessOperation.setTooltip(new Tooltip(newValue.explanation));
        });
        roughnessOperation.getItems().addAll(metallicOperation.getItems());
        roughnessOperation.getSelectionModel().selectFirst();

        choicePreset.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && !oldValue.equals(newValue)) {
                if (!TextureConverterCustom.name.equals(newValue.getName()) && (!TextureConverter.equals(oldValue,getConverter()))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("You changed some values, are you sure you want to change the preset?");
                    alert.setContentText("Your changes will be lost.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        updateInterface(newValue);
                    } else {
                        choicePreset.setValue(oldValue);
                    }
                } else if (TextureConverterCustom.name.equals(newValue.getName())) {
                    if (custom != null) {
                        custom.setValues(getConverter());
                    } else {
                        custom = getConverter();
                    }
                }
            }
        });

    }

    @FXML
    private void save() {
        converter = null;
        TextureConverter conv = getConverter();
        for (TextureConverter oldConv : choicePreset.getItems()) {
            if (TextureConverter.equals(oldConv,conv)) {
                converter = oldConv;
            }
        }
        if (converter == null) {
            if (custom == null) {
                converter = getConverter();
            } else {
                custom.setValues(getConverter());
                converter = custom;
            }
        }
        stage.close();
    }

    @FXML
    private void cancle() {
        converter = null;
        stage.close();
    }

    public TextureConverter getResult() {
        return converter;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextureConverter getConverter() {
        TextureConverter converter = new TextureConverterCustom();
        converter.setNormalSuffix(normalSuffix.getText().trim());
        converter.setNormalChannels(normalChannels.getValue());
        converter.setNormalFormat(normalFormat.getValue());

        converter.setMetallicSuffix(metallicSuffix.getText().trim());
        converter.setMetallicChannel(metallicChannel.getValue());
        converter.setMetallicOperation(metallicOperation.getValue());
        converter.setMetallicOperationExtra(metallicOperationExtra.getText().trim());

        converter.setEmissiveSuffix(emissiveSuffix.getText().trim());
        converter.setEmissiveChannel(emissiveChannel.getValue());
        converter.setEmissiveOperation(emissiveOperation.getValue());
        converter.setEmissiveOperationExtra(emissiveOperationExtra.getText().trim());

        converter.setRoughnessSuffix(roughnessSuffix.getText().trim());
        converter.setRoughnessChannel(roughnessChannel.getValue());
        converter.setRoughnessOperation(roughnessOperation.getValue());
        converter.setRoughnessOperationExtra(roughnessOperationExtra.getText().trim());
        return converter;
    }

    public void setConverters(ObservableList<TextureConverter> converters) {
        for (TextureConverter conv : converters) {
            choicePreset.getItems().add(conv);
            if (TextureConverterCustom.name.equals(conv.getName()))
                custom = conv;
        }
    }

    public void setConverter(TextureConverter converter) {
        choicePreset.setValue(converter);
        this.converter = converter;
        if (converter.getName().equals(TextureConverterCustom.name)) {
            custom = converter;
        }
        updateInterface(converter);
    }
    private void updateInterface(TextureConverter converter) {
        normalSuffix.setText(converter.getNormalSuffix());
        normalFormat.setValue(converter.getNormalFormat());
        normalChannels.setValue(converter.getNormalChannels());

        metallicSuffix.setText(converter.getMetallicSuffix());
        metallicChannel.setValue(converter.getMetallicChannel());
        metallicOperation.setValue(converter.getMetallicOperation());
        metallicOperationExtra.setText(converter.getMetallicOperationExtra());

        emissiveSuffix.setText(converter.getEmissiveSuffix());
        emissiveChannel.setValue(converter.getEmissiveChannel());
        emissiveOperation.setValue(converter.getEmissiveOperation());
        emissiveOperationExtra.setText(converter.getEmissiveOperationExtra());

        roughnessSuffix.setText(converter.getRoughnessSuffix());
        roughnessChannel.setValue(converter.getRoughnessChannel());
        roughnessOperation.setValue(converter.getRoughnessOperation());
        roughnessOperationExtra.setText(converter.getRoughnessOperationExtra());
    }
}
