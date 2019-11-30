package com.mrthomas20121.tinkerfirmacraft.Traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitFerromagnetism extends AbstractTrait {
    public TraitFerromagnetism() {
        super("ferromagnetism", 0xffffff);
    }
    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        if(event.getState().getBlock().getLocalizedName().toLowerCase().contains("iron")) {
            event.setNewSpeed(event.getOriginalSpeed()+3.2f);
        }
    }
    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        if(state.getBlock().getLocalizedName().toLowerCase().contains("iron")) {
            // player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 100, 4));
        }
    }
}
