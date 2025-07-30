package com.projectkorra.projectkorra;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.configuration.client.WrapperConfigClientPluginMessage;
import com.github.retrooper.packetevents.wrapper.configuration.server.WrapperConfigServerPluginMessage;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPluginMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPluginMessage;
import com.projectkorra.projectkorra.configuration.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ModListener extends PacketListenerAbstract {
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Configuration.Client.PLUGIN_MESSAGE) {
            WrapperConfigClientPluginMessage message = new WrapperConfigClientPluginMessage(event);
            handle(event.getUser(), message.getChannelName(), message.getData());
        }

        if (event.getPacketType() == PacketType.Play.Client.PLUGIN_MESSAGE) {
            WrapperPlayClientPluginMessage message = new WrapperPlayClientPluginMessage(event);
            handle(event.getUser(), message.getChannelName(), message.getData());
        }
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        ProjectKorra.clientBrandMap.remove(event.getUser().getUUID());
    }

    public void handle(User player, String channel, final byte[] msg) {
        if (channel.equalsIgnoreCase("minecraft:brand") || channel.equals("MC|Brand")) {
            if (ProjectKorra.clientBrandMap.containsKey(player.getUUID())) return;

            String brand = new String(msg, StandardCharsets.UTF_8).substring(1);

            ProjectKorra.clientBrandMap.put(player.getUUID(), brand);
        }
    }
}
