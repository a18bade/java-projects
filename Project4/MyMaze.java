// Names:Ahmed Bade and Dadir Awad
//written by bade0149 and awad0020

import java.util.Random;

public class MyMaze{
    Cell[][] maze;


    //initizalies the 2d array and creates and ending opening.
    public MyMaze(int rows, int cols) {

        maze = new Cell[rows][cols];

        for(int x = 0; x < maze.length; x++){
            for(int y = 0; y<maze[x].length; y++){
                maze[x][y] = new Cell();

                if(x == maze.length-1)
                    maze[x][y].setRight(false);
            }
        }
    }


    // helper function for debugging..

    public static void arrayPrinter(int[] array){

        String ar = "[" + array[0] + "," + array[1] + "]";

        System.out.println(ar);

    }

    // creates a maze
    public static MyMaze makeMaze(int rows, int cols) {

        MyMaze mymaze = new MyMaze(rows,cols);

        Stack1Gen<int[]> stack = new Stack1Gen<>();

        int[] coord = new int[] {0,0};

        stack.push(coord);

        mymaze.maze[coord[0]][coord[1]].setVisited(true);


        while(stack.isEmpty() == false){

            int numberOfdeadEnd;
            boolean deadEnd = true;
            int[] possibleDirections = {0,1,2,3};
            coord = stack.top();


            while(coord != null && deadEnd == true){


                //this while loop does two things
                // 1) removes dead ends until we reach a cell that isn't one or if the stack is empty.
                // 2) changes the array values of possibleDirections to -1. If going that direction would be out of bounds
                //0 is up, 1 right, 2 is down, and 3 is left.


                possibleDirections = new int[] {0,1,2,3};
                numberOfdeadEnd = 0;

                if(coord[0]-1<0 || mymaze.maze[coord[0]-1][coord[1]].getVisited()==true){
                    numberOfdeadEnd++;
                    possibleDirections[0] = -1;
                }

                if(coord[1]+1>=cols || mymaze.maze[coord[0]][coord[1]+1].getVisited()==true){
                    numberOfdeadEnd++;
                    possibleDirections[1] = -1;
                }

                if(coord[0]+1>=rows || mymaze.maze[coord[0]+1][coord[1]].getVisited()==true){
                    numberOfdeadEnd++;
                    possibleDirections[2] = -1;
                }

                if(coord[1]-1<0 || mymaze.maze[coord[0]][coord[1]-1].getVisited()==true){
                    numberOfdeadEnd++;
                    possibleDirections[3] = -1;
                }

                if (numberOfdeadEnd == 4){
                    stack.pop();
                }
                else{
                    deadEnd = false;
                }

                coord = stack.top();

            }


            if (coord != null){

                // if coord does not equal null that means we know that the cell we currently at has at least one unvisted neighbor cell;

                int randomDirection;
                int choice = -1;

                while(choice==-1){

                    //randomly choices a direction from possibleDirections(choice can't be -1)

                    randomDirection = (int)(Math.random() * 4);
                    choice = possibleDirections[randomDirection];

                }

                //adds correct cell to the stack and changes attributes(visited and wall)
                switch(choice){

                    case 0:

                        coord[0]-= 1;

                        stack.push(new int[] {coord[0],coord[1]});

                        mymaze.maze[coord[0]][coord[1]].setVisited(true);

                        mymaze.maze[coord[0]][coord[1]].setBottom(false);
                    
                        break;

                    case 1:

                        mymaze.maze[coord[0]][coord[1]].setRight(false);

                        coord[1]+= 1;

                        stack.push(new int[] {coord[0],coord[1]});

                        mymaze.maze[coord[0]][coord[1]].setVisited(true);
                        
                        break;

                    case 2:

                        mymaze.maze[coord[0]][coord[1]].setBottom(false);

                        coord[0]+= 1;                           

                        stack.push(new int[] {coord[0],coord[1]});

                        mymaze.maze[coord[0]][coord[1]].setVisited(true);
                                                
                        break;

                    case 3:

                        coord[1]-=1;

                        mymaze.maze[coord[0]][coord[1]].setRight(false);
                        
                        stack.push(new int[] {coord[0],coord[1]});

                        mymaze.maze[coord[0]][coord[1]].setVisited(true);

                        break;

                }
                
            }

        }

        //sets all cells back to unvisited

        for(int x = 0; x< mymaze.maze.length; x++){
            for(int y = 0; y < mymaze.maze[x].length; y++){

                mymaze.maze[x][y].setVisited(false);

            }

        }
        return mymaze;
    }

