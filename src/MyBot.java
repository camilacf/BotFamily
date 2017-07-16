import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot{
	public static String info= "/lista - imprime a lista de compras \n /add [item] - adiciona o item à lista de compras \n /clean - limpa a lista de compras";
	public static Map<String, ArrayList<String>> Listas = new HashMap<String, ArrayList<String>>();
	
	@Override
	public void onUpdateReceived(Update update) {
		// We check if the update has a message and the message has text
		if (update.hasMessage()) {

			String msg = update.getMessage().getText();
			String texto = new String();
			SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
			.setChatId(update.getMessage().getChatId());
			System.out.println(msg);
			
			if(update.getMessage().isUserMessage() && !msg.startsWith("/")){
				message.setText("Esse comando nao é valido. Tente um desses:\n"+info);
				try {
					sendMessage(message); // Call method to send the message
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
				System.out.println(update.getMessage().isUserMessage());
			}
			
			else {
				if(msg.equalsIgnoreCase("/novalista")){
					if(Listas.containsKey(message.getChatId())){
						for(int i=0; i<Listas.get(message.getChatId()).size(); i++){
							texto = texto + Listas.get(message.getChatId()).get(i) + "\n";
						}
						message.setText("sua lista anterior era:"+texto+"\n"+ "Para fazer uma lista nova use /clean");
					}
					ArrayList<String> Lista = new ArrayList<String>();
					Listas.put(message.getChatId(), Lista);
					message.setText("Lista criada. Para adicionar itens use '/add'; para visualizá-la use '/lista'");
				}
				if(msg.equalsIgnoreCase("/lista")){
					
				for(int i=0; i<Listas.get(message.getChatId()).size(); i++){
					texto = texto + i+1 +". "+Listas.get(message.getChatId()).get(i) + "\n";
				}
				if(texto.isEmpty()) texto = "NADA";
				message.setText(texto);
				System.out.print(texto);
				try {
					sendMessage(message); // Call method to send the message
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}

			if (msg.startsWith("/add")) {
				String item = new String();
				item = msg.substring(4);
				Listas.get(message.getChatId()).add(item);
			}
			if (msg.equalsIgnoreCase("/clean")) {	
				Listas.get(message.getChatId()).clear();
			}
			if (msg.equalsIgnoreCase("/help")) {
				message.setText(info);
				try {
					sendMessage(message); // Call method to send the message
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
			}
			
			}
	}

	@Override
	public String getBotUsername() {
		return "FamiliaCF_bot";
	}

	@Override
	public String getBotToken() {
		return "367430997:AAGn1TLQaVPD0KpLDORw7LHJ1mM6ugb1eGo";
	}
}
