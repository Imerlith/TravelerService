package zad1;

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
        JTextField countryTxtField = new JTextField();
        countryTxtField.setSize(200,100);
        JTextField cityTxtField = new JTextField();
        JLabel cityLabel = new JLabel("Miasto");
        JPanel topPanel = new JPanel();
        JPanel  countryPanel= new JPanel();
        JPanel  cityPanel= new JPanel();
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




        setVisible(true);
    }
}
