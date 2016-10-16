package helpers;

import pojo.Channel;

public class ChannelHelper {

    public static boolean isChannelFull(Channel channel) {
        return channel.getOccupancy() == channel.getMaxCapacity();
    }
}
