package kz.proffix4.telegrambot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

class MyTelegramBot extends TelegramLongPollingBot {

    // Метод получения команд бота, тут ничего не трогаем
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText(doCommand(update.getMessage().getChatId(),
                    update.getMessage().getText()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
            }
        }
    }

    // Тут задается нужное значение имени бота
    @Override
    public String getBotUsername() {
        return "KKI_BOT";
    }

    // Тут задается нужное значение токена
    @Override
    public String getBotToken() {
        return "995835586:AAH2tYNH6kNeR5Zh8ior7zm_Ex59wrrrqyk";
    }

    // Метод обработки команд бота
    public String doCommand(long chatId, String command) {
        if (command.startsWith("/ur")) {
            try {
                sendPhoto(new SendPhoto().setChatId(chatId).setNewPhoto(new File("btc.png")));
            } catch (TelegramApiException e) {
            }
            String[] param = command.split(" ");
            if (param.length > 1) {
                return "Ответ= " + getBTC(Integer.parseInt(param[1]), Integer.parseInt(param[2]), Integer.parseInt(param[3]), Integer.parseInt(param[4]));
            } else {
                return "Используйте команду /ur и введите переменные x a c b!";
            }
        }
        return "Используйте команду /ur и введите переменные x a c b!";
    }

    private String getBTC(int a, int b, int x, int c) {
        StringBuilder otv = new StringBuilder();
        double y = 0;
        if (x > 4) {
            y = (x*x+a*a)*c/2*b;
        } else {
            y = (x*x*x)*(a-b);
            
        }otv.append(y);
        return otv.toString();
    }
}

public class TSN_TELEGRAM_BOT {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            // ЗАПУСКАЕМ КЛАСС НАШЕГО БОТА
            botsApi.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
        }
    }

}
