import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private DefaultListModel<Cerc> model;
    
    private Cerc [] cercuri;
    private int cercuriCount;
    
    private Cerc creatingCircle;
    private Cerc circle1;
    private Cerc circle2;
    
    private int prevX;
    private int prevY;
    
    public MainFrame() {
        super("Exercitiul 3");
        initComponents();
                
        cercuri = new Cerc[100];
        model = new DefaultListModel<>();
        list.setModel(model);
                
        setSize(800, 300);
        setLocationRelativeTo(null);
        //pack();        
    }
    
    // TODO: scrie o metoda "getDistance" care primeste ca parametri coordonatele x1 si y1, si x2 si y2, coordonate a doua puncte in plan,
    //       si returneaza distanta euclidiana dintre punctele reprezentate de coordonatele date sub forma de int
    // HINT: vezi in fisierul "cerinte.txt"
    private int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(
            Math.pow(x1 - x2, 2) + 
            Math.pow(y1 - y2, 2)
        );
    }
    
    private Cerc adaugaCerc(int x, int y) {
        Cerc c = new Cerc(new Punct(x, y), 10) {
            @Override public String toString() {
                return "C{ c(" + x + ", " + y + "), " + raza + " }";  
            }
        };
        cercuri[cercuriCount++] = c;
        model.addElement(c);
        return c;
    }
    
    
    
    private void initComponents() {
        drawPanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                                
                //System.out.println("Repainting");
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.RED);
                
                for (int i = 0; i < cercuriCount; i++) {
                    Cerc c = cercuri[i];
                    if (c.equals(circle1) || c.equals(circle2)) {
                        g2.setColor(Color.BLUE);
                        //System.out.println("Found one");
                    } else {
                        g2.setColor(Color.RED);
                    }
                    g2.drawOval(c.centru.x, c.centru.y, c.raza, c.raza);
                    g2.drawOval(c.centru.x + c.raza / 2, c.centru.y + c.raza / 2, 1, 1);
                    
                }
                
            }
        };
        infPanel = new JPanel();
        listPanel = new JPanel();
        coordPanel = new JPanel();
        
        drawPanel.setSize(400, 300);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.addMouseMotionListener(new MouseMotionAdapter(){
            
            @Override public void mouseMoved(MouseEvent ev) {
                xValueLabel.setText(String.valueOf(ev.getX()));
                yValueLabel.setText(String.valueOf(ev.getY()));
            }
            
            @Override public void mouseDragged(MouseEvent me) {                
                xValueLabel.setText(String.valueOf(me.getX()));
                yValueLabel.setText(String.valueOf(me.getY()));
                
                if (creatingCircle == null) {
                    return;
                }
                
                int dist = getDistance(creatingCircle.centru.x, me.getX(), creatingCircle.centru.y, me.getY()) / 5;
                creatingCircle.raza = dist;
                repaint();
                
                //System.out.println(dist);
            }
        });
        
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                creatingCircle = adaugaCerc(me.getX(), me.getY());
                repaint();
                //System.out.println("Mouse pressed");
            }
            
            public void mouseReleased(MouseEvent me) {
                creatingCircle = null;
            }
        });
        drawPanel.setToolTipText("Apasa si mentine un click pe suprafa panoului pentru a desena si redimensiona un cerc");
        
        
        list = new JList<>();   
        JScrollPane scrollPane = new JScrollPane(list);
        //list.setSize(50, 200);
        listPanel.add(scrollPane);
        list.setFixedCellWidth(list.getWidth());
        list.setPrototypeCellValue(new Cerc(null, 0) {
            @Override public String toString() { return "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; }
        });
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent me) {
                if (list.getSelectedValue() == null) {
                    return;
                }
                
                if (circle1 != null && circle2 != null) {
                    circle1 = circle2 = null;
                }                
                
                if (circle1 == null) {
                    circle1 = list.getSelectedValue();
                    repaint();
                    //distanceValueLabel.setText("d(" + circle1 + ", ");
                    return;
                }
                
                circle2 = list.getSelectedValue();
                repaint();
                distanceValueLabel.setText(String.valueOf(circle1.centru.getDistance(circle2.centru)));
                
            }
        });
        list.setToolTipText("Click, pe rand, pe doua cercuri pentru a calcula distanta dintre centrele lor");
        
        xLabel = new JLabel("    x");
        yLabel = new JLabel("    y");
        distanceLabel = new JLabel("    Distanta ");
        xValueLabel = new JLabel("0");
        yValueLabel = new JLabel("0");
        distanceValueLabel = new JLabel("N/A");
        
        coordPanel.setLayout(new GridLayout(3, 2));
        
        coordPanel.add(xLabel); coordPanel.add(xValueLabel);
        coordPanel.add(yLabel); coordPanel.add(yValueLabel);
        coordPanel.add(distanceLabel); coordPanel.add(distanceValueLabel);
        //coordPanel.add(new JLabel("")); coordPanel.add(new JLabel(""));
        
        infPanel.setLayout(new BorderLayout());        
        infPanel.add(listPanel, BorderLayout.CENTER);
        infPanel.add(coordPanel, BorderLayout.SOUTH);
        
        add(drawPanel);
        add(infPanel, BorderLayout.EAST);
    }
    
    private JPanel drawPanel;
    private JPanel infPanel;
    private JPanel listPanel;
    private JPanel coordPanel;
    
    private JList<Cerc> list;
    
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel distanceLabel;
    private JLabel xValueLabel;
    private JLabel yValueLabel;
    private JLabel distanceValueLabel;
    
}