/*
 * SkyClaims - A Skyblock plugin made for Sponge
 * Copyright (C) 2017 Mohron
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SkyClaims is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SkyClaims.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.mohron.skyclaims.integration;

import io.github.nucleuspowered.nucleus.api.exceptions.NucleusException;
import io.github.nucleuspowered.nucleus.api.nucleusdata.Home;
import io.github.nucleuspowered.nucleus.api.nucleusdata.NamedLocation;
import io.github.nucleuspowered.nucleus.api.service.NucleusAFKService;
import io.github.nucleuspowered.nucleus.api.service.NucleusHomeService;
import io.github.nucleuspowered.nucleus.api.service.NucleusKitService;
import net.mohron.skyclaims.SkyClaims;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import java.time.Instant;
import java.util.Optional;

public class Nucleus {

	private static final SkyClaims PLUGIN = SkyClaims.getInstance();
	private static String HOME_NAME = "Island";
	private NucleusHomeService homeService;
	private NucleusKitService kitService;
	private NucleusAFKService afkService;

	public Nucleus() {
		PLUGIN.getLogger().info("Nucleus Integration Successful!");
		homeService = PLUGIN.getGame().getServiceManager().provideUnchecked(NucleusHomeService.class);
		kitService = PLUGIN.getGame().getServiceManager().provideUnchecked(NucleusKitService.class);
		afkService = PLUGIN.getGame().getServiceManager().provideUnchecked(NucleusAFKService.class);
	}

	public Optional<Transform<World>> getHome(User user) {
		Optional<Home> oHome = homeService.getHome(user, HOME_NAME);
		return oHome.flatMap(NamedLocation::getTransform);
	}

	public boolean modifyOrCreateHome(Player player) {
		try {
			homeService.modifyOrCreateHome(PLUGIN.getCause(), player, HOME_NAME, player.getLocation(), player.getRotation());
			player.sendMessage(Text.of(TextColors.GREEN, "Your home has been set!"));
			return true;
		} catch (NucleusException e) {
			player.sendMessage(Text.of(TextColors.RED, "An error was encountered while attempting to set your home!"));
			PLUGIN.getGame().getServer().getConsole().sendMessage(e.getText());
			return false;
		}
	}

	public void redeemFirstJoinKit(Player player) {
		// TODO: Redeem First Join Kit for player (Requires an API update)
		PLUGIN.getGame().getCommandManager().process(PLUGIN.getGame().getServer().getConsole(), "/firstjoinkit redeem " + player.getName());
	}

	public boolean isAFK(Player player) {
		return afkService.isAFK(player);
	}

	public Instant lastActivity(Player player) {
		return afkService.lastActivity(player);
	}
}
