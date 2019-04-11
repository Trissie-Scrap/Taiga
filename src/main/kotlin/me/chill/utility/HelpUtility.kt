package me.chill.utility

import com.google.gson.Gson
import com.google.gson.JsonObject
import me.chill.commandInfo
import me.chill.exception.CommandException
import me.chill.framework.Command
import me.chill.framework.CommandContainer
import java.io.File
import java.io.FileReader

data class ArgumentList(
  val name: String,
  val args: List<String>
)

data class CommandInfo(
  val name: String,
  val description: String,
  val syntax: String,
  val example: String,
  var category: String? = null,
  val argumentList: MutableList<ArgumentList>? = mutableListOf()
)

fun loadHelp(): List<CommandInfo> {
  val gson = Gson()
  val list = mutableListOf<CommandInfo>()
  val commandInfoList = gson.fromJson<JsonObject>(FileReader(File("config/help.json")), JsonObject::class.java)

  for (info in commandInfoList.entrySet()) {
    val categoryName = info.key
    info.value.asJsonArray.forEach {
      val entry = gson.fromJson(it, CommandInfo::class.java)
      entry.category = categoryName
      list.add(entry)
    }
  }
  CommandContainer.getCommandList().forEach {
    if (!list.asSequence().map { info -> info.name }.contains(it.name)) {
      throw CommandException(it.name, "Command not have a help in help.json")
    }
  }
  return list
}

fun findCommand(commandName: String) = commandInfo!!.first { it.name == commandName }

val Command.syntax get() = "$serverPrefix${findCommand(name).syntax}"

val Command.example get() = "$serverPrefix${findCommand(name).example}"

val Command.description get() = findCommand(name).description

val Command.argumentList get() = findCommand(name).argumentList?.toList()