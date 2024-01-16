package com.elvarg.game.model.commands.impl;

import com.elvarg.game.entity.impl.player.Player;
import com.elvarg.game.model.commands.Command;
import com.elvarg.game.model.rights.PlayerRights;
import com.elvarg.util.PlayerPunishment;

public class ReloadPunishments implements Command {

    @Override
    public void execute(Player player, String command, String[] parts) {
        PlayerPunishment.init();
        player.getPacketSender().sendConsoleMessage("Reloaded");
    }

    @Override
    public boolean canUse(Player player) {
        PlayerRights rights = player.getRights();
        return (rights == PlayerRights.OWNER || rights == PlayerRights.DEVELOPER);
    }

}
