# Taiga
![alt text](docs/smile.gif "Taiga Aisaka")

|**Prefix:** ?|**Creator:** @Chill#4048|**Language:** Kotlin|**Library:** JDA|[Invite Link](https://discordapp.com/oauth2/authorize?client_id=482340927709511682&scope=bot&permissions=8)|[Development Server](https://discord.gg/xtDNfyw)|[Website](https://woojiahao.github.io/Taiga)|
|---|---|---|---|---|---|---|

## Commands Overview
More information about commands can be found on the [website](https://woojiahao.github.io/Taiga)

<!--blk-1-->
1. [Administration](https://github.com/woojiahao/Taiga#Administration)
2. [Moderation](https://github.com/woojiahao/Taiga#Moderation)
3. [Permission](https://github.com/woojiahao/Taiga#Permission)
4. [Utility](https://github.com/woojiahao/Taiga#Utility)
5. [Role](https://github.com/woojiahao/Taiga#Role)
6. [Raid](https://github.com/woojiahao/Taiga#Raid)
7. [Suggestion](https://github.com/woojiahao/Taiga#Suggestion)
8. [Macro](https://github.com/woojiahao/Taiga#Macro)
9. [Fun](https://github.com/woojiahao/Taiga#Fun)
10. [Invite](https://github.com/woojiahao/Taiga#Invite)
### Administration
|Name|Description|
|---|---|
|`setchannel`|Sets the logging, welcome or suggestion channel|
|`setup`|Sets up the bot for moderation like adding the muted roles and overriding channel permissions|
|`preferences`|Displays all the properties set for the server|
|`set`|Sets the value for a preference|
|`get`|Displays the details of a specific preference set|
|`disable`|Disables a specific logging type, welcome/logging/suggestion|
|`enable`|Enables a specific logging type, welcome/logging/suggestion|
### Moderation
|Name|Description|
|---|---|
|`echo`|Echos a message into another channel, the message to echo cannot contain mentions|
|`nuke`|Nukes a certain number of messages, from 0 up to 99|
|`mute`|Mutes a user for a specified period of time, defaulted to minutes|
|`history`|Displays the history of a specific member, if no arguments are given, the invoker's history is retrieved|
|`strike`|Infracts a user|
|`warn`|Infracts a user with a 0 weight strike|
|`wiperecord`|Wipes the user's record from the database|
|`ban`|Bans a user/list of users (up to 30) from the server at once|
|`unban`|Unbans a user and leaves a mark on their history|
|`gag`|Temporarily mutes a user for 5 minutes to allow moderators to handle an ongoing situation|
|`clearstrike`|Removes a strike from a user|
### Permission
|Name|Description|
|---|---|
|`setpermission`|Sets the permission of a command/category to be available to a role and higher|
|`viewpermissions`|Views all the permissions set in the server|
|`setglobal`|Sets a single command or category of commands to be available to everyone|
### Utility
|Name|Description|
|---|---|
|`help`|Displays a help card for the user to learn more about a specific command or category, pass no arguments and it will display all commands available to you|
|`invite`|Displays all invite links related to me such as my development server and to invite me to your server|
|`ping`|Displays the ping for me|
|`serverinfo`|Displays information about the server|
|`source`|Displays all links related to my source code|
|`botinfo`|Displays information about the bot|
|`changelog`|Displays the latest changelog if no argument is given, displays a specific changelog if one can be found|
|`google`|Performs and displays a google search of a query|
### Role
|Name|Description|
|---|---|
|`assign`|Assigns a role to the specified user, the role must not be higher than my role|
|`roles`|Displays all roles and their IDs in the server|
|`clearjoinrole`|Clears the join role|
|`unassign`|Removes a role from the user, the role must not be higher than my role|
### Raid
|Name|Description|
|---|---|
|`viewraiders`|Displays all the raiders in the server|
|`freeraider`|Removes a user from the raid list and unmutes them|
|`freeallraiders`|Removes all users from the raid list and unmutes them|
### Suggestion
|Name|Description|
|---|---|
|`poolinfo`|Displays information about the suggestion pool|
|`pooltop`|Displays the latest suggestion in the pool|
|`poolaccept`|Accepts the latest suggestion in the pool|
|`pooldeny`|Denies the latest suggestion in the pool|
|`suggest`|Adds a suggestion to the suggestion pool|
|`respond`|Responds to a suggestion|
### Macro
|Name|Description|
|---|---|
|`listmacros`|Displays all the macros on the server|
|`addmacro`|Adds a macro to the server|
|`editmacro`|Edits an existing macro on the server|
|`removemacro`|Removes an existing macro from the server|
### Fun
|Name|Description|
|---|---|
|`clapify`|Clapifies a sentence|
|`flip`|Flips a coin|
|`cookie`|Give someone a cookie, if you don't pass any arguments, a cookie is given to a random stranger|
|`meme`|Displays a random popular meme from Imgflip|
|`joke`|Tells a joke|
|`emote`|Displays an emote based on the name if it can be found in any of the servers Taiga is in|
|`cat`|Displays a cat picture/GIF|
|`dog`|Displays a dog picture/GIF|
|`bird`|Displays a bird picture/GIF|
### Invite
|Name|Description|
|---|---|
|`addinvite`|Adds an invite to the whitelist, only the owner can add invites|
|`removeinvite`|Removes an invite from the whitelist, only the owner can remove invites|
|`whitelist`|Displays all the whitelisted invites for the server|
<!--blk-1-end-->

## Credits
Taiga's command framework is heavily inspired by @Fox#0001 HotBot.

* [HotBot's Repository](https://gitlab.com/Aberrantfox/hotbot)
* [HotBot in action](https://discord.gg/programming)