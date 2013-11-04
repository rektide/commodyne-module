package com.voodoowarez.commodyne;

import org.dynjs.runtime.DynJS;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.voodoowarez.commodyne.init.CommodyneNpm;


public class NpmModuleProviderTest {

	private DynJS runtime;

	@BeforeSuite
	public void createRuntime() {
		this.runtime = new Runner(CommodyneNpm.INITIALIZERS, null, null).makeRuntime();
	}

	@Test
	public void testInitialization() {
		Object result = this.runtime.evaluate("2+2");
		Assert.assertEquals(result, 4l);
	}

	@Test
	public void test1() {
		Object result = this.runtime.evaluate("require('./lib/test-1.js')");
		Assert.assertEquals(result, "hello from commodyne");
	}

	@Test
	public void test2() {
		Object result = this.runtime.evaluate("require('./lib/test-2.js')");
		Assert.assertEquals(result, "hello from commodyne");
	}
}
