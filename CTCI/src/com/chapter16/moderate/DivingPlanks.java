// package com.chapter16.moderate;
//
// import java.util.HashSet;
// import java.util.Stack;
//
// public class DivingPlanks {
//
//   class Plank {
//     public int length;
//
//     Plank(int length) {
//       this.length = length;
//     }
//   }
//
//   class Results {
//     Stack<Plank> planks;
//
//     public Results() {
//       planks = new Stack<>();
//     }
//
//     // Can be optimized
//     public int getTotalPlankLength() {
//       int totalLength = 0;
//       for (Plank plank: planks) {
//         totalLength += plank.length;
//       }
//
//       return totalLength;
//     }
//   }
//
//   private Plank getShortPlank() {
//     return new Plank(2);
//   }
//
//   private Plank getLongPlank() {
//
//   }
//
// /*
// private Plank getPlank(PlankType long/short) {
// // TODO - implement
// }
// */
//
//   void addPlank(Results results, Plank plank) {
//     if (plank == null) {
//       return;
//     }
//
//     results.planks.push(plank);
//   }
//
//   Plank removeLastPlank(Results results) {
//     if (results == null) {
//       return null;
//     }
//
//     return results.planks.pop();
//   }
//
//   public void generateAllPlanks(int targetLength, int maxPlanks, Results planks) {
//     if (targetLength == planks.getTotalPlankLength()) {
//       printResult(planks);
//       return;
//     } else if (targetLength < planks.getTotalPlankLength()) {
//       return;
//     }
//
//     Plank shortPlank = getShortPlank();
//     addPlank(planks, shortPlank);
//
//     generateAllPlanks(targetLength, maxPlanks - 1, planks);
//
//     removeLastPlank(planks);
//
//     Plank longPlank = getShortPlank();
//     addPlank(planks, longPlank);
//     maxPlanks--;
//
//     generateAllPlanks(targetLength, maxPlanks - 1, planks);
//   }
//
// }
