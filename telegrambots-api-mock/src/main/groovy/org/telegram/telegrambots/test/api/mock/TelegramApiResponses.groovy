package org.telegram.telegrambots.test.api.mock

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static groovy.json.JsonOutput.toJson

class TelegramApiResponses {

    static aDeleteWebhookResponse(boolean result = true, boolean ok = true, String description = "") {
        return aResponse()
                .withBody(toJson([
                ok: ok,
                result: result,
                description: description
        ]))
    }

    static aGetMeResponse() {
        return aResponse()
                .withBody(
                toJson([
                        ok: true,
                        result: new User(isBot: true)
                ])
        )
    }
}
