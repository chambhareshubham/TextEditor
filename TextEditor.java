import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar Menubar;
    JMenu file,edit;
    JMenuItem newfile,open,save,cut,copy,paste,selectall,close;
    JTextArea area;
    TextEditor(){
        frame= new JFrame();
        frame.setBackground(Color.BLACK);
        area= new JTextArea();
        frame.add(area);
        Menubar=new JMenuBar();
        file= new JMenu("File");

        newfile=new JMenuItem("NewFile");
        open = new JMenuItem("Open File");
        save= new JMenuItem("Save File");
        newfile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);

       cut =new JMenuItem("Cut");
       copy=new JMenuItem("Copy");
       paste =new JMenuItem("Paste");
       selectall= new JMenuItem("Select All");
       close=new JMenuItem("Close");
cut.addActionListener(this);
copy.addActionListener(this);
paste.addActionListener(this);
selectall.addActionListener(this);
close.addActionListener(this);

        file.add(newfile);
        file.add(save);
        file.add(open);

        edit= new JMenu("Edit");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);


        Menubar.add(file);
        Menubar.add(edit);

        frame.setBounds(0,0,500,500);
        frame.setVisible(true);
       // frame.setBackground(Color.BLACK);
        frame.setLayout(null);
       // Menubar=new JMenuBar();

        frame.setJMenuBar(Menubar);
        frame.add(area);
//frame.setBackground(Color.BLACK);
    }
    public static void main(String[] args) {
        TextEditor obj = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut){
            area.cut();
        }
        if(e.getSource()==copy){
            area.copy();
        }
        if(e.getSource()==paste){
            area.paste();
        }
        if(e.getSource()==selectall){
            area.selectAll();
        }
        if(e.getSource()==close){
            System.exit(0);
        }
        if(e.getSource()==open){
            JFileChooser filchooser= new JFileChooser("C");
            int chosen= filchooser.showOpenDialog(null);
if(chosen==JFileChooser.APPROVE_OPTION){
    File file= filchooser.getSelectedFile();
    String path= file.getPath();
    try{
        FileReader filereader= new FileReader(path);
        BufferedReader br = new BufferedReader(filereader);
        String intermidiate="",output="";
        while((intermidiate=br.readLine())!=null){
            output+=intermidiate+"\n";
        }
area.setText(output);
    }
    catch(FileNotFoundException ab){
ab.printStackTrace();
    }
    catch(IOException ioe){
        ioe.printStackTrace();
    }


}
        }
        if(e.getSource()==save){
            JFileChooser fileChooser= new JFileChooser("C");
            int choose = fileChooser.showSaveDialog(null);
            if(choose==JFileChooser.APPROVE_OPTION){
                File file= new File(fileChooser.getSelectedFile().getAbsoluteFile()+".text");
               try{ FileWriter fileWriter= new FileWriter(file);
               BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
               area.write(bufferedWriter);
               bufferedWriter.close();
               }
                catch (IOException ioException){
                   ioException.printStackTrace();
                }
            }

        }
        if(e.getSource()==newfile){
            TextEditor obj = new TextEditor();
        }
    }
}