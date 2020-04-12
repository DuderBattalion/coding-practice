package com.codejam2020.qualifier.parentingpartnering;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Algorithm
 * Create an array representing each minute in day (24 hours * 60 mins = 1440 minutes).
 * Sort schedules in ascending order.
 * For each schedule, try assigning a person in the minute array for all minutes in the interval.
 * "C" for Cameron, "J" for Jamie, and "CJ" if both Cameron and Jamie are busy in that minute.
 * If we have to assign a minute with a person that already has "CJ" assigned, the test case becomes IMPOSSIBLE.
 *
 * Note: We can further optimize this solution by removing the extra minute array reducing both time and
 * space complexity, but this works well within the computing constraints specified for the Google Code Jam problem.
 */
public class Solution {

  static class Schedule {
    public int id;
    public int startTime;
    public int endTime;
    public String person;

    public Schedule(int id, int start, int end) {
      this.id = id;
      this.startTime = start;
      this.endTime = end;
      this.person = "";
    }

    public int getStartTime() {
      return startTime;
    }

    public int getId() {
      return id;
    }

    @Override
    public String toString() {
      return "Schedule{" +
          "startTime=" + startTime +
          ", endTime=" + endTime +
          '}';
    }
  }

  static class TestCase {
    public List<Schedule> schedules;

    public TestCase() {
      schedules = new ArrayList<>();
    }

    public void sortSchedulesByStartTime() {
      this.schedules.sort(Comparator.comparing(Schedule::getStartTime));
    }

    public void sortSchedulesById() {
      this.schedules.sort(Comparator.comparing(Schedule::getId));
    }
  }

  public static class TooManyPeopleException extends Exception {
    public TooManyPeopleException(String errMsg) {
      super(errMsg);
    }
  }

  public static void main(String[] args) {
    Solution app = new Solution();
    StringBuilder result = new StringBuilder();
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int numTestCases = in.nextInt();
    in.nextLine();

    List<TestCase> testCases = parseTestCases(app, in, numTestCases);

    // An array representing all minutes in a day
    String[] minuteMap = new String[24 * 60];
    int count = 0;
    for (TestCase testCase: testCases) {
      result.append("Case #");
      result.append(count + 1);
      result.append(": ");
      count++;

      String resultStr;
      try {
        resultStr = findPersonsForIntervals(minuteMap, testCase);
      } catch (TooManyPeopleException e) {
        resultStr = ("IMPOSSIBLE");
      }

      result.append(resultStr);
      System.out.println(result.toString());

      result.setLength(0);
      clearMinuteMap(minuteMap);
    }
  }

  /**
   * Assigns persons "C" (Cameron) or "J" (Jamie) for a list of time intervals.
   * @throws TooManyPeopleException If both Cameron and Jamie are already busy during an interval
   */
  private static String findPersonsForIntervals(String[] map, TestCase testCase) throws TooManyPeopleException {
    // Sorting so we assign schedules in ASC order
    testCase.sortSchedulesByStartTime();

    for (Schedule schedule: testCase.schedules) {
      String person = findPersonForInterval(map, schedule.startTime, schedule.endTime);
      updateMinuteMap(map, schedule.startTime, schedule.endTime, person);
      schedule.person = person;
    }

    // Unsort the schedules, so we output assignments in the correct order
    testCase.sortSchedulesById();

    StringBuilder result = new StringBuilder();
    for (Schedule schedule: testCase.schedules) {
      result.append(schedule.person);
    }

    return result.toString();
  }

  /**
   * Find a suitable person (Cameron or Jamie) for a given interval
   * @throws TooManyPeopleException If both Cameron and Jamie are already busy during an interval
   */
  private static String findPersonForInterval(String[] map, int startMin, int endMin) throws TooManyPeopleException {
    String result;
    String people = getPeopleInInterval(map, startMin, endMin);
    switch (people) {
      case "CJ":
        throw new TooManyPeopleException("Both C and J are busy. Activating panic mode.");
      case "C":
        result = "J";
        break;
      case "J":
        result = "C";
        break;
      default:
        result = "C"; // switch with getRandomPerson() for a better algo

        break;
    }

    return result;
  }

  /**
   * Checks if a person is already busy within a time interval
   * @return Returns Cameron, Jamie or Both (C, J or CJ)
   */
  private static String getPeopleInInterval(String[] map, int startMin, int endMin) {
    boolean isC = false;
    boolean isJ = false;
    for (int i = startMin; i < endMin; i++) {
      String person = map[i];
      if (person != null) {
        switch (person) {
          case "C":
            isC = true;
            break;
          case "J":
            isJ = true;
            break;
          case "CJ":
            isC = true;
            isJ = true;
            break;
        }

        if (isC && isJ) {
          break;
        }
      }
    }

    String result = "";
    if (isC && isJ) {
      result = "CJ";
    } else if (isC) {
      result = "C";
    } else if (isJ) {
      result = "J";
    }

    return result;
  }

  private static String getRandomPerson() {
    String person;
    int rand = (int)(Math.random() * 100);
    if (rand % 2 == 0) {
      person = "C";
    } else {
      person = "J";
    }

    return person;
  }

  private static void updateMinuteMap(String[] map, int startMin, int endMin, String person) {
    for (int i = startMin; i < endMin; i++) {
      String currPerson = map[i];
      if (currPerson == null || currPerson.isEmpty()) {
        map[i] = person;
      } else {
        map[i] = "CJ";
      }
    }
  }

  private static void clearMinuteMap(String[] map) {
    Arrays.fill(map, null);
  }

  private static List<TestCase> parseTestCases(Solution app, Scanner in, int numTestCases) {
    TestCase testCase;
    List<TestCase> testCases = new ArrayList<>();
    for (int i = 0; i < numTestCases; i++) {
      int numSchedules = Integer.parseInt(in.nextLine());
      testCase = new TestCase();
      for (int j = 0; j < numSchedules; j++) {
        String scheduleStr = in.nextLine();
        String[] scheduleTokens = scheduleStr.split(" ");
        int startTime = Integer.parseInt(scheduleTokens[0]);
        int endTime = Integer.parseInt(scheduleTokens[1]);

        testCase.schedules.add(new Schedule(j, startTime, endTime));
      }

      testCases.add(testCase);
    }

    return testCases;
  }
}
