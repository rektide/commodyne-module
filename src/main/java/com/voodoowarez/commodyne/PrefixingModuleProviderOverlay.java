package com.voodoowarez.commodyne;

import java.util.Arrays;
import java.util.List;

import org.dynjs.runtime.DynJS;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.modules.ModuleProvider;

public class PrefixingModuleProviderOverlay extends ModuleProvider{

	protected ModuleProvider underlying;
	protected List<String> prefixes = Arrays.asList("");

	public PrefixingModuleProviderOverlay(ModuleProvider underlying, List<String> prefixes){
		this.underlying = underlying;
		if(prefixes != null && prefixes.size() > 0)
			this.prefixes.addAll(prefixes);
	}

	public void addPrefix(String prefix){
		this.prefixes.add(prefix);
	}

	@Override
	public boolean load(DynJS runtime, ExecutionContext context, String moduleID) {
		for(String prefix : prefixes){
			if(this.underlying.load(runtime, context, prefix+moduleID))
				return true;
		}
		return false;
	}

	@Override
	public String generateModuleID(ExecutionContext context, String moduleName) {
		for(String prefix : prefixes){
			String result = generateModuleID(context, prefix+moduleName);
			if(result != null)
				return result;
		}
		return null;
	}
}
