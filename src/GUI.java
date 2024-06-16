import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

class GUI extends JFrame {

    private JComboBox box;
    private AbstractClicker clicker;       // So both IC can access.

    public static FileWriter writer;    // Why do I need this to be static

    GUI() {
        System.out.println("Initializing GUI.");

        // Panels
        JPanel comboBoxPanel = new JPanel();
        JPanel mainButtonPanel = new JPanel();
        JPanel subButtonPanel1 = new JPanel();
        JPanel subButtonPanel2 = new JPanel();

        // Buttons
        JButton startButton = new JButton("START");
        JButton stopButton = new JButton("STOP");
        startButton.addActionListener(new startClicker());
        stopButton.addActionListener(new stopClicker());
        startButton.setPreferredSize(new Dimension(150,30));
        stopButton.setPreferredSize(new Dimension(150,30));

        // Combo box
        String[] boxValues = {"", "High Alch", "NMZ"};
        box = new JComboBox(boxValues);
        box.setPrototypeDisplayValue("I THINK THIS SHOULD BE GOOD");

        // Add components to panels
        comboBoxPanel.add(box);

        subButtonPanel1.add(startButton);
        subButtonPanel2.add(stopButton);

        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel,3));
        mainButtonPanel.add(subButtonPanel1);
        mainButtonPanel.add(subButtonPanel2);

        comboBoxPanel.setBackground(new Color(32, 37, 43));
        subButtonPanel1.setBackground(new Color(32, 37, 43));
        subButtonPanel2.setBackground(new Color(32, 37, 43));
        mainButtonPanel.setBackground(new Color(32, 37, 43));

        // Add panels to frame
        add(BorderLayout.WEST, comboBoxPanel);
        add(BorderLayout.CENTER, mainButtonPanel);

        // Frame properties
        setTitle("Marruhk's Smart Clicker");
        setSize(new Dimension(400, 120));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private AbstractClicker getSmartClicker(int index) {
        if (index == 1) {
            return new AlchemyClicker();
        } else if (index == 2) {
            return new NMZClicker();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select the type of Smart Clicker you want to use.",
                    "SmartClicker Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    class startClicker implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clicker = getSmartClicker(box.getSelectedIndex());

            // Create Directory called "logs"
            File dir = new File("session logs");
            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    System.out.println("Failed to create directory!");
                    return;
                }
            }
            System.out.println("Directory created at: " + dir.getAbsolutePath());

            // Store text files in the directory
            Date today = new Date();
            try {
                writer = new FileWriter(
                    new File(dir, String.format("%tY-%tm-%td TIME %tT", today, today, today, today).replaceAll(
                        ":", " ") + ".txt"
                    )
                );
                String clickerName = clicker.getClass().getName();
                writer.write("SmartClicker has Begun! Selected: " + clickerName);
                writer.write(System.lineSeparator());
                writer.write("--------------------------------------------------------------");
                writer.write(System.lineSeparator());
                writer.flush(); // Ensure data is written to disk
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (clicker != null) {
                System.out.println("Begin Auto Clicking");
                Runnable run = new ClickingThread(clicker);
                Thread loopThread = new Thread(run);
                loopThread.start();
            }
        }
    }

    class stopClicker implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Stopping clicker.");

            try {
                writer.write(System.lineSeparator());
                writer.close();
            } catch (Exception ex) {
                System.out.println("Failed to close writer.");
            }

            clicker.stopClicking();
        }
    }
}
