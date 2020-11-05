package View;

/**
 *
 * @author 1118038-Kevin Hartono,1118014-Mikhael Adriel,1118020-Albertus
 * Kevin,1118015-Ferani
 */
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConvexHullMST {

    public static void main(String[] args) {

        JFrame mainF = new GUI();
        mainF.setTitle("Convex Hull & MST");
        mainF.getContentPane().setLayout(new FlowLayout());
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainF.setLocationRelativeTo(null);
        mainF.setVisible(true);

        mainF.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave ?");
                switch (a) {
                    case JOptionPane.YES_OPTION:
                        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        break;
                    case JOptionPane.NO_OPTION:
                        mainF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        mainF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
