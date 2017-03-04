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

package net.mohron.skyclaims.data;


import net.mohron.skyclaims.SkyClaims;
import net.mohron.skyclaims.integration.Nucleus;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class UpdatePlayTimeTask implements Runnable {
	private static final SkyClaims PLUGIN = SkyClaims.getInstance();

	@Override
	public void run() {
		Optional<Nucleus> nucleus = PLUGIN.getIntegration().getNucleus();
		PLUGIN.getGame().getServer().getOnlinePlayers().forEach(player -> {
			UserData userData = PLUGIN.getDataStore().getOrCreateUserData(player);
			if (!nucleus.isPresent() || !nucleus.get().isAFK(player)){
				Duration timePlayed = Duration.ofMinutes(5).minus(Duration.between(nucleus.get().lastActivity(player), Instant.now()));
				if (!timePlayed.isNegative()) userData.addTimePlayed(Duration.ofMinutes(timePlayed.toMinutes()));
			} else {
				userData.addTimePlayed(Duration.ofMinutes(5));
			}
		});
	}
}
