package View;

/**
 *
 * @author 1118038-Kevin Hartono,1118014-Mikhael Adriel,1118020-Albertus
 * Kevin,1118015-Ferani
 */
import Control.Controller;
import static Control.Controller.resultMinimunSpanningTree;
import Model.Coordinate;
import Model.MST;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame {

    private JLabel labelResult;
    private JLabel cost;

    private JPanel buttonP;
    private JPanel searchP;

    private JButton find;
    private JButton reset;
    private JButton searchMST;

    int x = 0;
    int y = 0;

    public static boolean drawAxis = false;
    public static boolean deleteAxis = false;
    public static boolean tree = false;
    public static boolean spanningTree = false;
    public static ArrayList<Coordinate> pointList = new ArrayList<>();
    public static ArrayList<MST> listNodes = new ArrayList<>();
    public static ArrayList<MST> minimumSpaningTree = new ArrayList<>();
    public static Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);

    public GUI() {
        init();

        setSize(480, 500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void init() {
        buttonP = new JPanel(new GridLayout(1, 3));

        find = new JButton("Find");
        find.setPreferredSize(new Dimension(100, 50));
        find.setBackground(Color.blue);
        find.setForeground(Color.white);
        find.setFont(f);
        buttonP.add(find);

        searchMST = new JButton("MST");
        searchMST.setPreferredSize(new Dimension(100, 50));
        searchMST.setBackground(Color.green);
        searchMST.setForeground(Color.white);
        searchMST.setFont(f);
        buttonP.add(searchMST);

        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100, 50));
        reset.setBackground(Color.red);
        reset.setForeground(Color.white);
        reset.setFont(f);
        buttonP.add(reset);

        searchP = new DrawPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(440, 400);
            }
        };

        searchP.setBackground(Color.black);
        searchP.setBorder(BorderFactory.createLineBorder(Color.gray));

        getContentPane().add(buttonP);
        getContentPane().add(searchP);

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pointList.size() >= 3 && pointList.size() <= 100) {
                    drawAxis = true;
                    String str = "";
                    int counter = 1;
                    for (Coordinate p : pointList) {
                        str += ("Koordinat ke - " + counter + " : " + "(" + p.getX() + " , " + p.getY() + ")" + "\n");
                        counter++;
                    }
                    JOptionPane.showMessageDialog(null, str);
                    deleteAxis = false;
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Point less than 3.", "warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        labelResult = new JLabel("Cost MST:");
        labelResult.setForeground(Color.BLACK);
        labelResult.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        buttonP.add(labelResult);

        cost = new JLabel();
        cost.setForeground(Color.BLACK);
        cost.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        buttonP.add(cost);

        searchMST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree = true;
                Controller.createTreeNodes();
                searchP.repaint();
                Controller.createMinimumSpanningTree();

                int spanning_int = JOptionPane.showConfirmDialog(null, "show spanning tree now ?");
                if (spanning_int == 0) {
                    searchP.removeAll();
                    searchP.revalidate();
                    searchP.repaint();
                    spanningTree = true;
                }
                searchP.repaint();
                cost.setText(Integer.toString(resultMinimunSpanningTree(minimumSpaningTree)));
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawAxis = false;
                deleteAxis = true;
                tree = false;
                spanningTree = false;
                pointList.removeAll(pointList);
                listNodes.removeAll(listNodes);
                repaint();
            }
        });
    }
}
