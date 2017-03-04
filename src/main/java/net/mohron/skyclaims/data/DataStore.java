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

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.mohron.skyclaims.SkyClaims;
import net.mohron.skyclaims.team.iTeam;
import net.mohron.skyclaims.world.Island;
import org.spongepowered.api.entity.living.player.User;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DataStore {
	private Map<UUID, Island> islands = Maps.newHashMap();
	private Map<UUID, iTeam> teams = Maps.newHashMap();
	private Map<UUID, UserData> userData = Maps.newHashMap();
	private Set<Island> saveQueue = Sets.newHashSet();

	public Map<UUID, Island> getIslands(){
		return islands;
	}

	public void setIslands(Map<UUID, Island> islands) {
		this.islands = islands;
	}

	public Map<UUID, iTeam> getTeams() {
		return teams;
	}

	public Map<UUID, UserData> getAllUserData() {
		return userData;
	}

	public UserData getOrCreateUserData(UUID userId) {
		if (!userData.containsKey(userId)) userData.put(userId, new UserData(userId));
		return userData.get(userId);
	}

	public UserData getOrCreateUserData(User user) {
		return getOrCreateUserData(user.getUniqueId());
	}

	public Set<Island> getSaveQueue() {
		return saveQueue;
	}

	public void addToSaveQueue(Island island) {
		saveQueue.add(island);
	}

	public void processSaveQueue() {
		SkyClaims.getInstance().getLogger().info(String.format("Saving %s claims that were malformed.", saveQueue.size()));
		SkyClaims.getInstance().getDatabase().saveData(saveQueue);
		saveQueue.clear();
	}
}
