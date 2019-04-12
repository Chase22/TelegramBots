package org.telegram.telegrambots.test.api.mock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.stubbing.StubMapping

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import static org.telegram.telegrambots.test.api.mock.TelegramApiResponses.aDeleteWebhookResponse
import static org.telegram.telegrambots.test.api.mock.TelegramApiResponses.aGetMeResponse

class TelegramApiMock {
    private WireMockServer mockServer

    TelegramApiMock(int port = 0) {
        mockServer = new WireMockServer(
                options().port(port)
                        .notifier(new ConsoleNotifier(true))
        )
    }

    int getPort() {
        return mockServer.httpsPort()
    }

    def start() {
        mockServer.start()
        mockDefaults()
    }

    def stop() {
        mockServer.stop()
    }

    def mockDefaults() {
        mockDeleteWebhook()
        mockGetMe()
    }

    def mockDeleteWebhook(ResponseDefinitionBuilder response = aDeleteWebhookResponse()) {
        addStubMapping(
                post(matchBotMethod("deleteWebhook"))
                        .willReturn(response)
                        .build()
        )
    }

    def mockGetMe(ResponseDefinitionBuilder response = aGetMeResponse()) {
        addStubMapping(
                get(matchBotMethod("getMe"))
                .willReturn(response)
                .build()
        )
    }

    def addStubMapping(StubMapping customMapping) {
        mockServer.addStubMapping(customMapping)
    }

    private static matchBotMethod(String method) {
        return urlMatching("/bot.*/${method}")
    }
}
