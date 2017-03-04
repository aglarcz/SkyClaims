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

package net.mohron.skyclaims.command.team;

import com.google.common.collect.Lists;
import net.mohron.skyclaims.SkyClaims;
import net.mohron.skyclaims.command.argument.Argument;
import net.mohron.skyclaims.permissions.Permissions;
import net.mohron.skyclaims.util.CommandUtil;
import net.mohron.skyclaims.world.Island;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class CommandTeamInfo implements CommandExecutor {
	private static final SkyClaims PLUGIN = SkyClaims.getInstance();
	public static final String HELP_TEXT = "display detailed information on a team.";
	private static final Text TEAM = Text.of("team");

	public static CommandSpec commandSpec = CommandSpec.builder()
		.permission(Permissions.COMMAND_INFO)
		.description(Text.of(HELP_TEXT))
		.arguments(Argument.island(TEAM))
		.executor(new CommandTeamInfo())
		.build();

	public static void register() {
		try {
			PLUGIN.getGame().getCommandManager().register(PLUGIN, commandSpec);
			PLUGIN.getLogger().debug("Registered command: CommandTeamInfo");
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			PLUGIN.getLogger().error("Failed to register command: CommandTeamInfo");
		}
	}

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		List<Text> infoText = Lists.newArrayList();

		PaginationList.builder()
			.title(Text.of(TextColors.AQUA, "Team Info"))
			.padding(Text.of(TextColors.AQUA, TextStyles.STRIKETHROUGH, "-"))
			.contents(infoText)
			.sendTo(src);

		return CommandResult.success();
	}
}
