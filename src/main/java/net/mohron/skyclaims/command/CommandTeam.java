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

package net.mohron.skyclaims.command;

import net.mohron.skyclaims.SkyClaims;
import net.mohron.skyclaims.command.team.CommandInvite;
import net.mohron.skyclaims.command.team.CommandTeamInfo;
import net.mohron.skyclaims.permissions.Permissions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import static net.mohron.skyclaims.PluginInfo.NAME;
import static net.mohron.skyclaims.PluginInfo.VERSION;

public class CommandTeam implements CommandExecutor {
	private static final SkyClaims PLUGIN = SkyClaims.getInstance();
	public static String helpText = "used to run team commands or display help info.";
	private static Text HELP = Text.of("help");

	public static CommandSpec commandSpec = CommandSpec.builder()
		.permission(Permissions.COMMAND_ADMIN)
		.description(Text.of(helpText))
		.child(CommandInvite.commandSpec, "invite")
		.child(CommandTeamInfo.commandSpec, "info")
		.arguments(GenericArguments.optionalWeak(GenericArguments.onlyOne(GenericArguments.literal(HELP, "help"))))
		.executor(new CommandTeam())
		.build();

	public static void register() {
		try {
			Sponge.getCommandManager().register(PLUGIN, commandSpec, "ist");
			PLUGIN.getLogger().debug("Registered command: CommandTeam");
			registerSubCommands();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			PLUGIN.getLogger().error("Failed to register command: CommandTeam");
		}
	}

	private static void registerSubCommands() {
		CommandInvite.register();
		CommandTeamInfo.register();
	}

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Text helpContents = Text.EMPTY;
		boolean hasPerms = false;

		if (src.hasPermission(Permissions.COMMAND_CREATE_SCHEMATIC)) {
			helpContents = Text.join(helpContents, Text.of(
				TextColors.AQUA, "ist invite",
				TextColors.GOLD, " [player]",
				TextColors.DARK_GRAY, " - ",
				TextColors.DARK_GREEN, CommandInvite.HELP_TEXT
			));
			hasPerms = true;
		}

		if (hasPerms) {
			PaginationList.Builder paginationBuilder = PaginationList.builder()
				.title(Text.of(TextColors.AQUA, NAME, " Admin Help"))
				.padding(Text.of(TextColors.AQUA, TextStyles.STRIKETHROUGH, "-"))
				.contents(helpContents);
			paginationBuilder.sendTo(src);
		} else {
			src.sendMessage(Text.of(NAME + " " + VERSION));
		}

		return CommandResult.success();
	}
}
