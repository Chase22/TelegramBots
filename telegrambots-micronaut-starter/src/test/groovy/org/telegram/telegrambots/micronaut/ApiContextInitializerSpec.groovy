package org.telegram.telegrambots.micronaut


import org.telegram.telegrambots.meta.TelegramBotsApi
import spock.lang.Specification
import io.micronaut.context.ApplicationContext

class ApiContextInitializerSpec extends Specification {


    def "The TelegramApi should be available as a bean"() {
        when:
        ApplicationContext context = ApplicationContext.run()

        then:
        context.findBean(TelegramBotsApi).isPresent()

        cleanup:
        context.close()
    }
}
