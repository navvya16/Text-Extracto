import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class ExtractionGUI extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton openButton;
    JTextArea log;
    JFileChooser fc;
    JLabel tf;
    pdftoimage p = new pdftoimage();
    public ExtractionGUI() {
        super(new BorderLayout());
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,15,5,5));
        log.setEditable(false);
        Border border = BorderFactory.createMatteBorder(0,4,4,4,Color.BLACK);
        Border border1 = BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK);
        JScrollPane logScrollPane = new JScrollPane(log);
        Font font = new Font("Comic sans", Font.BOLD, 18);
        Font font1 = new Font("Comic sans", Font.BOLD, 25);
        log.setFont(font1);
        log.setBorder(border);
        fc = new JFileChooser();
        ImageIcon i= new ImageIcon("C:\\Users\\navvy\\IdeaProjects\\Text Extraction\\src\\main\\pdf_text.jpg");
        openButton = new JButton("ATTACH A PDF OR JPG");
        tf = new JLabel("Attach file");
        tf.setSize(30,4);
        tf.setFont(font1);
        openButton.setIcon(i);
        openButton.setSize(5,5);
        openButton.addActionListener(this);
        openButton.setHorizontalTextPosition(JButton.CENTER);
        openButton.setVerticalTextPosition(JButton.BOTTOM);
        openButton.setFont(font);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));//use FlowLayout
        buttonPanel.add(tf);
        buttonPanel.add(openButton);
        buttonPanel.setBorder(border1);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(ExtractionGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                tf.setText(file.getName());
                String filename = file.getPath();
                log.setText("COPY THE EXTRACTED TEXT!!!" + newline + newline);
                log.append(p.text(filename));
            }
        }
    }
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("EXTRACTING TEXT FROM PDF OR IMAGES ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ExtractionGUI());
        frame.setSize(1200,800);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}