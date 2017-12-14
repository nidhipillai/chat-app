/* Name: Nidhi Pillai
  Chat Application (Subscriber)
*/


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Scanner;

public class rsub {

    public static void main(String args[]) {
        Jedis jedis = new Jedis("localhost");
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the channel name:");
        String channel = scanner.nextLine();
        System.out.println("waiting for reply " + channel);

        while (true){
            System.out.println("Enter the string to Publish:");
            String msg = scanner.nextLine();
            jedis.publish(channel,msg);
		
            
                jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    //super.onMessage(channel, message);
                    System.out.println("Received message:" + message);
                }

                @Override
                public void onSubscribe(String channel, int subscribedChannels) {
                }

                @Override
                public void onUnsubscribe(String channel, int subscribedChannels) {
                }

                @Override
                public void onPMessage(String pattern, String channel, String message) {
                }

                @Override
                public void onPUnsubscribe(String pattern, int subscribedChannels) {
                }
                
                @Override
                public void onPSubscribe(String pattern, int subscribedChannels) {
                }

            }, channel);

        }
    }
}
