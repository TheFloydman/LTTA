package thefloydman.ltta;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import gigaherz.guidebook.client.BookRegistryEvent;

import thefloydman.ltta.config.ModConfig;

@Mod(modid = LTTA.MOD_ID, name = LTTA.NAME, version = LTTA.VERSION, dependencies = "after:gbook;after:mystcraft")
@Mod.EventBusSubscriber
public class LTTA {
	
	public static final String MOD_ID = "ltta";
    public static final String NAME = "Linking Through the Ages";
    public static final String VERSION = "1.2.2";
    
	@Optional.Method(modid = "gbook")
    @SubscribeEvent
    public static void registerBook (BookRegistryEvent event) {
    	event.register(new ResourceLocation(LTTA.MOD_ID + ":xml/ltta.xml"));
    }
    
    // Define guidebook ItemStack.
    @GameRegistry.ItemStackHolder(value = "gbook:guidebook", nbt = "{Book:\"" + LTTA.MOD_ID + ":xml/ltta.xml\"}")
    public static ItemStack gbookStack;

    // Give one guidebook per player on first join (unless disabled in config).
    @SubscribeEvent
    @Optional.Method(modid = "gbook")
    public static void checkGbookGiven(EntityJoinWorldEvent event) {
        final Entity entity = event.getEntity();
        final String bookPlayerTag = LTTA.MOD_ID + ":bookGiven";

        if (entity instanceof EntityPlayer && !entity.getEntityWorld().isRemote && !entity.getTags().contains(bookPlayerTag) && ModConfig.giveBookOnFirstSpawn == true) {
            ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entity, gbookStack.copy());
            entity.addTag(bookPlayerTag);
        } else if (ModConfig.giveBookOnFirstSpawn == false) {
        	entity.addTag(bookPlayerTag);
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @EventHandler
    public void init(FMLInitializationEvent event) {}    
    
}
