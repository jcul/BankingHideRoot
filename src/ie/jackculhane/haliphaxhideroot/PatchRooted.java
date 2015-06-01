package ie.jackculhane.haliphaxhideroot;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedBridge;

public class PatchRooted implements IXposedHookLoadPackage {
    
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable
    {
        if (
            lpparam.packageName.equals("com.cryptomathic.securecore") ||
            lpparam.packageName.equals("com.grppl.android.shell.halifax") ||
            lpparam.packageName.equals("com.grppl.android.shell.CMBlloydsTSB73") ||
            lpparam.packageName.equals("com.grppl.android.shell.BOS") ||
            lpparam.packageName.equals("uk.co.tsb.mobilebank")
        )
        {    
            XposedBridge.log("Loaded " + lpparam.packageName + ", hooking root methods");
            
            try
            {
                findAndHookMethod("com.cryptomathic.securecore.MalwareDetection", lpparam.classLoader, "getIsPossiblyRooted", XC_MethodReplacement.returnConstant(false));
                findAndHookMethod("com.cryptomathic.securecore.MalwareDetection", lpparam.classLoader, "getIsRooted", XC_MethodReplacement.returnConstant(false));
            }
            catch (NoSuchMethodError e)
            {
                XposedBridge.log("Unable to hook new methods, will attempt to patch old isRooted method (" + e.toString() + ")");
                findAndHookMethod("com.cryptomathic.securecore.TrusteerMalwareDetector", lpparam.classLoader, "isRooted", XC_MethodReplacement.returnConstant(false));
            }
        }        
    }
}
