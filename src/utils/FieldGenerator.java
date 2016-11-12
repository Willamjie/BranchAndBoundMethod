package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FieldGenerator {

    public static void generateField(String path) throws IOException {

        ////////////////////////////////// Connector 1

        Connector connector1 = new Connector();
        connector1.setX(10);
        connector1.setY(10);
        connector1.setHeight(200);
        connector1.setWidth(100);

        Channel channelTop1 = new Channel(2, true);
        Channel channelBottom1 = new Channel(2, false);

        connector1.setTopChannel(channelTop1);
        connector1.setBottomChannel(channelBottom1);

        channelTop1.setConnector(connector1);
        channelBottom1.setConnector(connector1);

        Pin pin1_1 = new Pin(50, 50);
        Pin pin1_2 = new Pin(50, 100);
        Pin pin1_3 = new Pin(50, 150);
        pin1_1.setContainer(connector1);
        pin1_2.setContainer(connector1);
        pin1_3.setContainer(connector1);

        connector1.setPins(Arrays.asList(pin1_1, pin1_2, pin1_3));

        ////////////////////////////////// Connector 2

        Connector connector2 = new Connector();
        connector2.setX(150);
        connector2.setY(10);
        connector2.setHeight(200);
        connector2.setWidth(100);

        Channel channelTop2 = new Channel(4, true);
        Channel channelBottom2 = new Channel(5, false);

        connector2.setTopChannel(channelTop2);
        connector2.setBottomChannel(channelBottom2);

        channelTop2.setConnector(connector2);
        channelBottom2.setConnector(connector2);

        Pin pin2_1 = new Pin(50, 50);
        pin2_1.setContainer(connector2);

        connector2.setPins(Arrays.asList(pin2_1));

        ////////////////////////////////// Connector 3

        Connector connector3 = new Connector();
        connector3.setX(290);
        connector3.setY(10);
        connector3.setHeight(200);
        connector3.setWidth(100);

        Channel channelTop3 = new Channel(3, true);
        Channel channelBottom3 = new Channel(2, false);

        connector3.setTopChannel(channelTop3);
        connector3.setBottomChannel(channelBottom3);

        channelTop3.setConnector(connector3);
        channelBottom3.setConnector(connector3);

        Pin pin3_1 = new Pin(50, 150);
        pin3_1.setContainer(connector3);

        connector3.setPins(Arrays.asList(pin3_1));

        ////////////////////////////////// Connector 4

        Connector connector4 = new Connector();
        connector4.setX(340);
        connector4.setY(10);
        connector4.setHeight(200);
        connector4.setWidth(100);

        Channel channelTop4 = new Channel(2, true);
        Channel channelBottom4 = new Channel(2, false);

        connector4.setTopChannel(channelTop4);
        connector4.setBottomChannel(channelBottom4);

        channelTop4.setConnector(connector4);
        channelBottom4.setConnector(connector4);

        Pin pin4_1 = new Pin(50, 50);
        Pin pin4_2 = new Pin(50, 100);
        Pin pin4_3 = new Pin(50, 150);

        pin4_1.setContainer(connector4);
        pin4_2.setContainer(connector4);
        pin4_3.setContainer(connector4);

        connector4.setPins(Arrays.asList(pin4_1, pin4_2, pin4_3));

        ////////////////////////////////// Links
        Link link1 = new Link(pin1_1, pin4_1, 3);
        Link link2 = new Link(pin1_2, pin3_1, 1);
        Link link3 = new Link(pin2_1, pin4_2, 1);
        Link link4 = new Link(pin1_3, pin4_3, 1);

        Field field = new Field(Arrays.asList(connector1, connector2, connector3, connector4),
                Arrays.asList(link1, link2, link3, link4));

        IOUtils.writeField(field, path);

    }

}
