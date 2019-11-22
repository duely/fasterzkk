package noobanidus.mods.fasterzkk;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import noobanidus.mods.fasterzkk.commands.CommandOcarina;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
@Mod(modid = FasterZKK.MODID, name = FasterZKK.MODNAME, version = FasterZKK.VERSION)
@SuppressWarnings("WeakerAccess")
public class FasterZKK {
  public static final String MODID = "fasterzkk";
  public static final String MODNAME = "Faster, ZAWA! KILL! KILL!";
  public static final String VERSION = "GRADLE:VERSION";

  public static Logger LOG;

  @SuppressWarnings("unused")
  @Mod.Instance(FasterZKK.MODID)
  public static FasterZKK instance;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    LOG = event.getModLog();
  }

  @Mod.EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand(new CommandOcarina());
  }
}
