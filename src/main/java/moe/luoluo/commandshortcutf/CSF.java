package moe.luoluo.commandshortcutf;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.HashMap;

@Mod(
modid=CSF.MODID, name=CSF.MODNAME,
version=CSF.VERSION, clientSideOnly=true
)
public class CSF {

public static final String MODID = "csf";
public static final String MODNAME = "CommandShortcutF";
public static final String VERSION = "1.0";

private HashMap<String, String> shortcuts = new HashMap();

@EventHandler
public void onPreInit(FMLPreInitializationEvent event) {
//? logger = event.getModLog();
Configuration config = new Configuration(event.getSuggestedConfigurationFile());

config.load();
String[] shortcutList = config.getStringList("List:", "shortcuts", new String[] { "pw-p warp" }, "<shortcut>-<orginal>");
config.save();

for (String shortcutInfo : shortcutList) {
try {
int splitIndex = shortcutInfo.indexOf('-');
				
String shortcutCommand = shortcutInfo.substring(0, splitIndex);
String orginalCommand = shortcutInfo.substring(splitIndex + 1);
shortcuts.put(shortcutCommand, orginalCommand);

} catch (Exception e) {
//	logger.error("Error while loading " + shortcutInfo);
}}
}


@EventHandler
public void onInit(FMLInitializationEvent event) {
	
for (HashMap.Entry<String, String> entry : shortcuts.entrySet()) {
ClientCommandHandler.instance.registerCommand(new CommandF(entry.getKey(), entry.getValue()));
}

}
}