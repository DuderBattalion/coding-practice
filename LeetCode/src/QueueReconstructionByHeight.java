import java.util.Collections;
import java.util.LinkedList;

public class QueueReconstructionByHeight {
    // Algorithm
    // Sort people by height descending, break ties in ascending order of k value
    // Read people - maybe use LinkedList to place people in correct indices.
    // Correct index is related to the k-value.
    public int[][] reconstructQueue(int[][] people) {
        LinkedList<Person> peopleList = createPeopleList(people);
        Collections.sort(peopleList);

        LinkedList<Person> queue = new LinkedList<>();
        for (Person person: peopleList) {
            queue.add(person.kVal, person);
        }

        return createQueueArray(queue);
    }

    private class Person implements Comparable<Person> {
        public int height;
        public int kVal;

        public Person(int height, int kVal) {
            this.height = height;
            this.kVal = kVal;
        }

        @Override
        public int compareTo(Person other) {
            if (this.height == other.height) {
                return this.kVal - other.kVal; // Ascending on kVal
            }

            return (this.height - other.height) * -1; // Descending on height
        }
    }

    private LinkedList<Person> createPeopleList(int[][] people) {
        LinkedList<Person> peopleList = new LinkedList<Person>();

        for(int i = 0; i < people.length; i++) {
            int height = people[i][0];
            int kVal = people[i][1];
            Person person = new Person(height, kVal);

            peopleList.add(person);
        }

        return peopleList;
    }

    private int[][] createQueueArray(LinkedList<Person> queue) {
        int[][] queueArray = new int[queue.size()][2];

        for (int i = 0; i < queue.size(); i++) {
            Person person = queue.get(i);
            queueArray[i][0] = person.height;
            queueArray[i][1] = person.kVal;
        }

        return queueArray;
    }
}
