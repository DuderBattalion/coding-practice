public class Driver {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(2);
//        medianFinder.addNum(4);
//        medianFinder.addNum(3);
//        medianFinder.addNum(1);
//        medianFinder.addNum(5);

        System.out.println(medianFinder.findMedian());
    }
}
