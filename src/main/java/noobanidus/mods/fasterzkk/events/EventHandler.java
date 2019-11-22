package noobanidus.mods.fasterzkk.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@SuppressWarnings("unused")
public class EventHandler {
  @SubscribeEvent
  public static void handleEntityDrops(LivingDropsEvent event) {
    EntityLivingBase entity = event.getEntityLiving();
    World world = entity.world;
    if (!world.isRemote) {
    }
  }
}
