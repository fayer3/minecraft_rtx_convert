package minecraft_converter.tools;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Messages {
    /**
     * shows the stacktrace of the given exception and some details in an alert window with the given message
     * @param e exception to show
     * @param message message to add
     */
    public static void showException(Exception e, String message) {
        final String defaultMessage = "Exception: " + e.getClass() +
                "\nCause: " + e.getCause() +
                "\nMessage: " + e.getMessage() +
                "\nStacktrace:";

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, defaultMessage);
            if (message != null && message.trim().isEmpty()) {
                alert.setHeaderText(message);
            }

            // source https://code.makery.ch/blog/javafx-dialogs-official/
            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exceptionText = sw.toString();

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(textArea);
            alert.getDialogPane().expandedProperty().setValue(true);

            alert.showAndWait();
        });
    }

    /**
     * shows the given message in an alert window
     * @param message the message to show
     */
    public static void showMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, message);
            alert.showAndWait();
        });
    }
}
