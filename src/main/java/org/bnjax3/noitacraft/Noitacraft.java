package org.bnjax3.noitacraft;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bnjax3.noitacraft.registry.ModBlocks;
import org.bnjax3.noitacraft.registry.ModContainers;
import org.bnjax3.noitacraft.registry.ModEntities;
import org.bnjax3.noitacraft.entity.render.SparkBoltRenderer;
import org.bnjax3.noitacraft.gui.screen.screens.WandAltarScreen;
import org.bnjax3.noitacraft.registry.ModItems;
import org.bnjax3.noitacraft.registry.ModTileEntities;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("noitacraft")
public class Noitacraft {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "noitacraft";
    public Noitacraft() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        /*
        TODO: as of 29/12/25
        --- Short term ---
        - triggers stopped working again¿¿¿
        - Make projectile rendering obey directions
        - Make more spells
        - Make more projectiles
        - Make more wands
        - The WandAltarTile extract function always returns null for no reason -> broke even worse now wtf
        - Make Utils.formattooltipdata better
        - add mana functionality (as a kind of durability) (hellish probably)
        - add uses to spells (have to change like 90% of the casting code probably AAARRGHGHH)
        - hacer que la tooltip de la varita muestre los spells que contiene
        - Register MagicProjectile data on entity nbts  NOOOOOOOOOOOOOOOOOOOOOO

        --- Medium-Long term ---
        - fix the light spells (currently commented out)
        - different held models for wands (currently look like ass)
        - also fix the textures because theyre ugly as hell when in hand
        - traducciones a español (solo argentina)(vamos carajo)
        - add loot tables and recipes (long term)
        - add sound effects?? (also long term)

        DONE:
        - nothing (lazy dumbass)
        - Make more sprites and models
        - Remake the get payload function in wand
        - Register wand item info on nbts (or make it be saved somehow)
        - make the projectile entities in the cast function actually spawn something
        - maybe fix the burning pile of shit that are like half of all the functions on SpellGroup
           (and fix the spell propeties thingy because im not sure if it works with other
            special properties like those in the OnCast method)
         */

        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModEntities.register(eventBus);
        ModItems.register(eventBus);
        ModContainers.register(eventBus);


        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code

    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        ScreenManager.register(ModContainers.WAND_ALTAR_CONTAINER.get(), WandAltarScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPARK_BOLT_PROJECTILE.get(), SparkBoltRenderer::new);

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("noitacraft", "helloworld", () -> {

            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here

        }
    }
}
