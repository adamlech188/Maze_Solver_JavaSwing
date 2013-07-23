/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author Adam
 */
public enum MazeCell {

    
    NORTHW(true,false, false, false),
    WESTW(false,false,true,false),
    NORTWESTW(true,false,true,false),
    UNEXPLORED(false,false,false,false),
    WALL(false,false,false,false),
    CURRENT_PATH(false,false,false,false),
    FAILED_PATH(false,false,false,false),
    INVALID_CELL(true,false,false,false),
    VISITED(false,false,true,false);
    
    boolean northW;
    boolean eastW;
    boolean southW;
    boolean westW;
    
    
    
    MazeCell(boolean northW, boolean southW, boolean westW, boolean eastW) {
        this.northW = northW;
        this.southW = southW;
        this.westW = westW;
        this.eastW = eastW;
    }

    public void setNorthW(boolean northW) {
        this.northW = northW;
    }

    public void setSouthW(boolean southW) {
        this.southW = southW;
    }

    public void setWestW(boolean westW) {
        this.westW = westW;
    }

    public void setEastW(boolean eastW) {
        this.eastW = eastW;
    }
}
