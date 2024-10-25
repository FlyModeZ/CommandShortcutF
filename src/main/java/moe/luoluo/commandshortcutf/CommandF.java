package moe.luoluo.commandshortcutf;

import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.*;
import java.util.*;

import net.minecraftforge.client.ClientCommandHandler;

public class CommandF implements ICommand {

private Minecraft minecraft = Minecraft.getMinecraft();
private String shortcutCommand = new String();
private String orginalCommand = new String();

public CommandF(String shortcutCommand, String orginalCommand) {
this.shortcutCommand = shortcutCommand;
this.orginalCommand = orginalCommand;
}

@Override
public String getCommandName() {
return shortcutCommand;
}

@Override
public String getCommandUsage(ICommandSender sender) {
return null;
}

@Override
public List<String> getCommandAliases() {
return Collections.emptyList();
}

@Override
public void processCommand(ICommandSender sender, String[] args){
String cmd = "/" + orginalCommand + " " + String.join(" ", args);

int result = ClientCommandHandler.instance.executeCommand(minecraft.thePlayer, cmd);

if (result != 1) { //客户端没这个命令，发给服务端
minecraft.thePlayer.sendChatMessage(cmd);
}

}

@Override
public boolean canCommandSenderUseCommand(ICommandSender sender) {
return true;
}

@Override
public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
return null;
}

@Override
public boolean isUsernameIndex(String[] args, int index) {
return false;
}

@Override
public int compareTo(ICommand o) {
return 0;
}

}