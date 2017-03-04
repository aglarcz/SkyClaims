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

package net.mohron.skyclaims.team;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.mohron.skyclaims.world.Island;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.service.permission.MemorySubjectData;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.service.permission.SubjectCollection;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Tristate;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

abstract class Team implements iTeam, Subject  {
	private final MemorySubjectData data = new MemorySubjectData(Sponge.getServiceManager().provideUnchecked(PermissionService.class));
	private final UUID id;
	private Text name;
	private User owner;
	private List<User> members = Lists.newArrayList();
	private List<Island> islands = Lists.newArrayList();
	private Duration playtime = Duration.ZERO;

	public Team(Text name, User owner) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.owner = owner;
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public Subject getSubject() {
		return this;
	}

	@Override
	public Text getName() {
		return name;
	}

	@Override
	public User getOwner() {
		return owner;
	}

	@Override
	public List<User> getMembers() {
		return members;
	}

	@Override
	public List<Island> getIslands() {
		return islands;
	}

	@Override
	public Duration getPlaytime() {
		return playtime;
	}

	@Override
	public Optional<CommandSource> getCommandSource() {
		return Optional.empty();
	}

	@Override
	public SubjectCollection getContainingCollection() {
		return Sponge.getServiceManager().provideUnchecked(PermissionService.class).getGroupSubjects();
	}

	@Override
	public MemorySubjectData getSubjectData() {
		return this.data;
	}

	@Override
	public MemorySubjectData getTransientSubjectData() {
		return this.data;
	}

	@Override
	public Tristate getPermissionValue(Set<Context> contexts, String permission) {
		return Tristate.fromBoolean(this.data.getPermissions(contexts).getOrDefault(permission, null));
	}

	@Override
	public boolean isChildOf(Set<Context> contexts, Subject parent) {
		return false;
	}

	@Override
	public List<Subject> getParents(Set<Context> contexts) {
		return ImmutableList.of();
	}

	@Override
	public Optional<String> getOption(Set<Context> contexts, String key) {
		return Optional.empty();
	}

	@Override
	public String getIdentifier() {
		return getId().toString();
	}

	@Override
	public Set<Context> getActiveContexts() {
		return ImmutableSet.of();
	}
}
