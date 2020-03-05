package noobanidus.mods.fasterzkk;

import com.google.common.collect.Sets;
import net.minecraftforge.common.config.Config;

import java.util.Set;

@Config(modid = FasterZKK.MODID)
public class ConfigFZKK {
  @Config.Comment("List of modids to be culled")
  public static String[] ModIds = new String[]{"zawa", "faunanatural"};

  @Config.Ignore
  private static Set<String> computedModIds = null;

  public static Set<String> getComputedModIds() {
    if (computedModIds == null) {
      computedModIds = Sets.newHashSet(ModIds);
    }
    return computedModIds;
  }
}
