import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.GetMe
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.test.api.mock.TelegramApiMock
import spock.lang.Specification
import spock.lang.Subject

class TelegramApiMockSpec extends Specification {

    @Subject
    TelegramApiMock apiMock = new TelegramApiMock(4040)

    def "a bot should be able to start against the mock"() {
        given:
        apiMock.start()
        apiMock.mockDeleteWebhook()

        DefaultBotOptions botOptions = new DefaultBotOptions()

        botOptions.setBaseUrl("http://localhost:4040/bot")

        TelegramLongPollingBot bot = new TelegramLongPollingBot(botOptions) {

            @Override
            void onUpdateReceived(final Update update) {

            }

            @Override
            String getBotUsername() {
                return "someUsername"
            }

            @Override
            String getBotToken() {
                return "someToken"
            }
        }

        ApiContextInitializer.init()

        TelegramBotsApi api = new TelegramBotsApi()

        when:
        api.registerBot(bot)

        User user = bot.execute(new GetMe())

        then:
        noExceptionThrown()

    }
}