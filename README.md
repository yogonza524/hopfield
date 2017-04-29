# Hopfield Network

```java
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
```
