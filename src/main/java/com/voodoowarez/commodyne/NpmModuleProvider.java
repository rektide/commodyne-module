package com.voodoowarez.commodyne;

import org.apache.commons.vfs2.FileSystemException;
import org.dynjs.Config;
import org.dynjs.runtime.DynJS;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.GlobalObject;
import org.dynjs.runtime.builtins.Require;
import org.dynjs.runtime.modules.ModuleProvider;
import org.dynjs.runtime.wrapper.JavascriptFunction;
import org.vertx.java.core.Vertx;

import com.voodoowarez.commodyne.init.dynjs.JVertx;
import com.voodoowarez.commodyne.modules.CommonsVfsModuleProvider;

public class NpmModuleProvider extends ModuleProvider {

	//protected List<ModuleProvider> underlying;
	protected Require require;
	protected DynJS dynJs; // internal instance for node-resolve

	public NpmModuleProvider(GlobalObject globalObject) {
		super();
		//this.underlying = underlying;

		final Config config = new Config();
		final Vertx vertx = (Vertx) globalObject.get("__jvertx");
		this.dynJs = new DynJS(config);
		Runner.initialize(this.dynJs, new RuntimeInitializer[]{ new JVertx(vertx) });
		final Require internalRequire = (Require) dynJs.getExecutionContext().getGlobalObject().get("require");
		try {
			final ModuleProvider internal = new CommonsVfsModuleProvider();
			internalRequire.addModuleProvider(internal);
		} catch (FileSystemException ex) {
			throw new RuntimeException(ex);
		}
		final JavascriptFunction req = (JavascriptFunction) internalRequire.call(this.dynJs.getExecutionContext(), null, "sync");

		this.require = (Require) globalObject.get("require");
		this.require.addModuleProvider(this);
	}

	public boolean load(DynJS runtime, ExecutionContext context, String moduleID){
	   return false;
	}

	@Override
	public String generateModuleID(ExecutionContext context, String moduleName) {
		//String id = this.underlying.generateModuleID(context, moduleName);
		//return id;
		return null;
	};
}
