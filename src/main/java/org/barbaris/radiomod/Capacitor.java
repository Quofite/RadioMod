package org.barbaris.radiomod;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class Capacitor extends Item {
    public Capacitor(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.radiomod.capacitor.tooltip"));
    }
}
