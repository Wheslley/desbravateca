package br.com.sisbrava.managebean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "classe")
public class Classes {

	@PostConstruct
	public void init() {

		System.out.println("@PostConstruct");

	}
}
