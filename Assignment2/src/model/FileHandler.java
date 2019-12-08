/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author ZuraH
 */
public class FileHandler {
    //This class loads a csv file, and by reading line after line adds them to a integer vector and returns it. 
    public static Vector<Integer> loadFile()
    {   
        Vector<Integer> table = new Vector<>();
        try
        {
            JButton open = new JButton();
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","csv");
            fc.setFileFilter(filter);
            
            if(fc.showOpenDialog(open)== JFileChooser.APPROVE_OPTION)
            {
                Path fullPath = Paths.get(fc.getSelectedFile().getAbsolutePath());           
                try
                {
                    BufferedReader br = Files.newBufferedReader(fullPath); 
                    {

                    // read the first line from the text file
                        String line = br.readLine();

                        // loop until all lines are read
                        int counter=0;
                        while (line != null) 
                        {

                            // use string.split to load a string array with the values from
                            // each line of
                            // the file, using a comma as the delimiter
                            String[] attributes = line.split(",");
                            
                            if(counter==0)
                            {
                                table.add(attributes.length);
                            }
                            for(int i=0;i<attributes.length;i++)
                            {
                                table.add(Integer.parseInt(attributes[i]));
                            }
                         
                            // read next line before looping
                            // if end of file reached, line would be null
                            line = br.readLine();
                        }
                    }
                }  
                catch (IOException ioe) 
                {
                            ioe.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            System.err.println("Error reading data");
        }
        return table;
    }
}
