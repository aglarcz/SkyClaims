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

import net.mohron.skyclaims.SkyClaims;
import nl.riebie.mcclans.api.ClanService;
import org.spongepowered.api.Sponge;

public class MCClans {
	private static final SkyClaims PLUGIN = SkyClaims.getInstance();
	private ClanService clanService;

	public MCClans() {
		PLUGIN.getLogger().info("MCClans Integration Successful!");
		clanService = Sponge.getServiceManager().provideUnchecked(ClanService.class);
	}

	public ClanService getClanService() {
		return clanService;
	}
}
