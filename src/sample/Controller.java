package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Accordion accordion;
    public TitledPane uredi;
    public HTMLEditor editor;
    public TitledPane dnevnik;
    public TextArea log;
    public Label status;
    public TextField vnos;

    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi HTML datoteko");
        File f = fc.showOpenDialog(null);
        if (f != null) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(f))){
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line).append('\n');
                br.close();
                editor.setHtmlText(sb.toString());
                log.appendText("Prebral si datoteko: " + f.getAbsolutePath() + '\n');
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void shraniCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi datoteko za shranjevanje");
        File f = fc.showSaveDialog(null);
        if (f != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
                bw.write(editor.getHtmlText());
                bw.close();
                log.appendText("Shranil si datoteko: " + f.getAbsolutePath() + '\n');
            }
            catch (Exception e) {

            }
        }
    }

    public void avtorCB(ActionEvent actionEvent) {
        status.setText("Jaz sem avtorica.");
        log.appendText("Izvedeti hočeš, kdo sem.");
    }

    public void izhodCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accordion.setExpandedPane(uredi);
    }

    public void skrijCB(ActionEvent actionEvent) {
        String iskanje = vnos.getText();
        String text = editor.getHtmlText();
        text = text.replaceAll(iskanje, "--SKRIVNOST--");
        editor.setHtmlText(text);
    }
}
