package com.bdqn.hq.entity;

import com.bdqn.hq.anno.Bean;

@Bean("module")
public class Module {
	// ģ�������ĸ�����
		private Project project;

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

}
