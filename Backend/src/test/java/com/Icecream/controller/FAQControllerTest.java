package com.Icecream.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class FAQControllerTest {
	
	@Before
	public void setup() {
		System.out.println("Hello setup");
	}
	@Test
	public void Test() {
		System.out.println("Test1");
	}
	@Test(expected=NullPointerException.class)
	public void Test2() {
		
		List<String> a = null;
		a.get(0);
	}
	@After
	public void cleanup() {
		System.out.println("Hello cleanup");
		
	}
	
}
