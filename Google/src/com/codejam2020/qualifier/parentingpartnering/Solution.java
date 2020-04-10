package com.codejam2020.qualifier.parentingpartnering;

import com.util.HelperMethods;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
  static class TestCase {
    public List<String> schedules;

    public TestCase() {
      schedules = new ArrayList<>();
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

    String[] minuteMap = new String[24 * 60];
    int count = 0;
    for (TestCase testCase: testCases) {
      result.append("Case #");
      result.append(count + 1);
      result.append(": ");
      count++;

      String resultStr;
      try {
        resultStr = findPersonsForIntervals(minuteMap, testCase.schedules);
      } catch (TooManyPeopleException e) {
        resultStr = ("IMPOSSIBLE");
      }

      result.append(resultStr);
      System.out.println(result.toString());

      result.setLength(0);
      clearMinuteMap(minuteMap);
    }
  }

  private static String findPersonsForIntervals(String[] map, List<String> schedules) throws TooManyPeopleException {
    StringBuilder result = new StringBuilder();
    for (String schedule: schedules) {
      String[] times = schedule.split(" ");
      int startMin = Integer.parseInt(times[0]);
      int endMin = Integer.parseInt(times[1]);

      String person = findPersonForInterval(map,startMin, endMin);
      updateMinuteMap(map, startMin, endMin, person);

      result.append(person);
    }

    return result.toString();
  }

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
        String schedule = in.nextLine();
        testCase.schedules.add(schedule);
      }

      testCases.add(testCase);
    }

    return testCases;
  }
}
