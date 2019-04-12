package org.telegram.telegrambots.test.api.mock

import groovy.json.JsonOutput
import org.apache.commons.lang3.RandomUtils

import static java.lang.Integer.MAX_VALUE

class User {
    Integer id = RandomUtils.nextInt(1, MAX_VALUE)
    boolean isBot = false
    String firstName = "firstName"
    String lastName = "lastName"
    String username = "username"
    String languageCode = "en"

    static create() {
        return new User()
    }

    String build() {
       return JsonOutput.toJson(this)
    }
}
