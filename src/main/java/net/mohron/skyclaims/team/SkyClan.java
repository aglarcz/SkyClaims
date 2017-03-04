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

import nl.riebie.mcclans.api.Clan;
import org.spongepowered.api.text.Text;

public class SkyClan extends Team {
	private Clan clan;

	SkyClan(Clan clan) {
		super(Text.of(clan.getName()), null);
		this.clan = clan;
	}

	public Clan getClan() {
		return clan;
	}

	@Override
	public TeamType getType() {
		return TeamType.CLAN;
	}

	@Override
	public Text getName() {
		return Text.of(clan.getName());
	}
}
