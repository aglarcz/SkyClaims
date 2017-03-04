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

import org.spongepowered.api.entity.living.player.User;

import java.time.Duration;
import java.util.UUID;

public class UserData {
	private UUID userId;
	private Duration timePlayed;

	public UserData(User user) {
		this.userId = user.getUniqueId();
	}

	public UserData(UUID userId) {
		this.userId = userId;
	}

	public UUID getUserId() {
		return userId;
	}

	public User getUser() {
		return null;
	}

	public Duration getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(Duration timePlayed) {
		this.timePlayed = timePlayed;
	}

	public void addTimePlayed(Duration time) {
		timePlayed = timePlayed.plus(time);
	}

}
