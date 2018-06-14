package de.mrcodngmen.privat.DiscordWerbung;


import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Main {
    public static void main(String[] args) {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "456851567090335747";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Ready!");
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        presence.details = "discord.whatiscoding.eu - Dein Discord für Entwickler";
        presence.largeImageKey = "whatiscoding_big";
        presence.largeImageText = "discord.whatiscoding.eu - Dein Discord für Entwickler";
        presence.smallImageKey ="whatiscoding_small";
        presence.smallImageText = "Made by @MrCodingMenYT";
        lib.Discord_UpdatePresence(presence);
        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }
}
