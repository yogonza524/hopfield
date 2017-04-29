/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.core.hopfield.Hopfield;
import com.core.hopfield.Hopfield.Result;
import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author gonza
 */
public class HopfieldTest {
    
    public HopfieldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void point01dot1Test() {
        System.out.println();
         System.out.println("Test point 01.1");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(4)
                 .addPattern(new Double[]{1.0,-1.0,-1.0,1.0})
                 .addPattern(new Double[]{-1.0,1.0,1.0,-1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{-1.0,1.0,1.0,-1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
         
         System.out.println();
         System.out.println("Orthogonality: " + h.orthogonality());
         
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
    
    @Test
    @Ignore
    public void point02dot6Test() {
         System.out.println();
         System.out.println("Test point 02.6");
         
         Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(5)
                 .addPattern(new Double[]{1.0,-1.0,-1.0,-1.0,1.0})
                 .addPattern(new Double[]{-1.0,-1.0,1.0,-1.0,1.0})
                 .addPattern(new Double[]{-1.0,-1.0,-1.0,1.0,1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{-1.0,-1.0,-1.0,-1.0,1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
         
         System.out.println();
         System.out.println("Orthogonality: " + h.orthogonality());
         
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
     }
    
    @Test
    @Ignore
    public void point02dot1Test() {
        System.out.println();
         System.out.println("Test point 02.1");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(5)
                 .addPattern(new Double[]{1.0,-1.0,-1.0,-1.0,-1.0})
                 .addPattern(new Double[]{1.0,1.0,-1.0,1.0,1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{-1.0,-1.0,1.0,-1.0,1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
         
         System.out.println();
         System.out.println("Orthogonality: " + h.orthogonality());
         
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
    
    @Test
    @Ignore
    public void point02dot2Test() {
        System.out.println();
         System.out.println("Test point 02.2");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(5)
                 .addPattern(new Double[]{-1.0,-1.0,-1.0,1.0,1.0})
                 .addPattern(new Double[]{-1.0,1.0,1.0,1.0,-1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{-1.0,-1.0,1.0,-1.0,1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
         
         System.out.println();
         System.out.println("Orthogonality: " + h.orthogonality());
         
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
    
    @Test
    @Ignore
    public void point02dot3Test() {
        System.out.println();
         System.out.println("Test point 02.3");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(5)
                 .addPattern(new Double[]{1.0,-1.0,-1.0,-1.0,1.0})
                 .addPattern(new Double[]{-1.0,-1.0,1.0,-1.0,1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{-1.0,-1.0,-1.0,-1.0,-1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
         
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
    
    @Test
    @Ignore
    public void point02dot4Test() {
        System.out.println();
         System.out.println("Test point 02.4");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(4)
                 .addPattern(new Double[]{1.0,-1.0,-1.0,-1.0})
                 .addPattern(new Double[]{1.0,1.0,-1.0,-1.0})
                 .addPattern(new Double[]{1.0,1.0,1.0,1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{-1.0,1.0,-1.0,1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
         
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
     
    @Test
    @Ignore
    public void point02dot5Test() {
         System.out.println();
         System.out.println("Test point 02.5");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(5)
                 .addPattern(new Double[]{1.0,1.0,-1.0,-1.0,1.0})
                 .addPattern(new Double[]{1.0,1.0,1.0,-1.0,-1.0})
                 .addPattern(new Double[]{1.0,1.0,-1.0,1.0,1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{1.0,-1.0,1.0,1.0,1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
        
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
    
    @Test
//    @Ignore
    public void example() {
         System.out.println();
         System.out.println("Example");
        
        Hopfield h = new Hopfield.HopfieldBuilder()
                 .dimension(9)
                 .addPattern(new Double[]{1.0, -1.0,1.0,-1.0,1.0,-1.0,1.0,-1.0,1.0})
                 .addPattern(new Double[]{1.0, 1.0,1.0,-1.0,1.0,-1.0,-1.0,1.0,-1.0})
                 .build();
         h.train();
         for (int i = 0; i < h.getW().length; i++) {
             System.out.println(ArrayUtils.toString(h.getW()[i]));
         }
         
         Result r = h.test(new Double[]{1.0, 1.0,-1.0,-1.0,1.0,-1.0,-1.0,1.0,-1.0});
         
         System.out.println("");
         System.out.println(ArrayUtils.toString(r.getPattern()));
         
         System.out.println();
         System.out.println("History stack for loop find process");
         
         for(Double[] p : r.getHistory()){
             System.out.println(ArrayUtils.toString(p));
         }
        
         System.out.println();
         
         System.out.println("Good capacity storage: " + h.goodCapacityStorage());
         System.out.println("Perfect capacity storage: " + h.perfectCapacityStorage());
    }
}