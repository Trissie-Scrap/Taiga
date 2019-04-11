package me.chill.database.operations

import me.chill.database.Preference
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun hasJoinRole(serverId: String) =
  transaction {
    Preference
      .select { Preference.serverId eq serverId }
      .first()[Preference.onJoinRole] != null
  }

fun getJoinRole(serverId: String) = getPreference<String?>(serverId, Preference.onJoinRole)

fun removeJoinRole(serverId: String) = editJoinRole(serverId, null)

fun editJoinRole(serverId: String, roleId: String?) =
  updatePreferences(serverId) { it[onJoinRole] = roleId }

