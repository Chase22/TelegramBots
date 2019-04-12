package org.telegram.telegrambots.micronaut;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceStartedEvent;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import javax.inject.Singleton;

@Factory
public class TelegramBotApiFactory implements ApplicationEventListener<ServiceStartedEvent> {

    @Singleton
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }

    public void onApplicationEvent(final ServiceStartedEvent event) {
        ApiContextInitializer.init();
    }
}
