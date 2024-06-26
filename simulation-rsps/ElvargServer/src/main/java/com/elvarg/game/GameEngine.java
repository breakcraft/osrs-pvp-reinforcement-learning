package com.elvarg.game;

import com.elvarg.game.content.clan.ClanChatManager;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The engine which processes the game.
 *
 * @author Professor Oak
 */
public final class GameEngine implements Runnable {

    /**
     * The {@link ScheduledExecutorService} which will be used for
     * this engine.
     */
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("GameThread").build());

    private static final int TICK_RATE = System.getenv().containsKey("TICK_RATE")
                                         ? Integer.parseInt(System.getenv("TICK_RATE"))
                                         : GameConstants.GAME_ENGINE_PROCESSING_CYCLE_RATE;

    /**
     * Initializes this {@link GameEngine}.
     */
    public void init() {
        executorService.scheduleAtFixedRate(this, 0, TICK_RATE, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        try {
            World.process();
        } catch (Throwable e) {
            e.printStackTrace();
            World.savePlayers();
            ClanChatManager.save();
        }
    }
}