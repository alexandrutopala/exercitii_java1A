import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import exceptii.*;

public class MainFrame extends JFrame implements Listener {    
    private DefaultListModel<Animal> model;
    private Subject subject;
    private Zoo zoo = Zoo.getInstance();
    private final String[] cmds = {
        "toate animalele",
        "cu greutatea intre",
        "cu varsta intre",
        "cu numele",
        "terestre",
        "acvatice"
    };
    private String prevItem;
    
    {
        // TODO: trateaza exceptiile checked aruncate mai jos folosind un singur bloc try-catch 
        //       care sa cuprinda toate cele 4 instructiuni de mai jos.
        //       In cazul aruncarii vreunei exceptii, afiseaza mesajul acesteia utilizatorului
        //       intr-o componenta grafica (nu in consola).
        // HINT: pentru a afisa un mesaj intr-o componenta grafica, foloseste metoda statica 
        //       "afiseazaMesaj(String mesaj)" din clasa Utils
        try {
            zoo.adaugaAnimal(new AnimalAcvatic("Nemo", 3, 2));
            zoo.adaugaAnimal(new AnimalAcvatic("Dory", 4, 4));
            zoo.adaugaAnimal(new AnimalAcvatic("Croco", 2, 40));
            zoo.adaugaAnimal(new AnimalTerestru("Leo", 8, 120, "Africa de Sud"));
        } catch (NumeDuplicatException e) {
            Utils.afiseazaMesaj(e.getMessage());
        }
    }
    
