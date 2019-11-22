package noobanidus.mods.fasterzkk.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandOcarina extends CommandBase {
  @Override
  public String getName() {
    return "fzkk";
  }

  @Override
  public String getUsage(ICommandSender sender) {
    return "/fzkk | /fzkk force";
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) sender;
      boolean force = false;
      if (args.length == 1 && args[0].equalsIgnoreCase("force")) {
        force = true;
      }

    }
  }
}
