package org.telegram.telegrambots.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.generics.LongPollingBot
import org.telegram.telegrambots.meta.generics.WebhookBot
import spock.lang.Specification

class BotRegististrySpec extends Specification {

    TelegramBotsApi mockBotsApi = Mock(TelegramBotsApi)

    def "Should register all webhook bots"() {

        when:
        ApplicationContext.build().packages("org.telegram.telegrambots.micronaut").run(EmbeddedServer)

        then:
        1*mockBotsApi.registerBot(_ as WebhookBot)
    }

    def "Should register all longpolling bots"() {

        when:
        println()

        then:
        1*mockBotsApi.registerBot(_ as LongPollingBot)
    }

    @MockBean(LongPollingBot)
    LongPollingBot mockLongPollingBot() {
        return Mock(LongPollingBot)
    }

    @MockBean(WebhookBot)
    WebhookBot mockWebHookBot() {
        return Mock(WebhookBot)
    }

    @MockBean(TelegramBotsApi)
    TelegramBotsApi telegramBotsApi() {
        return mockBotsApi
    }
}
