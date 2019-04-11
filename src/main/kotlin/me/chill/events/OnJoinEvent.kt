package me.chill.events

import me.chill.database.operations.*
import me.chill.database.states.TargetChannel
import me.chill.embed.types.newMemberJoinEmbed
import me.chill.exception.ListenerEventException
import me.chill.framework.CommandContainer
import me.chill.utility.*
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class OnJoinEvent : ListenerAdapter() {
  override fun onGuildMemberJoin(event: GuildMemberJoinEvent?) {
    event ?: throw ListenerEventException(
      "On Member Join",
      "Event object was null during member join"
    )

    if (!event.guild.getMember(event.jda.selfUser).hasPermission(Permission.MESSAGE_WRITE)) return

    val guild = event.guild
    val guildId = guild.id
    val joinChannel = guild.getTextChannelById(TargetChannel.Join.get(guildId))
    val loggingChannel = guild.getTextChannelById(TargetChannel.Logging.get(guildId))
    val member = event.member

    if (!TargetChannel.Join.isDisabled(guildId)) joinChannel.send(newMemberJoinEmbed(guild, member))

    if (hasJoinRole(guildId)) assignRole(guild, getJoinRole(guildId)!!, member.user.id)

    if (hasRaider(guildId, member.user.id)) {
      if (guild.getMutedRole() == null) {
        loggingChannel.send(
          failureEmbed(
            "Mute Failed",
            "Unable to apply mute to user as the **muted** role does not exist, run `${getPrefix(guild.id)}setup`"
          )
        )
      } else {
        guild.addRoleToUser(member, guild.getMutedRole()!!)
      }
      loggingChannel.send(
        failureEmbed(
          "Raider Rejoin",
          "Raider: ${printMember(member)} as rejoined the server"
        )
      )
    }
  }

  override fun onGuildJoin(event: GuildJoinEvent?) {
    event ?: throw ListenerEventException(
      "On Bot Join",
      "Event object was null during bot join"
    )

    val serverId = event.guild.id
    val defaultChannelId = event.guild.defaultChannel!!.id

//    event.guild.jda.getTextChannelById("482338281946742786").send(
//      successEmbed(
//        "Server Join",
//        "Joined ${event.guild.name}::$serverId on ${getDateTime()}"
//      )
//    )

    addServerPreference(serverId, defaultChannelId)
    loadGlobalPermissions(event.guild)
  }
}

private fun loadGlobalPermissions(guild: Guild) {
  val globalCommands = CommandContainer.getGlobalCommands()
  val everyoneRoleId = guild.getRolesByName("@everyone", false)[0].id
  batchAddPermissions(globalCommands, guild.id, everyoneRoleId)
}