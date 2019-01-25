package com.bdqn.hq.entity;

import com.bdqn.hq.anno.Bean;

@Bean("sudent")
public class Student {
	private StudentClass studentClass;

	public StudentClass getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}

	
}
