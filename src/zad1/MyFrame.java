package zad1;

import javafx.embed.swing.JFXPanel;
import javafx.scene.web.WebEngine;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
    MyFrame(){
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Traveler service");
        JLabel topLabel  = new JLabel("Proszę podać miasto oraz kraj");
        JLabel countryLabel  = new JLabel("Kraj");
        countryLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField countryTxtField = new JTextField();
        countryTxtField.setPreferredSize(new Dimension(200,50));
        JTextField cityTxtField = new JTextField();
        cityTxtField.setPreferredSize(new Dimension(200,50));
        JButton submitBtn = new JButton("Zatwierdź");
        submitBtn.addActionListener(listener->{
            JFrame temp = new JFrame("sd ad");
           WebEngine webEngine = new WebEngine();

        });
        submitBtn.setPreferredSize(new Dimension(200,50));
        JLabel cityLabel = new JLabel("Miasto");
        JPanel topPanel = new JPanel();
        JPanel  countryPanel= new JPanel();
        JPanel  cityPanel= new JPanel();
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitBtn);
        topPanel.add(topLabel);
        countryPanel.add(countryLabel);
        countryPanel.add(countryTxtField);
        cityPanel.add(cityLabel);
        cityPanel.add(cityTxtField);
        topLabel.setFont(new Font("Century",Font.PLAIN,20));
        GridLayout gl = new GridLayout(4,1);
        setLayout(gl);
        getContentPane().add(topPanel);
        getContentPane().add(countryPanel);
        getContentPane().add(cityPanel);
        getContentPane().add(bottomPanel);



        setResizable(false);
        setVisible(true);
    }

}
