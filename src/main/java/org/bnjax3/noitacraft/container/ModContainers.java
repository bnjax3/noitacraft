package org.bnjax3.noitacraft.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.container.containers.WandAltarContainer;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Noitacraft.MOD_ID);

    public static RegistryObject<ContainerType<WandAltarContainer>> WAND_ALTAR_CONTAINER = CONTAINERS.register("wand_altar_container", () -> IForgeContainerType.create(((windowId, inv, data) -> new WandAltarContainer(windowId, inv.player.getCommandSenderWorld(), data.readBlockPos(), inv, inv.player))));



    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
