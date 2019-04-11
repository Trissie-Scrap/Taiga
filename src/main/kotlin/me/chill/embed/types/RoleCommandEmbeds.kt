package me.chill.embed.types

import me.chill.settings.green
import me.chill.utility.embed
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Role

fun listRolesEmbed(guild: Guild, roles: List<Role>) =
  embed {
    title = "Roles in ${guild.name}"
    color = green
    description = roles.joinToString("\n") {
      "**${it.name}** :: ${it.id}"
    }
    thumbnail = guild.iconUrl
  }
