/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.energy.blocks;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import forestry.core.blocks.IBlockTypeTesr;
import forestry.core.blocks.IMachinePropertiesTesr;
import forestry.core.blocks.MachinePropertiesTesr;
import forestry.core.config.Constants;
import forestry.core.proxy.Proxies;
import forestry.core.tiles.TileBase;
import forestry.core.tiles.TileEngine;
import forestry.energy.PluginEnergy;
import forestry.energy.tiles.TileEngineBiogas;
import forestry.energy.tiles.TileEngineClockwork;
import forestry.energy.tiles.TileEngineElectric;
import forestry.energy.tiles.TileEnginePeat;
import forestry.energy.tiles.TileEuGenerator;

public enum BlockTypeEngine implements IBlockTypeTesr {
	PEAT(createEngineProperties(0, TileEnginePeat.class, "peat", "/engine_copper_")),
	BIOGAS(createEngineProperties(1, TileEngineBiogas.class, "biogas", "/engine_bronze_")),
	CLOCKWORK(createEngineProperties(2, TileEngineClockwork.class, "clockwork", "/engine_clock_")),
	ELECTRICAL(createEngineProperties(3, TileEngineElectric.class, "electrical", "/engine_tin_")),
	GENERATOR(createMachineProperties(4, TileEuGenerator.class, "generator", "/generator_"));

	public static final BlockTypeEngine[] VALUES = values();

	@Nonnull
	private final IMachinePropertiesTesr<?> machineProperties;

	BlockTypeEngine(@Nonnull IMachinePropertiesTesr<?> machineProperties) {
		this.machineProperties = machineProperties;
	}

	protected static IMachinePropertiesTesr<?> createEngineProperties(int meta, @Nonnull Class<? extends TileEngine> teClass, @Nonnull String name, @Nonnull String textureName) {
		TileEntitySpecialRenderer<TileEngine> renderer = PluginEnergy.proxy.getRenderDefaultEngine(Constants.TEXTURE_PATH_BLOCKS + textureName);
		return new MachinePropertiesEngine<>(meta, teClass, name, renderer);
	}

	protected static IMachinePropertiesTesr<?> createMachineProperties(int meta, @Nonnull Class<? extends TileBase> teClass, @Nonnull String name, @Nonnull String textureName) {
		TileEntitySpecialRenderer<TileBase> renderer = Proxies.render.getRenderDefaultMachine(Constants.TEXTURE_PATH_BLOCKS + textureName);
		return new MachinePropertiesTesr<>(meta, teClass, name, renderer);
	}

	@Nonnull
	@Override
	public IMachinePropertiesTesr<?> getMachineProperties() {
		return machineProperties;
	}

	@Override
	public String getName() {
		return getMachineProperties().getName();
	}
}