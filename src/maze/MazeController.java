/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Adam
 */
public class MazeController extends JFrame implements ActionListener{

    JButton solveButton;
    JButton clearButton;
    JCheckBox drawingMode;
    JLabel solutionPath;
    MazeView grid = new MazeView();
    Maze mazeModel = new Maze(8); 
    MazeSolver mazeSolver = new MazeSolver(mazeModel); 
   
   // MazeGenerator mazeGenerator = new MazeGenerator(mazeModel.getMazeArray());

    public MazeController() {
        super("Maze Game");
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });


        setLayout(new GridBagLayout());
        addComponents();
        setBackground(Color.gray);
        setSize(600, 600);

        setVisible(true);
       

    }

    private void addComponents() {
      
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 0, 10);

        solveButton = new JButton("Solve Maze");
        solveButton.addActionListener(this);
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 0;
        c.ipady = 0;

        getContentPane().add(solveButton, c);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1;
        c.weighty = 0;
        c.ipadx = 30;
        c.ipady = 0;
        getContentPane().add(clearButton, c);

        drawingMode = new JCheckBox("Draw Maze");
        drawingMode.addActionListener(this);
        c.fill = GridBagConstraints.NONE;

        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.anchor = GridBagConstraints.EAST;
        getContentPane().add(drawingMode, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;

        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipady = 0;
        c.ipadx = 0;
        c.insets = new Insets(10, 10, 0, 10);


        grid.setModel(mazeModel); 
        grid.setSize(400, 400);
        grid.setBackground(Color.white);
        grid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(grid, c);

        solutionPath = new JLabel("Solution is:");
        
        c.gridx = 0;
        c.gridy = 2;

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(solutionPath, c);


    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      
       if(e.getSource() == clearButton) { 
            
           mazeModel.clearMaze(); 
           grid.repaint(); 
           
       }
       if(e.getSource() == drawingMode) { 
        
            if(grid.isDrawingMode()) {
                grid.setDrawingMode(false);
            }
            else { 
                grid.setDrawingMode(true);
            }
        }
       if(e.getSource()== solveButton) { 
           
         mazeSolver.solve(); 
         grid.repaint(); 
     
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MazeController();
        
    }

}
