package minecraft_converter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.datatypes.TextureConverter;

import java.util.Optional;

public class SettingsController {

    private Settings config;

    private Stage stage;

    @FXML
    private Button removeButton;

    @FXML
    private TableView<StringProperty> excludeTextures;
    @FXML
    private TableColumn<StringProperty, String> excludeTexturesColumn;

    @FXML
    private CheckBox convertLeather;
    @FXML
    private TextField horseArmor;
    @FXML
    private TextField horseItem;
    @FXML
    private CheckBox convertFire;
    @FXML
    private CheckBox convertPaintingIncomplete;
    @FXML
    private CheckBox convertBannerIncomplete;
    @FXML
    private CheckBox convertParticleIncomplete;
    @FXML
    private ChoiceBox<banner> bannerpattern;
    @FXML
    private CheckBox mipmapCheck;
    @FXML
    private Slider mipmapSlider;

    public enum banner {
        ANY("any", "any"),
        NONE("none", ""),
        CREEPER("creeper", ""),
        GLOBE("globe", ""),
        FLOWER("flower", ""),
        MOJANG("mojang", ""),
        SKULL("skull", "");
        public String display;
        public String path;
        banner(String display, String path) {
            this.display = display;
            this.path = path;
        }
        @Override
        public String toString() {
            return display;
        }
    }

    @FXML
    public void initialize() {
        excludeTexturesColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));
        excludeTexturesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        excludeTexturesColumn.setOnEditCommit(p -> p.getRowValue().setValue((p.getNewValue() != null ? p.getNewValue() : "")));

        excludeTextures.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> removeButton.setDisable(newValue == null));
        convertLeather.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png"));
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/item/leather_horse_armor.png"));
                horseItem.setDisable(false);
                horseArmor.setDisable(false);
            } else {
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png"));
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/item/leather_horse_armor.png"));
                excludeTextures.getItems().add(new SimpleStringProperty("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png"));
                excludeTextures.getItems().add(new SimpleStringProperty("assets/minecraft/textures/item/leather_horse_armor.png"));
                horseItem.setDisable(true);
                horseArmor.setDisable(true);
            }
        });
        convertFire.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/block/fire_0.png"));
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/block/fire_1.png"));
            } else {
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/block/fire_0.png"));
                excludeTextures.getItems().removeIf(s -> s.get().equals("assets/minecraft/textures/block/fire_1.png"));
                excludeTextures.getItems().add(new SimpleStringProperty("assets/minecraft/textures/block/fire_0.png"));
                excludeTextures.getItems().add(new SimpleStringProperty("assets/minecraft/textures/block/fire_1.png"));
            }
        });
        bannerpattern.getItems().add(banner.ANY);
        bannerpattern.getItems().add(banner.NONE);
        bannerpattern.getItems().add(banner.CREEPER);
        bannerpattern.getItems().add(banner.FLOWER);
        bannerpattern.getItems().add(banner.GLOBE);
        bannerpattern.getItems().add(banner.MOJANG);
        bannerpattern.getItems().add(banner.SKULL);

        mipmapCheck.selectedProperty().addListener((observable, oldValue, newValue) -> mipmapSlider.setDisable(!newValue));
    }

    @FXML
    private void save() {
        config = getSettings();
        stage.close();
    }

    @FXML
    private void cancle() {
        stage.close();
    }


    public void setSettings(Settings settings) {
        config = settings;
        setValues(settings);
    }

    public Settings getResult() {
        return config;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void add() {
        excludeTextures.getItems().add((new SimpleStringProperty("")));

    }
    @FXML
    private void remove() {
        if (excludeTextures.getSelectionModel().getSelectedItem() != null) {
            excludeTextures.getItems().remove(excludeTextures.getSelectionModel().getSelectedItem());
        }
    }

    private void setValues(Settings settings) {
        for (String s : settings.excludedTextures) {
            excludeTextures.getItems().add(new SimpleStringProperty(s));
        }
        horseArmor.setText(settings.options.get("horseLeatherArmorThreshold"));
        horseItem.setText(settings.options.get("horseLeatherItemThreshold"));
        convertLeather.setSelected(excludeTextures.getItems().filtered(stringProperty ->
                stringProperty.get().equals("assets/minecraft/textures/entity/horse/armor/horse_armor_leather.png") || stringProperty.get().equals("assets/minecraft/textures/item/leather_horse_armor.png")).size() != 2);
        convertFire.setSelected(excludeTextures.getItems().filtered(stringProperty -> stringProperty.get().startsWith("assets/minecraft/textures/block/fire")).size() != 2);
        convertBannerIncomplete.setSelected(settings.partialBanner);
        convertPaintingIncomplete.setSelected(settings.partialPainting);
        convertParticleIncomplete.setSelected(settings.partialParticles);


        if (settings.mipmaps != -1) {
            mipmapSlider.setValue(settings.mipmaps);
            mipmapSlider.setDisable(false);
            mipmapCheck.setSelected(true);
        }



        bannerpattern.setValue(settings.bannerPattern);
    }

    private Settings getSettings() {
        Settings s = new Settings(config);
        s.excludedTextures.clear();
        for (StringProperty string : excludeTextures.getItems()) {
            s.excludedTextures.add(string.getValue());
        }
        s.options.put("horseLeatherArmorThreshold", horseArmor.getText());
        s.options.put("horseLeatherItemThreshold", horseItem.getText());
        s.partialBanner = convertBannerIncomplete.isSelected();
        s.partialPainting = convertPaintingIncomplete.isSelected();
        s.partialParticles = convertParticleIncomplete.isSelected();

        s.options.put("assets/minecraft/textures/item/creeper_banner_pattern.png", "");
        s.options.put("assets/minecraft/textures/item/globe_banner_pattern.png", "");
        s.options.put("assets/minecraft/textures/item/flower_banner_pattern.png", "");
        s.options.put("assets/minecraft/textures/item/mojang_banner_pattern.png", "");
        s.options.put("assets/minecraft/textures/item/skull_banner_pattern.png", "");
        switch (bannerpattern.getValue()) {
            case GLOBE:
                s.options.put("assets/minecraft/textures/item/globe_banner_pattern.png", "textures/items/banner_pattern.png");
                break;
            case CREEPER:
                s.options.put("assets/minecraft/textures/item/creeper_banner_pattern.png", "textures/items/banner_pattern.png");
                break;
            case FLOWER:
                s.options.put("assets/minecraft/textures/item/flower_banner_pattern.png", "textures/items/banner_pattern.png");
                break;
            case MOJANG:
                s.options.put("assets/minecraft/textures/item/mojang_banner_pattern.png", "textures/items/banner_pattern.png");
                break;
            case SKULL:
                s.options.put("assets/minecraft/textures/item/skull_banner_pattern.png", "textures/items/banner_pattern.png");
                break;
            case ANY:
                s.options.put("assets/minecraft/textures/item/creeper_banner_pattern.png", "textures/items/banner_pattern.png");
                s.options.put("assets/minecraft/textures/item/globe_banner_pattern.png", "textures/items/banner_pattern.png");
                s.options.put("assets/minecraft/textures/item/flower_banner_pattern.png", "textures/items/banner_pattern.png");
                s.options.put("assets/minecraft/textures/item/mojang_banner_pattern.png", "textures/items/banner_pattern.png");
                s.options.put("assets/minecraft/textures/item/skull_banner_pattern.png", "textures/items/banner_pattern.png");
                break;
        }
        s.bannerPattern = bannerpattern.getValue();
        s.mipmaps = mipmapCheck.isSelected() ? Math.round(Math.round(mipmapSlider.getValue())) : -1;
        return s;
    }
}
