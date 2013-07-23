/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Adam
 */
class MazeView extends Canvas {
    //Data field. 

    int size;
    private Maze mazeModel;
    private boolean drawingMode = false;
    float pixelWidth, pixelHeight, rWidth, rHeight;
    int X, Y;

    public MazeView() {
        super();

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent evt) {

                X = X(evt.getX());
                Y = Y(evt.getY());
             
                if (isDrawingMode()) {
                    mazeModel.setCell(X, Y, MazeCell.WALL);
                }

            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                X = X(evt.getX());
                Y = Y(evt.getY());
                if (isDrawingMode() && mazeModel.getCell(X, Y) != MazeCell.WALL) {
                    mazeModel.setCell(X, Y, MazeCell.WALL);
                } else {
                    mazeModel.setCell(X, Y, MazeCell.UNEXPLORED);
                }

            }
        });


    }
    // ----------------------------------------------------------

    /**
     * Sets the model that is rendered by this view. It adds the view as an
     * observer for the model.
     *
     * @param model - model represented by Maze class.
     */
    public void setModel(Maze model) {
        this.mazeModel = model;
        size = model.size();
        rHeight = size;
        rWidth = size;
        mazeModel.addObserver(new MazeObserver());

    }

    public void initgr() {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        pixelWidth = rWidth / maxX;
        pixelHeight = rHeight / maxY;

    }

    private int pixelX(float x) {
        return Math.round(x / pixelWidth);
    }

    private int pixelY(float y) {
        return Math.round(y / pixelHeight);
    }

    private int X(int x) {

        return (int) (x * pixelWidth);

    }

    private int Y(int y) {
        return (int) (y * pixelHeight);
    }

    @Override
    public void paint(Graphics g) {
        initgr();
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {

                g.setColor(Color.black);
                g.drawRect(pixelX(k), pixelY(i), pixelX( 1), pixelY( 1));
               

            }

        }


    }

    @Override
    public void update(Graphics g) {
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                if (mazeModel.getCell(k, i) == MazeCell.WALL) {
                  
                    g.setColor(Color.black);
                    g.fillRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                }
                else if (mazeModel.getCell(k, i) == MazeCell.CURRENT_PATH) {
                  
                    g.setColor(Color.green);
                    g.fillRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                    g.setColor(Color.black);
                    g.drawRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                }
                else if (mazeModel.getCell(k, i) == MazeCell.FAILED_PATH) {
                  
                    g.setColor(Color.red);
                    g.fillRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                    g.setColor(Color.black);
                    g.drawRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                }
               
                
                else { 
                    g.setColor(Color.white); 
                    g.fillRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                    g.setColor(Color.black);
                    g.drawRect(pixelX(k), pixelY(i), pixelX(1), pixelY(1));
                }
                
            }
        }
    }

    public void setDrawingMode(boolean drawingMode) {
        this.drawingMode = drawingMode;
    }

    public boolean isDrawingMode() {
        return this.drawingMode;
    }

    /**
     * --- /** An observer that listens to the changes made to the Maze model.
     * This is a nested class inside a MazeView that can still access methods
     * that belong to the surrounding view.
     */
    private class MazeObserver
            implements Observer {

        /**
         * Called when the Maze model (in this case modelMaze instance) is
         * changed any time users interacts with the maze view.
         *
         * @param observable - the Observable object that was changed.
         * @param data - extra data about notification
         */
        @Override
        public void update(Observable observable, Object data) {
            // The invalidate method that is used to force a view to be
            // repainted at the earliest opportunity.
            repaint();
        }
    }
}
