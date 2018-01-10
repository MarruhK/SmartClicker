import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Used for:
    Alching
    Thieving, Knights
    NMZ rapid heal
    Other AC uses
    Record speed of user clicks.
 */
class GUI extends JFrame {

    private JComboBox box;
    private long currentTime = 0;       // Time since execution, in seconds.
    private SmartClicker clicker;       // So both IC can access.

    GUI() {
        // Panels
        JPanel panel1 = new JPanel();
        JPanel mainButtonPanel = new JPanel();
        JPanel subButtonPanel1 = new JPanel();
        JPanel subButtonPanel2 = new JPanel();


        // COMPONENTS
        // Buttons
        JButton startButton = new JButton("START");
        JButton stopButton = new JButton("STOP");
        startButton.addActionListener(new startClicker());
        stopButton.addActionListener(new stopClicker());
        startButton.setPreferredSize(new Dimension(150,30));
        stopButton.setPreferredSize(new Dimension(150,30));

        // combobox
        String[] boxValues = {"", "High Alch", "Knights Thieving", "NMX Rapid Heal"};
        box = new JComboBox(boxValues);
        box.setPrototypeDisplayValue("I THINK THIS SHOULD BE GOOD");

        // Add components to panels
        panel1.add(box);

        subButtonPanel1.add(startButton);
        subButtonPanel2.add(stopButton);

        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel,3));
        mainButtonPanel.add(subButtonPanel1);
        mainButtonPanel.add(subButtonPanel2);

        // Add panels to frame
        add(BorderLayout.WEST, panel1);
        add(BorderLayout.CENTER, mainButtonPanel);

        // Frame properties
        setSize(new Dimension(400, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Marruhk's Smart Clicker");
    }


    // Poly to get proper clicker
    private SmartClicker getSmartClicker(int index) {
        if (index == 1) {
            return new AlchemyClicker();
        } else if (index == 2) {
            return new KnightsClicker();
        } else if (index == 3) {
            return new NMZClicker();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select the type of Smart Clicker you want to use.",
                    "SmartClicker Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // INNER CLASSES
    class startClicker implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clicker = getSmartClicker(box.getSelectedIndex());

            if (!(clicker == null)){
                System.out.println("Begin AC");
                Runnable run = new RunnableExiter(clicker);
                Thread loopThread = new Thread(run);
                loopThread.start();
            }
        }
    }

    class stopClicker implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clicker.stopClicking();
        }
    }
}
