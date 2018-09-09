package thefloydman.ltta;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.ItemHandlerHelper;

import gigaherz.guidebook.client.BookRegistryEvent;

import thefloydman.ltta.config.ModConfig;

@Mod(modid = LTTA.MOD_ID, name = LTTA.NAME, version = LTTA.VERSION, dependencies = "after:gbook;after:mystcraft")
@Mod.EventBusSubscriber
public class LTTA {
	
	public static final String MOD_ID = "ltta";
    public static final String NAME = "Linking Through the Ages";
    public static final String VERSION = "1.3.0";
    
    // Register LTTA and Narayani for Dummies with Guidebook.
	@Optional.Method(modid = "gbook")
    @SubscribeEvent
    public static void registerBook (BookRegistryEvent event) {
    	event.register(new ResourceLocation(LTTA.MOD_ID + ":xml/ltta.xml"));
    	event.register(new ResourceLocation(LTTA.MOD_ID + ":xml/narayan_book.xml"));
    }
    
    // Define LTTA ItemStack.
    @GameRegistry.ItemStackHolder(value = "gbook:guidebook", nbt = "{Book:\"" + LTTA.MOD_ID + ":xml/ltta.xml\"}")
    public static ItemStack lttaStack;

    // Give one LTTA book per player on first join (unless disabled in config).
    @Optional.Method(modid = "mystcraft")
    @SubscribeEvent
    public static void checkGbookGiven(EntityJoinWorldEvent event) {
        final Entity entity = event.getEntity();
        final String bookPlayerTag = LTTA.MOD_ID + ":bookGiven";

        if (entity instanceof EntityPlayer && !entity.getEntityWorld().isRemote && !entity.getTags().contains(bookPlayerTag) && ModConfig.giveBookOnFirstSpawn == true) {
            ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entity, lttaStack.copy());
            entity.addTag(bookPlayerTag);
        } else if (ModConfig.giveBookOnFirstSpawn == false) {
        	entity.addTag(bookPlayerTag);
        }
    }
	
    // Add Narayani book to Mystcraft loot table.
    @Optional.Method(modid = "mystcraft")
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent event) {
            if(event.getName().toString().equals("mystcraft:mystcraft_treasure")) {
            	LootEntry customEntry = event.getLootTableManager().getLootTableFromLocation(new ResourceLocation("ltta", "loot_narayan_book")).getPool("main").getEntry("narayani_mystcraft");
            	event.getTable().getPool("mystcraft:mystcraft_treasure").addEntry(customEntry);
            }
	}

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @EventHandler
    public void init(FMLInitializationEvent event) {}    
    
}
