import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdaugaAnimalFrame extends JFrame {
    private Zoo zoo;
    private Subject subject;
    
    public AdaugaAnimalFrame(Zoo zoo, Subject subject, int height, int x, int y) {
        super("Adauga un animal");
        initComponents();
        this.zoo = zoo;
        this.subject = subject;
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                subject.notifyWindowClosed();
            }
        });
        
        setSize(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        
        setLocation(x, y);
    }
    
    private void adaugaAnimal() {
        try {
            String nume = numeField.getText();
            int varsta = Integer.parseInt(varstaField.getText());
            int greutate = Integer.parseInt(greutateField.getText());
            
            Animal a = acvaticButton.isSelected() ? 
                                new AnimalAcvatic(nume, varsta, greutate) : 
                                new AnimalTerestru(nume, varsta, greutate, regiuneField.getText());
                                
            // TODO: trateaza exceptiile checked aruncate mai jos folosind blocul try-catch 
            //       In cazul aruncarii vreunei exceptii, afiseaza mesajul acesteia utilizatorului
            //       intr-o componenta grafica (nu in consola).
            // HINT: pentru a afisa un mesaj intr-o componenta grafica, foloseste metoda statica 
            //       "afiseazaMesaj(String mesaj)" din clasa Utils
            zoo.adaugaAnimal(a);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numar introdus invalid");
            varstaField.setText("");
            greutateField.setText("");
            return;
        }
        
        numeField.setText("");
        varstaField.setText("");
        greutateField.setText("");
        regiuneField.setText("");
        subject.notifyListeners();
    }
    
    private void initComponents() {
        numeLabel = new JLabel("Nume ");
        greutateLabel = new JLabel("Greutate ");
        varstaLabel = new JLabel("Varsta ");
        numeField = new JTextField();
        greutateField = new JTextField();
        varstaField = new JTextField();
        acvaticButton = new JRadioButton("Acvatic", true);
        terestruButton = new JRadioButton("Terestru", false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(acvaticButton);
        bg.add(terestruButton);
        regiuneLabel = new JLabel("Regiune");
        regiuneField = new JTextField();
        adaugaButton = new JButton("Adauga");
        anuleazaButton = new JButton("Inchide");
                
        adaugaButton.addActionListener(ev -> adaugaAnimal());
        anuleazaButton.addActionListener(ev -> {dispose(); subject.notifyWindowClosed(); });
        
        numeField.setSize(numeField.getWidth(), 5);
        
        regiuneLabel.setVisible(false);
        regiuneField.setVisible(false);
        
        acvaticButton.addItemListener(ie -> {
            if (acvaticButton.isSelected()) {
                regiuneLabel.setVisible(false);
                regiuneField.setVisible(false);
            } else {
                regiuneLabel.setVisible(true);
                regiuneField.setVisible(true);
            }
        });
        
        add(numeLabel); add(numeField);
        add(varstaLabel); add(varstaField);
        add(greutateLabel); add(greutateField);
        add(acvaticButton); add(terestruButton);
        add(regiuneLabel); add(regiuneField);
        add(adaugaButton); add(anuleazaButton);
                
        setLayout(new GridLayout(6, 2));
    }
    
    private JLabel numeLabel;
    private JTextField numeField;
    private JLabel greutateLabel;
    private JTextField greutateField;
    private JLabel varstaLabel;
    private JTextField varstaField;
    private JRadioButton acvaticButton;
    private JRadioButton terestruButton;
    private JLabel regiuneLabel;
    private JTextField regiuneField;
    private JButton adaugaButton;
    private JButton anuleazaButton;
     
}
