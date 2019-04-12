package org.telegram.telegrambots.micronaut;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.WebhookBot;

import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Singleton
public class BotRegisterService implements ApplicationEventListener<StartupEvent> {
    private final TelegramBotsApi telegramBotsApi;
    private final List<WebhookBot> webHookBots;
    private final List<LongPollingBot> longPollingBots;

    public BotRegisterService(final TelegramBotsApi telegramBotsApi,
            final List<WebhookBot> webHookBots,
            final List<LongPollingBot> longPollingBots) {
        Objects.requireNonNull(telegramBotsApi, "telegramBotsApi");
        Objects.requireNonNull(webHookBots, "webHookBots");
        Objects.requireNonNull(longPollingBots, "longPollingBots");

        this.telegramBotsApi = telegramBotsApi;
        this.webHookBots = webHookBots;
        this.longPollingBots = longPollingBots;
    }

    @Override
    public void onApplicationEvent(final StartupEvent event) {
        try {
            for (LongPollingBot bot : longPollingBots) {
                BotSession session = telegramBotsApi.registerBot(bot);
//                handleAfterRegistrationHook(bot, session);
            }
            for (WebhookBot bot : webHookBots) {
                telegramBotsApi.registerBot(bot);
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
