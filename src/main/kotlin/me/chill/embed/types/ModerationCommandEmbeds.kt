package me.chill.embed.types

import me.chill.database.operations.getStrikeCount
import me.chill.database.operations.getUserCount
import me.chill.database.states.TimeMultiplier
import me.chill.infraction.UserInfractionRecord
import me.chill.settings.cyan
import me.chill.settings.green
import me.chill.settings.orange
import me.chill.settings.red
import me.chill.utility.embed
import me.chill.utility.findUser
import me.chill.utility.printMember
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.time.format.DateTimeFormatter

fun historyEmbed(guild: Guild, user: User, jda: JDA, userInfractionRecord: UserInfractionRecord) =
  embed {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val jodaFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
    val joinDate = if (guild.getMember(user) == null) {
      "User is not on the server"
    } else {
      guild.getMember(user).timeJoined.format(dateTimeFormatter)
    }

    title = "${user.name}'s History"
    color = cyan
    thumbnail = user.avatarUrl

    field {
      title = "Summary"
      description =
        "${user.name}#${user.discriminator} has **${userInfractionRecord.getStrikes().size}** infraction(s)\n" +
        "Current Strike Count: **${getStrikeCount(guild.id, user.id)}/3**\n" +
        "Join Date: **$joinDate**\n" +
        "Creation Date: **${user.timeCreated.format(dateTimeFormatter)}**\n" +
        "Invites Sent: **${getUserCount(user.id, guild.id)}/5**"
    }

    field {}

    if (userInfractionRecord.getStrikes().isEmpty()) {
      field {
        title = "This user has no infractions"
        description = "Squeaky clean!"
      }
    } else {
      userInfractionRecord.getStrikes().forEach { userStrike ->
        val isExpired = if (DateTime.now() > userStrike.expiryDate) "expired" else "not expired"

        field {
          title = "ID :: ${userStrike.strikeId} :: Weight :: ${userStrike.strikeWeight}"
          description =
            "This infraction expires on **${jodaFormatter.print(userStrike.expiryDate)}** " +
            "and is **$isExpired**\n" +
            "Issued by **${jda.findUser(userStrike.actingModeratorId).name}** " +
            "on **${jodaFormatter.print(userStrike.strikeDate)}**\n" +
            "__**Reason:**__\n${userStrike.strikeReason}"
        }
      }
    }
  }

fun strikeSuccessEmbed(strikeWeight: Int, target: Member, strikeReason: String) =
  embed {
    title = "User Striked"
    color = orange
    description = "${printMember(target)} has been striked"

    field {
      title = "Reason"
      description = strikeReason
      inline = false
    }

    field {
      title = "Weight"
      description = strikeWeight.toString()
      inline = false
    }
  }

fun userStrikeNotificationEmbed(guildName: String, strikeReason: String,
                                strikeWeight: Int, strikeCount: Int) =
  embed {
    title = "Strike"
    description = "You have been striked in **$guildName**"
    color = red

    field {
      title = "Reason"
      description = strikeReason
    }

    field {
      title = "Weight"
      description = strikeWeight.toString()
    }

    field {
      title = "Infraction Status"
      description = "Your strike count is at **$strikeCount/3**"
    }
  }

fun userMuteNotificationEmbed(guildName: String, duration: Int, reason: String, guildTimeMultiplier: TimeMultiplier) =
  embed {
    title = "Mute"
    description = "You have been muted in **$guildName**"
    color = red

    field {
      title = "Reason"
      description = reason
    }

    field {
      title = "Duration"
      description = "$duration ${guildTimeMultiplier.fullTerm}(s)"
    }
  }

fun muteSuccessEmbed(member: Member, duration: Int, reason: String, guildTimeMultiplier: TimeMultiplier) =
  embed {
    title = "User Muted"
    description = "User: ${printMember(member)} has been muted for **$duration** ${guildTimeMultiplier.fullTerm}(s)"
    color = green
    field {
      title = "Reason"
      description = reason
    }
  }