import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor implements ActionListener {
    // declaring properties of the texteditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;


    // file menu item
    JMenuItem newFile, openFile, saveFile;
    // edit menu item
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){

        frame = new JFrame();  // initialize a frame


        menuBar = new JMenuBar();  // initialize a menu bar

        // initialize text area
        textArea = new JTextArea();

        // initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initialize file menu item

        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open Saved File");
        saveFile = new JMenuItem("Save File");

        // add action listener to the file item
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add menu item to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initialize edit menu item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // add action listerns to the edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // add menu item to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add to the menu bar
        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);  // set menuBar to frame
        // create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        // create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        frame.setBounds(0,0,500,500);  // set dimension of a frame
        frame.setTitle("TextEditor By Hemant Kumar");
        frame.setVisible(true);
        frame.setLayout(null);

    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut) {
            // perform cut operation
            textArea.cut();
        }
        if (e.getSource() == copy) {
            //perform copy operation
            textArea.copy();
        }
        if (e.getSource() == paste) {
            //perform paste operation
            textArea.paste();
        }
        if (e.getSource() == selectAll) {
            //perform select all operation
            textArea.selectAll();
        }
        if (e.getSource() == close) {
            //perform close editor operation
            System.exit(0);
        }
        if (e.getSource() == openFile) {
            // perform open file function or open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                try {
                    // initialize file path
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    // set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (e.getSource() == saveFile) {
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();

                }
            }
        }
        if (e.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }
}