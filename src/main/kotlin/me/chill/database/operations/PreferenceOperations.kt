package me.chill.database.operations

import me.chill.database.Preference
import me.chill.database.states.TimeMultiplier
import me.chill.defaultPrefix
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.transactions.transaction

data class ServerPreference(
  val serverId: String,
  val prefix: String,
  val joinChannel: String,
  val loggingChannel: String,
  val suggestionChannel: String,
  val messageLimit: Int,
  val messageDuration: Int,
  val roleExcluded: String?,
  val welcomeDisabled: Boolean,
  val welcomeMessage: String,
  val timeMultiplier: TimeMultiplier,
  val onJoinRole: String?,
  val loggingDisabled: Boolean,
  val suggestionDisabled: Boolean,
  val inviteExcluded: String?,
  val userActivityChannel: String,
  val userActivityTrackingDisabled: Boolean
)

fun addServerPreference(serverId: String, defaultChannelId: String) {
  transaction {
    Preference.insert {
      // General
      it[Preference.serverId] = serverId

      // Prefix
      it[prefix] = defaultPrefix

      // Channel Assignment
      it[joinChannel] = defaultChannelId
      it[loggingChannel] = defaultChannelId
      it[suggestionChannel] = defaultChannelId
      it[userActivityChannel] = defaultChannelId
      it[disableWelcome] = true
      it[disableLogging] = true
      it[disableSuggestion] = true
      it[disableUserActivityTracking] = true

      // Raid
      it[raidMessageLimit] = 5
      it[raidMessageDuration] = 3
      it[raidRoleExcluded] = null

      // Member On Join
      it[welcomeMessage] = "Welcome! Remember to read #rules-and-info"
      it[timeMultiplier] = TimeMultiplier.M.name
      it[onJoinRole] = null

      // Invite
      it[inviteRoleExcluded] = null
    }
  }
}

fun removeServerPreference(serverId: String) {
  transaction {
    Preference.deleteWhere { Preference.serverId eq serverId }
  }
}

@Suppress("UNCHECKED_CAST")
fun <T> getPreference(serverId: String, column: Column<*>): T =
  transaction {
    Preference.select { Preference.serverId eq serverId }.first()[column] as T
  }

fun updatePreferences(serverId: String, updateStatement: Preference.(UpdateStatement) -> Unit) {
  transaction {
    Preference.update({ Preference.serverId eq serverId }, body = updateStatement)
  }
}

fun getAllPreferences(serverId: String) =
  transaction {
    val result = Preference.select { Preference.serverId eq serverId }.first()
    ServerPreference(
      result[Preference.serverId],
      result[Preference.prefix],
      result[Preference.joinChannel],
      result[Preference.loggingChannel],
      result[Preference.suggestionChannel],
      result[Preference.raidMessageLimit],
      result[Preference.raidMessageDuration],
      result[Preference.raidRoleExcluded],
      result[Preference.disableWelcome],
      result[Preference.welcomeMessage],
      TimeMultiplier.valueOf(result[Preference.timeMultiplier]),
      result[Preference.onJoinRole],
      result[Preference.disableLogging],
      result[Preference.disableSuggestion],
      result[Preference.inviteRoleExcluded],
      result[Preference.userActivityChannel],
      result[Preference.disableUserActivityTracking]
    )
  }