    public MainFrame() {
        super("Exercitiul 4");
        initComponents();
         
        model = new DefaultListModel<>();
        subject = new Subject();
        
        subject.addListener(this);
        list.setModel(model);
        
        refresh(zoo.getAnimale());
        
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void refresh(Animal[] animale) {
        //System.out.println("list refreshed");
        model.clear();
        
        int i = 0;
        while (i < animale.length && animale[i] != null) {;
            model.addElement(animale[i]);
            i++;
        }
        list.revalidate();
    }
    
    @Override
    public void notifyList() {
        //System.out.println("Afiseaza animale : " + optionsComboBox.getSelectedItem());
        ActionListener[] listeners = optionButtons.get(optionsComboBox.getSelectedItem()).getActionListeners();
        for (ActionListener al : listeners) {
            al.actionPerformed(null);
        }
    }
    
    @Override
    public void notifyWindowClosed() {
        adaugaButton.setEnabled(true);
    }
    
    private void initComponents() {
        searchPanel = new JPanel();
        listPanel = new JPanel();
        list = new JList<>();
        optionsComboBox = new JComboBox<>();
        optionsComboBoxModel = new DefaultComboBoxModel<>(cmds);
        adaugaButton = new JButton("Adauga");
        cautaAnimalLabel = new JLabel("Cauta animale  ");
        initPanels();
        
        optionsComboBox.setModel(optionsComboBoxModel);
        optionsComboBox.setSelectedItem(0);
        prevItem = cmds[0];
        
        optionsComboBox.addItemListener((ItemEvent ie) -> {
            
            if (ie.getItem().equals(prevItem)) {
                return;
            }
            
            searchPanel.removeAll();
            
            searchPanel.add(cautaAnimalLabel);
            searchPanel.add(optionsComboBox);
            searchPanel.add(optionPanels.get(ie.getItem()));
            
            Boolean notify = notifyListOnComboBoxItemChanged.get(ie.getItem());
            //System.out.println("For item " + ie.getItem() + " notify list:" + notify);
            
            if (notify != null ? notify : false) {
                notifyList();
            }
            
            prevItem = (String) ie.getItem();
            MainFrame.this.revalidate();
            MainFrame.this.repaint();
            
        });
        
        list.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent ev) {
                if (list.getSelectedValue() == null ||
                    ev.getClickCount() != 2 || 
                    ev.getButton() != MouseEvent.BUTTON1) {
                    return;    
                }
                
                final Animal selected = list.getSelectedValue();
                model.removeElement(selected);
                // TODO: trateaza exceptiile checked aruncate mai jos folosind blocul try-catch
                //       In cazul aruncarii vreunei exceptii, afiseaza mesajul acesteia utilizatorului
                //       intr-o componenta grafica (nu in consola).
                // HINT: pentru a afisa un mesaj intr-o componenta grafica, foloseste metoda statica 
                //       "afiseazaMesaj(String mesaj)" din clasa Utils
                try {
                    zoo.stergeAnimal(selected.getId());
                } catch (IdInexistentException e) {
                    Utils.afiseazaMesaj(e.getMessage());
                }
                
            }
        });
        
        list.setToolTipText("Dublu click pe un element pentru al sterge");
        
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(cautaAnimalLabel);
        searchPanel.add(optionsComboBox);
        searchPanel.add(optionPanels.get(optionsComboBox.getSelectedItem()));
        
        list.setPreferredSize(new Dimension(410, 320));
        JScrollPane jsp = new JScrollPane(list);       
        jsp.setPreferredSize(new Dimension(430, 350));
        listPanel.add(jsp);
        
        adaugaButton.addActionListener(ev -> { 
            new AdaugaAnimalFrame(zoo, subject, getHeight(), getX() + getWidth() + 2, getY()).setVisible(true);
            adaugaButton.setEnabled(false);
        });
        
        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(adaugaButton, BorderLayout.SOUTH);
    }
    
    private void initPanels() {
        optionPanels = new LinkedHashMap<>();
        optionButtons = new LinkedHashMap<>();
        notifyListOnComboBoxItemChanged = new HashMap<>();
        
        JPanel panel;
        
        // toate animalele
        panel = new JPanel();
        JButton jb2 = new JButton("Afiseaza rezultatele");
        //panel.add(jb2);
        jb2.addActionListener(ev -> refresh(zoo.getAnimale()));
        
        optionButtons.put(cmds[0], jb2);
        optionPanels.put(cmds[0], panel);
        notifyListOnComboBoxItemChanged.put(cmds[0], true);
        
        // greutate
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JTextField jtf1 = new JTextField(""); jtf1.setColumns(3);
        panel.add(jtf1);
        JLabel jl1 = new JLabel(" si ");
        panel.add(jl1);
        JTextField jtf2 = new JTextField(""); jtf2.setColumns(3);
        panel.add(jtf2);
        JButton jb1 = new JButton("Cauta");
        
        jb1.addActionListener(ev -> {
            int min; 
            int max; 
            try {
                min = Integer.parseInt(jtf1.getText());                
            } catch (NumberFormatException e) {
                min = 0;
                jtf1.setText(""); 
            }
            
            try {
                max = Integer.parseInt(jtf2.getText());           
            } catch (NumberFormatException e) {
                max = Integer.MAX_VALUE;
                jtf2.setText(""); 
            }
            refresh(zoo.findAnimaleByGreutate(min, max));
        });
        panel.add(jb1);
        
        optionButtons.put(cmds[1], jb1);
        optionPanels.put(cmds[1], panel);
        notifyListOnComboBoxItemChanged.put(cmds[1], true);
        
        //varsta
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JTextField jtf3 = new JTextField(""); jtf3.setColumns(3);
        panel.add(jtf3);
        JLabel jl2 = new JLabel(" si ");
        panel.add(jl2);
        JTextField jtf4 = new JTextField(""); jtf4.setColumns(3);
        panel.add(jtf4);
        JButton jb3 = new JButton("Cauta");
        jb3.addActionListener(ev -> {
            int min; 
            int max; 
            try {
                min = Integer.parseInt(jtf3.getText());                
            } catch (NumberFormatException e) {
                min = 0;
                jtf3.setText(""); 
            }
            
            try {
                max = Integer.parseInt(jtf4.getText());           
            } catch (NumberFormatException e) {
                max = Integer.MAX_VALUE;
                jtf4.setText(""); 
            }
            refresh(zoo.findAnimaleByVarsta(min, max));
        });
        panel.add(jb3);
        
        optionPanels.put(cmds[2], panel);
        optionButtons.put(cmds[2], jb3);
        notifyListOnComboBoxItemChanged.put(cmds[2], true);
        
        // nume
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JTextField jtf5 = new JTextField(""); jtf5.setColumns(10);
        panel.add(jtf5);
        JButton jb4 = new JButton("Cauta");
        jb4.addActionListener(ev -> {
            try {
                refresh(zoo.findAnimaleByNume(jtf5.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Numarul introdus este invalid");
                jtf1.setText(""); jtf2.setText("");
            }
        });
        panel.add(jb4);
        
        optionPanels.put(cmds[3], panel);
        optionButtons.put(cmds[3], jb4);
        notifyListOnComboBoxItemChanged.put(cmds[3], true);
        
        // animale terestre
        panel = new JPanel();
        JButton jb5 = new JButton("Afiseaza rezultatele");
        jb5.addActionListener(ev -> refresh(zoo.findAnimaleTerestre()));
        
        optionPanels.put(cmds[4], panel);
        optionButtons.put(cmds[4], jb5);
        notifyListOnComboBoxItemChanged.put(cmds[4], true);
        
        // animale acvatice
        panel = new JPanel();
        JButton jb6 = new JButton("Afiseaza rezultatele");
        jb6.addActionListener(ev -> refresh(zoo.findAnimaleAcvatice()));
        
        optionPanels.put(cmds[5], panel);
        optionButtons.put(cmds[5], jb6);
        notifyListOnComboBoxItemChanged.put(cmds[5], true);
    }
    
    private JLabel cautaAnimalLabel;
    
    private JPanel searchPanel;
    private JPanel listPanel;
    private JList<Animal> list;
    
    private JComboBox<String> optionsComboBox;
    private DefaultComboBoxModel<String> optionsComboBoxModel;
    private Map<String, JPanel> optionPanels;
    private Map<String, JButton> optionButtons;
     
    private JTextField numeField;
    private JButton adaugaButton;
    
    private Map<String, Boolean> notifyListOnComboBoxItemChanged;
}