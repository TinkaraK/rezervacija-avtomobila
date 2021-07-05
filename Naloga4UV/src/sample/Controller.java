package sample;

import com.sun.tools.jconsole.JConsoleContext;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    public ComboBox krajPrevzema;
    public ComboBox uraPrevzema;
    public DatePicker datumPrevzema;
    public ComboBox krajVracila;
    public ComboBox uraVracila;
    public DatePicker datumVracila;
    public RadioButton velik;
    public ToggleGroup velikostVozila;
    public RadioButton srednji;
    public RadioButton majhen;
    public ComboBox znamkaModel;
    public RadioButton bencin;
    public ToggleGroup vrstaMotorja;
    public RadioButton dizel;
    public RadioButton rocni;
    public ToggleGroup menjalnik;
    public RadioButton avtomatski;
    public TextField ime;
    public TextField priimek;
    public TextField naslov;
    public TextField mesto;
    public TextField postnaSt;
    public TextField email;
    public TextField telefon;
    public Spinner starost;
    public Spinner vozniski;
    public TextField cena;
    public TextField stKartice;
    public TextField ccv;
    public RadioButton kartica;
    public ToggleGroup placilo;
    public RadioButton gotovina;
    public RadioButton dodatnoDa;
    public ToggleGroup dodatnoZav;
    public RadioButton dodatnoNe;
    public TabPane tabpane;
    public Tab potrditevTP;
    public Tab najemTP;
    public Tab izbiraTP;
    public Tab najemnikTP;
    public Tab cenaTP;
    public TextArea potrditevZaAvto;
    public TextArea potrditevZaNarocnika;
    public TextArea potrditevZaCeno;
    HashMap<String, Object> rezervacijaAvta = new HashMap<>();
    public boolean canSave = true;
    StringBuilder alertEmpty = new StringBuilder();
    StringBuilder alertValidation = new StringBuilder();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        starost.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 100));
        starost.setPromptText("Vpišite starost");
        vozniski.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 3));
        starost.setPromptText("Vpišite št. let");
        krajPrevzema.getItems().addAll("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo mesto", "Murska Sobota", "Jesenice", "Portorož", "letališče Brnik", "letališče Maribor");
        krajVracila.getItems().addAll("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo mesto", "Murska Sobota", "Jesenice", "Portorož", "letališče Brnik", "letališče Maribor");
        uraPrevzema.getItems().addAll("00:00", "00:30", "01:00", "01:30","02:00", "02:30","03:00", "03:30","04:00", "04:30","05:00", "05:30", "06:00", "06:30",
                "07:00", "07:30","08:00", "08:30","09:00", "09:30","10:00", "10:30","11:00", "11:30","12:00", "12:30","13:00", "13:30","14:00", "14:30",
                "15:00", "15:30","16:00", "16:30","17:00", "17:30","18:00", "18:30","19:00", "19:30","20:00", "20:30","21:00",
                "21:30","22:00", "22:30","23:00", "23:30");
        uraVracila.getItems().addAll("00:00", "00:30", "01:00", "01:30","02:00", "02:30","03:00", "03:30","04:00", "04:30","05:00", "05:30", "06:00", "06:30",
                "07:00", "07:30","08:00", "08:30","09:00", "09:30","10:00", "10:30","11:00", "11:30","12:00", "12:30","13:00", "13:30","14:00", "14:30",
                "15:00", "15:30","16:00", "16:30","17:00", "17:30","18:00", "18:30","19:00", "19:30","20:00", "20:30","21:00",
                "21:30","22:00", "22:30","23:00", "23:30");

        znamkaModel.getItems().addAll("MB C180", "Audi A5", "Toyota Avensis", "Honda Accord", "Škoda Octavia", "Toyota Yaris");



    }



    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi 'ime'.txt datoteko ");
        File f = fc.showOpenDialog(null);
        if (f != null) {
            StringBuilder sb = new StringBuilder();
            ponastavi();
            try (BufferedReader br = new BufferedReader(new FileReader(f))){
                String line;
                String[] splitLine;
                while ((line = br.readLine()) != null) {
                    splitLine = line.split("- ");
                    zapisi(splitLine[0], splitLine[1]);
                }
                izbiraTP.setDisable(false);
                najemnikTP.setDisable(false);
                cenaTP.setDisable(false);
                potrditevTP.setDisable(false);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void zapisi(String name, String value) {
        switch (name) {
            case "datumPrevzema":
                datumPrevzema.setValue(LocalDate.parse(value));
                break;
            case "datumVracila":
                datumVracila.setValue(LocalDate.parse(value));
                break;
            case "uraVracila":
                uraVracila.setValue(value);
                break;
            case "uraPrevzema":
                uraPrevzema.setValue(value);
                break;
            case "krajPrevzema":
                krajPrevzema.setValue(value);
                break;
            case "krajVracila":
                krajVracila.setValue(value);
                break;
            case "velikost":
                if (value.equals("velik"))
                    velik.setSelected(true);
                else if (value.equals("majhen"))
                    majhen.setSelected(true);
                else
                    srednji.setSelected(true);
                break;
            case "znamkaModel":
                znamkaModel.setValue(value);
                break;
            case "motor":
                if (value.equals("dizel"))
                    dizel.setSelected(true);
                else
                    bencin.setSelected(true);
                break;
            case "menjalnik":
                if (value.equals("rocni"))
                    rocni.setSelected(true);
                else
                    avtomatski.setSelected(true);
                break;
            case "ime":
                ime.setText(value);
                break;
            case "priimek":
                priimek.setText(value);
                break;
            case "naslov":
                naslov.setText(value);
                break;
            case "email":
                email.setText(value);
                break;
            case "postnaStevilka":
                postnaSt.setText(value);
                break;
            case "mesto":
                mesto.setText(value);
                break;
            case "telefon":
                telefon.setText(value);
                break;
            case "starost":
                starost.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, Integer.parseInt(value)));
                break;
            case "vozniski":
                vozniski.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, Integer.parseInt(value)));
                break;
            case "dodatno":
                if (value.equals("da"))
                    dodatnoDa.setSelected(true);
                else
                    dodatnoNe.setSelected(true);
                break;
            case "cena":
                cena.setText(value);
                break;
            case "nacinPlacila":
                if (value.equals("kartica"))
                    kartica.setSelected(true);
                else
                    gotovina.setSelected(true);
                break;
            case "stKartice":
                if (kartica.isSelected())
                    stKartice.setText(value);
                if (gotovina.isSelected()) {
                    stKartice.setEditable(false);
                    stKartice.setText("");
                }
                break;
            case "ccv":
                if (kartica.isSelected())
                    ccv.setText(value);
                if (gotovina.isSelected()) {
                    ccv.setEditable(false);
                    ccv.setText("");
                }
                break;
        }

    }

    public void ponastaviCB(ActionEvent actionEvent) {
        ponastavi();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ponastavi");
        alert.setHeaderText("Sporočilo");
        alert.setContentText("Polja so ponastavljena.");
        alert.showAndWait();
    }


    public void prikaziSpecificnaVozila(ActionEvent actionEvent) {
        String name = velikostVozila.getSelectedToggle().toString();
        String remove = (String) name.subSequence(0, name.indexOf("]"));
        name = name.replace(remove + "]", "");
        name = name.replace("'", "");
        System.out.println(name);
        switch (name) {
            case "velik":
                znamkaModel.getItems().clear();
                znamkaModel.getItems().addAll("BMW X5", "Hyundai Kona", "Volkswagen Passat", "Ford focus karavan", "Citroen Berlingo");
                break;
            case "srednji":
                znamkaModel.getItems().clear();
                znamkaModel.getItems().addAll("MB C180", "Audi A5", "Toyota Avensis", "Honda Accord", "Škoda Octavia", "Toyota Yaris");
                break;
            case "majhen":
                znamkaModel.getItems().clear();
                znamkaModel.getItems().addAll("Mini Cooper", "Smart4Two", "Chevrolet Aveo", "Renault Clio");
                break;
        }
    }
    public boolean notOnlyCharacters(String str) {
        return ! Pattern.matches("[a-zA-ZčČšŠžŽćĆđĐ]+", str);
    }

    public void alertInfo(String str) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Izpolnite vsa polja");
        alert.setHeaderText("Izpolnite vsa polja.");
        alert.setContentText(str);
        alert.showAndWait();
    }

    public void prikaziPotrditev() {
        StringBuilder potrdiAvto = new StringBuilder();
        StringBuilder potrdiNajemnik = new StringBuilder();
        StringBuilder potrdiCeno = new StringBuilder();
        potrdiAvto.append("Lokacija prevzema: \n\t").append(krajPrevzema.getValue()).append(", ").append(datumPrevzema.getValue()).append(" ob ").append(uraPrevzema.getValue()).append("\n");
        potrdiAvto.append("Lokacija vračila: \n\t" + krajVracila.getValue() +  ", " + datumVracila.getValue() + " ob " + uraVracila.getValue()+ "\n");
        potrdiAvto.append("Znamka in model vozila: \n\t" + znamkaModel.getValue()+ "\n");
        potrdiNajemnik.append("Najemnik:\n\t" + ime.getText() + " " + priimek.getText() + "\n" + "\t" + naslov.getText() + ", " + postnaSt.getText() + ", " +  mesto.getText()+ "\n");
        potrdiNajemnik.append("\t" + email.getText() + "\n\t" + telefon.getText() + "\n");
        potrdiNajemnik.append("\tstarost: " + starost.getValue() + " let\n");
        potrdiNajemnik.append("\tvozniški izpit: " + vozniski.getValue() + " let\n");
        if (dodatnoDa.isSelected())
            potrdiNajemnik.append("Dodatno avtomobilsko zavarovanje: DA\n");
        else
            potrdiNajemnik.append("Dodatno avtomobilsko zavarovanje: NE\n");
        potrdiCeno.append(cena.getText() + " \n");
        if (gotovina.isSelected())
            potrdiCeno.append("Način plačila: gotovina\n");
        else {
            String stevilka = stKartice.getText();
            potrdiCeno.append("Način plačila: kartica\n").append("Številka kreditne kartice: XXXX-XXXX-XXXX-").append(stevilka.substring(stevilka.length() - 4)).append("\n").append("Način plačila: kartica\n");
        }
        potrditevZaAvto.setText(potrdiAvto.toString());
        potrditevZaNarocnika.setText(potrdiNajemnik.toString());
        potrditevZaCeno.setText(potrdiCeno.toString());
    }

    public void izracunajCeno(ActionEvent actionEvent) {
        long stDni = ChronoUnit.DAYS.between(datumPrevzema.getValue(), datumVracila.getValue());
        int izracunCena = (int) (stDni * 75);
        if (dodatnoDa.isSelected())
            izracunCena += 2;
        cena.setText("Cena na dan: 75 €, skupaj cena " + Integer.toString(izracunCena) + " €");
    }

    public void zakljuciCB(ActionEvent actionEvent) {
// kreditna
        if (stKartice.getText().isEmpty() && kartica.isSelected()) {
            alertEmpty.append("Izpolnite številko kreditne kartice.\n");
            if (! stKartice.getStyleClass().contains("error"))
                stKartice.getStyleClass().add("error");
        }
        else if (! Pattern.matches("[0-9]{4}[-0-9]{4}[-0-9]{4}[-0-9]{4}[0-9]{3}", stKartice.getText()) && kartica.isSelected()) {
            alertValidation.append("Neustrezna številka kreditne kartice.\n");
            if (! stKartice.getStyleClass().contains("error"))
                stKartice.getStyleClass().add("error");
        }
        else if (gotovina.isSelected())
            rezervacijaAvta.put("stKartice", "x");
        else {
            rezervacijaAvta.put("stKartice", stKartice.getText());
            stKartice.getStyleClass().remove("error");
        }

        // ccv
        if (ccv.getText().isEmpty() && kartica.isSelected()) {
            alertEmpty.append("Izpolnite CCV kodo.\n");
            if (! ccv.getStyleClass().contains("error"))
                ccv.getStyleClass().add("error");
        }
        else if (! Pattern.matches("[0-9]{3}", ccv.getText()) && kartica.isSelected()) {
            alertValidation.append("Neustrezna CCV koda. Vsebovati mora 3 števila..\n");
            if (! ccv.getStyleClass().contains("error"))
                ccv.getStyleClass().add("error");
        }
        else if (gotovina.isSelected())
            rezervacijaAvta.put("ccv", "x");
        else {
            rezervacijaAvta.put("ccv", ccv.getText());
            ccv.getStyleClass().remove("error");
        }

        rezervacijaAvta.put("cena", cena.getText());

        if (dodatnoDa.isSelected())
            rezervacijaAvta.put("dodatno", "da");
        else
            rezervacijaAvta.put("dodatno", "ne");

        if (kartica.isSelected())
            rezervacijaAvta.put("nacinPlacila", "kartica");
        else
            rezervacijaAvta.put("nacinPlacila", "gotovina");

        if (check()) {
            prikaziPotrditev();
            potrditevTP.setDisable(false);
            tabpane.getSelectionModel().selectLast();
        }
    }


        public void shraniCB(ActionEvent actionEvent) {
            try {
                File myObj = new File(String.format("%s%s.txt", ime.getText(), priimek.getText()));
                if (myObj.createNewFile()){
                    System.out.println(("Datoteka ustvarjena. " + myObj.getName()));
                }
                else
                    System.out.println(("Datoteka ustvarjena. " + myObj.getName()));
                FileWriter fw = new FileWriter(myObj.getName());
                for (Map.Entry atribut : rezervacijaAvta.entrySet()) {
                    fw.write(atribut.getKey() + "- " + atribut.getValue() + "\n");
                }
                fw.close();
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Rezervacija potrjena");
                a1.setContentText("Rezervacija je potrjena. \nPodatki so shranjeni v datoteki " + myObj.getName());
                a1.setHeaderText("Sporočilo");
                a1.showAndWait();
                izbiraTP.setDisable(true);
                najemnikTP.setDisable(true);
                cenaTP.setDisable(true);
                potrditevTP.setDisable(true);
                tabpane.getSelectionModel().selectFirst();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            ponastavi();
    }


    private boolean validEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void zapriCB(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Zapri");
        alert.setContentText("Ali ste prepričani, da želite zapreti aplikacijo?");
        alert.showAndWait();
        System.exit(0);
    }

    public void ponastavi() {
        krajPrevzema.getSelectionModel().clearSelection();
        krajPrevzema.getStyleClass().remove("error");
        krajPrevzema.setPromptText("Izberite kraj prevzema");

        krajVracila.getSelectionModel().clearSelection();
        krajVracila.getStyleClass().remove("error");
        krajVracila.setPromptText("Izberite kraj vračila");

        uraPrevzema.getSelectionModel().clearSelection();
        uraPrevzema.setPromptText("Izberite uro prevzema");
        uraPrevzema.getStyleClass().remove("error");

        uraVracila.getSelectionModel().clearSelection();
        uraVracila.setPromptText("Izberite uro vračila");
        uraVracila.getStyleClass().remove("error");

        datumPrevzema.setValue(null);
        datumPrevzema.getStyleClass().remove("error");

        datumVracila.setValue(null);
        datumVracila.getStyleClass().remove("error");

        srednji.setSelected(true);

        bencin.setSelected(true);

        znamkaModel.getSelectionModel().clearSelection();
        znamkaModel.getStyleClass().remove("error");

        rocni.setSelected(true);

        ime.clear();
        ime.getStyleClass().remove("error");

        priimek.clear();
        priimek.getStyleClass().remove("error");

        naslov.clear();
        naslov.getStyleClass().remove("error");

        mesto.clear();
        mesto.getStyleClass().remove("error");

        postnaSt.clear();
        postnaSt.getStyleClass().remove("error");

        email.clear();
        email.getStyleClass().remove("error");

        telefon.clear();
        telefon.getStyleClass().remove("error");

        starost.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 100));
        starost.getStyleClass().remove("error");

        vozniski.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        vozniski.getStyleClass().remove("error");

        dodatnoDa.setSelected(true);

        cena.clear();

        kartica.setSelected(true);

        stKartice.clear();
        stKartice.getStyleClass().remove("error");

        ccv.clear();
        ccv.getStyleClass().remove("error");

        potrditevZaAvto.clear();
        potrditevZaCeno.clear();
        potrditevZaAvto.clear();
    }

    public void pomocCB(ActionEvent actionEvent) {
        ponastavi();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacije");
        alert.setHeaderText("Pomoč");
        alert.setContentText("Vpišite vse podatke. \nZa shranjevanje pritisnite gumb 'Shrani' ali pritisnite kombinacijo tipk CTRL + S.\n" +
                "Za odprtje datoteke pritisnite CTRL + O ali odprite ukaz v meniju.\n" +
                "Za ponastavitev podatkov pritisnite gumb, ali pritisnite CTRL + P.");
        alert.showAndWait();
    }

    public void odpriNaslednjega(ActionEvent actionEvent) {
        tabpane.getSelectionModel().selectNext();
    }

    public void odpriPrejsnjega(ActionEvent actionEvent) {
        tabpane.getSelectionModel().selectPrevious();
    }

    public void blokirajKartico(ActionEvent actionEvent) {
        stKartice.setEditable(false);
        ccv.setEditable(false);
        stKartice.clear();
        ccv.clear();
        stKartice.setPromptText("");
        ccv.setPromptText("");
    }

    public void odblokirajKartico(ActionEvent actionEvent) {
        stKartice.setEditable(true);
        ccv.setEditable(true);
        stKartice.setPromptText("Vpišite številko kartice");
        ccv.setPromptText("Vpišite CCV kodo");
    }

    public void odpriIzbiro(ActionEvent actionEvent) {
        //datum prevzema
        if (datumPrevzema.getValue() == null) {
            alertEmpty.append("Izpolnite datum prevzema.\n");
            if (! datumPrevzema.getStyleClass().contains("error"))
                datumPrevzema.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("datumPrevzema", datumPrevzema.getValue());
            datumPrevzema.getStyleClass().remove("error");

        }

        if (datumVracila.getValue() == null) {
            alertEmpty.append("Izpolnite datum vračila.\n");
            if (! datumVracila.getStyleClass().contains("error"))
                datumVracila.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("datumVracila", datumVracila.getValue());
            datumVracila.getStyleClass().remove("error");
        }

        // kraj prevzema
        if (krajPrevzema.getValue() == null) {
            alertEmpty.append("Izberite kraj prevzema.\n");
            if (! krajPrevzema.getStyleClass().contains("error"))
                krajPrevzema.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("krajPrevzema", krajPrevzema.getValue());
            krajPrevzema.getStyleClass().remove("error");
        }

        // kraj vracila
        if (krajVracila.getValue() == null) {
            alertEmpty.append("Izberite kraj vračila.\n");
            if (! krajVracila.getStyleClass().contains("error"))
                krajVracila.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("krajVracila", krajVracila.getValue());
            krajVracila.getStyleClass().remove("error");
        }


        // ura prevzema
        if (uraPrevzema.getValue() == null) {
            alertEmpty.append("Izberite uro prevzema.\n");
            if (! uraPrevzema.getStyleClass().contains("error"))
                uraPrevzema.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("uraPrevzema", uraPrevzema.getValue());
            uraPrevzema.getStyleClass().remove("error");
        }
        // ura vracila
        if (uraVracila.getValue() == null) {
            alertEmpty.append("Izberite uro vračila.\n");
            if (! uraVracila.getStyleClass().contains("error"))
                uraVracila.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("uraVracila", uraVracila.getValue());
            uraVracila.getStyleClass().remove("error");
        }
        if (check()) {
            System.out.println("odpri izbiro");
            tabpane.getSelectionModel().selectNext();
            izbiraTP.setDisable(false);
        }
    }
    public boolean check() {
        boolean goNext = true;
        if (! alertEmpty.isEmpty()) {
            alertInfo(String.valueOf(alertEmpty));
            goNext = false;
        }

        if (! alertValidation.isEmpty()) {
            alertInfo(String.valueOf(alertValidation));
            goNext = false;
        }
        alertEmpty = new StringBuilder();
        alertValidation = new StringBuilder();
        return goNext;
    }

    public void odpriNajemnika(ActionEvent actionEvent) {

        if (velik.isSelected())
            rezervacijaAvta.put("velikost", "velik");
        else if (srednji.isSelected())
            rezervacijaAvta.put("velikost", "srednji");
        else
            rezervacijaAvta.put("velikost", "majhen");

        if (bencin.isSelected())
            rezervacijaAvta.put("gorivo", "bencin");
        else
            rezervacijaAvta.put("gorivo", "dizel");

        if (rocni.isSelected())
            rezervacijaAvta.put("menjalnik", "rocni");
        else
            rezervacijaAvta.put("menjalnik", "avtomatski");

        if (znamkaModel.getValue() == null) {
            alertEmpty.append("Izberite znamko in model avtomobila.\n");
            if (! znamkaModel.getStyleClass().contains("error"))
                znamkaModel.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("znamkaModel", znamkaModel.getValue());
            znamkaModel.getStyleClass().remove("error");
        }


        if (check()) {
            tabpane.getSelectionModel().selectNext();
            najemnikTP.setDisable(false);
        }
    }

    public void odpriPlacilo(ActionEvent actionEvent) {
        //ime
        if (ime.getText().isEmpty()) {
            alertEmpty.append("Izpolnite ime.\n");
            if (! ime.getStyleClass().contains("error"))
                ime.getStyleClass().add("error");
        }
        else if (notOnlyCharacters(ime.getText())) {
            alertValidation.append("V imenu so lahko samo črke.\n");
            if (! ime.getStyleClass().contains("error"))
                ime.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("ime", ime.getText());
            ime.getStyleClass().remove("error");
        }
        //priimek

        if (priimek.getText().isEmpty()) {
            alertEmpty.append("Izpolnite priimek.\n");
            if (! priimek.getStyleClass().contains("error"))
                priimek.getStyleClass().add("error");
        }
        else if (notOnlyCharacters(priimek.getText())) {
            alertValidation.append("V priimku so lahko samo črke.\n");
            if (! priimek.getStyleClass().contains("error"))
                priimek.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("priimek", priimek.getText());
            priimek.getStyleClass().remove("error");
        }


        //naslov
        if (naslov.getText().isEmpty()) {
            alertEmpty.append("Izpolnite naslov.\n");
            if (! naslov.getStyleClass().contains("error"))
                naslov.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("naslov", naslov.getText());
            naslov.getStyleClass().remove("error");
        }
        // postna stevilka
        if (postnaSt.getText().isEmpty()) {
            alertEmpty.append("Izpolnite poštno številko.\n");
            if (! postnaSt.getStyleClass().contains("error"))
                postnaSt.getStyleClass().add("error");
        }
        else if (! Pattern.matches("[0-9]{4}", postnaSt.getText())) {
            alertValidation.append("Neustrezna poštna številka.\n");
            if (! postnaSt.getStyleClass().contains("error"))
                postnaSt.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("postnaStevilka", postnaSt.getText());
            postnaSt.getStyleClass().remove("error");
        }
        // mesto
        if (mesto.getText().isEmpty()) {
            alertEmpty.append("Izpolnite mesto.\n");
            if (! mesto.getStyleClass().contains("error"))
                mesto.getStyleClass().add("error");
        }
        else if (notOnlyCharacters(mesto.getText())) {
            alertValidation.append("V mestu so lahko samo črke.\n");
            if (! mesto.getStyleClass().contains("error"))
                mesto.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("mesto", mesto.getText());
            mesto.getStyleClass().remove("error");
        }

        if (email.getText().isEmpty()) {
            alertEmpty.append("Izpolnite e-mail naslov.\n");
            if (! email.getStyleClass().contains("error"))
                email.getStyleClass().add("error");
        }
        else if (! validEmail(email.getText())) { // PREVERI ZA MAIL
            alertValidation.append("Vnesite pravilen e-mail naslov.\n");
            if (! email.getStyleClass().contains("error"))
                email.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("email", email.getText());
            email.getStyleClass().remove("error");
        }

        // telefonska
        if (telefon.getText().isEmpty()) {
            alertEmpty.append("Izpolnite telefonsko številko.\n");
            if (! telefon.getStyleClass().contains("error"))
                telefon.getStyleClass().add("error");
        }
        else if (! Pattern.matches("[0-9]{9}", telefon.getText())) {
            alertValidation.append("Neustrezna telefonska številka.\n");
            if (! telefon.getStyleClass().contains("error"))
                telefon.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("telefon", telefon.getText());
            telefon.getStyleClass().remove("error");
        }


        if (starost.getValue() == null) {
            alertEmpty.append("Vpišite starost.\n");
            if (! starost.getStyleClass().contains("error"))
                starost.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("starost", starost.getValue());
            starost.getStyleClass().remove("error");
        }

        if (vozniski.getValue() == null) {
            alertEmpty.append("Vpišite čas vozniškega.\n"); // KAKO SE RECE TEMUUU
            if (! vozniski.getStyleClass().contains("error"))
                vozniski.getStyleClass().add("error");
        }
        else {
            rezervacijaAvta.put("vozniski", vozniski.getValue());
            vozniski.getStyleClass().remove("error");
        }


        if (check()) {
            tabpane.getSelectionModel().selectNext();
            cenaTP.setDisable(false);
        }
    }
}
