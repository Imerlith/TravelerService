package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
    MyFrame(){
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Traveler service");
        JLabel topLabel  = new JLabel("Proszę podać miasto oraz kraj w języku angielskim");
        JLabel countryLabel  = new JLabel("Country");
        countryLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField countryTxtField = new JTextField();
        countryTxtField.setPreferredSize(new Dimension(200,50));
        JTextField cityTxtField = new JTextField();
        cityTxtField.setPreferredSize(new Dimension(200,50));
        JTextField currencyTextField = new JTextField();
        currencyTextField.setPreferredSize(new Dimension(200,50));
        JButton submitBtn = new JButton("Zatwierdź");
        submitBtn.addActionListener(listener->{
            Service service = new Service(countryTxtField.getText().toLowerCase().replaceAll("\\s+",""));
            String currency = currencyTextField.getText();
            String city = cityTxtField.getText();
            JFrame window = new JFrame("Search Result");
            window.setSize(850,700);
            window.setLocationRelativeTo(null);
            JLabel kraj = new JLabel(""+countryTxtField.getText());
            JLabel miasto = new JLabel(""+city);
            JLabel temperatura = new JLabel();
            JLabel pogoda = new JLabel(service.getWeather(cityTxtField.getText()));
            JLabel waluta = new JLabel(currency);
            JLabel kurs1 = new JLabel("Kurs ECB: "+service.getRateFor(currency));
            JLabel kurs2 = new JLabel("Kurs NBP: "+service.getNBPRate());
            JPanel dane = new JPanel();
            JPanel pogodap = new JPanel();
            JPanel  walutyp= new JPanel();
            dane.add(kraj);
            dane.add(miasto);
            dane.add(waluta);
            pogodap.add(temperatura);
            pogodap.add(pogoda);
            walutyp.add(kurs1);
            walutyp.add(kurs2);
            JPanel content = new JPanel();
            window.getContentPane().add(content);
            LayoutManager layout = new BoxLayout(content, BoxLayout.Y_AXIS);
            Box[] boxes = new Box[4];
            boxes[0] = Box.createHorizontalBox();
            boxes[1] = Box.createHorizontalBox();
            boxes[2] = Box.createHorizontalBox();
            boxes[3] = Box.createHorizontalBox();

            Box.createGlue();
            Box.createGlue();
            Box.createGlue();
            Box.createGlue();

            content.add(boxes[0]);
            content.add(boxes[1]);
            content.add(boxes[2]);
            content.add(boxes[3]);

            boxes[0].add(dane);
            boxes[1].add(pogodap);
            boxes[2].add(walutyp);



            boxes[0].setPreferredSize(new Dimension(window.getWidth(),50));
            boxes[1].setPreferredSize(new Dimension(window.getWidth(),50));
            boxes[2].setPreferredSize(new Dimension(window.getWidth(),50));

            JFXPanel jfxPanel = new JFXPanel();
           boxes[3].add(jfxPanel);
            boxes[3].setPreferredSize(new Dimension(window.getWidth(),500));
            Platform.runLater(()->{
                WebView webView = new WebView();
                jfxPanel.setScene(new Scene(webView));
                webView.getEngine().load("http://en.wikipedia.org/wiki/"+city);
            });

            window.setVisible(true);

        });
        submitBtn.setPreferredSize(new Dimension(200,50));
        JLabel cityLabel = new JLabel("City");
        JLabel currLabel = new JLabel("Currency");
        JPanel topPanel = new JPanel();
        JPanel  countryPanel= new JPanel();
        JPanel  cityPanel= new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel currPanel = new JPanel();
        currPanel.add(currLabel);
        currPanel.add(currencyTextField);

        bottomPanel.add(submitBtn);
        topPanel.add(topLabel);
        countryPanel.add(countryLabel);
        countryPanel.add(countryTxtField);
        cityPanel.add(cityLabel);
        cityPanel.add(cityTxtField);
        topLabel.setFont(new Font("Century",Font.PLAIN,20));
        GridLayout gl = new GridLayout(5,1);
        setLayout(gl);
        getContentPane().add(topPanel);
        getContentPane().add(countryPanel);
        getContentPane().add(cityPanel);
        getContentPane().add(currPanel);
        getContentPane().add(bottomPanel);



        setResizable(false);
        setVisible(true);
    }

}