    //prints the maze

    public void printMaze(boolean path) {


        //draws the top border
        String mazeRepresentation = "|";

        for(int x = 0; x < maze[0].length; x++){

            mazeRepresentation+= "---|";
        }


        //draws the rest of the grid

        for(int x = 0; x < maze.length; x++){

            if (x == 0){
                mazeRepresentation+= "\n" + " ";
            }


            else{
                mazeRepresentation+= "\n" + "|";
            }

            // for loop for the cells and their verical walls
            for(int y = 0; y < maze[x].length; y++){

                if(path == true){


                    if(maze[x][y].getVisited() == true && maze[x][y].getRight() == true){
                        mazeRepresentation+= " * |";
                    }

                    else if(maze[x][y].getVisited() == true && maze[x][y].getRight() == false){
                        mazeRepresentation+= " *  ";
                    }
                    else if(maze[x][y].getVisited() == false && maze[x][y].getRight() == true){
                        mazeRepresentation+= "   |";
                    }

                    else if(maze[x][y].getVisited() == false && maze[x][y].getRight() == false){
                        mazeRepresentation+= "    ";
                    }

                }

                if(path!= true){

                    if(maze[x][y].getRight() == true){

                        mazeRepresentation+= "   |";
                    }

                    else if(maze[x][y].getRight() == false){
                    mazeRepresentation+= "    ";
                    }


                }
            }

            // for loop for the cells and their bottom walls
            mazeRepresentation+= "\n" + "|";
            for(int z = 0; z< maze[x].length; z++){


                if(maze[x][z].getBottom() == true){
                    mazeRepresentation+= "---|";
                }
                else{
                    mazeRepresentation+= "   |";
                }

            }
        }

        mazeRepresentation+= "\n";  


        // prints out the string
        System.out.print(mazeRepresentation);


    }

    public void solveMaze() {

        Q1Gen<int[]> queue = new Q1Gen<>();

        int[] coord = new int[] {0,0};

        queue.add(coord);

        int[] currentCell;
        boolean endFound = false;


        //implements algorithm in write-up...
        while(queue.isEmpty() == false && endFound == false){


            currentCell = queue.remove();
            maze[currentCell[0]][currentCell[1]].setVisited(true);

            if (currentCell[0] == maze.length-1 && currentCell[1] == maze[0].length-1)
                endFound = true;


            if(currentCell[0]-1>=0 && maze[currentCell[0]-1][currentCell[1]].getBottom()==false){

                if(maze[currentCell[0]-1][currentCell[1]].getVisited()==false)
                    queue.add(new int[] {currentCell[0]-1,currentCell[1]});
            }

            if(currentCell[1]+1<maze[0].length && maze[currentCell[0]][currentCell[1]].getRight()==false){

                if(maze[currentCell[0]][currentCell[1]+1].getVisited()==false)
                    queue.add(new int[] {currentCell[0],currentCell[1]+1});

                        
            }

            if(currentCell[0]+1<maze.length && maze[currentCell[0]][currentCell[1]].getBottom()==false){

                if(maze[currentCell[0]+1][currentCell[1]].getVisited()==false)
                    queue.add(new int[] {currentCell[0]+1,currentCell[1]});
            }

            if(currentCell[1]-1>=0 && maze[currentCell[0]][currentCell[1]-1].getRight()==false){
                if(maze[currentCell[0]][currentCell[1]-1].getVisited()==false)
                    queue.add(new int[] {currentCell[0],currentCell[1]-1});
            }

        }

    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */

        MyMaze testmaze = MyMaze.makeMaze(20,10);

        System.out.println("Creating maze...");
        testmaze.printMaze(false);


        System.out.println("Solving maze...(with path shown)...");
        testmaze.solveMaze();
        testmaze.printMaze(true);


    }
}
