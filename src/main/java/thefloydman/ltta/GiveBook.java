package thefloydman.ltta;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class GiveBook {
	
	@GameRegistry.ItemStackHolder(value = "gbook:guidebook", nbt = "{Book:\"ltta:xml/ltta.xml\"}")
    public static ItemStack gbookStack;

    // Give one gbook per player on first join
    @SubscribeEvent
    public static void checkGbookGiven(EntityJoinWorldEvent event)
    {
        final Entity entity = event.getEntity();
        final String bookPlayerTag = "ltta:guidebookGiven";

        if (entity instanceof EntityPlayer && !entity.getEntityWorld().isRemote && !entity.getTags().contains(bookPlayerTag))
        {
            ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entity, gbookStack);
            entity.addTag(bookPlayerTag);
        }
    }

}
