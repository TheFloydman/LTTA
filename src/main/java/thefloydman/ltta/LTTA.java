package thefloydman.ltta;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import thefloydman.ltta.GiveBook;

@Mod(modid = LTTA.MODID, name = LTTA.NAME, version = LTTA.VERSION)
//@Mod.EventBusSubscriber
public class LTTA {
	
    public static final String MODID = "ltta";
    public static final String NAME = "Linking Through the Ages";
    public static final String VERSION = "1.0.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new GiveBook());
    }    
    
}
