package com.pubtools.sercurity.bean;

import java.util.HashSet;
import java.util.Set;


public class Roles {
	private Set<Resources> resources = new HashSet<Resources>();

	public Set<Resources> getResources() {
		return resources;
	}

	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}

}
