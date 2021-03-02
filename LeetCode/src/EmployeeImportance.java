import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeImportance {
    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    // Algorithm:
    // Populate importance structure in one pass
    // In second pass, do DFS to add up importance
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> importanceMap = new HashMap<>();
        Employee mainEmployee = null;
        for (Employee employee: employees) {
            importanceMap.put(employee.id, employee);

            if (employee.id == id) {
                mainEmployee = employee;
            }
        }

        return getTotalImportance(mainEmployee, importanceMap, new HashMap<>());
    }

    private static int getTotalImportance(Employee employee, Map<Integer, Employee> importanceMap,
                                          Map<Integer, Integer> totalImportanceMap) {
        int id = employee.id;
        if (totalImportanceMap.containsKey(id)) {
            return totalImportanceMap.get(id);
        }

        if (!importanceMap.containsKey(id)) {
            throw new RuntimeException("Error: Invalid id"); // Basic error handling
        }

        int totalImportance = employee.importance;
        for (int subordinateId: employee.subordinates) {
            Employee subordinate = importanceMap.get(subordinateId);
            totalImportance += getTotalImportance(subordinate, importanceMap, totalImportanceMap);
        }

        totalImportanceMap.put(id, totalImportance);
        return totalImportance;
    }
}
