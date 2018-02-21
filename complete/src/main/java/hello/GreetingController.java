package hello;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

	private Collection<Greeting> messages = new ConcurrentLinkedQueue<>();
	
	@SubscribeMapping("/existing.messages")
    public Collection<Greeting> existingMessages() throws Exception {
        return messages;
    }
	
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        Greeting g = new Greeting("Hello, " + message.getName() + "!");
        messages.add(g);
        return g;
    }

}
