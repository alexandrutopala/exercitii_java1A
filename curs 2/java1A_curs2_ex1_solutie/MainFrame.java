import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel listPanel;
    private JPanel addPanel;
    private JList<Persoana> list;
    private JTextField numeField;
    private JTextField varstaField;
    private JButton addButton;
    private JLabel varstaLabel;
    private JLabel numeLabel;
    private DefaultListModel<Persoana> model;
        
    public MainFrame() {
        super("Execitiul 2");
        initComponents();
        
        model = new DefaultListModel<>();
        
        list.setModel(model);
        
        //setSize(500, 500);
        pack();
        setLocationRelativeTo(null);        
    }
    
    // TODO: Scrie o metoda numita "createPersoana" care primeste un parametru de tip String reprezentand numele unei persoane si 
    //       un parametru de tip int reprezentand varsta unei persoane si care returneaza o instanta de Persoana cu numele si varsta
    //       date
    Persoana createPersoana(String nume, int varsta) {
        return new Persoana(nume, varsta);
    }
        
    private void initComponents() {
        list = new JList<>();
        listPanel = new JPanel();
        addPanel = new JPanel();
        
        list = new JList<>();
        listPanel.add(new JScrollPane(list));
        
        numeField = new JTextField();
        varstaField = new JTextField();
        numeLabel = new JLabel("Nume ");
        varstaLabel = new JLabel("Varsta ");
        
        addPanel.setLayout(new GridLayout(3, 1));        
        
        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(1, 2));
        aux.add(numeLabel);
        aux.add(numeField);
        
        addPanel.add(aux);
        
        aux = new JPanel();
        aux.setLayout(new GridLayout(1, 2));
        aux.add(varstaLabel);
        aux.add(varstaField);
        
        addPanel.add(aux);
        
        addButton = new JButton("Adauga");
        addPanel.add(addButton);
        
        add(listPanel, BorderLayout.NORTH);
        add(addPanel, BorderLayout.SOUTH);
        
        addButton.addActionListener(ev -> {
            String nume = numeField.getText();
            int varsta = 0;
            
            try {
                varsta = Integer.parseInt(varstaField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Varsta trebuie sa fie un numar valid");
                return;
            }
            
            Persoana p = createPersoana(nume, varsta);
            model.addElement(new Persoana(p.nume, p.varsta) {
                public String toString() {
                    return nume + " " + varsta;
                }
            });
            
            numeField.setText("");
            varstaField.setText("");
        });
        
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ev) {
                if (ev.getClickCount() != 2 || 
                    list.getSelectedValue() == null) {
                        
                    return;
                }
                
                final Persoana p = list.getSelectedValue();
                model.removeElement(p);
            }
        });
        
        list.setToolTipText("Dublu-click pe o persoana pentru a o sterge");
    }
    
    
}