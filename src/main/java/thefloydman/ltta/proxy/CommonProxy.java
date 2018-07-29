package thefloydman.ltta.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;


@Mod.EventBusSubscriber
public class CommonProxy {
    public void init(FMLInitializationEvent event) {}

    public void drawPoemWord(float x, float y, float z, float scale, String word) {}
}
