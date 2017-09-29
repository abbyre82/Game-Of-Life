//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Game of Life
// Files:            GameOfLife.java, Config.java, Milestone3Tests.java
// Semester:         CS 302 Spring 2017
//
// Author:           Abby Rechkin
// Email:            rechkin@wisc.edu
// CS Login:         rechkin
// Lecturer's Name:  Gary Dahl
// Lab Section:      306
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:     Holly Michalak
// Partner Email:    hmichalak@wisc.edu
// Partner CS Login: michalak
// Lecturer's Name:  Gary Dahl
// Lab Section:      306
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    _X__ Write-up states that Pair Programming is allowed for this assignment.
//    _X__ We have both read the CS302 Pair Programming policy.
//    _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.
//
// Persons:          Chrissy R, friend, helped us debug 
//					isCellLivinginNextGeneration
// Online Sources:   N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains some testing methods for the GameOfLife project.
 * These methods are intended to serve the following objectives:
 * 1) provide an example of a way to incrementally test your code
 * 2) provide example method calls for some GameOfLife methods
 *    
 * Toward these objectives, the expectation is that part of the 
 * grade for the GameOfLife project is completing the commenting of 
 * these methods. Specific places are noted with TODO but add
 * any other comments you feel would be useful.
 * 
 * These methods are all private as they are only intended for use 
 * within this class.
 * 
 * @author Jim Williams
 * 
 * 
 * 
 */
public class Milestone3Tests {
    
    /**
     * This is the main method that runs the various tests.
     * Uncomment the tests when you are ready for them to run.
     * 
     * @param args  unused
     */
    public static void main(String[] args) {
        
        //testIsCellAlive();
        //testNumNeighborsAlive();
        //testIsCellLivingInNextGeneration();
    }
    
    /**
     * This runs some tests on the isCellAlive method.
     * These tests check that the method correctly
     * identifies whether a cell is alive or dead, including
     * cells that are not within the bounds of the world.
     */
    private static void testIsCellAlive() {
        boolean error = false;
        
        boolean [][] world = new boolean[5][7];
        boolean isAlive;
        
        //test 1
        isAlive = GameOfLife.isCellAlive(world, 10, 10);
        if ( isAlive) {
            error = true;
            System.out.println("testCellAlive 1: Error, "
                    + "cell 10,10 is not in the world"
                    + " and so is not alive.");
        }
        
        //test 2
        isAlive = GameOfLife.isCellAlive(world, 1, 2);
        if ( isAlive) {
            error = true;
            System.out.println("testCellAlive 2: Error, cell "
                    + "should not be alive.");
        }
        
        //test 3
        //set specific cell to living
        world[1][2] = true;
        isAlive = GameOfLife.isCellAlive(world, 1, 2);
        if ( !isAlive) {
            error = true;
            System.out.println("testCellAlive 3: Error, cell "
                    + "should be alive.");
        }
        
        //can you think of other tests that would be useful?
        
        if ( error) {
            System.out.println("testCellAlive: failed");
        } else {
            System.out.println("testCellAlive: passed");
        }
    }
    
    /**
     * This runs some tests on the numNeighborsAlive method.
     * These tests make sure that the method is properly
     * detecting the number of live neighbors a cell has, including
     * whether that cell is within the bounds of the world.
     * 
     */    
    private static void testNumNeighborsAlive() {
        boolean error = false;
        
        boolean [][] world = new boolean[5][7];
        int numAlive;
        
        //test 1
        numAlive = GameOfLife.numNeighborsAlive(world, 10, 10);
        if ( numAlive != 0) {
            error = true;
            System.out.println("testNumNeighborsAlive 1: Error, "
                    + "should be no living cells around 10,10");
        }
        
        //test 2
        numAlive = GameOfLife.numNeighborsAlive(world, 1, 2);
        if ( numAlive != 0) {
            error = true;
            System.out.println("testNumNeighborsAlive 2: Error, "
                    + "should not have any cells alive.");
        }
        
        //test 3
        //set specific cell to living
        world[1][2] = true;
        numAlive = GameOfLife.numNeighborsAlive(world, 1, 2);
        if ( numAlive != 0) {
            error = true;
            System.out.println("testNumNeighborsAlive 3: Error, "
                    + "should have no cells alive.");
        }
        
        //test 4
        //set specific cell to living
        world[1][2] = true;
        numAlive = GameOfLife.numNeighborsAlive(world, 2, 2);
        if ( numAlive != 1) {
            error = true;
            System.out.println("testNumNeighborsAlive 4: Error, "
                    + "should have 1 neighbor alive.");
        }        
        
        //can you think of other tests that would be useful?
        
        
        if ( error) {
            System.out.println("testNumNeighborsAlive: failed");
        } else {
            System.out.println("testNumNeighborsAlive: passed");
        }
    }    
    
    /**
     * This runs some tests on the isCellLivingInNextGeneration method.
     * These tests check to see that the rules of the game are
     * properly applied.
     * 
     */    
    private static void testIsCellLivingInNextGeneration() {
        boolean error = false;
        boolean isAlive;
            
        //test 1: Any live cell with fewer than two live neighbors dies, 
        //   as if caused by under population.
        isAlive = GameOfLife.isCellLivingInNextGeneration( 1, true);
        if ( isAlive) {
            error = true;
            System.out.println("testIsCellLivingInNextGeneration 1: Error, "
                    + "Any live cell with fewer than two live "
                    + "neighbors dies, as if caused by under population.");
        }
        
        //test 2: Any live cell with two or three live neighbors lives on 
        //        to the next generation.
        isAlive = GameOfLife.isCellLivingInNextGeneration( 2, true);
        if ( !isAlive) {
            error = true;
            System.out.println("testIsCellLivingInNextGeneration 2: Error, "
                    + "Any live cell with two or three live neighbors "
                    + "lives on to the next generation.");
        }
        
        //test 3: Any live cell with more than three live neighbors dies, 
        //        as if by overcrowding.
        isAlive = GameOfLife.isCellLivingInNextGeneration( 5, true);
        if ( isAlive) {
            error = true;
            System.out.println("testIsCellLivingInNextGeneration 3: Error, "
                    + "Any live cell with more than three live neighbors "
                    + "dies, as if by overcrowding.");
        }        
        
        //test 4: Any dead cell with exactly three live neighbors becomes a 
        //        live cell, as if by reproduction.
        isAlive = GameOfLife.isCellLivingInNextGeneration(3, false);
        if ( !isAlive){
        	error = true;
        	System.out.println("testISCellLivingInNextGeneration 4: Error,"
        			+ "Any dead cell with exactly three live neighbors"
        			+ "becomes a live cell, as if by reproduction.");
        }

        
        if ( error) {
            System.out.println("testIsCellLivingInNextGeneration: failed");
        } else {
            System.out.println("testIsCellLivingInNextGeneration: passed");
        }
    }    

}
