package com.bdqn.hq.test;

import com.bdqn.hq.entity.Module;
import com.bdqn.hq.entity.Student;
import com.bdqn.hq.util.BeanFactory;

public class Test {
	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory("com/bdqn/hq/entity");
		
		Student student = (Student)factory.getBean("student");
		System.out.println(student);
		System.out.println(student.getStudentClass());
		
		Module module = (Module)factory.getBean("moudle");
		System.out.println(module);
		System.out.println(module.getProject());
	}

}
