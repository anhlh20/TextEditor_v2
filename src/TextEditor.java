import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, formatMenu;
    JMenuItem openItemMenu, saveItemMenu, exitItemMenu, copyItemMenu, cutItemMenu, pasteItemMenu, fontFamilyItem, fontStyleItem, fontSizeItem;
    String cutOrCopyString;
    TextEditor(){
        this.setTitle("Text Editor");
        this.setLayout(new FlowLayout());
        //exit application after close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set location to center window
        this.setLocationRelativeTo(null);
//        this.setSize(600,600);
        this.setVisible(true);


        //create text area
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //create scroll pane for text area
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500,500));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //create menuBar: menuBar - menu - menuItem
        /*
        File - Edit - Format
            File:
                Open - Save - Exit
            Edit:
                Copy - Cut - Paste
            Format:
                Font Family - Font Style - Font Size
        */


        //create file menu and file menu item
        fileMenu = new JMenu("File");
        // Alt + F to open File
        fileMenu.setMnemonic(KeyEvent.VK_F);
            openItemMenu = new JMenuItem("Open");
            saveItemMenu = new JMenuItem("Save");
            exitItemMenu = new JMenuItem("Exit");

            fileMenu.add(openItemMenu);
            fileMenu.add(saveItemMenu);
            fileMenu.add(exitItemMenu);

        //create edit menu and add edit menu item
        editMenu = new JMenu("Edit");
        //Alt + E to open Edit
        editMenu.setMnemonic(KeyEvent.VK_E);

            cutItemMenu = new JMenuItem("Cut");
            //Ctrl + I to cut
            cutItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
            cutItemMenu.addActionListener(this);

            copyItemMenu = new JMenuItem("Copy");
            //Ctrl + M to cut
            copyItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
            copyItemMenu.addActionListener(this);

            pasteItemMenu = new JMenuItem("Paste");
            //Ctrl + P to paste
            pasteItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
            pasteItemMenu.addActionListener(this);

            editMenu.add(cutItemMenu);
            editMenu.add(copyItemMenu);
            editMenu.add(pasteItemMenu);

        //create format menu and add format menu item
        formatMenu = new JMenu("Format");
        //Alt + O to open Format
        formatMenu.setMnemonic(KeyEvent.VK_O);
            fontFamilyItem = new JMenuItem("Font Family");
            fontStyleItem = new JMenuItem("Font Style");
            fontSizeItem = new JMenuItem("Font Size");

            formatMenu.add(fontFamilyItem);
            formatMenu.add(fontStyleItem);
            formatMenu.add(fontSizeItem);

        //create menu bar
        menuBar = new JMenuBar();
            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(formatMenu);

        //end menubar

        this.setJMenuBar(menuBar);
        this.add(scrollPane);

        //fit content of this full when display if this don't set size
        //always bottom
        this.pack();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //handle Edit menu: Cut, copy, paste
        if (e.getSource() == cutItemMenu || e.getSource() == copyItemMenu){
            cutOrCopyString = textArea.getSelectedText();
            if(e.getSource() == cutItemMenu){
                textArea.replaceSelection("");
            }
        }
        else if (e.getSource() == pasteItemMenu){
            textArea.insert(cutOrCopyString, textArea.getCaretPosition());
        }
    }
}
