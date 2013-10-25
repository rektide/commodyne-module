package com.voodoowarez.commodyne.init;

import org.dynjs.Config;
import org.dynjs.runtime.DynJS;

import com.voodoowarez.commodyne.NpmModuleProvider;
import com.voodoowarez.commodyne.RuntimeInitializer;
import com.voodoowarez.commodyne.init.nodyn.Basics;
import com.voodoowarez.commodyne.init.nodyn.LastJsJs;

public class CommodyneNpm implements RuntimeInitializer {

	static public final RuntimeInitializer[] COMMODYNE_NPM = new RuntimeInitializer[] { new Basics(), new CommodyneNpm(), new LastJsJs() };

	public void prepConfig(Config config) {
	}

	public void initialize(DynJS dynjs, Config config) {
		new NpmModuleProvider(dynjs.getExecutionContext().getGlobalObject());
	}

}
