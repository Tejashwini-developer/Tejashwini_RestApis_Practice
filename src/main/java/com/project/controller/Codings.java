package com.project.controller;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Codings {

	public static void main(String[] args) {
		System.out.println("Hi");

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, 10, "scotland", "shankar"));
		empList.add(new Employee(4, 15, "america", "sarojini"));
		empList.add(new Employee(8, 13, "switzerland", "teju"));
		empList.add(new Employee(9, 13, "switzerland", "tejashwini"));
		empList.add(new Employee(3, 05, "norway", "xyz"));
		empList.add(new Employee(10, 05, "spain", "xyzxyz"));

		// 1. sort in natural ascending order
		List<Employee> ascSorted = empList.stream().sorted().collect(Collectors.toList());
		System.out.println("ascSorted -" + ascSorted);

		// 2. sort in descending order using lambda exression , not specifyig any fields
		// for sorting, so it will sort based on comparable id field which is defined in
		// Employee class
		List<Employee> descSorted = empList.stream().sorted((s1, s2) -> s2.compareTo(s1)).collect(Collectors.toList());
		System.out.println("descSorted -" + descSorted);

		// 3. desc based on names lambda expression
		List<Employee> descSortedByName = empList.stream().sorted((s1, s2) -> s2.getName().compareTo(s1.getName()))
				.collect(Collectors.toList());
		System.out.println("descSortedByName -" + descSortedByName);

		// 4. desc based on id lambda expression
		List<Employee> descSortedById = empList.stream().sorted((s1, s2) -> s2.getId() - s1.getId())
				.collect(Collectors.toList());
		System.out.println("descSortedById -" + descSortedById);

		// 5.sort in desc/reverse order, here don't specify any particular field , it
		// will sort based
		// on comparable defined in Employee class which is id.
		List<Employee> descSortedById1 = empList.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		System.out.println("descSortedById1 -" + descSortedById1);

		// 7. sort based on employee name ascending order
		List<Employee> descSortedByName1 = empList.stream().sorted(Comparator.comparing(Employee::getName))
				.collect(Collectors.toList());
		System.out.println("descSortedByName1 -" + descSortedByName1);

		// 8. sort employee list based on id in desc order
		List<Employee> descSortedById2 = empList.stream().sorted(Comparator.comparing(Employee::getId).reversed())
				.collect(Collectors.toList());
		System.out.println("descSortedById2 -" + descSortedById2);

		// 9. select 2nd highest employee id only.
		Optional<Employee> secondHighestEmpId = empList.stream()
				.sorted(Comparator.comparing(Employee::getId).reversed()).distinct().skip(1).findFirst();
		System.out.println("secondHighestEmpId -" + secondHighestEmpId);

		// 10. select highest 2 employee ids
		List<Employee> highest2EmpIds = empList.stream().sorted(Comparator.comparing(Employee::getId).reversed())
				.distinct().limit(2).collect(Collectors.toList());
		System.out.println("highest2EmpIds -" + highest2EmpIds);

		// 11. maximum and minimum emp id employee
		Employee maxIdEmp = empList.stream().max(Comparator.comparing(Employee::getId)).get();
		Employee minIdEmp = empList.stream().min(Comparator.comparing(Employee::getId)).get();

		System.out.println("maxIdEmp -" + maxIdEmp);
		System.out.println("minIdEmp -" + minIdEmp);

		// 12. Employees working in each dept /group by dept
		Map<Integer, List<Employee>> groupByDept = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.toList()));
		System.out.println("groupByDept -" + groupByDept);

		// 13. count of employess working in same dept
		Map<Integer, Long> groupByDeptCount = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
		System.out.println("groupByDeptCount -" + groupByDeptCount);

		// 14. Find Maximum empId from each department
		Map<Integer, Optional<Employee>> groupByDeptAndMaxEmpId = empList.stream().collect(
				Collectors.groupingBy(Employee::getDeptId, Collectors.maxBy(Comparator.comparing(Employee::getId))));
		System.out.println("groupByDeptAndMaxEmpId -" + groupByDeptAndMaxEmpId);

		// 15. convert string/employee name to upper case and join them by comma or
		// hypen
		String uppercasenames = empList.stream().map(emp -> emp.getName().toUpperCase())
				.collect(Collectors.joining("-"));
		System.out.println("uppercasenames -" + uppercasenames);

		// 16. Duplicate elements from list using Frequency(duplicate dept id)
		List<Integer> deptIdList = empList.stream().map(emp -> emp.getDeptId()).collect(Collectors.toList());
		Set<Integer> duplicateId = deptIdList.stream().filter(id -> Collections.frequency(deptIdList, id) > 1)
				.collect(Collectors.toSet());
		System.out.println("duplicateId -" + duplicateId);

		// 17. find employess who's address is switzerland
		List<Employee> addressIsSwitzerland = empList.stream().filter(emp -> "switzerland".equals(emp.getAddress()))
				.collect(Collectors.toList());
		System.out.println("addressIsSwitzerland -" + addressIsSwitzerland);

		// 18. Find duplicate elements in a stream using Set
		Set<Integer> uniqueIdList = new HashSet<>();
		Set<Integer> duplicateIdUsingSet = deptIdList.stream().filter(id -> !uniqueIdList.add(id))
				.collect(Collectors.toSet());
		System.out.println("duplicateIdUsingSet -" + duplicateIdUsingSet);

		// 19. Find duplicate elements in a stream using Map
		Map<Integer, Long> mapOfCount = deptIdList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		List<Integer> duplicateIdUsingMap = mapOfCount.entrySet().stream().filter(entry -> entry.getValue() > 1)
				.map(entry -> entry.getKey()).collect(Collectors.toList());
		System.out.println("duplicateIdUsingMap -" + duplicateIdUsingMap);

		// 20. Find duplicate characters from the given String (using map)
		String str = "Tejashwinih";
		Map<Character, Long> characterCountMap = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		List<Character> duplicateCharUsingMap = characterCountMap.entrySet().stream()
				.filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).collect(Collectors.toList());
		System.out.println("duplicateCharUsingMap -" + duplicateCharUsingMap);

		// 21. Find duplicate characters from the given String (using frequency)
		List<Character> charList = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Set<Character> duplicateCharUsingFrequency = charList.stream()
				.filter(ch -> Collections.frequency(charList, ch) > 1).collect(Collectors.toSet());
		System.out.println("duplicateCharUsingFrequency -" + duplicateCharUsingFrequency);

		// 22. sort charcaters from the given string
		String s1 = "tejashwini";
		List<Character> listChars = s1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.sort(listChars);
		System.out.println("Sorted listChars -" + listChars);

		// 23. sort charcaters from the given string reverse/desc order
		String s2 = "tejashwini";
		List<Character> listChs = s2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		List<Character> reversedListChs = listChs.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		System.out.println("reversedListChs -" + reversedListChs);

		// 24. sort strings in reverse order and join them using hypen
		List<String> strList = Arrays.asList("gfh", "aaa", "zxy", "zdc", "bbb");
		List<String> ReversedStrList = strList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println("ReversedStrList -" + ReversedStrList);

		// 25. Find last element from the integer array
		Integer[] arr = { 1, 2, 3, 4, 5, 2, 10 };
		List<Integer> lst = Arrays.asList(arr);
		Integer lastEle = lst.stream().skip(lst.size() - 1).findFirst().get();
		System.out.println("lastEle -" + lastEle);

		// 26. Find max and min from Integer list
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Integer max = list.stream().max(Integer::compare).get();
		Integer min = list.stream().min(Integer::compare).get();
		System.out.println("max - " + max + "  min - " + min);

		// 27. Find max and min from primitive int array
		int[] array = { 1, 2, 3, 4, 5 };
		int maximum = Arrays.stream(array).max().getAsInt();
		int minimum = Arrays.stream(array).min().getAsInt();
		System.out.println("maximum - " + maximum + "  minimum - " + minimum);

		// 28. IntSummaryStatistics max, min, average, count, sum using List
		// List<Integer> list = Arrays.asList(1,2,3,4,5);
		IntSummaryStatistics statistics = list.stream().mapToInt(Integer::intValue).summaryStatistics();
		System.out.println(
				"max - " + statistics.getMax() + "  min - " + statistics.getMin() + "  sum - " + statistics.getSum()
						+ "  count - " + statistics.getCount() + "  average - " + statistics.getAverage());

		// 29. IntSummaryStatistics max, min, average, count, sum using primitive array
		// int[] array = {1,2,3,4,5};
		IntSummaryStatistics statistics1 = Arrays.stream(array).summaryStatistics();
		System.out.println(
				"max - " + statistics1.getMax() + "  min - " + statistics1.getMin() + "  sum - " + statistics1.getSum()
						+ "  count - " + statistics1.getCount() + "  average - " + statistics1.getAverage());

		// 30. minimum elements required to give sum as given target
		int target = 6;
		int[] aray = { 2, 1, 4, 3, 5 };
		Arrays.sort(aray);

		System.out.println("sorted aray -" + Arrays.toString(aray));
		int count = 0;
		for (int i = aray.length - 1; i >= 0; i--) {
			if (target >= aray[i]) {
				target = target - aray[i];
				count++;
				System.out.println("element = " + aray[i]);
			}
			if (target == 0)
				break;
		}
		if (target == 0)
			System.out.println("Minimum elemnts required = " + count);
		else
			System.out.println("Minimum elemnts required = -1");

		// 31. Grouping array elements -ve on left & +ve on right using sort way
		Integer[] ar = { 4, -8, -1, 0, -3, -2, 5, 1 };
		Arrays.sort(ar, (a, b) -> {
			if (a < 0 && b >= 0)
				return -1;
			if (a >= 0 && b < 0)
				return 1;
			return 0;
		});
		System.out.println("groupped array - " + Arrays.toString(ar));

		// 32. Grouping array elements -ve on left & +ve on right without sort
		int[] arrry = { 4, -8, -1, 0, -3, -2, 5, 1 };
		int left = 0;
		int right = arrry.length - 1;
		while (left < right) {
			if (arrry[left] < 0)
				left++;
			else if (arrry[right] >= 0)
				right--;
			else {
				int tmp = arrry[left];
				arrry[left] = arrry[right];
				arrry[right] = tmp;
				left++;
				right--;
			}
		}
		System.out.println("groupped array without sort way - " + Arrays.toString(arrry));

		// 34. Reverse the array in place
		int[] ary = { 4, -8, -1, 0, -3, -2, 5, 1 };
		int l = 0;
		int r = ary.length - 1;
		while (l < r) {
			int temp = ary[l];
			ary[l] = ary[r];
			ary[r] = temp;
			l++;
			r--;
		}
		System.out.println("In place Reversed array - " + Arrays.toString(ary));

		// 35. Find first non repeating character in a string
		String s = "swLkkkMss"; // output = w
		int repeat = 0;
		for (int i = 0; i < s.length(); i++) {
			repeat = 0;
			for (int j = 0; j < s.length(); j++) {
				if (i != j && s.charAt(i) == s.charAt(j)) {
					repeat = 1;
					break;
				}
			}
			if (repeat == 0) {
				System.out.println("first non repeating character is - " + s.charAt(i));
				break;
			}
		}

		// 36. find Top 2 students per class by marks.
		List<Student> students = Arrays.asList(new Student(2, "Aarav", "Class-10", 88),
				new Student(5, "Isha", "Class-10", 93), new Student(1, "Neel", "Class-10", 76),
				new Student(6, "Riya", "Class-9", 91), new Student(7, "Kabir", "Class-9", 84),
				new Student(10, "Tanvi", "Class-9", 97), new Student(12, "Arjun", "Class-8", 62),
				new Student(14, "Sara", "Class-8", 75), new Student(13, "Vikram", "Class-8", 75) // same marks as Sara
		);

		Map<String, List<Student>> top2StudentsFromEachClass = students.stream()
				.collect(Collectors.groupingBy(Student::getClassName,
						Collectors.collectingAndThen(Collectors.toList(),
								group -> group.stream().sorted(Comparator.comparing(Student::getMarks).reversed())
										.limit(2).collect(Collectors.toList()))));

		System.out.println("top2StudentsFromEachDept - " + top2StudentsFromEachClass);

		// 37. Identify the student with the second highest mark in the class.
		Map<String, List<Student>> secondHighestFromEachClass = students.stream()
				.collect(Collectors.groupingBy(Student::getClassName,
						Collectors.collectingAndThen(Collectors.toList(),
								group1 -> group1.stream().sorted(Comparator.comparing(Student::getMarks).reversed())
										.skip(1).limit(1).collect(Collectors.toList())))); // .skip(1).findFirst().get())));
		System.out.println("secondHighestFromEachClass - " + secondHighestFromEachClass);

		
		
		
		/*
		 * select * from Employee order by salary desc offset 2 rows Fetch next 1 row
		 * only;
		 * 
		 * select MAX(salary) from Employee where salary NOT IN (select MAX(salary) from
		 * Employee);
		 * 
		 * select deptId,count(1) from Department group by deptId;
		 * 
		 */

	}

}
