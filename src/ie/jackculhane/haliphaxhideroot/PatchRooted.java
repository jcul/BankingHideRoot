package ie.jackculhane.haliphaxhideroot;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedBridge;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class PatchRooted implements IXposedHookLoadPackage {
	
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable
	{
		// com/cryptomathic/securecore/TrusteerMalwareDetector
		
	    if (lpparam.packageName.equals("com.cryptomathic.securecore") || lpparam.packageName.equals("com.grppl.android.shell.halifax"))
	    {
	    
	    	XposedBridge.log("Loaded " + lpparam.packageName + " replacing isRooted() method");
	    	findAndHookMethod("com.cryptomathic.securecore.TrusteerMalwareDetector", lpparam.classLoader, "isRooted", XC_MethodReplacement.returnConstant(false));
	    }
	}
}