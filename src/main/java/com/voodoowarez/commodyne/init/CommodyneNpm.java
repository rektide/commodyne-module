package com.voodoowarez.commodyne.init;

import java.util.Arrays;

import org.dynjs.Config;
import org.dynjs.runtime.DynJS;

import com.voodoowarez.commodyne.NpmModuleProvider;
import com.voodoowarez.commodyne.RuntimeInitializer;
import com.voodoowarez.commodyne.init.nodyn.Npm;

public class CommodyneNpm implements RuntimeInitializer {

	static {
		final RuntimeInitializer[] orig = com.voodoowarez.commodyne.init.Nodyn.INITIALIZERS, 
		  inits = Arrays.copyOf(orig, orig.length);
		for(int i = 0; i< orig.length; ++i){
			RuntimeInitializer init = orig[i];
			if(init instanceof Npm){
				inits[i] = new CommodyneNpm();
			}
		}
		INITIALIZERS = inits;
	}

	static public final RuntimeInitializer[] INITIALIZERS;

	public void prepConfig(Config config) {
	}

	public void initialize(DynJS dynjs) {
		new NpmModuleProvider(dynjs.getExecutionContext().getGlobalObject());
	}

}
