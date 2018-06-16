/*
 * Thank you to Choonster for this code.
 * View the original example at
 * https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/acf537dad272a4a7148d8e2f124e1bdf2226f2a4/src/main/java/choonster/testmod3/config/ModConfig.java
 */

package thefloydman.ltta.config;

import thefloydman.ltta.LTTA;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = LTTA.MOD_ID)
@Config.LangKey("ltta.config.title")
public class ModConfig {

	@Config.Comment("Players are given a guidebook on first spawn by default. Setting this to false will prevent this action. (Players will still be able to craft a guidebook.)")
	public static boolean giveBookOnFirstSpawn = true;

	@Mod.EventBusSubscriber(modid = LTTA.MOD_ID)
	private static class EventHandler {

		// Inject the new values and save to the config file when the config has been changed from the GUI.
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(LTTA.MOD_ID)) {
				ConfigManager.sync(LTTA.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}