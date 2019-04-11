package me.chill.arguments.types

import me.chill.arguments.Argument
import me.chill.arguments.ArgumentParseMap
import me.chill.framework.CommandContainer
import net.dv8tion.jda.api.entities.Guild
import org.apache.commons.lang3.text.WordUtils

class CategoryName : Argument {
  override fun check(guild: Guild, arg: String) =
    if (!CommandContainer.hasCategory(arg))
      ArgumentParseMap(false, "Category: **$arg** does not exist")
    else
      ArgumentParseMap(true, parsedValue = WordUtils.capitalize(arg))
}