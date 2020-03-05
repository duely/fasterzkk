package noobanidus.mods.fasterzkk;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import java.util.HashSet;
import java.util.Set;

public class CommandFZKK extends CommandBase {
  @Override
  public String getName() {
    return "fzkk";
  }

  @Override
  public String getUsage(ICommandSender sender) {
    return "/fzkk";
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    int dim = sender.getEntityWorld().provider.getDimension();
    WorldServer world = DimensionManager.getWorld(dim);

    Object2IntOpenHashMap<ResourceLocation> list = new Object2IntOpenHashMap<>();
    list.defaultReturnValue(0);
    world.loadedEntityList.stream().map(EntityList::getKey).filter(e -> e != null && ConfigFZKK.getComputedModIds().contains(e.getNamespace())).forEach(e -> list.put(e, list.getInt(e) + 1));
    Set<ResourceLocation> toCull = new HashSet<>();
    list.forEach((o, i) -> {
      if (i >= 50) {
        toCull.add(o);
      }
    });
    if (!toCull.isEmpty()) {
      int count = 0;
      for (Entity e : world.loadedEntityList) {
        if (!(e instanceof EntityLiving)) {
          continue;
        }

        ResourceLocation k = EntityList.getKey(e);
        if (toCull.contains(k)) {
          EntityLiving living = (EntityLiving) e;
          if (living.isNoDespawnRequired()) {
            continue;
          }
          if (living.hasCustomName()) {
            continue;
          }
          if (living instanceof IEntityOwnable) {
            if (((IEntityOwnable) living).getOwnerId() != null) {
              continue;
            }
          }
          if (living instanceof AbstractHorse) {
            if (((AbstractHorse) living).getOwnerUniqueId() != null) {
              continue;
            }
          }
          e.setDropItemsWhenDead(false);
          e.setDead();
          count++;
        }
      }
      sender.sendMessage(new TextComponentTranslation("fasterzkk.command.killed", count));
    }
  }
}
