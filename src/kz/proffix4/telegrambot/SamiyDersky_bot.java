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
        return "SamiyDersky_bot";
    }

    // Тут задается нужное значение токена
    @Override
    public String getBotToken() {
        return "1074719842:AAFYLt6gNYo0lHOYEmuSmuFcKo8tWtDJxo8";
    }

    // Метод обработки команд бота
    public String doCommand(long chatId, String command) {
        if (command.startsWith("/kz1")) {
            try {
                sendPhoto(new SendPhoto().setChatId(chatId).setNewPhoto(new File("btc.png")));
            } catch (TelegramApiException e) {
            }
            String[] param = command.split(" ");
            if (param.length > 1) { 
                return "Салам алейкум, че хотел? " + getResh(Integer.parseInt(param[1]),Integer.parseInt(param[2]),Integer.parseInt(param[3]));
            } else {
         return "Используйте команду /kz1 и введите значение a b x";
            }
        }
        return "Используйте команду /kz1 и введите значение a b x";
    }

    private String getResh( int a, int b, int x) {
        StringBuilder ans = new StringBuilder();
        double y=0;
        if (x < 5)
            y = (x*((a*a)+(b*b)))/ (6*a) ;
        else 
            y =  x*( 1 - (a*b));
        ans.append(y);
        return ans.toString();
    }
}
public class SamiyDersky_bot {

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
